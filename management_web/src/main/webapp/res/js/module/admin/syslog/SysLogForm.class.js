var m_nWidth = 500;
var m_nHeight = 380;

function getSysLogForm() {
	return new Ext.FormPanel( {
		labelAlign : 'top',
		frame : true,
		bodyStyle : 'padding:5px 5px 0',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '用户姓名',
			name : 'sName',
			anchor : '100%'
		}, {
			xtype : 'textfield',
			fieldLabel : '所在模块',
			name : 'sModule',
			anchor : '100%'
		}, {
			xtype : 'textfield',
			fieldLabel : '创建时间',
			name : 'dCreateTime',
			anchor : '100%'
		}, {
			xtype : 'htmleditor',
			fieldLabel : '操作内容',
			name : 'sContent',
			enableFont: false,
			height : 120,
			anchor : '100%'
		} ]
	});
}

//查看系统日志
Ext.ViewSysLogWindow = function() {
	var me = this;
	Ext.ViewSysLogWindow.superclass.constructor.call(this, {
		layout : 'fit',
		width : m_nWidth,
		height : m_nHeight,
		plain : true,
		title : '查看系统日志',
		closeAction : 'hide',
		modal : true,
		iconCls : 'form',
		items : getSysLogForm(),
		buttons : [ {
			text : '关闭',
			iconCls : 'exit',
			handler : function() 
			{
				me.hide();
			}
		} ],
		listeners : {
			show : function(win) 
			{
				Ext.Ajax.request( {
					url : 'getSysLog.do',
					method : 'post',
					params : {
						nSysLogId : win.nSysLogId
					},
					success : function(resp, action) 
					{
						var obj = Ext.util.JSON.decode(resp.responseText);
						win.items.get(0).getForm().setValues( {
							'sName':obj.name,
							'sModule':obj.moduleName,
							'dCreateTime':(new Date(obj.createDate)).format('Y-m-d H:i:s'),
							'sContent':obj.content
						});
					},
					failure : function(resp, action) 
					{
						Ext.Msg.alert('出错提示', '系统错误!');
					}
				});
			}
		}
	});
};

Ext.extend(Ext.ViewSysLogWindow, Ext.Window);
