package com.management.module.admin.sysmgr.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.sysmgr.BgURR;
import com.management.pojo.admin.sysmgr.BgUserLinkRole;

/**
 * 后台角色与用户关联管理持久层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Repository("BgURRDao")
@Scope("prototype")
public class BgURRDao extends HBaseDao<BgUserLinkRole>
{
	
	/**
	 * 获取关联资料列表
	 * @param nBgRoleId
	 * @return List<BgURR>
	 */
	public List<BgUserLinkRole> getBgURRList(int nBgRoleId)
	{
		return select("FROM BgUserLinkRole WHERE nRoleId=? ", new Field().addInt(nBgRoleId));
	}
	
	/**
	 * 获取关联
	 * @param nBgRoleId
	 * @param nBgUserId
	 * @return BgURR
	 */
	public BgUserLinkRole getBgURR(Integer nBgRoleId, String sUserId)
	{
		List<BgUserLinkRole> aBgURR = select("FROM BgUserLinkRole WHERE nRoleId=? AND sUserId=?", new Field().addInt(nBgRoleId).addStr(sUserId));
		return aBgURR != null && aBgURR.size() > 0 ? aBgURR.get(0) : null;
	}
	
	/**
	 * 增加关联资料
	 * 
	 * @param oBgURR
	 * @return int
	 */
	public int addBgURR(BgUserLinkRole oBgURR)
	{
		return insert(oBgURR);
	}
	
	/**
	 * 删除关联
	 * @param nBgRoleId
	 * @return int
	 */
	public int delBgURRByRoleId(int nBgRoleId)
	{
		return delete("DELETE FROM BgUserLinkRole WHERE nRoleId=?", new Field().addInt(nBgRoleId));
	}
	public int delBgURRByUR(String sUserId, int nBgRoleId)
	{
		return delete("DELETE FROM BgUserLinkRole WHERE sUserId IN ("+sUserId+") AND nRoleId=?", new Field().addInt(nBgRoleId));
	}
	public int delBgURRByUser(String sUserId)
	{
		return delete("DELETE FROM BgUserLinkRole WHERE sUserId IN ("+sUserId+")");
	}
}
