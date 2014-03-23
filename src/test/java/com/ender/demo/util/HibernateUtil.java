/**
 * @Project: hibernate-demo
 * @Title: HibernateUtil.java
 * @Package com.ender.demo.util
 * @Description: TODO
 * Copyright: Copyright (c) 2010 
 * Company:ENDER.crop
 * 
 * @author ender
 * @date 2014-2-16 下午5:44:48
 * @version V1.0.0
 */
package com.ender.demo.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @ClassName: HibernateUtil
 * @Description: TODO
 * @author ender
 * @date 2014-2-16 下午5:44:48
 *
 */
public class HibernateUtil {
	private static SessionFactory factory = null;
	static{
//		factory = new Configuration().configure().buildSessionFactory();
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.configure().build();
		factory = new Configuration().configure().buildSessionFactory(serviceRegistry);
	}
	
	public static Session openSession(){
		return factory.openSession();
	}
}
