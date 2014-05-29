package com.management.module.admin.area.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.core.util.Tools;
import com.management.module.admin.area.dao.AreaFromPhoneDao;
import com.management.pojo.admin.area.AreaFromPhone;

/**
 * 地区号段管理逻辑层
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Service("AreaFromPhoneService")
@Scope("prototype")
public class AreaFromPhoneService
{
	@Resource(name = "AreaFromPhoneDao")
	private AreaFromPhoneDao m_oAreaFromPhoneDao;
	
	/**
	 * 获取一个地区号段
	 * @param nAreaFromPhoneId
	 * @return AreaFromPhone
	 */
	public AreaFromPhone getAreaFromPhone(Integer nAreaFromPhoneId)
	{
		return m_oAreaFromPhoneDao.getAreaFromPhone(nAreaFromPhoneId);
	}
	
	/**
	 * 通过号段获取地区
	 * @param sPhone
	 * @return List<AreaFromPhone>
	 */
	public List<AreaFromPhone> getAreaFromPhone(String sPhone)
	{
		if(checkMobile(sPhone)) 
		{
			sPhone = sPhone.substring(0, 7);
			return m_oAreaFromPhoneDao.getAreaFromPhone(sPhone);
		}
		return null;
	}	
	
	/**
	 * 获取地区号段列表
	 * @param sPhone
	 * @return List<AreaFromPhone>
	 */
	public List<AreaFromPhone> getAreaFromPhoneList(String sPhone, int nStart, int nLimit)
	{
		return m_oAreaFromPhoneDao.getAreaFromPhoneList(sPhone, nStart, nLimit);
	}
	public Long getAreaFromPhoneListTotal(String sPhone)
	{
		return m_oAreaFromPhoneDao.getAreaFromPhoneListTotal(sPhone);
	}
	
	/**
	 * 增加一个地区号段
	 * @param sPhone
	 * @param nAreaId
	 * @param sAreaName
	 * @param sDesc
	 * @param nStatus
	 * @return int
	 */
	public int addAreaFromPhone(String sPhone, Integer nAreaId, String sAreaName, String sDesc, Integer nStatus)
	{
		AreaFromPhone oAreaFromPhone = new AreaFromPhone();
		oAreaFromPhone.setPhone(sPhone);
		oAreaFromPhone.setAreaId(nAreaId);
		oAreaFromPhone.setAreaName(sAreaName);
		oAreaFromPhone.setDesc(sDesc);
		oAreaFromPhone.setStatus(nStatus);

		return m_oAreaFromPhoneDao.addAreaFromPhone(oAreaFromPhone);
	}
	
	/**
	 * 修改一个地区号段
	 * @param nAreaFromPhoneId
	 * @param sPhone
	 * @param nAreaId
	 * @param sAreaName
	 * @param sDesc
	 * @param nStatus
	 * @return int
	 */
	public int editAreaFromPhone(int nAreaFromPhoneId, String sPhone, Integer nAreaId, String sAreaName, String sDesc, Integer nStatus)
	{
		AreaFromPhone oAreaFromPhone = getAreaFromPhone(nAreaFromPhoneId);
		oAreaFromPhone.setPhone(sPhone);
		oAreaFromPhone.setAreaId(nAreaId);
		oAreaFromPhone.setAreaName(sAreaName);
		oAreaFromPhone.setDesc(sDesc);
		oAreaFromPhone.setStatus(nStatus);
		return m_oAreaFromPhoneDao.editAreaFromPhone(oAreaFromPhone);
	}
	
	/**
	 * 删除一个地区号段
	 * @param nAreaFromPhoneId
	 * @return int
	 */
	public int delAreaFromPhone(Integer nAreaFromPhoneId)
	{
		AreaFromPhone oAreaFromPhone = getAreaFromPhone(nAreaFromPhoneId);
		return m_oAreaFromPhoneDao.delAreaFromPhone(oAreaFromPhone);
	}
	
	private boolean checkMobile(String sMobile)
	{
		if(sMobile == null || sMobile.isEmpty()) return false;
		//移动号码
		if(Tools.checkMather(sMobile, "^1(3[4-9]|5[012789]|8[78])\\d{8}$")) return true;
		//电信号码
		if(Tools.checkMather(sMobile, "^18[09]\\d{8}$")) return true;
		//联通号码
		if(Tools.checkMather(sMobile, "^1(3[0-2]|5[56]|8[56])\\d{8}$")) return true;
		//CDMA
		if(Tools.checkMather(sMobile, "^1[35]3\\d{8}$")) return true;
		return false;
	}
	
}
