Ext.BuPreferentialTopToolbar = function(oGrid)
{
	var me = this;
	Ext.BuPreferentialTopToolbar.superclass.constructor.call(this, 
	{
        enableOverflow: true,
        items: [
        	{
        		xtype: 'tbtext', 
        		text: '优惠信息内容：'
        	},
        	{
        		xtype: 'textfield'
        	},{xtype: 'tbspacer', width: 10},
        	{
        		xtype: 'tbtext', 
        		text: '优惠信息编码：'
        	},
        	{
        		xtype: 'textfield'
        	},{xtype: 'tbspacer', width: 10},
        	{
	            text: '搜索',
	            iconCls: 'search',
	            handler: function(oButton, oEvent)
	            {
        			var aComponents = me.findByType('textfield');
        			
        			var SInfo = aComponents[0].getValue();
        			var SInfocode = aComponents[1].getValue();
        			oGrid.sinfo = SInfo;
        			oGrid.sinfocode = SInfocode;
        			oGrid.getStore().load({params:{sInfo:SInfo, sInfoCode:SInfocode}});
        		}
        	},{xtype: 'tbspacer', width: 10},
        	{
	            text: '查看',
	            iconCls: 'look',
	            handler: function(oButton, oEvent)
	            {
        			var oRecord = oGrid.getSelectionModel().getSelected();
	    			if(oRecord != undefined)
	    			{
	        			if(!oButton.Window)
	        			{
	        				oButton.Window = new Ext.ViewBuPreferentialWindow();
	        			}
	        			oButton.Window.NPreferenid=oRecord.data.npreferenid;
	        			if(!oButton.Window.isVisible()) oButton.Window.show(oButton.getEl());//show 为一种效果（扩大缩小）
	    			}else
	    			{
	    				Ext.MessageBox.alert('出错提示', '请选择要查看的景点优惠信息资料！');
	    			}
    			}
        	},{xtype: 'tbspacer', width: 10},
        	{
	            text: '增加',
	            iconCls: 'add',
	            handler: function(oButton, oEvent)
	            {
        			if(!oButton.Window)
        			{
        				oButton.Window = new Ext.AddBuPreferentialWindow();
        			}
        			if(!oButton.Window.isVisible()) oButton.Window.show(oButton.getEl());
				}
        	},{xtype: 'tbspacer', width: 10},
        	{
	            text: '修改',
	            iconCls: 'edit_icon',
	            handler: function(oButton, oEvent)
	            {
        			var oRecord = oGrid.getSelectionModel().getSelected();
	    			if(oRecord != undefined)
	    			{
	        			if(!oButton.Window)
	        			{
	        				oButton.Window = new Ext.EditBuPreferentialWindow();
	        			}
	        			oButton.Window.NPreferenid=oRecord.data.npreferenid;
	        			if(!oButton.Window.isVisible()) oButton.Window.show(oButton.getEl());
	    			}else
	    			{
	    				Ext.MessageBox.alert('出错提示', '请选择要修改的景点优惠信息资料！');
	    			}
				}
        	},{xtype: 'tbspacer', width: 10},
        	{
        		text: '删除',
        		iconCls: 'del',
	            handler: function(oButton, oEvent){
        			var aRecord = oGrid.getSelectionModel().getSelections();
	    			if(aRecord.length > 0)
		    		{
	    				Ext.MessageBox.confirm('温馨提示', '真的要删除选择的景点优惠信息资料吗？', function(buttonId, text, opt)
	    				{
	    					if(buttonId == 'yes')
	    					{
	    						var sBuPreferentialIdList = [0];
	    						for(var nKey in aRecord)
	    						{
	    							if(aRecord[nKey].data != undefined)
	    							{
	    								if(aRecord[nKey].data.npreferenid != 1) {
	    									sBuPreferentialIdList.push(aRecord[nKey].data.npreferenid);	
	    								}	    									
	    							}
	    						}

	    						Ext.Ajax.request({   
	    							   url: 'delBuPreferential.do',   
	    							   method: 'post',   
	    							   params: {sBuPreferentialIdList: sBuPreferentialIdList.join(',')},   
	    							   success: function(resp, action)
	    							   {   
	    							       var obj = Ext.util.JSON.decode(resp.responseText);   
	    							       if(obj.success)   
	    							       {    
	    							    	   oGrid.getStore().reload();
	    							        }   
	    							        else  
	    							        {   
	    							            Ext.Msg.alert('出错提示', obj.msg);   
	    							        }     
	    							    },   
	    							    failure:function(resp, action)
	    							    {   
	    							        Ext.Msg.alert('出错提示','系统错误');   
	    							    }   
	    						}); 
	    					}
	    				});		    			
		    		}else
	    			{
	    				Ext.MessageBox.alert('出错提示', '请选择要删除的景点优惠信息！');
	    			}
				}
        	},
        	'->',
        	{
        		text: '帮助',
        		iconCls: 'help'
        	}
        ]
	});
};

Ext.extend(Ext.BuPreferentialTopToolbar, Ext.Toolbar);