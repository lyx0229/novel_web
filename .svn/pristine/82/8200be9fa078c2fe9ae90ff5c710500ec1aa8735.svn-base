package com.novel.api.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import java.util.Date;
   /**
    * 用户书架记录
    * Mon Jun 25 15:49:43 CST 2018 lyx
    */ 
 @Entity 
 @Table(name = "tbl_novel_store") 


public class NovelStore implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8058671431897772155L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	@Column(name="user_id") 
	private int user_id;
	@Column (name="novel_id") 
	private int novel_id;
	@Column 
	private Date update_time;
	@Column 
	private int book_state;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getNovel_id() {
		return novel_id;
	}
	public void setNovel_id(int novel_id) {
		this.novel_id = novel_id;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public int getBook_state() {
		return book_state;
	}
	public void setBook_state(int book_state) {
		this.book_state = book_state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

