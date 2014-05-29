Ext.Member = function()
{
    var me = this;
    var oStore = new Ext.data.JsonStore({
    	autoDestroy:true,
    	proxy : new Ext.data.HttpProxy({
    		method : 'post',
    		url: 'getMemberList.do'
    	}),
    });
}