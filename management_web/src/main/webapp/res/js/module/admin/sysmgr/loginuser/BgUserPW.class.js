var m_nWidth = 350;
var m_nHeight = 250;

function getBgUserForm() {
	return new Ext.FormPanel( {
		labelAlign : 'top',
		frame : true,
		bodyStyle : 'padding:5px 5px 0',
		fileUpload : true,
		items : [{
			xtype : 'textfield',
			fieldLabel : '<font color="red">*</font>原密码',
			name : 'sPassword1',
			inputType : 'password',
			allowBlank : false,
			validateOnBlur : true,
			invalidText : '请输入原密码',
			blankText : '请输入原密码！',
			anchor : '100%'
		}, {
			xtype : 'textfield',
			fieldLabel : '<font color="red">*</font>新密码',
			name : 'sPassword2',
			inputType : 'password',
			allowBlank : false,
			validateOnBlur : true,
			invalidText : '请输入新密码',
			blankText : '请输入新密码！',
			anchor : '100%'
		},{
			xtype : 'textfield',
			fieldLabel : '<font color="red">*</font>确认密码',
			name : 'sPassword3',
			inputType : 'password',
			allowBlank : false,
			validateOnBlur : true,
			invalidText : '请输入确认密码',
			blankText : '请输入确认密码！',
			anchor : '100%'
		}]
	});
}

//修改后台用户密码
Ext.EditMyBgUserWindow = function() {
	var me = this;
	Ext.EditMyBgUserWindow.superclass.constructor.call(this,{
		layout : 'fit',
		width : m_nWidth,
		height : m_nHeight,
		plain : true,
		closable: false,
		title : '修改后台用户密码',
		iconCls : 'form',
		items : getBgUserForm(),
		buttons : [{
			text : '提交',
			iconCls : 'save',
			handler : function() {
				var oForm = me.items.get(0).getForm();
				if (oForm.isValid()) {
					var oValues = oForm.getValues();
					if(oValues.sPassword2 == oValues.sPassword3)
					{
						oForm.submit({
							method : 'post',
							url : 'editMyBgUserPW.do',
							waitTitle: '出错提示',
							waitMsg: '数据正在提交中，请稍候...',
							success : function(form,action) {
								oForm.reset();
								Ext.MessageBox.alert('出错提示', action.result.msg);
							},
							failure : function(form,action) {
								Ext.MessageBox.alert('出错提示', action.result.msg);
							}
						});
					}else
					{
						Ext.MessageBox.alert('出错提示',	'新密码和确认密码不匹配!');
					}
				} else {
					Ext.MessageBox.alert('出错提示',	'表单验证有误，不准提交数据!');
				}
			}
			}
		]
	});
};

Ext.extend(Ext.EditMyBgUserWindow, Ext.Window);
