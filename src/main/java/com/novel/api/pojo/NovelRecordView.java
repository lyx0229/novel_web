package com.novel.api.pojo;

import java.math.BigInteger;
import java.util.Date;

/**
 * 小说阅读记录
 * @author Administrator
 *
 */
public class NovelRecordView {

	private BigInteger id;
	private BigInteger user_id;
	private BigInteger novel_id;
	private Date update_time;
	private BigInteger novel_detail_id;
	private String chapter_name;//章节名称
	private String chapter_url;//章节url
	private String novel_author;//作者
	private String novel_name;//小说名称
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getUser_id() {
		return user_id;
	}
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	public BigInteger getNovel_id() {
		return novel_id;
	}
	public void setNovel_id(BigInteger novel_id) {
		this.novel_id = novel_id;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public BigInteger getNovel_detail_id() {
		return novel_detail_id;
	}
	public void setNovel_detail_id(BigInteger novel_detail_id) {
		this.novel_detail_id = novel_detail_id;
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
	public String getNovel_author() {
		return novel_author;
	}
	public void setNovel_author(String novel_author) {
		this.novel_author = novel_author;
	}
	public String getNovel_name() {
		return novel_name;
	}
	public void setNovel_name(String novel_name) {
		this.novel_name = novel_name;
	}
	
}
