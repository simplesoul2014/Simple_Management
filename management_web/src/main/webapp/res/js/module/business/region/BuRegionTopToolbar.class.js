Ext.BuRegionTopToolbar = function(oGrid)
{
	var me = this;
	Ext.BuRegionTopToolbar.superclass.constructor.call(this, 
	{
        enableOverflow: true,
        items: [
        	{
        		xtype: 'tbtext', 
        		text: '地市名称：'
        	},
        	{
        		xtype: 'textfield'
        	},{xtype: 'tbspacer', width: 10},
        	{
        		xtype: 'tbtext', 
        		text: '地市缩写：'
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
        			
        			var SCityname = aComponents[0].getValue();
        			var SZonenumber = aComponents[1].getValue();
        			oGrid.scityname = SCityname;
        			oGrid.szonenumber = SZonenumber;
        			oGrid.getStore().load({params:{SCityname:SCityname, SZonenumber:SZonenumber}});
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
	        				oButton.Window = new Ext.ViewBuRegionWindow();
	        			}
	        			oButton.Window.NRegionid=oRecord.data.nregionid;
	        			if(!oButton.Window.isVisible()) oButton.Window.show(oButton.getEl());//show 为一种效果（扩大缩小）
	    			}else
	    			{
	    				Ext.MessageBox.alert('出错提示', '请选择要查看的地市信息资料！');
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
        				oButton.Window = new Ext.AddBuRegionWindow();
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
	        				oButton.Window = new Ext.EditBuRegionWindow();
	        			}
	        		oButton.Window.NRegionid=oRecord.data.nregionid;
	        			if(!oButton.Window.isVisible()) oButton.Window.show(oButton.getEl());
	    			}else
	    			{
	    				Ext.MessageBox.alert('出错提示', '请选择要修改的地市信息资料！');
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
	    				Ext.MessageBox.confirm('温馨提示', '真的要删除选择的地市信息资料吗？', function(buttonId, text, opt)
	    				{
	    					if(buttonId == 'yes')
	    					{
	    						var sBuRegionIdList = [0];
	    						for(var nKey in aRecord)
	    						{
	    							if(aRecord[nKey].data != undefined)
	    							{
	    								if(aRecord[nKey].data.nregionid != 1) {
	    									
	    									sBuRegionIdList.push(aRecord[nKey].data.nregionid);	
	    								}	    									
	    							}
	    						}

	    						Ext.Ajax.request({   
	    							   url: 'delBuRegion.do',   
	    							   method: 'post',   
	    							   params: {sBuRegionIdList: sBuRegionIdList.join(',')},   
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
	    				Ext.MessageBox.alert('出错提示', '请选择要删除的地市信息！');
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

Ext.extend(Ext.BuRegionTopToolbar, Ext.Toolbar);