var m_nWidth = 550;
var m_nHeight = 300;

function getBgNavForm()
{
	return new Ext.FormPanel({
	    labelAlign: 'top',
	    frame:true,
	    bodyStyle:'padding:5px 5px 0',
	    fileUpload: true,
		items:[{
		        xtype:'textfield',
		        fieldLabel: '<font color="red">*</font>导航名称',
		        name: 'sName',
		        allowBlank:false,
		        validateOnBlur: true,
		        invalidText: '请输入导航名称',
		        blankText:'请输入导航名称！',
		        anchor:'100%'
	    	},{
		        xtype:'textfield',
		        fieldLabel: '超链接',
		        name: 'sUrl',
		        anchor:'100%'
	    	},{
			    xtype:'htmleditor',
			    name: 'sRemark',
				enableFont: false,
			    fieldLabel:'备注',
			    height:90,
			    anchor:'100%'
		}]
	});
}

//增加导航
Ext.AddBgNavWindow = function()
{
	var me = this;
	Ext.AddBgNavWindow.superclass.constructor.call(this, {
		layout:'fit',
        width: m_nWidth,
        height: m_nHeight,
        plain: true,
        title: '增加导航结点',
        closeAction: 'hide',
        modal: true,
        iconCls: 'form',
        items: getBgNavForm(), 
        buttons: [{
            text:'提交',
            iconCls:'save',
            handler: function(){
        		var oForm = me.items.get(0).getForm();
				if (oForm.isValid()) 
				{
					var oNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
					var nBgTreeId = 1;
					if(oNode)
					{
						nBgTreeId = oNode.id.replace(/[^0-9]*/, '');
					}
					
    				oForm.submit({
	    		        method: 'post',
	    		        url: 'addBgNavTreeNode.do',
	    		        params: {nPId: nBgTreeId},
	    		        waitTitle: '出错提示',
						waitMsg: '数据正在提交中，请稍候...',
	    		        success : function(form, action) 
	    		        {
	    				 	var oParentNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
	    				 	oParentNode.appendChild(new Ext.tree.TreeNode({
	    				 		text: action.result.param.sName,
	    				 		id: 'NavTreeId_'+action.result.param.nBgTreeId,
	    				 		leaf: true
	    				 	}));
	    				 	oParentNode.expand();
	    				 	oForm.reset();
	    				 	me.hide(oParentNode.getUI().getEl());
	    				},
	    				failure : function(form, action) 
	    				{
	    					Ext.MessageBox.alert('出错提示', action.result.msg);
	    				}
	    			});
				}else
				{
					Ext.MessageBox.alert('出错提示', '表单验证有误，不准提交数据!');
				}
        	}
        },{
            text:'清空',
            iconCls:'reset',
            handler: function()
            {
    			me.items.get(0).getForm().reset();
        	}
        },{
            text: '关闭',
            iconCls:'exit',
            handler: function()
            {
    			me.items.get(0).getForm().reset();
    			me.hide();
            }
        }]
	});
};
Ext.extend(Ext.AddBgNavWindow, Ext.Window);

//修改导航
Ext.EditBgNavWindow = function()
{
	var me = this;
	Ext.EditBgNavWindow.superclass.constructor.call(this, {
		layout:'fit',
        width: m_nWidth,
        height: m_nHeight,
        plain: true,
        title: '修改导航结点',
        closeAction: 'hide',
        modal: true,
        iconCls: 'form',
        items: getBgNavForm(), 
        buttons: [{
            text:'提交',
            iconCls:'save',
            handler: function(){
            	var oForm = me.items.get(0).getForm();
				if (oForm.isValid()) 
				{
					oForm.submit({
	    		        method: 'post',
	    		        url: 'editBgNavTreeNode.do',
	    		        params: {nBgTreeId: me.nBgTreeId},
	    		        waitTitle: '出错提示',
						waitMsg: '数据正在提交中，请稍候...',
	    		        success : function(form, action) 
	    		        {
							g_oViewPort.items.get(0).getSelectionModel().getSelectedNode().setText(action.result.param.sName);
							me.hide();
	    				},
	    				failure : function(form, action) 
	    				{
	    					Ext.MessageBox.alert('出错提示', action.result.msg);
	    				}
	    			});
				}else
				{
					Ext.MessageBox.alert('出错提示', '表单验证有误，不准提交数据!');
				}
        	}
        },{
            text: '关闭',
            iconCls:'exit',
            handler: function()
            {
    			me.items.get(0).getForm().reset();
    			me.hide();
            }
        }],
		listeners:{
			show : function(win)
			{
				getNavTreeNode(win);
			}
		}
	});
};
Ext.extend(Ext.EditBgNavWindow, Ext.Window);

//查看导航
Ext.ViewBgNavWindow = function()
{
	var me = this;
	Ext.ViewBgNavWindow.superclass.constructor.call(this, {
		layout:'fit',
        width: m_nWidth,
        height: m_nHeight,
        plain: true,
        title: '查看导航结点',
        closeAction: 'hide',
        modal: true,
        iconCls: 'form',
        items:getBgNavForm(), 
        buttons: [
        {
            text: '关闭',
            iconCls:'exit',
            handler: function(){
    			me.hide();
            }
        }],
		listeners:{
			show : function(win)
			{
				getNavTreeNode(win);
			}
		}
	});
};
Ext.extend(Ext.ViewBgNavWindow, Ext.Window);

function getNavTreeNode(win)
{
	Ext.Ajax.request({   
	   url: 'getBgNavTreeNode.do',   
	   method: 'post',   
	   params: {nBgTreeId: win.nBgTreeId},   
	   success: function(resp, action)
	   {   
	       var obj = Ext.util.JSON.decode(resp.responseText); 
			win.items.get(0).getForm().setValues({
				'sName' : obj.powerName,
				'sUrl' : obj.url,
				'sRemark' : obj.remark
			});
	    },   
	    failure:function(resp, action)
	    {   
	        Ext.Msg.alert('出错提示','系统错误!');   
	    }   
	}); 	
}


