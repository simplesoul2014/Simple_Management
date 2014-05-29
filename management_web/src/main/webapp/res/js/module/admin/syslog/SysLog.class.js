Ext.SysLog = function()
{
	var me = this;
	var oStore = new Ext.data.JsonStore({
	    autoDestroy: true,
	    proxy : new Ext.data.HttpProxy({
	        method: 'post',
	        url: 'getSysLogList.do'
	    }),
	    root: 'data',
	    idProperty: 'cmSysLogId',
	    totalProperty: 'total',
	    fields: [ 
	          {name :'cmSysLogId', type:'int'}
	    	, 'name'
	    	, 'moduleName'
	    	, 'content'
	    	, {name :'createDate', type:'date', dateFormat: 'time'}
	    ]
	});
	oStore.load({params:{sName:'', sContent:''}});
	
	Ext.SysLog.superclass.constructor.call(this, {
        loadMask : true,
        ddGroup: 'gridToTreeDDGroup',
        enableDragDrop: true,
        stripeRows: true,
        sm: new Ext.grid.RowSelectionModel({singleSelect:true}),
        border:false,
        region:'center',
        store: oStore,		
        columns: [
        	{header:'序号', width: 60, fixed: true, menuDisabled: true
        		, renderer: function(value, metaData, record, rowIndex, colIndex, store){
        			return store.reader.jsonData.start + rowIndex + 1;
        		}
        	 }
            ,{header: '用户姓名', dataIndex: 'name', width: 140}
            ,{header: '操作模块', dataIndex: 'moduleName', width: 140}
            ,{header: '操作内容', dataIndex: 'content', id:'content', renderer : function(value){return Ext.util.Format.ellipsis(value, 100);}}
            ,{header: '录入时间', dataIndex: 'createDate', width: 140, sortable: true, renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
        ],
        autoExpandColumn: 'content',
        tbar: new Ext.SysLogTopToolbar(this),
        bbar: new Ext.PagingToolbar({
            pageSize: 100,
            store: oStore,
            displayInfo: true,
            listeners:{
        		beforechange : function()
        		{
        			oStore.setBaseParam('sName', me.sName || '');
        			oStore.setBaseParam('sContent', me.sContent || '');
        		}
            }
        })
	});
};

Ext.extend(Ext.SysLog, Ext.grid.GridPanel);
