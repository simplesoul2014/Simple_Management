var m_nWidth = 560;
var m_nHeight = 420;

function isImage(value)
{  
    var start = value.lastIndexOf('.');  
    var end = value.length;  
    var imageVal = value.substring((start+1),end);  
    if(imageVal=='jpg'||imageVal=='gif'||imageVal=='GIF'||imageVal=='JPG')
    {  
        return true;  
    }else{  
        return '请上传JPG或GIF图片类型！';  
    }  
      
}  

function checksize(value)
{  

    if(value.length>0&&value.length<50)
    {  
        return true;  
    }else{  
        return '请输入景点导读,但不能超过50字！';  
    }  
      
}  


function getBuAttractionsForm() 
{
	return new Ext.FormPanel( {
		labelWidth: 70,
		labelAlign:'right',
		frame : true,
		bodyStyle : 'padding:5px 5px 0',
		fileUpload : true,
		items : 
		[
		{
			layout : 'column',
			items :[
			{
				columnWidth : .5,
				layout : 'form',
				items : [
				{
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>景点名称',
					name : 'SAttracionsname',
					tabIndex:1,
					allowBlank : false,
					validateOnBlur : true,
					invalidText : '请输入景点名称',
					blankText : '请输入景点名称！',
					anchor : '95%'
				}, 
				{
					xtype : 'numberfield',
					fieldLabel : '<font color="red">*</font>景点排序',
					name : 'NOrder',
					tabIndex:3,
					allowBlank : false,
					validateOnBlur : true,
					invalidText : '请输入景点排序',
					blankText : '请输入景点排序！',
					anchor : '95%'
				}]
			}, {
				columnWidth : .5,
				layout : 'form',
				items : [
					{
					xtype : 'radiogroup',
					fieldLabel : '景点状态',
					name: 'NStatus',
					height: 22,
					items: [
		                {boxLabel: '已删除', name: 'NStatus', inputValue:0},
		                {boxLabel: '上架', name: 'NStatus', inputValue:1,checked: true},
		                {boxLabel: '下架', name: 'NStatus', inputValue:2 }
					],
					anchor : '95%'
				},
					{
						xtype : 'buregiontrigger',
						fieldLabel : '<font color="red">*</font>所属地市',
						name : 'SRegionname',
						allowBlank : false,
						validateOnBlur : true,
						invalidText : '请选择所属地市',
						blankText : '请选择所属地市！',
						anchor : '95%'
					}	
				]
			} ]
		},
		{
			xtype : 'textarea',
			fieldLabel : '<font color="red">*</font>景点导读',
			name : 'SReview',
			allowBlank : false,
			validateOnBlur : true,
			validator : checksize, 
			invalidText : '请输入景点导读,但不能超过50字！',
			blankText : '请输入景点导读,但不能超过50字！',
			anchor : '85%'
		}, 
		{
	        xtype:'fileuploadfield',  
			fieldLabel : '<font color="red">*</font>景点图片1',
			name : 'SImagepath',
			allowBlank : false,
			validateOnBlur : true,
			validator : isImage,  
			invalidText : '请输入景点图片1',
			blankText : '请输入景点图片1！',
			anchor : '85%'
			
		},
		{
	        xtype:'fileuploadfield',  
			fieldLabel : '<font color="red">*</font>景点图片2',
			name : 'SImagepath2',
			allowBlank : false,
			validateOnBlur : true,
			validator : isImage,  
			invalidText : '请输入景点图片2',
			blankText : '请输入景点图片2！',
			anchor : '85%'
			
		}, 
		{
	        xtype:'fileuploadfield',  
			fieldLabel : '<font color="red">*</font>景点图片3',
			name : 'SImagepath3',
			allowBlank : false,
			validateOnBlur : true,
			validator : isImage,  
			invalidText : '请输入景点图片3',
			blankText : '请输入景点图片3！',
			anchor : '85%'
			
		}, 
		{
			xtype : 'textfield',
			fieldLabel : '<font color="red">*</font>景点地址',
			name : 'SDescription',
			allowBlank : false,
			validateOnBlur : true,
			invalidText : '请输入景点地址',
			blankText : '请输入景点地址！',
			anchor : '85%'
		}, 
		{
			xtype : 'htmleditor',
			fieldLabel : '景点详情',
			name : 'SDetail',
			enableFont: false,
			height : 130,
			anchor : '100%'
		} 
		]
	});
}

