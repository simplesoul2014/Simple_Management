package com.management.module.admin.sysmgr.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.sysmgr.BgURP;

/**
 * 后台岗位与用户关联管理持久层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Repository("BgURPDao")
@Scope("prototype")
public class BgURPDao extends HBaseDao<BgURP>
{
	
	/**
	 * 获取关联资料列表
	 * @param nBgUserId
	 * @return List<BgURP>
	 */
	public List<BgURP> getBgURPList(int nBgUserId)
	{
		return select("FROM BgURP WHERE nBgUserId=? ORDER BY nBgURPId DESC", new Field().addInt(nBgUserId));
	}
	
	/**
	 * 获取关联列表
	 * @param sBgPostIdList
	 * @return List<BgURP>
	 */
	public List<BgURP> getBgURPList(String sBgPostIdList)
	{
		return select("FROM BgURP WHERE nBgPostId IN ("+sBgPostIdList+")");
	}
	
	/**
	 * 获取关联
	 * @param nBgPostId
	 * @param nBgUserId
	 * @return BgURP
	 */
	public BgURP getBgURP(Integer nBgPostId, Integer nBgUserId)
	{
		List<BgURP> aBgURP = select("FROM BgURP WHERE nBgPostId=? AND nBgUserId", new Field().addInt(nBgPostId).addInt(nBgUserId));
		return aBgURP != null && aBgURP.size() > 0 ? aBgURP.get(0) : null;
	}
	
	/**
	 * 增加关联资料
	 * 
	 * @param oBgURP
	 * @return int
	 */
	public int addBgURP(BgURP oBgURP)
	{
		return insert(oBgURP);
	}
	
	/**
	 * 删除关联
	 * @param sBgUserId
	 * @return int
	 */
	public int delBgURPByUserId(String sBgUserId)
	{
		return delete("DELETE FROM BgURP WHERE nBgUserId IN ("+sBgUserId+")");
	}
	public int delBgURPByPostId(String sBgPostId)
	{
		return delete("DELETE FROM BgURP WHERE nBgPostId IN ("+sBgPostId+")");
	}
}
