var m_nWidth = 450;
var m_nHeight = 260;

function getAreaForm()
{
	return new Ext.FormPanel({
	    labelAlign: 'top',
	    frame:true,
	    bodyStyle:'padding:5px 5px 0',
	    fileUpload: true,
		items:[{
		        xtype:'textfield',
		        fieldLabel: '<font color="red">*</font>地区名称',
		        name: 'sName',
		        allowBlank:false,
		        validateOnBlur: true,
		        invalidText: '请输入地区名称',
		        blankText:'请输入地区名称！',
		        anchor:'100%'
	    	},{
				xtype : 'radiogroup',
				fieldLabel : '地区类型',
				name: 'nType',
				height: 22,
				items: [
	                {boxLabel: '国家', name: 'nType', inputValue:1},
	                {boxLabel: '省份', name: 'nType', inputValue:2},
	                {boxLabel: '地市', name: 'nType', inputValue:3, checked: true}
				],
		        anchor:'60%'
	    	}]
	});
}

//增加地区
Ext.AddAreaWindow = function()
{
	var me = this;
	Ext.AddAreaWindow.superclass.constructor.call(this, {
		layout:'fit',
        width: m_nWidth,
        height: m_nHeight,
        plain: true,
        title: '增加地区',
        closeAction: 'hide',
        modal: true,
        iconCls: 'form',
        items: getAreaForm(), 
        buttons: [{
            text:'提交',
            iconCls:'save',
            handler: function(){
        		var oForm = me.items.get(0).getForm();
				if (oForm.isValid()) 
				{
					var oNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
					var nAreaId = 1;
					if(oNode)
					{
						nAreaId = oNode.id.replace(/[^0-9]*/, '');
					}
					
    				oForm.submit({
	    		        method: 'post',
	    		        url: 'addArea.do',
	    		        params: {nPId: nAreaId},
	    		        waitTitle: '出错提示',
						waitMsg: '数据正在提交中，请稍候...',
	    		        success : function(form, action) 
	    		        {
	    				 	var oParentNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
	    				 	oParentNode.appendChild(new Ext.tree.TreeNode({
	    				 		text: action.result.param.sName,
	    				 		id: 'AreaId_'+action.result.param.nAreaId,
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
Ext.extend(Ext.AddAreaWindow, Ext.Window);

//修改地区
Ext.EditAreaWindow = function()
{
	var me = this;
	Ext.EditAreaWindow.superclass.constructor.call(this, {
		layout:'fit',
        width: m_nWidth,
        height: m_nHeight,
        plain: true,
        title: '修改地区',
        closeAction: 'hide',
        modal: true,
        iconCls: 'form',
        items: getAreaForm(), 
        buttons: [{
            text:'提交',
            iconCls:'save',
            handler: function(){
            	var oForm = me.items.get(0).getForm();
				if (oForm.isValid()) 
				{
					oForm.submit({
	    		        method: 'post',
	    		        url: 'editArea.do',
	    		        params: {nAreaId: me.nAreaId},
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
				getArea(win);
			}
		}
	});
};
Ext.extend(Ext.EditAreaWindow, Ext.Window);

//查看地区
Ext.ViewAreaWindow = function()
{
	var me = this;
	Ext.ViewAreaWindow.superclass.constructor.call(this, {
		layout:'fit',
        width: m_nWidth,
        height: m_nHeight,
        plain: true,
        title: '查看地区',
        closeAction: 'hide',
        modal: true,
        iconCls: 'form',
        items:getAreaForm(), 
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
				getArea(win);
			}
		}
	});
};

function getArea(win)
{
	Ext.Ajax.request({   
	   url: 'getArea.do',   
	   method: 'post',   
	   params: {nAreaId: win.nAreaId},   
	   success: function(resp, action)
	   {   
	       var obj = Ext.util.JSON.decode(resp.responseText);    
			win.items.get(0).getForm().setValues({
				'sName' : obj.name,
				'nType' : obj.type
			});
	    },   
	    failure:function(resp, action)
	    {   
	        Ext.Msg.alert('出错提示','系统错误!');   
	    }   
	}); 	
}

Ext.extend(Ext.ViewAreaWindow, Ext.Window);
