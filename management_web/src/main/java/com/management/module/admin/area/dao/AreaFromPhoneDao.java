package com.management.module.admin.area.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.area.AreaFromPhone;

/**
 * 地区号段管理持久层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Repository("AreaFromPhoneDao")
@Scope("prototype")
public class AreaFromPhoneDao extends HBaseDao<AreaFromPhone>
{
	/**
	 * 获取一个地区号段
	 * 
	 * @param nAreaFromPhoneId
	 * @return AreaFromPhone
	 */
	public AreaFromPhone getAreaFromPhone(Integer nAreaFromPhoneId)
	{
		return getById(nAreaFromPhoneId);
	}
	
	/**
	 * 通过号段获取地区
	 * @param sPhone
	 * @return List<AreaFromPhone>
	 */
	public List<AreaFromPhone> getAreaFromPhone(String sPhone)
	{
		return select("FROM AreaFromPhone WHERE sPhone=?", new Field().addStr(sPhone));
	}
	
	/**
	 * 获取地区号段列表
	 * 
	 * @param sPhone
	 * @return List<AreaFromPhone>
	 */
	public List<AreaFromPhone> getAreaFromPhoneList(String sPhone, int nStart, int nLimit)
	{
		return select("FROM AreaFromPhone WHERE sPhone LIKE ? ORDER BY nAreaFromPhoneId", new Field().addStr("%" + sPhone + "%"), nStart, nLimit);
	}
	
	/**
	 * 获取地区号段列表记录总数
	 * 
	 * @param sPhone
	 * @return Long
	 */
	public Long getAreaFromPhoneListTotal(String sPhone)
	{
		return getTotal("FROM AreaFromPhone WHERE sPhone LIKE ?",
				new Field().addStr("%" + sPhone + "%"));
	}
	
	/**
	 * 增加一个地区号段
	 * 
	 * @param oAreaFromPhone
	 * @return int
	 */
	public int addAreaFromPhone(AreaFromPhone oAreaFromPhone)
	{
		return insert(oAreaFromPhone);
	}
	
	/**
	 * 修改一个地区号段
	 * 
	 * @param oAreaFromPhone
	 * @return int
	 */
	public int editAreaFromPhone(AreaFromPhone oAreaFromPhone)
	{
		return update(oAreaFromPhone);
	}
	
	/**
	 * 删除一个地区号段
	 * 
	 * @param nAreaFromPhoneId
	 * @return int
	 */
	public int delAreaFromPhone(AreaFromPhone oAreaFromPhone)
	{
		return delete(oAreaFromPhone);
	}
	
}
