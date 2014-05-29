Ext.LeftNav = function(oCenterNav)
{
	Ext.LeftNav.superclass.constructor.call(this, {
		region: 'west',
		title: '系统导航',
		dataUrl:'../navtree/getMyBgNavTree.do',
		split: true,
		collapsible: true,
		width: 200,
		minSize: 150,
		maxSize: 400,
		collapseMode: 'mini',
		margins: '0 0 5 0',
		cmargins: '0 5 5 5',
		animate:true, 
        autoScroll:true,
        enableDD:true,
        containerScroll: true,
        line: true,
        tools: [{id:'refresh', handler: function(event, toolEl, panel, tc){
    			panel.getRootNode().reload();
    		}
        }],
        rootVisible: false,
        root: new Ext.tree.AsyncTreeNode({id:'TreeNav_0', expanded: true}),
        mainWin: oCenterNav,
        listeners: {click: onNodeClick}
	});
};
Ext.extend(Ext.LeftNav, Ext.tree.TreePanel);

function onNodeClick(oNode, oEvent)
{
	var sUrl = oNode.attributes.url;
	if(sUrl != undefined && sUrl != null && !Ext.isEmpty(sUrl.trim()))
	{
		oEvent.stopEvent();
		var oPanel = oNode.getOwnerTree().mainWin;
		
		var oTab = oPanel.getComponent('Frame_' + oNode.id);
		if(oTab)
		{
			oPanel.setActiveTab(oTab);
		}else
		{
			var oIFrameComponent = new Ext.IFrameComponent({
				id: 'Frame_'+oNode.id, 
				url: sUrl, 
				title: oNode.text, 
				tabTip: oNode.text
			});
			oPanel.setActiveTab(oPanel.add(oIFrameComponent));
		}
	}
}