Ext.BuAttractionsTopToolbar = function(oGrid)
{
	var me = this;
	Ext.BuAttractionsTopToolbar.superclass.constructor.call(this, 
	{
        enableOverflow: true,
        items: [
        	{
        		xtype: 'tbtext', 
        		text: '景点名称：'
        	},
        	{
        		xtype: 'textfield'
        	},{xtype: 'tbspacer', width: 10},
        	{
        		xtype: 'tbtext', 
        		text: '地市名称：'
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
        			
        			var SAttractionsName = aComponents[0].getValue();
        			var Cityname = aComponents[1].getValue();
        			oGrid.SAttractionsName = SAttractionsName;
        			oGrid.Cityname = Cityname;
        			oGrid.getStore().load({params:{SAttractionsName:SAttractionsName, Cityname:Cityname}});
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
	        				oButton.Window = new Ext.ViewBuAttractionsWindow();
	        			}
	        			oButton.Window.NAttractionid=oRecord.data.nattractionid;
	        			if(!oButton.Window.isVisible()) oButton.Window.show(oButton.getEl());//show 为一种效果（扩大缩小）
	    			}else
	    			{
	    				Ext.MessageBox.alert('出错提示', '请选择要查看的景点信息资料！');
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
        				oButton.Window = new Ext.AddBuAttractionsWindow();
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
	        				oButton.Window = new Ext.EditBuAttractionsWindow();
	        			}
	        			oButton.Window.NAttractionid=oRecord.data.nattractionid;
	        			if(!oButton.Window.isVisible()) oButton.Window.show(oButton.getEl());
	    			}else
	    			{
	    				Ext.MessageBox.alert('出错提示', '请选择要修改的景点信息资料！');
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
	    				Ext.MessageBox.confirm('温馨提示', '真的要删除选择的景点信息资料吗？', function(buttonId, text, opt)
	    				{
	    					if(buttonId == 'yes')
	    					{
	    						var sBuAttractionsIslist = [0];
	    						for(var nKey in aRecord)
	    						{
	    							if(aRecord[nKey].data != undefined)
	    							{
	    								if(aRecord[nKey].data.nattractionid != 1) {
	    									sBuAttractionsIslist.push(aRecord[nKey].data.nattractionid);	
	    								}	    									
	    							}
	    						}

	    						Ext.Ajax.request({   
	    							   url: 'delBuAttractions.do',   
	    							   method: 'post',   
	    							   params: {sBuAttractionsIslist: sBuAttractionsIslist.join(',')},   
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
	    				Ext.MessageBox.alert('出错提示', '请选择要删除的景点信息！');
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

Ext.extend(Ext.BuAttractionsTopToolbar, Ext.Toolbar);