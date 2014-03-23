/**
 * @Project: hibernate-demo
 * @Title: Student.java
 * @Package com.ender.demo
 * @Description: TODO
 * Copyright: Copyright (c) 2010 
 * Company:ENDER.crop
 * 
 * @author ender
 * @date 2014-2-16 下午4:32:52
 * @version V1.0.0
 */
package com.ender.demo;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @ClassName: Student
 * @Description: TODO
 * @author ender
 * @date 2014-2-16 下午4:32:52
 *
 */
@Entity
@Table(name="t_student",catalog="hibernateTest")
public class Student {
	private int id;
	private String name;
	private String email;
	private String phone;
	private String major;
	private long studentNo;
	private int grade;
	
	private Set<Course> courses = new HashSet<Course>();
	
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * @return the studentNo
	 */
	public long getStudentNo() {
		return studentNo;
	}
	/**
	 * @param studentNo the studentNo to set
	 */
	public void setStudentNo(long studentNo) {
		this.studentNo = studentNo;
	}
	/**
	 * @return the grade
	 */
	public int getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}
	/**
	 * @return the courses
	 */
	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	@JoinTable(name="student_course",joinColumns={@JoinColumn(name="student_id",
	referencedColumnName="id")},inverseJoinColumns={@JoinColumn(name="course_id",
	referencedColumnName="id")})
	public Set<Course> getCourses() {
		return courses;
	}
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	@Override
	public String toString(){
		StringBuffer strBuffer = new StringBuffer("Student:{"); 
		try{
			Field[] fields = this.getClass().getDeclaredFields();
			for(Field field : fields){
				System.out.println(field.getName());
				if(field.getName().equalsIgnoreCase("courses")||field.getName().equalsIgnoreCase("teacher")){
					System.out.println(field.getName());
					strBuffer.append("["+field.getName()+":"+field.get(this).getClass().getField("id")+"]");
				}
				else if(field.getType().isAssignableFrom(Course.class)||field.getType().isAssignableFrom(Teacher.class)){
					strBuffer.append("["+field.getName()+":"+field.get(this).getClass().getField("id")+"]");
				}else{
					strBuffer.append("["+field.getName()+":"+field.get(this).toString()+"]");
				}
			}
		}catch(Exception e){
			
		}
		
		strBuffer.append("}");
		
		return strBuffer.toString();
	}
	
}
