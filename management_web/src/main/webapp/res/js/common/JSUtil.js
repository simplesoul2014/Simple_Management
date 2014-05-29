(function(){
	JSUtil = function(){};
	
	JSUtil.prototype = {
		//验证Email是否合法
		isEmail : function(sEmail)
		{
			if(this.isEmpty(sEmail)) return false;
			return/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(sEmail);
		},
		//验证字符串是否为空
		isEmpty : function(sStr)
		{
			return (sStr === null || sStr === undefined || this.trim(sStr) === '') ? true : false;
		},
		//清除字符串两边的空格
		trim : function(sStr)
		{
			return sStr.replace(/(^\s*)|(\s*$)/g, '').replace(/(^　*)|(　*$)/g, '');
		},
		//是否是数字
		isNumber : function(sStr)
		{
			return /^\d+$/.test(sStr);
		},
		//两值是否相等
		isEquals : function(sStr1, sStr2)
		{
			return sStr1 == sStr2;
		},
		//是否为YYYY-MM-DD格式的日期
		isDate : function(sStr)
		{
			return /^\d{4}\-[0-1]{0,1}\d{1}\-[0-3]{0,1}\d{1}$/.test(sStr);
		},
		//是否只有字符、数字和_
		isAccount : function(sStr)
		{
			return /^[0-0a-zA-Z_]+$/.test(sStr);
		},
		//判断是否为手机号码
		isMobile : function(sMobile)
		{
			return /^13\d{9}$/g.test(sMobile)||/^15[0-35-9]\d{8}$/g.test(sMobile)||/^18[05-9]\d{8}$/g.test(sMobile);
		},
		//把字符转换成整数
		toInt : function(sValue)
		{
			return paseInt(sValue, 10);
		},
		toFloat : function(sValue)
		{
			return parseFloat(sValue, 10);
		},
		//打开窗口
		openWin : function(sUrl, nWidth, nHeight)
		{
		    var nTop = (window.screen.availHeight - 30 - nHeight)/2;        
		    var nLeft = (window.screen.availWidth - 10 - nWidth)/2;           
		    window.open(sUrl, "", 'height='+nHeight+',innerHeight='+nHeight+',width='+nWidth+',innerWidth='+nWidth+',top='+nTop+',left='+nLeft+',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
		}
	};
})();