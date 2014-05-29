var m_nWidth = 650;
var m_nHeight = 450;

function getBgUserForm() {
	return new Ext.FormPanel( {
		labelAlign : 'top',
		frame : true,
		bodyStyle : 'padding:5px 5px 0',
		fileUpload : true,
		items : [ {
			layout : 'column',
			items : [ {
				columnWidth : .5,
				layout : 'form',
				items : [{
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>用户姓名',
					name : 'sName',
					tabIndex:3,
					allowBlank : false,
					validateOnBlur : true,
					invalidText : '请输入用户姓名',
					blankText : '请输入用户姓名！',
					anchor : '95%'
				}, {
					xtype : 'textfield',
					fieldLabel : '用户QQ',
					name : 'sQQ',
					anchor : '95%'
				}, {
					xtype : 'textfield',
					fieldLabel : '用户类型',
					name : 'nUserType',
					anchor : '95%'
				}, {
					xtype : 'textfield',
					fieldLabel : '用户传真',
					name : 'sFax',
					anchor : '95%'
				}]
			}, {
				columnWidth : .5,
				layout : 'form',
				items : [{
					xtype : 'radiogroup',
					fieldLabel : '用户性别',
					name: 'nSex',
					height: 22,
					items: [
		                {boxLabel: '男', name: 'nSex', inputValue:1},
		                {boxLabel: '女', name: 'nSex', inputValue:2},
		                {boxLabel: '未知', name: 'nSex', inputValue:3, checked: true}
					],
					anchor : '50%'
				}, {
					xtype : 'textfield',
					fieldLabel : '用户电邮',
					name : 'sEmail',
					anchor : '100%'
				}, {
					xtype : 'textfield',
					fieldLabel : '用户电话',
					name : 'sTel',
					anchor : '100%'
				}, {
					xtype : 'textfield',
					fieldLabel : '用户邮编',
					name : 'sZip',
					anchor : '100%'
				} ]
			} ]
		}, {
			xtype : 'textfield',
			fieldLabel : '用户地址',
			name : 'sAddress',
			anchor : '100%'
		}, {
			xtype : 'htmleditor',
			fieldLabel : '用户简介',
			name : 'sBrief',
			enableFont: false,
			height : 100,
			anchor : '100%'
		} ]
	});
}

//修改后台用户资料
Ext.EditMyBgUserWindow = function() {
	var me = this;
	Ext.EditMyBgUserWindow.superclass.constructor.call(this,{
		layout : 'fit',
		width : m_nWidth,
		height : m_nHeight,
		plain : true,
		closable: false,
		title : '修改后台用户资料',
		iconCls : 'form',
		items : getBgUserForm(),
		buttons : [{
			text : '提交',
			iconCls : 'save',
			handler : function() {
				var oForm = me.items.get(0).getForm();
				if (oForm.isValid()) {
					oForm.submit({
						method : 'post',
						url : 'editMyBgUserFile.do',
						waitTitle: '出错提示',
						waitMsg: '数据正在提交中，请稍候...',
						success : function(form,action) {
							Ext.MessageBox.alert('出错提示', action.result.msg);
						},
						failure : function(form,action) {
							Ext.MessageBox.alert('出错提示', action.result.msg);
						}
					});
				} else {
					Ext.MessageBox.alert('出错提示',	'表单验证有误，不准提交数据!');
				}
			}
			}
		],
		listeners : {
			show : function(win) {
				Ext.Ajax.request( {
					url : 'getMyCpUser.do',
					method : 'post',
					success : function(resp, action) {
						var obj = Ext.util.JSON
								.decode(resp.responseText);
						win.items.get(0).getForm().setValues( {
							'sName':obj[0][0].userName,
							'nSex':obj[0][1].userSex,
							'nUserType':obj[0][1].userType,
							'sQQ':obj[0][1].userQq,
							'sEmail':obj[0][1].userEmail,
							'nUserType':obj[0][1].userType,
							'sTel':obj[0][1].userTel,
							'sFax':obj[0][1].userFax,
							'sZip':obj[0][1].userPost,
							'sAddress':obj[0][1].userAdrr,
							'sBrief':obj[0][1].userDesc
						});
					},
					failure : function(resp, action) {
						Ext.Msg.alert('出错提示', '系统错误!');
					}
				});
			}
		}
	});
};

Ext.extend(Ext.EditMyBgUserWindow, Ext.Window);
