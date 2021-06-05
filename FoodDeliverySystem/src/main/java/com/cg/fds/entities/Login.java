package com.cg.fds.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Login {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private String userid;
	private String userName;
	private String password;
	
	//CONSTRUCTOR
	/**
	 * @param userid
	 * @param userName
	 * @param password
	 */
	public Login(String userid, String userName, String password) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.password = password;
	}
	
	//SETTER-GETTER
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
}
