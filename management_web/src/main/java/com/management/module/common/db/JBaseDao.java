package com.management.module.common.db;

import javax.annotation.Resource;

import com.core.db.IJDao;

/**
 * JDBC数据库抽象类
 * @Author 伍锐凡
 * @Date 2012-3-17
 * @Version 1.0
 * @Remark
 */
public abstract class JBaseDao
{
	@Resource(name = "UNIONCAST")
	protected IJDao m_oDB;
	
	protected IJDao getDB()
	{
		return m_oDB;
	}
}
