package com.management.module.admin.sysmgr.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.sysmgr.BgRRT;
import com.management.pojo.admin.sysmgr.BgRoleLinkPower;

/**
 * 后台角色与树结点关联管理持久层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Repository("BgRRTDao")
@Scope("prototype")
public class BgRRTDao extends HBaseDao<BgRoleLinkPower>
{
	
	/**
	 * 获取关联资料列表
	 * @param nBgRoleId
	 * @return List<BgRRT>
	 */
	public List<BgRoleLinkPower> getBgRRTList(int nBgRoleId)
	{
		return select("FROM BgRoleLinkPower WHERE nRoleId=?", new Field().addInt(nBgRoleId));
		//return select("FROM BgRRT WHERE nBgRoleId=? ORDER BY nBgRRTId DESC", new Field().addInt(nBgRoleId));
	}
	
	/**
	 * 增加关联资料
	 * 
	 * @param oBgRRT
	 * @return int
	 */
	public int addBgRRT(BgRoleLinkPower oBgRRT)
	{
		return insert(oBgRRT);
	}
	
	/**
	 * 删除关联
	 * @param nBgRoleId
	 * @return int
	 */
	public int delBgRRT(int nBgRoleId)
	{
		return delete("DELETE FROM BgRoleLinkPower WHERE nRoleId=?", new Field().addInt(nBgRoleId));
	}
}
