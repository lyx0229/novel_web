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
    * 阅读记录
    * Wed Jul 18 14:42:03 CST 2018 lyx
    */ 
 @Entity 
 @Table(name = "tbl_novel_record") 


public class NovelRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8850936813968781839L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	@Column 
	private int user_id;
	@Column 
	private int novel_id;
	@Column 
	private Date update_time;
	@Column 
	private int novel_detail_id;
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
	public int getNovel_detail_id() {
		return novel_detail_id;
	}
	public void setNovel_detail_id(int novel_detail_id) {
		this.novel_detail_id = novel_detail_id;
	}
}

