/**
 * @Project: hibernate-demo
 * @Title: TestFind.java
 * @Package com.ender.demo
 * @Description: TODO
 * Copyright: Copyright (c) 2010 
 * Company:ENDER.crop
 * 
 * @author ender
 * @date 2014-2-22 下午10:52:21
 * @version V1.0.0
 */
package com.ender.demo;

import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.ender.demo.util.HibernateUtil;

/**
 * @ClassName: TestFind
 * @Description: TODO
 * @author ender
 * @date 2014-2-22 下午10:52:21
 *
 */
public class TestFind {
	
	@Test
	public void getTeachers(){
		Session session = HibernateUtil.openSession();
//		session.beginTransaction();
		
		String hql = "from Teacher";
		Query query = session.createQuery(hql);
		List<Teacher> list = query.list();
		for(Teacher teacher : list){
//			System.out.println(teacher.getId());
			System.out.println(teacher.toString());
		}
		System.out.println("*************************************************");
		session.close();
	}
	@Test
	public void getTeacherById(){
		Session session = HibernateUtil.openSession();
		
		String hql = "from Teacher t where t.id=:id";
		Query query = session.createQuery(hql);
		query.setInteger("id", 2);
		Teacher teacher = (Teacher)query.uniqueResult();
		System.out.println(teacher.toString());
//		Set<Course> set = teacher.getCourses();
//		for(Course course : set){
////			System.out.println(course.getId());
//			System.out.println(course.toString());
//		}
		System.out.println("*************************************************");
		Assert.assertEquals(2, teacher.getId());
		
		session.close();
	}
}
