var m_nWidth = 550;
var m_nHeight = 400;

function getEnumForm(){
	return new Ext.FormPanel({
    labelAlign: 'top',
    frame:true,
    bodyStyle:'padding:5px 5px 0',
    fileUpload: true,
	items:[{
	        xtype:'textfield',
	        fieldLabel: '<font color="red">*</font>表名',
	        name: 'sTable',
	        allowBlank:false,
	        validateOnBlur: true,
	        invalidText: '请输入表名',
	        blankText:'请输入表名！',
	        anchor:'100%'
    	},{
	        xtype:'textfield',
	        fieldLabel: '<font color="red">*</font>字段名',
	        name: 'sField',
	        allowBlank:false,
	        validateOnBlur: true,
	        invalidText: '请输入字段名',
	        blankText:'请输入字段名！',
	        anchor:'100%'
    	},{
	        xtype:'textfield',
	        fieldLabel: '<font color="red">*</font>枚举名',
	        name: 'sName',
	        allowBlank:false,
	        validateOnBlur: true,
	        invalidText: '请输入枚举名',
	        blankText:'请输入枚举名！',
	        anchor:'100%'
    	},{
	        xtype:'textfield',
	        fieldLabel: '<font color="red">*</font>枚举名值',
	        name: 'nValue',
	        allowBlank:false,
	        validateOnBlur: true,
	        invalidText: '请输入枚举值',
	        blankText:'请输入枚举值！',
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

//增加枚举
Ext.AddEnumWindow = function()
{
	var me = this;
	Ext.AddEnumWindow.superclass.constructor.call(this, {
		layout:'fit',
        width:m_nWidth,
        height:m_nHeight,
        plain: true,
        title: '增加枚举',
        closeAction: 'hide',
        modal: true,
        iconCls: 'form',
        items: getEnumForm(), 
        buttons: [{
            text:'提交',
            iconCls:'save',
            handler: function()
            {
        		var oForm = me.items.get(0).getForm();
				if (oForm.isValid()) 
				{
    				oForm.submit({
	    		        method: 'post',
	    		        url: 'addEnum.do',
	    		        waitTitle: '出错提示',
						waitMsg: '数据正在提交中，请稍候...',
	    		        success : function(form, action) 
	    		        {
	    				 	var oParentNode = g_oViewPort.items.get(0).getSelectionModel().getSelectedNode();
	    				 	oParentNode.appendChild(new Ext.tree.TreeNode({
	    				 		text: action.result.param.sName,
	    				 		id: 'D-'+action.result.param.nEnumId,
	    				 		leaf: true
	    				 	}));
	    				 	oParentNode.reload();
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
        }],
        listeners:{
			show : function(win)
			{
				win.items.get(0).getForm().setValues({
					'sTable' : me.sTable,
					'sField' : me.sField
				});
			}
        }
	});
};
Ext.extend(Ext.AddEnumWindow, Ext.Window);

//修改枚举
Ext.EditEnumWindow = function()
{
	var me = this;
	Ext.EditEnumWindow.superclass.constructor.call(this, {
		layout:'fit',
		width:m_nWidth,
	    height:m_nHeight,
        plain: true,
        title: '修改枚举',
        closeAction: 'hide',
        modal: true,
        iconCls: 'form',
        items: getEnumForm(), 
        buttons: [{
            text:'提交',
            iconCls:'save',
            handler: function(){
            	var oForm = me.items.get(0).getForm();
				if (oForm.isValid()) 
				{
					oForm.submit({
	    		        method: 'post',
	    		        url: 'editEnum.do',
	    		        params: {nEnumId: me.nEnumId},
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
				getEnum(win); 
			}
		}
	});
};
Ext.extend(Ext.EditEnumWindow, Ext.Window);

//查看枚举
Ext.ViewEnumWindow = function()
{
	var me = this;
	Ext.ViewEnumWindow.superclass.constructor.call(this, {
		layout:'fit',
		width:m_nWidth,
	    height:m_nHeight,
        plain: true,
        title: '查看枚举',
        closeAction: 'hide',
        modal: true,
        iconCls: 'form',
        items:getEnumForm(), 
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
				getEnum(win);
			}
		}
	});
};

function getEnum(win)
{
	Ext.Ajax.request({   
		   url: 'getEnum.do',   
		   method: 'post',   
		   params: {nEnumId: win.nEnumId},   
		   success: function(resp, action)
		   {   
		       var obj = Ext.util.JSON.decode(resp.responseText);    
				win.items.get(0).getForm().setValues({
					'sTable' : obj.table,
					'sField' : obj.field,
					'sName' : obj.name,
					'nValue' : obj.value,
					'sRemark' : obj.remark
				});
		    },   
		    failure:function(resp, action)
		    {   
		        Ext.Msg.alert('出错提示','系统错误!');   
		    }   
	}); 	
}

Ext.extend(Ext.ViewEnumWindow, Ext.Window);
