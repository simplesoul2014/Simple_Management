Ext.BLANK_IMAGE_URL = '../../../res/images/common/default/s.gif';

Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var oBgUserWindow = new Ext.EditMyBgUserWindow();
	oBgUserWindow.show();
});

Ext.override(Ext.form.RadioGroup, {
	setValue: function(v){
		this.eachItem(function(item){   
			item.setValue(item.getRawValue() == v);   
		});
	},
	getValue: function(){
		var value = 3;
		this.eachItem(function(item){
			if(item.checked){
				value = item.getRawValue();
				return false;
			}
		});

		return value;
	}
});