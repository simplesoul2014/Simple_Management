package com.management.util;

/**
 * 地市编码
 * @author huyh
 *
 */
public enum AreaEnum {
	
	GZ("01"),SZ("02"),DG("03"),FS("04"),ZH("05"),ST("06"),SG("07"),HY("08"),
	MZ("09"),HZ("10"),SW("11"),ZS("12"),JM("13"),YJ("14"),ZJ("15"),MM("16"),
	ZQ("17"),CZ("18"),JY("19"),YF("20"),QY("21"),
	GZC("01"),SZC("02"),DGC("03"),FSC("04"),ZHC("05"),STC("06"),SGC("07"),
	HYC("08"),MZC("09"),HZC("10"),SWC("11"),ZSC("12"),JMC("13"),YJC("14"),
	ZJC("15"),MMC("16"),ZQC("17"),CZC("18"),JYC("19"),YFC("20"),QYC("21");
	
	private String areaCode;
	
	AreaEnum(String areaCode){
		this.areaCode=areaCode;
	}
	
	public String getAreaCode(){
		return areaCode;
	}
	public static AreaEnum getArea(String Area){    
		return valueOf(Area);    
	}     
}
