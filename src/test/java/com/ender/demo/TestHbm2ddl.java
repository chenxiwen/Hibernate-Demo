/**
 * @Project: hibernate-demo
 * @Title: TestHbm2ddl.java
 * @Package com.ender.demo
 * @Description: TODO
 * Copyright: Copyright (c) 2010 
 * Company:ENDER.crop
 * 
 * @author ender
 * @date 2014-2-16 下午5:38:12
 * @version V1.0.0
 */
package com.ender.demo;

import junit.framework.Assert;

import org.hibernate.Session;
import org.junit.Test;

import com.ender.demo.util.HibernateUtil;

/**
 * @ClassName: TestHbm2ddl
 * @Description: TODO
 * @author ender
 * @date 2014-2-16 下午5:38:12
 *
 */
public class TestHbm2ddl {
	@Test
	public void testAddStudent(){
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Student student1 = new Student();
		student1.setName("chenxiwen");
		student1.setPhone("13366409169");
		student1.setEmail("chenxiwenender@163.com");
		student1.setMajor("software");
		student1.setGrade(1);
		student1.setStudentNo(0620010601);
		
		session.save(student1);
		
		Assert.assertTrue(student1.getId()>0);
		session.delete(student1);
		
		session.getTransaction().commit();
		session.flush();
		session.clear();
		session.close();
		
	}
	
}
