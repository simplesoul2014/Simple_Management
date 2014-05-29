var m_nWidth = 470;
var m_nHeight = 420;

function getBuPreferentialForm() 
{
	return new Ext.FormPanel( {
		//labelAlign : 'top',
		labelWidth: 70,
		labelAlign:'right',
		frame : true,
		bodyStyle : 'padding:5px 5px 0',
		fileUpload : true,
		items : 
		[
		{
			xtype : 'textfield',
			fieldLabel : '<font color="red">*</font>优惠内容',
			name : 'SInfo',		
			allowBlank : false,
			validateOnBlur : true,
			invalidText : '请输入优惠内容',
			blankText : '请输入优惠内容！',
			anchor : '80%'
		},
		{
			xtype : 'textfield',
			fieldLabel : '<font color="red">*</font>联系人',
			name : 'SContactpeople',
			allowBlank : false,
			validateOnBlur : true,
			invalidText : '请输入优惠联系人',
			blankText : '请输入优惠联系人！',
			anchor : '80%'
		},
		{
			xtype : 'textfield',
			fieldLabel : '<font color="red">*</font>联系电话',
			name : 'STel',
			allowBlank : false,
			validateOnBlur : true,
			invalidText : '请输入联系电话',
			blankText : '请输入联系电话！',
			anchor : '80%'
		},  
		{
			xtype : 'textfield',
			fieldLabel : '<font color="red">*</font>优惠编码',
			name : 'SInfocode',
			allowBlank : false,
			validateOnBlur : true,
			invalidText : '请输入优惠编码',
			blankText : '请输入优惠编码！',
			anchor : '80%'
		}, 
		{
			
			xtype : 'radiogroup',
			fieldLabel : '优惠状态',
			name: 'NStatus',
			height: 22,
			items: [
		           {boxLabel: '删除', name: 'NStatus', inputValue:0},
		           {boxLabel: '上架', name: 'NStatus', inputValue:1},
		           {boxLabel: '下架', name: 'NStatus', inputValue:2, checked: true}
			],
			anchor : '80%'
		},
		{
			xtype : 'htmleditor',
			fieldLabel : '优惠描述',
			name : 'SDescription',
			enableFont: false,
			height : 130,
			anchor : '100%'
		} 
		]
	});
}

//增加景点优惠信息资料
Ext.AddBuPreferentialWindow = function() 
{
	var me = this;
	Ext.AddBuPreferentialWindow.superclass.constructor.call(this, 
	{
		layout : 'fit',
		width : m_nWidth,
		height : m_nHeight,
		plain : true, 
		title : '增加景点优惠信息',
		closeAction : 'hide',
		modal : true,
		iconCls : 'form',
		items : getBuPreferentialForm(),
		buttons : [ {
			text : '提交',
			iconCls : 'save',
			handler : function() 
			{
				var oForm = me.items.get(0).getForm();
				if (oForm.isValid()) 
				{
//					alert(oForm.findField('sBgRole').getRealValue());
					oForm.submit( {
						method : 'post',
						url : 'addBuPreferential.do',
						waitTitle: '出错提示',
						waitMsg: '数据正在提交中，请稍候...',
						success : function(form, action) 
						{
							g_oViewPort.items.get(0).getStore().reload();
							oForm.reset();
							me.hide();
						},
						failure : function(form, action) 
						{
							Ext.MessageBox.alert('出错提示', action.result.msg);
						}
					});
				} else 
				{
					Ext.MessageBox.alert('出错提示', '表单验证有误，不准提交数据!');
				}
			}
		}, {
			text : '清空',
			iconCls : 'reset',
			handler : function() 
			{
				me.items.get(0).getForm().reset();
			}
		}, {
			text : '关闭',
			iconCls : 'exit',
			handler : function() 
			{
				me.items.get(0).getForm().reset();
				me.hide();
			}
		} ]
	});
};
Ext.extend(Ext.AddBuPreferentialWindow, Ext.Window);

Ext.EditBuPreferentialWindow = function() 
{
	var me = this;
	Ext.EditBuPreferentialWindow.superclass.constructor.call(this,
	{
		layout : 'fit',
		width : m_nWidth,
		height : m_nHeight,
		plain : true,
		title : '修改景点优惠信息',
		closeAction : 'hide',
		modal : true,
		iconCls : 'form',
		items : getBuPreferentialForm(),
		buttons : [{
			text : '提交',
			iconCls : 'save',
			handler : function() 
			{
				var oForm = me.items.get(0).getForm();
				if (oForm.isValid()) 
				{
					oForm.submit({
						method : 'post',
						url : 'editBuPreferential.do',
						params : {
							NPreferenid : me.NPreferenid
						},
						waitTitle: '出错提示',
						waitMsg: '数据正在提交中，请稍候...',
						success : function(form,action) 
						{
							g_oViewPort.items.get(0).getStore().reload();
							me.hide();
						},
						failure : function(form,action) 
						{
							Ext.MessageBox.alert('出错提示', action.result.msg);
						}
					});
				} else 
				{
					Ext.MessageBox.alert('出错提示',	'表单验证有误，不准提交数据!');
				}
			}
			}, {
				text : '关闭',
				iconCls : 'exit',
				handler : function() 
				{
					me.items.get(0).getForm().reset();
					me.hide();
				}
			} 
		],
		listeners : {
			show : function(win) 
			{
				getBuPreferential(win);
			}
		}
	});
};

Ext.extend(Ext.EditBuPreferentialWindow, Ext.Window);



Ext.ViewBuPreferentialWindow = function() 
{
	var me = this;
	Ext.ViewBuPreferentialWindow.superclass.constructor.call(this, 
	{
		layout : 'fit',
		width : m_nWidth,
		height : m_nHeight,
		plain : true,
		title : '查看景点优惠信息',
		closeAction : 'hide',
		modal : true,
		iconCls : 'form',
		items : getBuPreferentialForm(),
		buttons : [ {
			text : '关闭',
			iconCls : 'exit',
			handler : function() {
				me.hide();
			}
		} ],
		listeners : {
			show : function(win) {getBuPreferential(win);}
		}
	});
};

function getBuPreferential(win)
{
	Ext.Ajax.request( {
		url : 'getBuPreferential.do',
		method : 'post',
		params : {
			NPreferenid : win.NPreferenid
		},
		success : function(resp, action) 
		{
			var obj = Ext.util.JSON.decode(resp.responseText);
			win.items.get(0).getForm().setValues( {
				'SInfo':obj.sinfo,
				'STel':obj.stel,
				'SContactpeople':obj.scontactpeople,
				'SDescription':obj.sdescription,
				'SInfocode':obj.sinfocode,
				'NStatus':obj.nstatus
			});
			var oForm = win.items.get(0).getForm();
		},
		failure : function(resp, action) 
		{
			Ext.Msg.alert('出错提示', '系统错误!');
		}
	});
}

Ext.extend(Ext.ViewBuPreferentialWindow, Ext.Window);