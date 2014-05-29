var m_nWidth = 470;
var m_nHeight = 280;

function isLetter(value)
{  
	
 	var reg = new RegExp("^[A-Z]+$");
    if(!reg.test(value)){
        return "请输入大写字母!";
    }
	return true;
}  

function getBuRegionForm() 
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
			fieldLabel : '<font color="red">*</font>省份名称',
			name : 'SProvincename',		
			allowBlank : false,
			validateOnBlur : true,
			invalidText : '请输入省份名称',
			blankText : '请输入省份名称！',
			anchor : '80%'
		},
		{
			xtype : 'textfield',
			fieldLabel : '<font color="red">*</font>地市名称',
			name : 'SCityname',
			allowBlank : false,
			validateOnBlur : true,
			invalidText : '请输入地市名称',
			blankText : '请输入地市名称！',
			anchor : '80%'
		},
		{
			xtype : 'textfield',
			fieldLabel : '<font color="red">*</font>地市缩写',
			name : 'SZonenumber',
			allowBlank : false,
			validateOnBlur : true,
			invalidText : '请输入大写字母！',
			validator : isLetter,  
			blankText : '请输入大写字母！',
			anchor : '80%'
		},  
		{
			xtype : 'htmleditor',
			fieldLabel : '地市描述',
			name : 'SDescription',
			enableFont: false,
			height : 130,
			anchor : '100%'
		} 
		]
	});
}

//增加地市信息资料
Ext.AddBuRegionWindow = function() 
{
	var me = this;
	Ext.AddBuRegionWindow.superclass.constructor.call(this, 
	{
		layout : 'fit',
		width : m_nWidth,
		height : m_nHeight,
		plain : true,
		title : '增加地市信息',
		closeAction : 'hide',
		modal : true,
		iconCls : 'form',
		items : getBuRegionForm(),
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
						url : 'addBuRegion.do',
//						params : {
//							//nBgDeptId : oForm.findField('sDeptName').getRealValue()
//							sBgRoleId: oForm.findField('sBgRole').getRealValue()
//							//,sBgPostId: oForm.findField('sBgPost').getRealValue()
//						},
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
Ext.extend(Ext.AddBuRegionWindow, Ext.Window);

//修改后台用户资料
Ext.EditBuRegionWindow = function() 
{
	var me = this;
	Ext.EditBuRegionWindow.superclass.constructor.call(this,
	{
		layout : 'fit',
		width : m_nWidth,
		height : m_nHeight,
		plain : true,
		title : '修改地市信息',
		closeAction : 'hide',
		modal : true,
		iconCls : 'form',
		items : getBuRegionForm(),
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
						url : 'editBuRegion.do',
						params : {
							NRegionid : me.NRegionid
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
				getBuRegion(win);
			}
		}
	});
};

Ext.extend(Ext.EditBuRegionWindow, Ext.Window);

//查看后台用户资料
Ext.ViewBuRegionWindow = function() 
{
	var me = this;
	Ext.ViewBuRegionWindow.superclass.constructor.call(this, 
	{
		layout : 'fit',
		width : m_nWidth,
		height : m_nHeight,
		plain : true,
		title : '查看地市信息',
		closeAction : 'hide',
		modal : true,
		iconCls : 'form',
		items : getBuRegionForm(),
		buttons : [ {
			text : '关闭',
			iconCls : 'exit',
			handler : function() {
				me.hide();
			}
		} ],
		listeners : {
			show : function(win) {getBuRegion(win);}
		}
	});
};

function getBuRegion(win)
{
	Ext.Ajax.request( {
		url : 'getBuRegion.do',
		method : 'post',
		params : {
			NRegionid : win.NRegionid
		},
		success : function(resp, action) 
		{
			var obj = Ext.util.JSON.decode(resp.responseText);
			win.items.get(0).getForm().setValues( {
				'SProvincename':obj[0].sprovincename,
				'SCityname':obj[0].scityname,
				'SZonenumber':obj[0].szonenumber,
				'SDescription':obj[0].sdescription
			});
			var oForm = win.items.get(0).getForm();
		},
		failure : function(resp, action) 
		{
			Ext.Msg.alert('出错提示', '系统错误!');
		}
	});
}

Ext.extend(Ext.ViewBuRegionWindow, Ext.Window);