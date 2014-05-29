Ext.EnumMenu = function()
{
	var me = this;
	Ext.EnumMenu.superclass.constructor.call(this, {
		items: 
		[
			{
		 		text: '查看',
		 		iconCls: 'look',
		 		handler: function(oItem, oEvent)
		 		{
					var oNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
		 			var nEnumId = oNode.id.replace(/[^0-9]*/,'');
		 			if(!me.ViewEnumWindow)
		 			{
		 				me.ViewEnumWindow = new Ext.ViewEnumWindow();
		 			}
		 			me.ViewEnumWindow.nEnumId=nEnumId;
		 			if(!me.ViewEnumWindow.isVisible()) me.ViewEnumWindow.show(oNode.getUI().getEl());
	 			}
		 	},
		 	{
		 		text: '增加',
		 		iconCls: 'add',
		 		handler: function(oItem, oEvent)
		 		{
		 			if(!me.AddEnumWindow)
		 			{
		 				me.AddEnumWindow = new Ext.AddEnumWindow();
		 			}
		 			var oNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
		 			me.AddEnumWindow.sTable = '';
		 			me.AddEnumWindow.sField = '';
		 			var cFlag = oNode.id.substring(0, 1);
		 			switch(cFlag)
					{
						case 'A' :
						break;
						case 'B' :
							var aParam = oNode.id.split('-');
							me.AddEnumWindow.sTable = aParam[1];
						break;
						case 'C' :
							var aParam = oNode.id.split('-');
							me.AddEnumWindow.sTable = aParam[1];
							me.AddEnumWindow.sField = aParam[2];
						break;
					}
		 			if(!me.AddEnumWindow.isVisible()) me.AddEnumWindow.show(oNode.getUI().getEl());
		 		}
		 	},
		 	{
		 		text: '修改',
		 		iconCls: 'edit_icon',
		 		handler: function(oItem, oEvent){
		 			var oNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
		 			var aParam = oNode.id.split('-');
		 			var nEnumId = aParam[1];
		 			if(!me.EditEnumWindow)
		 			{
		 				me.EditEnumWindow = new Ext.EditEnumWindow();
		 			}
		 			me.EditEnumWindow.nEnumId=nEnumId;
		 			if(!me.EditEnumWindow.isVisible()) me.EditEnumWindow.show(oNode.getUI().getEl());
	 			}
		 	},
		 	{
		 		text: '删除',
		 		iconCls: 'del',
		 		handler: function(oItem, oEvent){
			 		Ext.MessageBox.confirm('出错提示', '真的要删除此枚举吗？', function(buttonId, text, opt)
			 		{
    					if(buttonId == 'yes')
    					{
    						var oNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
    						var oParentNode = oNode.parentNode;
    			 			var aParam = oNode.id.split('-');
    			 			var sUrl = 'delTable.do';
    			 			var oParams = {};
    			 			switch(aParam[0])
    			 			{
	    			 			case 'B' :
				 					sUrl = 'delTable.do';
				 					oParams = {sTable: aParam[1]};
	    			 			break;
    			 				case 'C' :
    			 					sUrl = 'delField.do';
    			 					oParams = {sTable: aParam[1], sField: aParam[2]};
        			 			break;
    			 				case 'D' :
    			 					sUrl = 'delEnum.do';
    			 					oParams = {nEnumId: aParam[1]};
        			 			break;
    			 			}
    						Ext.Ajax.request({   
    							   url: sUrl,   
    							   method: 'post',   
    							   params: oParams,
    							   success: function(resp, action){   
    								    oParentNode.reload();
							        	oParentNode.expand();
    							    },   
    							    failure:function(resp, action){   
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
Ext.extend(Ext.EnumMenu, Ext.menu.Menu);