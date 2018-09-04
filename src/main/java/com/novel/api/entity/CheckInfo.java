package com.novel.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
   /**
    * 用户签到信息表
    * Mon Jun 25 14:11:04 CST 2018 lyx
    */ 
 @Entity 
 @Table(name = "tbl_check_info") 


public class CheckInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7892876889999844996L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	@Column (name="user_id")
	private int userID;
	@Column 
	private int check_status;
	@Column 
	private Date check_time;
	@Column 
	private int check_peas;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getCheck_status() {
		return check_status;
	}
	public void setCheck_status(int check_status) {
		this.check_status = check_status;
	}
	
	public Date getCheck_time() {
		return check_time;
	}
	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}
	public int getCheck_peas() {
		return check_peas;
	}
	public void setCheck_peas(int check_peas) {
		this.check_peas = check_peas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

