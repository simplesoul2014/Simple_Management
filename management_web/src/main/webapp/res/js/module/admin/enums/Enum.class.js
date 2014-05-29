Ext.Enum = function()
{
	var me = this;
	Ext.Enum.superclass.constructor.call(this, {
		region: 'center',
		title: '枚举树管理',
		dataUrl:'getEnumList.do',
		border: false,
		collapsible: true,
		collapseMode: 'mini',
		margins: '0 0 5 0',
		cmargins: '0 5 5 5',
		animate:true, 
        autoScroll:true,
        enableDD:true,
        containerScroll: true,
        enableDD:true,
        line: true,
        tools: [{id:'refresh', handler: function(event,toolEl,panel,tc){
    			panel.getRootNode().reload();
    		}
        }],
        rootVisible: true,
        root: new Ext.tree.AsyncTreeNode({id:'A-0',expanded: true, text:'全部枚举'}),
        listeners: {
			contextmenu: function(oNode, oEvent)
			{
				oEvent.stopEvent();
				oNode.select();
				if(!me.EnumMenu)
				{
					me.EnumMenu = new Ext.EnumMenu();
				}
				var cFlag = oNode.id.substring(0, 1);
				me.EnumMenu.items.get(0).show();
				me.EnumMenu.items.get(1).show();
				me.EnumMenu.items.get(2).show();
				me.EnumMenu.items.get(3).show();
				switch(cFlag)
				{
					case 'A' :
						me.EnumMenu.items.get(0).hide();
						me.EnumMenu.items.get(2).hide();
						me.EnumMenu.items.get(3).hide();
					break;
					case 'B' :
						me.EnumMenu.items.get(0).hide();
						me.EnumMenu.items.get(2).hide();
					case 'C' :
						me.EnumMenu.items.get(0).hide();
						me.EnumMenu.items.get(2).hide();
					break;
					case 'D' :
						me.EnumMenu.items.get(1).hide();
					break;
				}

				me.EnumMenu.showAt(oEvent.getXY());
			},
			nodedragover: function(oEvent){
				var oNode = oEvent.target;
				oNode.leaf = false;
				return true;
			}
		}
	});
};
Ext.extend(Ext.Enum, Ext.tree.TreePanel);
