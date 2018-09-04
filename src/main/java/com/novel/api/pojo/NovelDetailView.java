package com.novel.api.pojo;

import java.math.BigInteger;
import java.util.Date;
/**
 * 小说信息
 * @author Administrator
 *
 */
public class NovelDetailView  {
	
	private BigInteger id;
	private BigInteger novel_id;
	private String chapter_name;
	private String chapter_url;
	private BigInteger free_state;
	private Date update_time;
	private String free_str;
	
	public String getFree_str() {
		switch(getFree_state().intValue()){
		  case 0: free_str="免费";break;
		  case 1: free_str="可购买";break;
		  case 2: free_str="已购买";break;
		  default : free_str="";break;
		}
		return free_str;
	}

	public void setFree_str(String free_str) {
		this.free_str = free_str;
	}

	public BigInteger getId() {
		return id;
	}
	public String getChapter_name() {
		return chapter_name;
	}

	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getNovel_id() {
		return novel_id;
	}
	public void setNovel_id(BigInteger novel_id) {
		this.novel_id = novel_id;
	}

	public String getChapter_url() {
		return chapter_url;
	}

	public void setChapter_url(String chapter_url) {
		this.chapter_url = chapter_url;
	}

	public BigInteger getFree_state() {
		return free_state;
	}

	public void setFree_state(BigInteger free_state) {
		this.free_state = free_state;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}
