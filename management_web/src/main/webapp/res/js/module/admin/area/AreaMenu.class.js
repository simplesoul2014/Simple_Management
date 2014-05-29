Ext.AreaMenu = function()
{
	var me = this;
	Ext.AreaMenu.superclass.constructor.call(this, {
		items: 
		[
			{
		 		text: '查看',
		 		iconCls: 'look',
		 		handler: function(oItem, oEvent)
		 		{
					var oNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
		 			var nAreaId = oNode.id.replace(/[^0-9]*/,'');
		 			if(!me.ViewAreaWindow)
		 			{
		 				me.ViewAreaWindow = new Ext.ViewAreaWindow();
		 			}
		 			me.ViewAreaWindow.nAreaId=nAreaId;
		 			if(!me.ViewAreaWindow.isVisible()) me.ViewAreaWindow.show(oNode.getUI().getEl());
	 			}
		 	},
		 	{
		 		text: '增加',
		 		iconCls: 'add',
		 		handler: function(oItem, oEvent)
		 		{
		 			if(!me.AddAreaWindow)
		 			{
		 				me.AddAreaWindow = new Ext.AddAreaWindow();
		 			}
		 			var oNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
		 			if(!me.AddAreaWindow.isVisible()) me.AddAreaWindow.show(oNode.getUI().getEl());
		 		}
		 	},
		 	{
		 		text: '修改',
		 		iconCls: 'edit_icon',
		 		handler: function(oItem, oEvent)
		 		{
		 			var oNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
		 			var nAreaId = oNode.id.replace(/[^0-9]*/,'');
		 			if(!me.EditAreaWindow)
		 			{
		 				me.EditAreaWindow = new Ext.EditAreaWindow();
		 			}
		 			me.EditAreaWindow.nAreaId=nAreaId;
		 			if(!me.EditAreaWindow.isVisible()) me.EditAreaWindow.show(oNode.getUI().getEl());
	 			}
		 	},
		 	{
		 		text: '删除',
		 		iconCls: 'del',
		 		handler: function(oItem, oEvent)
		 		{
			 		Ext.MessageBox.confirm('出错提示', '真的要删除此地区吗？', function(buttonId, text, opt)
			 		{
    					if(buttonId == 'yes')
    					{
    						var oNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
    						var nAreaId = oNode.id.replace(/[^0-9]*/,'');
    						Ext.Ajax.request({   
    							   url: 'delArea.do',   
    							   method: 'post',   
    							   params: {nAreaId: nAreaId},
    							   success: function(resp, action)
    							   {   
    							        var obj = Ext.util.JSON.decode(resp.responseText);   
    							        if(obj.success)   
    							        {   
    							        	oNode.removeAll(true);
    							        	oNode.remove(true);
    							        }   
    							        else  
    							        {   
    							            Ext.Msg.alert('出错提示', obj.msg);   
    							        }     
    							    },   
    							    failure:function(resp, action)
    							    {   
    							        Ext.Msg.alert('出错提示','系统错误');   
    							    }   
    						}); 
    					}
    				});		
	 			}
		 	}
		]
	});
};
Ext.extend(Ext.AreaMenu, Ext.menu.Menu);