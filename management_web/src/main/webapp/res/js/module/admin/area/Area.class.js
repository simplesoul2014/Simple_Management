Ext.Area = function()
{
	var me = this;
	Ext.Area.superclass.constructor.call(this, {
		region: 'center',
		title: '地区管理',
		dataUrl:'getAreaTree.do',
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
        root: new Ext.tree.AsyncTreeNode({id:'AreaId_1',expanded: true, text:'全部地区'}),
        listeners: {
			contextmenu: function(oNode, oEvent)
			{
				oEvent.stopEvent();
				oNode.select();
				if(!me.AreaMenu)
				{
					me.AreaMenu = new Ext.AreaMenu();
				}
				if(oNode.getOwnerTree().getRootNode() == oNode)
				{
					me.AreaMenu.items.get(2).hide();
					me.AreaMenu.items.get(3).hide();
				}else
				{
					if(!me.AreaMenu.items.get(2).isVisible()) me.AreaMenu.items.get(2).show();
					if(!me.AreaMenu.items.get(3).isVisible()) me.AreaMenu.items.get(3).show();
				}
				me.AreaMenu.showAt(oEvent.getXY());
			},
			nodedragover: function(oEvent)
			{
				var oNode = oEvent.target;
				oNode.leaf = false;
				return true;
			},
			beforeNodeDrop: function(oEvent)
			{
				var aData = oEvent.data.selections||[];
				var aAreaId = [];
				for(var i=0,iLen=aData.length;i<iLen;i++)
				{
					aAreaId.push(aData[i].id);
				}
				
				var nDestAreaId = oEvent.target.id.replace(/[^0-9]*/,'');
				var nSrcAreaId = aAreaId.length > 0 ? 0 : oEvent.dropNode.id.replace(/[^0-9]*/,'');
				
				Ext.Ajax.request({   
					   url: 'editMoveArea.do',   
					   method: 'post',   
					   params: {nDestAreaId: nDestAreaId
							, nSrcAreaId: nSrcAreaId},
					   success: function(resp, action)
					   {   
					       var obj = Ext.util.JSON.decode(resp.responseText);   
					       if(!obj.success)   
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
		}
	});
};
Ext.extend(Ext.Area, Ext.tree.TreePanel);
