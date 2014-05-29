package com.management.module.admin.sysmgr.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.sysmgr.BgPost;

/**
 * 后台岗位管理持久层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Repository("BgPostDao")
@Scope("prototype")
public class BgPostDao extends HBaseDao<BgPost>
{
	/**
	 * 获取一个岗位资料
	 * 
	 * @param nBgPostId
	 * @return BgPost
	 */
	public BgPost getBgPost(int nBgPostId)
	{
		return getById(new Integer(nBgPostId));
	}
	
	/**
	 * 获取岗位列表
	 * @param nBgUserId
	 * @return List<BgPost>
	 */
	public List<BgPost> getBgPostList(Integer nBgUserId)
	{
		return select("SELECT A FROM BgPost AS A, BgURP AS B WHERE A.nBgPostId=B.nBgPostId AND B.nBgUserId=?", new Field().addInt(nBgUserId));
	}
	
	/**
	 * 获取后台岗位资料列表
	 * 
	 * @param sName
	 * @param sDeptName
	 * @param nStart
	 * @param nOffset
	 * @return List<BgPost>
	 */
	public List<BgPost> getBgPostList(String sName, String sDeptName,
			int nStart, int nOffset)
	{
		return select(
				"FROM BgPost WHERE sName LIKE ? AND NVL(sDeptName, ' ') LIKE ? ORDER BY nBgPostId DESC",
				new Field().addStr("%" + sName + "%").addStr(
						"%" + sDeptName + "%"), nStart, nOffset);
	}
	
	/**
	 * 获取后台岗位列表总数
	 * 
	 * @param sName
	 * @param sDeptName
	 * @return Long
	 */
	public Long getBgPostListTotal(String sName, String sDeptName)
	{
		return getTotal(
				"FROM BgPost WHERE sName LIKE ? AND NVL(sDeptName, ' ') LIKE ?",
				new Field().addStr("%" + sName + "%").addStr(
						"%" + sDeptName + "%"));
	}
	
	/**
	 * 增加一个后台岗位资料
	 * 
	 * @param oBgPost
	 * @return int
	 */
	public int addBgPost(BgPost oBgPost)
	{
		return insert(oBgPost);
	}
	
	/**
	 * 修改一个后台岗位资料
	 * 
	 * @param oBgPost
	 * @return int
	 */
	public int editBgPost(BgPost oBgPost)
	{
		return update(oBgPost);
	}
	
	/**
	 * 删除岗位列表
	 * 
	 * @param sBgPostIdList，ID之间用逗号隔开
	 * @return int
	 */
	public int delBgPostList(String sBgPostIdList)
	{
		return delete("DELETE BgPost WHERE nBgPostId IN ("+sBgPostIdList+")");
	}
}
