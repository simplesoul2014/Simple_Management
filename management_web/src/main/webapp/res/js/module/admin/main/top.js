var aTopNav = [
    '<div style="float:left;padding-left:5px;padding-top:6px">'
    ,'<div style="padding-left:5px;padding-top:5px;color:#fff;font-size:30px;font-weight:bolder">信息管理系统</div>'
    ,'</div><div style="float:right;padding-right:15px;padding-top:8px;font-size:9pt">'
    ,'<img src="../../../res/images/common/icons/run.gif"> 目前登录人：<span id="admin_name"></span>'
    ,' <img src="../../../res/images/common/icons/exit1.gif"> <a href="javascript:void(Exit())">退出</a>'
    ,'</div>'];
Ext.TopNav = Ext.extend(Ext.BoxComponent, {
	region: 'north',
	height: 60,
    id: 'TopNav',
    border: false,
    autoEl: {
        tag: 'div',
        cls: 'MainTopBG',
        html:aTopNav.join('')
    },
    listeners:{
    	render : function(box){
    	
    	}
    }
});

function Exit()
{
	Ext.MessageBox.confirm('出错提示', '是否真的要退出本系统？', function(buttonId, text, opt)
	{
		if(buttonId == 'yes')
		{
			Ext.Ajax.request({   
				   url: '../../bglogin/logout.do',   
				   method: 'post',   
				   success: function(resp, action)
				   {   
					   location.href = "../../bglogin/index.do";
				   },   
				   failure:function(resp, action)
				   {   
				       Ext.Msg.alert('出错提示','系统错误');   
				   }   
			}); 
		}
	});
}