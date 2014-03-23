/**
 * @Project: hibernate-demo
 * @Title: TestOORelation.java
 * @Package com.ender.demo
 * @Description: TODO
 * Copyright: Copyright (c) 2010 
 * Company:ENDER.crop
 * 
 * @author ender
 * @date 2014-2-16 下午9:53:25
 * @version V1.0.0
 */
package com.ender.demo;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.ender.demo.util.HibernateUtil;

/**
 * @ClassName: TestOORelation
 * @Description: TODO
 * @author ender
 * @date 2014-2-16 下午9:53:25
 *
 */
public class TestOORelation {
	
	@Test
	public void testStudentCourse(){  //many to many
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Student student1 = new Student();
		student1.setName("chenxiwen");
		student1.setPhone("13366409169");
		student1.setEmail("chenxiwenender@163.com");
		student1.setMajor("software");
		student1.setGrade(1);
		student1.setStudentNo(new Long("0620010601"));
		
		Student student2 = new Student();
		student2.setName("wangxinru");
		student2.setPhone("15142855318");
		student2.setEmail("wangxinru@163.com");
		student2.setMajor("software");
		student2.setGrade(1);
		student2.setStudentNo(new Long("0920010601"));
		
		Course course1 = new Course();
		course1.setName("课程1");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2011, Calendar.MAY, 18);
		course1.setStartDate(calendar.getTime());
		calendar.set(2011, Calendar.NOVEMBER, 3);
		course1.setStopDate(calendar.getTime());
		Set<Student> set = new HashSet<Student>();
		set.add(student1);
		set.add(student2);
		course1.setStudents(set);
		
		Course course2 = new Course();
		course2.setName("课程2");
		//Calendar calendar = Calendar.getInstance();
		calendar.set(2012, Calendar.MAY, 18);
		course2.setStartDate(calendar.getTime());
		calendar.set(2012, Calendar.NOVEMBER, 3);
		course2.setStopDate(calendar.getTime());
		Set<Student> set2 = new HashSet<Student>();
		set2.add(student1);
		set2.add(student2);
		course2.setStudents(set2);
		
		session.save(student1);
		session.save(student2);
		session.save(course1);
		session.save(course2);
		
		session.getTransaction().commit();
		session.flush();
		session.clear();
		session.close();
	}
	
	@Test
	public void testCourseTeacher(){  //many to one  course(n)  teacher(1)
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Calendar calendar = Calendar.getInstance();
		
		Teacher teacher1 = new Teacher();
		teacher1.setName("ender");
		teacher1.setTitle("senior");
		calendar.set(1987, Calendar.NOVEMBER, 3);
		teacher1.setBirthday(calendar.getTime());
		teacher1.setEmail("chenxiwenender@163.com");
		teacher1.setPhone("13366409169");
		
		Teacher teacher2 = new Teacher();
		teacher2.setName("zzh");
		teacher2.setTitle("junior");
		calendar.set(1989, Calendar.APRIL, 7);
		teacher2.setBirthday(calendar.getTime());
		teacher2.setEmail("chenxiwenender@163.com");
		teacher2.setPhone("13366409169");
		
		Course course1 = new Course();
		course1.setName("课程1");
		
		calendar.set(2011, Calendar.MAY, 18);
		course1.setStartDate(calendar.getTime());
		calendar.set(2011, Calendar.NOVEMBER, 3);
		course1.setStopDate(calendar.getTime());
		Set<Student> set = new HashSet<Student>();
		course1.setTeacher(teacher1);
		course1.setStudents(set);		
		
		
		Course course2 = new Course();
		course2.setName("课程2");
		//Calendar calendar = Calendar.getInstance();
		calendar.set(2012, Calendar.MAY, 18);
		course2.setStartDate(calendar.getTime());
		calendar.set(2012, Calendar.NOVEMBER, 3);
		course2.setStopDate(calendar.getTime());
		Set<Student> set2 = new HashSet<Student>();
		course2.setTeacher(teacher2);
		course2.setStudents(set2);
		
		Set<Course> teacher1Courses = new HashSet<Course>();
		teacher1Courses.add(course1);
		teacher1Courses.add(course2);
		teacher1.setCourses(teacher1Courses);
		
		Set<Course> teacher2Courses = new HashSet<Course>();
		teacher2Courses.add(course1);
		teacher2Courses.add(course2);
		teacher2.setCourses(teacher2Courses);
		
		session.saveOrUpdate(teacher1);
		session.saveOrUpdate(teacher2);
		session.saveOrUpdate(course1);
		session.saveOrUpdate(course2);
		
		session.getTransaction().commit();
		session.flush();
		session.clear();
		session.close();
	}
	
	
	public boolean resetDB(Session session){
		Query query = session.createQuery("drop database if exists hibernateTest");
		int result = query.executeUpdate();	//返回值是int型，表示改变数据库记录的条数
		if(result>0){   //drop database successed
			String sql = "create database if not exists hibernateTest";
			Query query_create = session.createSQLQuery(sql);
			int result_create = query_create.executeUpdate();
			System.out.println("result_create:"+result_create);
		}
		
		Query query_databases = session.createSQLQuery("show databases");
		List<String> databasesList = (List<String>)query_databases.list();
		if(databasesList.contains("hibernateTest")){
			Query useDatabase = session.createSQLQuery("use hibernateTest");
			if(useDatabase.executeUpdate()>0){
				Query query_tables = session.createSQLQuery("show tables");
				return query_tables.list().size()==0?true:false;
			}
		}
		
		return false;
	}
	
	
}
