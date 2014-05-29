package com.management.module.admin.sysmgr.dao;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.sysmgr.BgCpUser;
import com.management.pojo.admin.sysmgr.BgCpUserDetail;

/**
 * 后台用户管理持久层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Repository("BgUserDao")
@Scope("prototype")
public class BgUserDao extends HBaseDao<BgCpUser>
{
	/**
	 * 获取一个后台用户资料
	 * 
	 * @param nBgUserId
	 * @return BgUser
	 */
	public BgCpUser getBgUser(int nBgUserId)
	{
		return getById(new Integer(nBgUserId));
	}
	
	/**
	 * 获取一个后台用户资料
	 * 
	 * @param nBgUserId
	 * @return BgUser
	 */
	public BgCpUser getBgUserById(String sUserId)
	{
		return getById(sUserId);
	}
	
	/**
	 * 获取一个后台用户资料
	 * 
	 * @param nBgUserId
	 * @return BgUser
	 */
	public List getCpUser(String sBgUserId)
	{
		List aBgUser = select(
				"FROM BgCpUser b,BgCpUserDetail d WHERE b.sUserId=d.sUserId AND b.sUserId=?", new Field()
						.addStr(sBgUserId));
		return (aBgUser != null && aBgUser.size() > 0) ? aBgUser : null;
	}
	
	/**
	 * 通过帐号和密码获取后台用户资料
	 * 
	 * @param sAccount
	 * @param sPassword
	 * @return BgCpUser
	 */
	public BgCpUser getBgCpUser(String sAccount, String sPassword)
	{ //"FROM BgUser WHERE sAccount=? AND sPassword=?", new Field()
		Date nowDay=new Date();
		List<BgCpUser> aBgUser = select(
				"FROM BgCpUser WHERE sUserName=? AND sPassword=?", new Field()
						.addStr(sAccount).addStr(sPassword));
		return (aBgUser != null && aBgUser.size() > 0) ? aBgUser.get(0) : null;
	}
	
	/**
	 * 通过帐号获取用户资料
	 * @param sAccount
	 * @return BgUser
	 */
	public BgCpUser getBgUser(String sAccount)
	{
		/*List<BgCpUser> aBgUser = select(
				"FROM BgCpUser WHERE sUserName=?", new Field()
						.addStr(sAccount));
		return (aBgUser != null && aBgUser.size() > 0) ? aBgUser.get(0) : null;*/
		return getById(sAccount);
	}
	/**
	 * 获取后台用户资料列表
	 * @param nBgRoleId
	 * @param sName
	 * @param sAccount
	 * @param nStart
	 * @param nOffset
	 * @return List<BgUser>
	 */
	public List<BgCpUser> getBgUserList(Integer nBgRoleId, String sName, String sAccount,
			int nStart, int nOffset)
	{
		Field oField = new Field();
		if(nBgRoleId != 0) oField.addInt(nBgRoleId);
		String sHQL = nBgRoleId == 0 ? 
				//"FROM BgCpUser AS A,BgCpUserDetail AS B WHERE A.sUserId=B.sUserId AND A.sUserName LIKE ? AND A.sUserId LIKE ?" 
				"FROM BgCpUser  WHERE  sUserName LIKE ? AND sUserId LIKE ?" 
				: "SELECT A FROM BgCpUser AS A, BgUserLinkRole AS B, BgCpUserDetail AS C WHERE A.sUserId=C.sUserId AND A.sUserId=B.sUserId AND B.nRoleId=? AND A.sUserName LIKE ? AND A.sUserId LIKE ?";
			//	: "SELECT A FROM BgCpUser AS A, BgURR AS B WHERE A.nBgUserId=B.nBgUserId AND B.nBgRoleId=? AND A.sName LIKE ? AND A.sAccount LIKE ? ORDER BY A.nBgUserId DESC";
		           
		return select(
				sHQL,
				oField.addStr("%" + sName + "%").addStr(
						"%" + sAccount + "%"), nStart, nOffset);
	}
	
	/**
	 * 获取后台用户列表总数
	 * @param nBgRoleId
	 * @param sName
	 * @param sAccount
	 * @return Long
	 */
	public Long getBgUserListTotal(Integer nBgRoleId, String sName, String sAccount)
	{
		Field oField = new Field();
		if(nBgRoleId != 0) oField.addInt(nBgRoleId);
		String sHQL = nBgRoleId == 0 ? 
				"FROM BgCpUser WHERE sUserName LIKE ? AND sUserId LIKE ?" 
				: "SELECT A FROM BgCpUser AS A, BgUserLinkRole AS B WHERE A.sUserId=B.sUserId AND B.nRoleId=? AND A.sUserName LIKE ? AND A.sUserId LIKE ?";
				// : "FROM BgUser AS A, BgURR AS B WHERE A.nBgUserId=B.nBgUserId AND B.nBgRoleId=? AND A.sName LIKE ? AND A.sAccount LIKE ?";
		return getTotal(
				sHQL,
				oField.addStr("%" + sName + "%").addStr(
						"%" + sAccount + "%"));
	}
	
	/**
	 * 增加一个后台用户资料
	 * 
	 * @param oBgUser
	 * @return int
	 */
	public int addBgUser(BgCpUser oBgUser)
	{
		return insert(oBgUser);
	}
	
	/**
	 * 修改一个后台用户资料
	 * 
	 * @param oBgUser
	 * @return int
	 */
	public int editBgUser(BgCpUser oBgUser)
	{
		return update(oBgUser);
	}
	
	/**
	 * 修改用户部门名称
	 * @param nBgDeptId
	 * @param sDeptName
	 * @return int
	 */
	public int editDeptName(Integer nBgDeptId, String sDeptName)
	{
		return update("UPDATE BgUser SET sDeptName=? WHERE nBgDeptId=?", new Field().addStr(sDeptName).addInt(nBgDeptId));
	}
	
	/**
	 * 删除用户部门
	 * @param nBgDeptId
	 * @return int
	 */
	public int delDeptName(Integer nBgDeptId)
	{
		return update("UPDATE BgUser SET nBgDeptId=0,sDeptName='' WHERE nBgDeptId=?", new Field().addInt(nBgDeptId));
	}
	
	/**
	 * 删除用户列表
	 * 
	 * @param sBgUserIdList，ID之间用逗号隔开
	 * @return int
	 */
	public int delBgUserList(String sBgUserIdList)
	{
		int flag = delete("DELETE BgCpUser WHERE sUserId IN ("+sBgUserIdList+") AND sUserId!=1");
		if(flag>0){
			flag=delete("DELETE BgCpUserDetail WHERE sUserId IN ("+sBgUserIdList+") AND sUserId!=1");
		}
		return flag;
	}
}