Ext.AddBuAttractionsWindow = function() 
{
	var me = this;
	Ext.AddBuAttractionsWindow.superclass.constructor.call(this, 
	{
		layout : 'fit',
		width : m_nWidth,
		height : m_nHeight,
		plain : true, 
		title : '增加景点信息资料',
		closeAction : 'hide',
		modal : true,
		iconCls : 'form',
		items : getBuAttractionsForm(),
		buttons : [ {
			text : '提交',
			iconCls : 'save',
			handler : function() 
			{
				var oForm = me.items.get(0).getForm();
				if (oForm.isValid()) 
				{
					oForm.submit( {
						method : 'post',
						url : 'addBuAttractions.do',
						params : {
							NRegionid: oForm.findField('SRegionname').getRealValue()
						},
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
Ext.extend(Ext.AddBuAttractionsWindow, Ext.Window);

//修改后台用户资料
Ext.EditBuAttractionsWindow = function() 
{
	var me = this;
	Ext.EditBuAttractionsWindow.superclass.constructor.call(this,
	{
		layout : 'fit',
		width : m_nWidth,
		height : m_nHeight,
		plain : true,
		title : '修改景点信息资料',
		closeAction : 'hide',
		modal : true,
		iconCls : 'form',
		items : getBuAttractionsForm(),
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
						url : 'editBuAttractions.do',
						params : {
							NAttractionid : me.NAttractionid,
							NRegionid: oForm.findField('SRegionname').getRealValue()
//							SPreferentialIdlist:oForm.findField('SpreferentialCode').getRealValue()
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
				getBuAttractions(win);
			}
		}
	});
};

Ext.extend(Ext.EditBuAttractionsWindow, Ext.Window);



Ext.ViewBuAttractionsWindow = function() 
{
	var me = this;
	Ext.ViewBuAttractionsWindow.superclass.constructor.call(this, 
	{
		layout : 'fit',
		width : m_nWidth,
		height : m_nHeight,
		plain : true,
		title : '查看景点信息资料',
		closeAction : 'hide',
		modal : true,
		iconCls : 'form',
		items : getBuAttractionsForm(),
		buttons : [ {
			text : '关闭',
			iconCls : 'exit',
			handler : function() {
				me.hide();
			}
		} ],
		listeners : {
			show : function(win) {getBuAttractions(win);}
		}
	});
};

function getBuAttractions(win)
{
	Ext.Ajax.request( {
		url : 'getBuAttractions.do',
		method : 'post',
		params : {
			NAttractionid : win.NAttractionid
		},
		success : function(resp, action) 
		{
			var obj = Ext.util.JSON.decode(resp.responseText);
			win.items.get(0).getForm().setValues( {
				'SAttracionsname':obj.sattracionsname,
				'NRegionid':obj.nregionid,
				'SReview':obj.sreview,
				'SDetail':obj.sdetail,
//				'NIspreferential':obj.nispreferential,
				'SDescription':obj.sdescription,
				'NOrder':obj.norder,
				'NStatus':obj.nstatus,
				'SRegionname':obj.sregionname,
//				'SpreferentialCode':obj.spreferentialCode,
				'SImagepath':obj.simagepath,
				'SImagepath2':obj.simagepath2,
				'SImagepath3':obj.simagepath3
			});
			var oForm = win.items.get(0).getForm();
			oForm.findField('SRegionname').setRealValue(obj.nregionid);
//			oForm.findField('SpreferentialCode').setRealValue(obj.nispreferential);
		},
		failure : function(resp, action) 
		{
			Ext.Msg.alert('出错提示', '系统错误!');
		}
	});
}

Ext.extend(Ext.ViewBuAttractionsWindow, Ext.Window);