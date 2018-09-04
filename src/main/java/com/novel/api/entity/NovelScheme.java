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
    * 小说推广信息
    * Thu Aug 09 17:50:33 CST 2018 lyx
    */ 
 @Entity 
 @Table(name = "tbl_novel_scheme") 


public class NovelScheme implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5279276125006358256L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	@Column 
	private int novel_detail_id;
	@Column 
	private int novel_id;
	@Column 
	private String scheme_url;
	@Column 
	private int click_no;
	@Column 
	private String channel;
	@Column 
	private String image_url;
	@Column 
	private Date create_time;
	@Column 
	private Date update_time;
	
	
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNovel_detail_id() {
		return novel_detail_id;
	}
	public void setNovel_detail_id(int novel_detail_id) {
		this.novel_detail_id = novel_detail_id;
	}
	public int getNovel_id() {
		return novel_id;
	}
	public void setNovel_id(int novel_id) {
		this.novel_id = novel_id;
	}
	public String getScheme_url() {
		return scheme_url;
	}
	public void setScheme_url(String scheme_url) {
		this.scheme_url = scheme_url;
	}
	public int getClick_no() {
		return click_no;
	}
	public void setClick_no(int click_no) {
		this.click_no = click_no;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	
}

