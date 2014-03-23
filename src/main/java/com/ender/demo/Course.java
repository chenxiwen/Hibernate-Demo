/**
 * @Project: hibernate-demo
 * @Title: Course.java
 * @Package com.ender.demo
 * @Description: TODO
 * Copyright: Copyright (c) 2010 
 * Company:ENDER.crop
 * 
 * @author ender
 * @date 2014-2-16 下午7:09:19
 * @version V1.0.0
 */
package com.ender.demo;

import java.lang.reflect.Field;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @ClassName: Course
 * @Description: TODO
 * @author ender
 * @date 2014-2-16 下午7:09:19
 *
 */
@Entity
@Table(name="t_course")
public class Course {
	private int id;
	private String name;
	private Date startDate;
	private Date stopDate;
	
	private Set<Student> students = new HashSet<Student>();
	
	private Teacher teacher;
	
	
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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the stopDate
	 */
	public Date getStopDate() {
		return stopDate;
	}
	/**
	 * @param stopDate the stopDate to set
	 */
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	/**
	 * @return the students
	 */
	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	@JoinTable(name="student_course",joinColumns={@JoinColumn(name="course_id",
	referencedColumnName="id")},inverseJoinColumns={@JoinColumn(name="student_id",
	referencedColumnName="id")})
	public Set<Student> getStudents() {
		return students;
	}
	/**
	 * @param students the students to set
	 */
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	/**
	 * @return the teacher
	 */
	@ManyToOne(cascade={CascadeType.ALL})
	public Teacher getTeacher() {
		return teacher;
	}
	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	@Override
	public String toString(){
		StringBuffer strBuffer = new StringBuffer("Course:{"); 
		try{
			Field[] fields = this.getClass().getDeclaredFields();
			for(Field field : fields){
				if(field.getName().equalsIgnoreCase("students")||field.getName().equalsIgnoreCase("teacher")){
					System.out.println(field.getName());
					strBuffer.append("["+field.getName()+":"+field.get(this).getClass().getField("id")+"]");
				}
				else if(field.getType().isAssignableFrom(Student.class)||field.getType().isAssignableFrom(Teacher.class)){
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
