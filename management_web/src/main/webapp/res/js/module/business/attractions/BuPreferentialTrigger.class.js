Ext.BuPreferentialTopToolbar = function(oGrid)
{
	var me = this;
	Ext.BuPreferentialTopToolbar.superclass.constructor.call(this, 
	{
        enableOverflow: true,
        items: [
        	{
        		xtype: 'tbtext', 
        		text: '优惠内容：'
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
        			var SInfo = aComponents[0].getValue();
        			oGrid.getStore().load({params:{SInfo:SInfo}});
        		}
        	},{xtype: 'tbspacer', width: 10},
        	{
	            text: '选择返回',
	            iconCls: 'look',
	            handler: function(oButton, oEvent)
	            {
	            	var aRecord = oGrid.getSelectionModel().getSelections();
	            	var NPreferenid = [];
	            	var SInfocode = [];
					for(var nKey in aRecord)
					{
						if(aRecord[nKey].data != undefined)
						{
							NPreferenid.push(aRecord[nKey].data.npreferenid);
							SInfocode.push(aRecord[nKey].data.sinfocode);
						}
					}
	    			if(NPreferenid.length > 0)
	    			{
	    				oGrid.trigger.setRealValue(NPreferenid.join(','));
	    				oGrid.trigger.setValue(SInfocode.join(','));
	    				oGrid.win.hide();
	    			}else
	    			{
	    				Ext.MessageBox.alert('出错提示', '请选择景点拥有优惠！');
	    			}
    			}
        	}
        ]
	});
};
Ext.extend(Ext.BuPreferentialTopToolbar, Ext.Toolbar);

Ext.BuPreferentialGrid = function(config)
{
	Ext.apply(this, config);
	var me = this;
	var oStore = new Ext.data.JsonStore({
	    autoDestroy: true,
	    proxy : new Ext.data.HttpProxy({
	        method: 'post',
	        url: '../bupreferential/getBuPreferentiallist.do'
	    }),
	    root: 'data',
	    idProperty: 'NPreferenid',
	    totalProperty: 'total',
	    fields: [ 
	          {name :'npreferenid', type:'int'}
	    	, 'sinfocode'
	    	, 'sinfo'
	    ]
	});
	oStore.load({params:{sName:'', sDeptName:''}});
	var oSM = new Ext.grid.CheckboxSelectionModel();
	
	Ext.BuPreferentialGrid.superclass.constructor.call(this, {
		region:'center',
        store: oStore,
        loadMask : true,
        ddGroup: 'gridToTreeDDGroup',
        enableDragDrop: true,
        sm:oSM,
        columns: [
        	oSM
        	,{header:'序号', width: 60, fixed: true, menuDisabled: true
        		, renderer: function(value, metaData, record, rowIndex, colIndex, store){
        			return store.reader.jsonData.start + rowIndex + 1;
        		}
        	 }
        	 ,{header: '优惠编码', dataIndex: 'sinfocode', id:'sinfocode'}
            ,{header: '优惠内容', dataIndex: 'sinfo', id:'sinfo'}
            
        ],
        stripeRows: true,
        autoExpandColumn: 'sinfocode',
        border:false,
        tbar: new Ext.BuPreferentialTopToolbar(this),
        bbar: new Ext.PagingToolbar({
            pageSize: 100,
            store: oStore,
            displayInfo: true,
            listeners:{
        		beforechange : function()
        		{
        			oStore.setBaseParam('SInfo', me.SInfo || '');
        		}
            }
        })
	});
};
Ext.extend(Ext.BuPreferentialGrid, Ext.grid.GridPanel);

Ext.BuPreferentialGridWindow = function(trigger) 
{
	var me = this;
	Ext.BuPreferentialGridWindow.superclass.constructor.call(this, 
	{
		layout : 'border',
		width : m_nWidth,
		height : m_nHeight,
		plain : true,
		title : '查找景点优惠',
		closeAction : 'hide',
		modal : true,
		iconCls : 'form',
		items : new Ext.BuPreferentialGrid({trigger: trigger, win: me}),
		buttons : [ {
			text : '关闭',
			iconCls : 'exit',
			handler : function() {
				me.hide();
			}
		} ]
	});
};
Ext.extend(Ext.BuPreferentialGridWindow, Ext.Window);		

//触发控件
Ext.form.BuPreferentialTrigger = Ext.extend(Ext.form.TriggerField, 
{
	triggerClass: 'x-form-search-trigger',
	editable: false,
	realValue : '',
	initComponent : function()
	{
		this.win = new Ext.BuPreferentialGridWindow(this),
        Ext.form.BuPreferentialTrigger.superclass.initComponent.call(this);
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
Ext.reg('bupreferentialtrigger', Ext.form.BuPreferentialTrigger);