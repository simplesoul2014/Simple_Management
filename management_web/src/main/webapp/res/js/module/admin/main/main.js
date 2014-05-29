Ext.BLANK_IMAGE_URL = '../../../res/images/common/default/s.gif';

//入口函数
var g_oViewPort = null;
Ext.onReady(function(){
	Ext.useShims = true;
	Ext.QuickTips.init();
	
	var oCenterNav = new Ext.CenterNav();
	
	setTimeout(function()
	{
		var oIFrameComponent = new Ext.IFrameComponent({id:'WelcomePanel', url:'main.jsp', title:'我的首页', tabTip:'我的首页', closable:false});
		var p = oCenterNav.add(oIFrameComponent);
		oCenterNav.setActiveTab(p);
	}, 50);
	
	g_oViewPort = new Ext.Viewport({
		layout: 'border',
		items: [new Ext.TopNav(), new Ext.LeftNav(oCenterNav), oCenterNav]
	});

	Ext.Ajax.request({   
		   url: '../sysmgr/getMyBgUser.do',   
		   method: 'post',   
		   success: function(resp, action){   
		       var obj = Ext.util.JSON.decode(resp.responseText);   
		       //alert("resp.responseText"+resp.responseText);
		       Ext.get('admin_name').dom.innerHTML = obj.userName;
		    },   
		    failure:function(resp, action){   
		        Ext.Msg.alert('出错提示','系统错误');   
		    }   
	}); 
});
