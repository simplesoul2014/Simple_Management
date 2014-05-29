Ext.BuAttractions = function()
{
	var me = this;
	var oStore = new Ext.data.JsonStore({
	    autoDestroy: true,
	    proxy : new Ext.data.HttpProxy({
	        method: 'post',
	        url: 'getBuAttractionslist.do'
	    }),
	    root: 'data',
	    idProperty: 'NAttractionid',
	    totalProperty: 'total',
	    
	    fields: [ 
	        'nattractionid'
	    	, 'nattractionid'
	    	, 'sattracionsname'
	    	, 'nregionid'
	    	, 'sreview'
	    	, 'simagepath'
	    	, 'sdetail'
			, 'nispreferential'
			, 'sregionname'
			, 'nstatus'
			, 'spreferentialCode'
			, 'sdescription'
			, 'norder'
	    ]
	});
	oStore.load({params:{SAttractionsName:'', Cityname:''}});
	var oSM = new Ext.grid.CheckboxSelectionModel();
	
	Ext.BuAttractions.superclass.constructor.call(this, {
		region:'center',
        store: oStore,
        loadMask : true,
        ddGroup: 'gridBuAttractionsGroup',
        enableDragDrop: true,
        sm:oSM,
        
        columns: [
        	oSM
        	,{header:'序号', width: 60, fixed: true, menuDisabled: true
        		, renderer: function(value, metaData, record, rowIndex, colIndex, store){
        			return store.reader.jsonData.start + rowIndex + 1;
        		}
        	 }
            ,{header: '景点信息ID', dataIndex: 'nattractionid', width: 140}
         	,{header: '景点名称', dataIndex: 'sattracionsname',id:"attracionsname"}
         	 ,{header: '景点排序', dataIndex: 'norder'}	  
         	,{header: '景点信息导读', dataIndex: 'sreview',width: 280}
            ,{header: '状态', dataIndex: 'nstatus', width: 140
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
            ,{header: '景点地址', dataIndex: 'sdescription',width: 280}    
        ],
        stripeRows: true,
        autoExpandColumn: 'attracionsname',
        border:false,
        tbar: new Ext.BuAttractionsTopToolbar(this),
        bbar: new Ext.PagingToolbar({
            pageSize: 100,
            store: oStore,
            displayInfo: true,
            listeners:{
        		beforechange : function()
        		{
        			oStore.setBaseParam('SAttractionsName', me.SAttractionsName || '');
        			oStore.setBaseParam('Cityname', me.Cityname || '');
        		}
            }
        })
	});
};

Ext.extend(Ext.BuAttractions, Ext.grid.GridPanel);