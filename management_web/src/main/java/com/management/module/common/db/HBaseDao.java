package com.management.module.common.db;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.core.db.HDaoImpl;

/**
 * Hibernate数据库抽象类
 * @Author 伍锐凡
 * @Date 2012-3-15
 * @Version 1.0
 * @Remark 
 * @param <T>
 */
public abstract class HBaseDao<T> extends HDaoImpl<T>
{
	@Autowired
    @Qualifier("sessionFactory")
	@Override
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
}
