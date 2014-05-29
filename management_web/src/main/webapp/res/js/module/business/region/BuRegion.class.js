Ext.BuRegion = function()
{
	var me = this;
	var oStore = new Ext.data.JsonStore({
	    autoDestroy: true,
	    proxy : new Ext.data.HttpProxy({
	        method: 'post',
	        url: 'getBuRegionlist.do'
	    }),
	    root: 'data',
	    idProperty: 'NRegionid',
	    totalProperty: 'total',
	    
	    fields: [ 
	        'nregionid'
	    	, 'nregionid'
	    	, 'szonenumber'
	    	, 'sprovincename'
	    	, 'scityname'
			, 'sdescription'
	    ]
	});
	oStore.load({params:{SCityname:'', SZonenumber:''}});
	var oSM = new Ext.grid.CheckboxSelectionModel();
	
	Ext.BuRegion.superclass.constructor.call(this, {
		region:'center',
        store: oStore,
        loadMask : true,
        ddGroup: 'gridBuRegionGroup',
        enableDragDrop: true,
        sm:oSM,
        
        columns: [
        	oSM
        	,{header:'序号', width: 60, fixed: true, menuDisabled: true
        		, renderer: function(value, metaData, record, rowIndex, colIndex, store){
        			return store.reader.jsonData.start + rowIndex + 1;
        		}
        	 }
            ,{header: '地市ID', dataIndex: 'nregionid', width: 140}
            ,{header: '省份名称', dataIndex: 'sprovincename',id:'cityname',width: 140}
         	,{header: '地市名称', dataIndex: 'scityname'}   
         	,{header: '地市缩写', dataIndex: 'szonenumber'}
            ,{header: '地市描述', dataIndex: 'sdescription',id:'description'}    
        ],
        stripeRows: true,
        autoExpandColumn: 'description',
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
        			oStore.setBaseParam('SZonenumber', me.SZonenumber || '');
        		}
            }
        })
	});
};

Ext.extend(Ext.BuRegion, Ext.grid.GridPanel);