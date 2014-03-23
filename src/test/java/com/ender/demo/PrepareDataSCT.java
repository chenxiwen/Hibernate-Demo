/**
 * @Project: hibernate-demo
 * @Title: PrepareDataSCT.java
 * @Package com.ender.demo
 * @Description: TODO
 * Copyright: Copyright (c) 2010 
 * Company:ENDER.crop
 * 
 * @author ender
 * @date 2014-2-23 上午11:30:57
 * @version V1.0.0
 */
package com.ender.demo;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;

import com.ender.demo.util.HibernateUtil;

/**
 * @ClassName: PrepareDataSCT
 * @Description: TODO
 * @author ender
 * @date 2014-2-23 上午11:30:57
 *
 */
public class PrepareDataSCT {
	
	@Test
	public void prepareData(){
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
		calendar.set(2011, Calendar.MAY, 18);
		course1.setStartDate(calendar.getTime());
		calendar.set(2011, Calendar.NOVEMBER, 3);
		course1.setStopDate(calendar.getTime());
		Set<Student> set = new HashSet<Student>();
		set.add(student1);                  //由课程来添加学生，学生那边目前不会维护课程
		set.add(student2);
		course1.setTeacher(teacher1);    //这里 课程和教师不应该相互管理，应该交给一方来管理，多对一的话就应该是交给1的那方  也就是teacher
		course1.setStudents(set);		
		
		
		Course course2 = new Course();
		course2.setName("课程2");
		calendar.set(2012, Calendar.MAY, 18);
		course2.setStartDate(calendar.getTime());
		calendar.set(2012, Calendar.NOVEMBER, 3);
		course2.setStopDate(calendar.getTime());
		Set<Student> set2 = new HashSet<Student>();
		set2.add(student1);
		set2.add(student2);
		course2.setTeacher(teacher2);
		course2.setStudents(set2);
		
		Course course3 = new Course();
		course3.setName("课程3");
		calendar.set(2012, Calendar.MAY, 18);
		course3.setStartDate(calendar.getTime());
		calendar.set(2012, Calendar.NOVEMBER, 3);
		course3.setStopDate(calendar.getTime());
		Set<Student> set3 = new HashSet<Student>();
		set3.add(student1);
		set3.add(student2);
		course3.setTeacher(teacher2);
		course3.setStudents(set3);
		

		//给教师安排课程
//		Set<Course> teacher1Courses = new HashSet<Course>();
//		teacher1Courses.add(course1);
//		teacher1Courses.add(course3);
//		teacher1.setCourses(teacher1Courses);
//		
//		Set<Course> teacher2Courses = new HashSet<Course>();
//		teacher2Courses.add(course2);
//		teacher2.setCourses(teacher2Courses);
		
		session.saveOrUpdate(teacher1);
		session.saveOrUpdate(teacher2);
		session.saveOrUpdate(course1);
		session.saveOrUpdate(course2);
		session.saveOrUpdate(course3);
		session.saveOrUpdate(student1);
		session.saveOrUpdate(student2);
		
		session.getTransaction().commit();
		session.flush();
		session.clear();
		session.close();
		
	}
}
