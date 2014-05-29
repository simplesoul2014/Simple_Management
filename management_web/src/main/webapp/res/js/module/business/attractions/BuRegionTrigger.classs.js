Ext.BuRegionTopToolbar = function(oGrid)
{
	var me = this;
	Ext.BuRegionTopToolbar.superclass.constructor.call(this, 
	{
        enableOverflow: true,
        items: [
        	{
        		xtype: 'tbtext', 
        		text: '地市名称：'
        	},
        	{
        		xtype: 'textfield'
        	},{xtype: 'tbspacer', width: 10},
        	{
	            text: '搜索',
	            iconCls: 'search',
	            handler: function(oButton, oEvent)
	            {
        			var aComponents = me.findByType('textfield');
        			var SCityName = aComponents[0].getValue();
        			oGrid.getStore().load({params:{SCityname:SCityName, SZonenumber:''}});
        		}
        	},
        		{xtype: 'tbspacer', width: 10},
        	{
	            text: '选择返回',
	            iconCls: 'look',
	            handler: function(oButton, oEvent)
	            {
	            	var aRecord = oGrid.getSelectionModel().getSelections();
	            	var NRegionid = [];
	            	var SCityname = [];
					for(var nKey in aRecord)
					{
						if(aRecord[nKey].data != undefined)
						{
							NRegionid.push(aRecord[nKey].data.nregionid);
							SCityname.push(aRecord[nKey].data.scityname);
						}
					}
	    			if(NRegionid.length > 0)
	    			{
	    				oGrid.trigger.setRealValue(NRegionid.join(','));
	    				oGrid.trigger.setValue(SCityname.join(','));
	    				oGrid.win.hide();
	    			}else
	    			{
	    				Ext.MessageBox.alert('出错提示', '请选择景点所属地市！');
	    			}
    			}
        	}
        ]
	});
};
Ext.extend(Ext.BuRegionTopToolbar, Ext.Toolbar);

Ext.BuRegionGrid = function(config)
{
	Ext.apply(this, config);
	var me = this;
	var oStore = new Ext.data.JsonStore({
	    autoDestroy: true,
	    proxy : new Ext.data.HttpProxy({
	        method: 'post',
	         url: '../buregion/getBuRegionlist.do'
	    }),
	    root: 'data',
	    idProperty: 'NRegionid',
	    totalProperty: 'total',
	    fields: [ 
	          {name :'nregionid', type:'int'}
	    	, 'scityname'
	    	, 'szonenumber'
	    ]
	});
	oStore.load({params:{SCityname:'', SZonenumber:''}});
    var oSM = new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	Ext.BuRegionGrid.superclass.constructor.call(this, {
		region:'center',
        store: oStore,
        loadMask : true,
        ddGroup: 'gridToTreeDDGroup',
        enableDragDrop: true,
        sm:oSM,
        listeners:{
         render:function(){
         var hd_checker = this.getEl().select('div.x-grid3-hd-checker');
	         if (hd_checker.hasClass('x-grid3-hd-checker')) {   
	                hd_checker.removeClass('x-grid3-hd-checker'); // 去掉全选框 
	            } 
	         }
         },
        columns: [
        	oSM
        	,{header:'序号', width: 60, fixed: true, menuDisabled: true
        		, renderer: function(value, metaData, record, rowIndex, colIndex, store){
        			return store.reader.jsonData.start + rowIndex + 1;
        		}
        	 }
            ,{header: '地市名称', dataIndex: 'scityname', id:'scityname'}
            ,{header: '地市编码', dataIndex: 'szonenumber', id:'szonenumber'}
        ],
        stripeRows: true,
        autoExpandColumn: 'scityname',
        border:false,
        tbar: new Ext.BuRegionTopToolbar(this),
        bbar: new Ext.PagingToolbar({
            pageSize: 100,
            store: oStore,
            displayInfo: true,
            listeners:{
        		beforechange : function()
        		{
        			oStore.setBaseParam('SCityname', me.SCityname || '');
        		}
            }
        })
	});
};
Ext.extend(Ext.BuRegionGrid, Ext.grid.GridPanel);

Ext.BuRegionGridWindow = function(trigger) 
{
	var me = this;
	var oGrid=new Ext.BuRegionGrid({trigger: trigger, win: me});

	Ext.BuRegionGridWindow.superclass.constructor.call(this, 
	{
		layout : 'border',
		width : m_nWidth,
		height : m_nHeight,
		plain : true,
		title : '查找所属地市',
		closeAction : 'hide',
		modal : true,
		iconCls : 'form',
		items : oGrid,
		buttons : [
			{
				text : '确定',
				iconCls : 'save',
				handler: function(oButton, oEvent)
	            {
	            	var aRecord = oGrid.getSelectionModel().getSelections();
	            	var NRegionid = [];
	            	var SCityname = [];
					for(var nKey in aRecord)
					{
						if(aRecord[nKey].data != undefined)
						{
							NRegionid.push(aRecord[nKey].data.nregionid);
							SCityname.push(aRecord[nKey].data.scityname);
						}
					}
	    			if(NRegionid.length > 0)
	    			{
	    				oGrid.trigger.setRealValue(NRegionid.join(','));
	    				oGrid.trigger.setValue(SCityname.join(','));
	    				oGrid.win.hide();
	    			}else
	    			{
	    				Ext.MessageBox.alert('出错提示', '请选择景点所属地市！');
	    			}
    			}			
			},
			{
			text : '关闭',
			iconCls : 'exit',
			handler : function() {
				me.hide();
			}
		} ]
	});
};
Ext.extend(Ext.BuRegionGridWindow, Ext.Window);		

//触发控件
Ext.form.BuRegionTrigger = Ext.extend(Ext.form.TriggerField, 
{
	triggerClass: 'x-form-search-trigger',
	editable: false,
	realValue : '',
	initComponent : function()
	{
		this.win = new Ext.BuRegionGridWindow(this),
        Ext.form.BuRegionTrigger.superclass.initComponent.call(this);
	},
	onTriggerClick : function()
	{
		this.win.show(this.trigger);
	},
	setRealValue : function(v)
	{
		this.realValue = v;
	},
	getRealValue : function()
	{
		return this.realValue;
	}
});
Ext.reg('buregiontrigger', Ext.form.BuRegionTrigger);