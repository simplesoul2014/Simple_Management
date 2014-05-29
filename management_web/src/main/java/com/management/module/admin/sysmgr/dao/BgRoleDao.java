package com.management.module.admin.sysmgr.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.sysmgr.BgCpRole;
import com.management.pojo.admin.sysmgr.BgRole;

/**
 * 后台角色管理持久层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Repository("BgRoleDao")
@Scope("prototype")
public class BgRoleDao extends HBaseDao<BgCpRole>
{
	/**
	 * 获取一个后台角色资料
	 * 
	 * @param nBgRoleId
	 * @return BgRole
	 */
	public BgCpRole getBgRole(int nBgRoleId)
	{
		return getById(new Integer(nBgRoleId));
	}
	
	/**
	 * 通过用户标识获取角色
	 * @param nBgUserId
	 * @return List<BgRole>
	 */
	public List<BgCpRole> getBgRoleList(String sUserId)
	{
		return select("SELECT A FROM BgCpRole AS A, BgUserLinkRole AS B WHERE A.nRoleId=B.nRoleId AND B.sUserId=?", new Field().addStr(sUserId));
		//return select("SELECT A FROM BgRole AS A, BgURR AS B WHERE A.nBgRoleId=B.nBgRoleId AND B.nBgUserId=?", new Field().addInt(nBgUserId));
	}
	
	/**
	 * 获取后台角色资料列表
	 * @param sName
	 * @return List<BgCpRole>
	 */
	public List<BgCpRole> getBgRoleList(String sName, int nStart, int nOffset)
	{
		return select("FROM BgCpRole WHERE sName LIKE ? ORDER BY sName DESC", new Field().addStr("%"+sName+"%"), nStart, nOffset);
	}
	
	/**
	 * 获取后台角色列表数量
	 * @param sName
	 * @return Long
	 */
	public Long getBgRoleListTotal(String sName)
	{
		return getTotal("FROM BgCpRole WHERE sName LIKE ?", new Field().addStr("%"+sName+"%"));
	}
	
	/**
	 * 获取后台角色资料列表
	 * @return List<BgRole>
	 */
	public List<BgCpRole> getBgRoleList()
	{
		return select("FROM BgCpRole");
	}
	
	/**
	 * 增加一个后台角色资料
	 * 
	 * @param oBgRole
	 * @return int
	 */
	public int addBgRole(BgCpRole oBgRole)
	{
		return insert(oBgRole);
	}
	
	/**
	 * 修改一个后台角色资料
	 * 
	 * @param oBgRole
	 * @return int
	 */
	public int editBgRole(BgCpRole oBgRole)
	{
		return update(oBgRole);
	}
	
	/**
	 * 删除一个后台角色资料
	 * 
	 * @param oBgRole
	 * @return int
	 */
	public int delBgRole(BgCpRole oBgRole)
	{
		return delete(oBgRole);
	}
}
