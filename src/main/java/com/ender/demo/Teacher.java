/**
 * @Project: hibernate-demo
 * @Title: Teacher.java
 * @Package com.ender.demo
 * @Description: TODO
 * Copyright: Copyright (c) 2010 
 * Company:ENDER.crop
 * 
 * @author ender
 * @date 2014-2-16 下午7:04:22
 * @version V1.0.0
 */
package com.ender.demo;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @ClassName: Teacher
 * @Description: TODO
 * @author ender
 * @date 2014-2-16 下午7:04:22
 *
 */
@Entity
@Table(name="t_teacher",catalog="hibernateTest")
public class Teacher {
	private int id;
	private String name;
	private String title;
	private String email;
	private String phone;
	private Date birthday;
	
	private Set<Course> courses = new HashSet<Course>();
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the courses
	 */
	@OneToMany(fetch=FetchType.EAGER,mappedBy="teacher")
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
		StringBuffer strBuffer = new StringBuffer("Teacher:{"); 
		try{
			Field[] fields = this.getClass().getDeclaredFields();
			for(Field field : fields){
				if(field.getName().equalsIgnoreCase("courses")||field.getName().equalsIgnoreCase("students")){
					System.out.println(field.getName());
					strBuffer.append("["+field.getName()+":"+field.get(this).getClass().getField("id")+"]");
				}
				else if(field.getType().isAssignableFrom(Course.class)||field.getType().isAssignableFrom(Student.class)){
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
