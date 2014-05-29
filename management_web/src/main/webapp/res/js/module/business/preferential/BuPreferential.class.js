Ext.BuPreferential = function()
{
	var me = this;
	var oStore = new Ext.data.JsonStore({
	    autoDestroy: true,
	    proxy : new Ext.data.HttpProxy({
	        method: 'post',
	        url: 'getBuPreferentiallist.do'
	    }),
	    root: 'data',
	    idProperty: 'NPreferenid',
	    totalProperty: 'total',
	    
	    fields: [ 
	        'npreferenid'
	    	, 'npreferenid'
	    	, 'sinfo'
	    	, 'stel'
	    	, 'scontactpeople'
	    	, 'sinfocode'
	    	, 'nstatus'
			, 'sdescription'
	    ]
	});
	oStore.load({params:{sInfo:'', sInfoCode:''}});
	var oSM = new Ext.grid.CheckboxSelectionModel();
	
	Ext.BuPreferential.superclass.constructor.call(this, {
		region:'center',
        store: oStore,
        loadMask : true,
        ddGroup: 'gridBuPreferentialGroup',
        enableDragDrop: true,
        sm:oSM,
        
        columns: [
        	oSM
        	,{header:'序号', width: 60, fixed: true, menuDisabled: true
        		, renderer: function(value, metaData, record, rowIndex, colIndex, store){
        			return store.reader.jsonData.start + rowIndex + 1;
        		}
        	 }
            ,{header: '优惠信息ID', dataIndex: 'npreferenid', width: 140}
         	,{header: '优惠信息内容', dataIndex: 'sinfo',id:"info"}
         	,{header: '优惠联系电话', dataIndex: 'stel'}
         	,{header: '优惠联系人', dataIndex: 'scontactpeople'}
         	,{header: '优惠信息编码', dataIndex: 'sinfocode'}
            ,{header: '状态', dataIndex: 'nstatus', width: 80
            	, renderer : function(value){
            		switch(value)
            		{
            			case 0 :
            				return '已删除';
            				break;
            			case 1 :
            				return '上架';
            				break;
            			case 2 :
            				return '下架';
            				break;
            		}
            	}
             }
            ,{header: '景点信息描述', dataIndex: 'sdescription',width: 280}    
        ],
        stripeRows: true,
        autoExpandColumn: 'info',
        border:false,
        tbar: new Ext.BuPreferentialTopToolbar(this),
        bbar: new Ext.PagingToolbar({
            pageSize: 100,
            store: oStore,
            displayInfo: true,
            listeners:{
        		beforechange : function()
        		{
        			oStore.setBaseParam('sInfo', me.sInfo || '');
        			oStore.setBaseParam('sInfoCode', me.sInfoCode || '');
        		}
            }
        })
	});
};

Ext.extend(Ext.BuPreferential, Ext.grid.GridPanel);