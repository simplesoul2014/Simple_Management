package com.management.module.admin.sysmgr.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.sysmgr.BgCpUserDetail;

 
/**
 * 后台用户管理持久层
 * 
 * @Author 姚林勇
 * @Date 2012-1-4
 * @Version 1.0
 * @Remark
 */
@Repository("BgCpUserDetailDao")
@Scope("prototype")
public class BgCpUserDetailDao extends HBaseDao<BgCpUserDetail> {
	/**
	 * 获取一个后台用户资料
	 * 
	 * @param nBgUserId
	 * @return BgUser
	 */
	public BgCpUserDetail getBgCpUserDetail(String sUserId)
	{
		return getById(sUserId);
	}
	/**
	 * 修改一个后台用户资料
	 * 
	 * @param oBgCpUserDetail
	 * @return int
	 */
	public int editBgUser(BgCpUserDetail oBgCpUserDetail)
	{
		return update(oBgCpUserDetail);
	}
	/**
	 * 增加一个后台用户资料
	 * 
	 * @param oBgUser
	 * @return int
	 */
	public int addBgUser(BgCpUserDetail oBgUser)
	{
		return insert(oBgUser);
	}
	
}
