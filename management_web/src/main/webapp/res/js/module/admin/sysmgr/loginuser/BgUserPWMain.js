Ext.BLANK_IMAGE_URL = '../../../res/images/common/default/s.gif';

Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var oBgUserWindow = new Ext.EditMyBgUserWindow();
	oBgUserWindow.show();
});
