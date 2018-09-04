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
    *小说章节购买信息
    * Wed Jul 18 09:36:17 CST 2018 lyx
    */ 
 @Entity 
 @Table(name = "tbl_chapter_buy") 
public class ChapterBuyInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5723304103331718873L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	@Column 
	private long novel_detail_id;
	@Column 
	private long user_id;
	@Column 
	private Date update_time;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getNovel_detail_id() {
		return novel_detail_id;
	}
	public void setNovel_detail_id(long novel_detail_id) {
		this.novel_detail_id = novel_detail_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}

