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
    * 小说明细
    * Mon Jul 16 15:55:15 CST 2018 lyx
    */ 
 @Entity 
 @Table(name = "tbl_novel_detail") 

public class NovelDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 373886754543871292L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	@Column 
	private int novel_id;
	@Column 
	private String chapter_name;
	@Column 
	private String chapter_url;
	@Column 
	private int free_state;
	@Column 
	private Date update_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNovel_id() {
		return novel_id;
	}
	public void setNovel_id(int novel_id) {
		this.novel_id = novel_id;
	}
	public String getChapter_name() {
		return chapter_name;
	}
	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}
	public String getChapter_url() {
		return chapter_url;
	}
	public void setChapter_url(String chapter_url) {
		this.chapter_url = chapter_url;
	}
	public int getFree_state() {
		return free_state;
	}
	public void setFree_state(int free_state) {
		this.free_state = free_state;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	
}

