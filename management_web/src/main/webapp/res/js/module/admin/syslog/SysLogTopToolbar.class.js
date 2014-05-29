Ext.SysLogTopToolbar = function(oGrid)
{
	var me = this;
	Ext.SysLogTopToolbar.superclass.constructor.call(this, {
        enableOverflow: true,
        items: [
        	{
        		xtype: 'tbtext', 
        		text: '用户姓名：'
        	},
        	{
        		xtype: 'textfield'
        	},{xtype: 'tbspacer', width: 10},
        	{
        		xtype: 'tbtext', 
        		text: '操作内容：'
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
        			var sName = aComponents[0].getValue();
        			var sContent = aComponents[1].getValue();
        			oGrid.sName = sName;
        			oGrid.sContent = sContent;
        			oGrid.getStore().load({params:{sName:sName, sContent:sContent}});
        		}
        	},{xtype: 'tbspacer', width: 10},
        	{
	            text: '查看',
	            iconCls: 'look',
	            handler: function(oButton, oEvent)
	            {
        			var oRecord = oGrid.getSelectionModel().getSelected();
	    			if(oRecord != undefined)
	    			{
	        			if(!oButton.Window)
	        			{
	        				oButton.Window = new Ext.ViewSysLogWindow();
	        			}
	        			oButton.Window.nSysLogId=oRecord.data.cmSysLogId;
	        			if(!oButton.Window.isVisible()) oButton.Window.show(oButton.getEl());
	    			}else
	    			{
	    				Ext.MessageBox.alert('出错提示', '请选择要查看的系统日志资料！');
	    			}
    			}
        	},
        	'->',
        	{
        		text: '帮助',
        		iconCls: 'help'
        	}
        ]
	});
};

Ext.extend(Ext.SysLogTopToolbar, Ext.Toolbar);