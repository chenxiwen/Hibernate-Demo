/**
 * @Project: hibernate-demo
 * @Title: User.java
 * @Package com.ender.demo
 * @Description: TODO
 * Copyright: Copyright (c) 2010 
 * Company:ENDER.crop
 * 
 * @author ender
 * @date 2014-2-16 下午4:09:11
 * @version V1.0.0
 */
package com.ender.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: User
 * @Description: TODO
 * @author ender
 * @date 2014-2-16 下午4:09:11
 *
 */
@Entity
@Table(name="t_user", catalog="hibernateTest")
public class User {
	
	private int id;
	private String username;
	private String password;
	private String nickName;
	private String email;
	private String phone;
	
	
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	
	
	
}
