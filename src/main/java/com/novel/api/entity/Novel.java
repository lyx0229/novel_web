package com.novel.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GenerationType;
import java.util.Date;
   /**
    * 小说信息表
    * Fri Jul 13 15:14:36 CST 2018 lyx
    */ 
 @Entity 
 @Table(name = "tbl_novel") 


public class Novel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 961781266925776670L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	@Column 
	private String novel_name;
	@Column 
	private String novel_imgurl;
	@Column 
	private String novel_brief;
	@Column 
	private String novel_author;
	@Column 
	private int sex_status;
	@Column 
	private int type_status;
	@Column 
	private int novel_state;
	@Column 
	private int hot_status;
	@Column 
	private String novel_new_chapter;
	@Column 
	private Date novel_update_time;
	@Column(name = " novel_number_votes ") 
	private int votes;
	@Column 
	private String novel_page_url;
	@Transient
	private String sex_str;
	@Transient
	private String type_str;
	@Transient
	private String novel_str;
	@Transient
	private String hot_str;
	@Column 
	private BigDecimal new_price;
	@Column 
	private BigDecimal old_price;
	
	public String getHot_str() {
		switch(getHot_status()){
		  case 1: hot_str="滚动列表";break;
		  case 2: hot_str="本周主打";break;
		  case 3: hot_str="新书试读";break;
		  case 4: hot_str="连载更新";break;
		  case 5: hot_str="人气推荐";break;
		  default : hot_str="";break;
		}
		return hot_str;
	}
	
	
	
	public String getType_str() {
		switch(getType_status()){
		  case 1: type_str="奇幻玄幻";break;
		  case 2: type_str="武侠仙侠";break;
		  case 3: type_str="都市言情";break;
		  case 4: type_str="历史穿越";break;
		  case 5: type_str="官场军事";break;
		  case 6: type_str="都市异能";break;
		  case 7: type_str="惊悚恐怖";break;
		  case 8: type_str="豪门总裁";break;
		  case 9: type_str="古代言情";break;
		  case 10: type_str="现代言情";break;
		  case 11: type_str="穿越重生";break;
		  case 12: type_str="耿美百合";break;
		  case 13: type_str="惊悚恐怖";break;
		  default : type_str="";break;
		}
		return type_str;
	}
	public void setType_str(String type_str) {
		this.type_str = type_str;
	}
	public String getSex_str() {
		if(getSex_status()==1){
			this.sex_str="女";
		}if(getSex_status()==0){
			this.sex_str="男";
		}
		return sex_str;
	}
	



	public BigDecimal getNew_price() {
		return new_price;
	}



	public void setNew_price(BigDecimal new_price) {
		this.new_price = new_price;
	}



	public BigDecimal getOld_price() {
		return old_price;
	}



	public void setOld_price(BigDecimal old_price) {
		this.old_price = old_price;
	}



	public int getHot_status() {
		return hot_status;
	}
	public void setHot_status(int hot_status) {
		this.hot_status = hot_status;
	}
	public String getNovel_str() {
		
		if(getNovel_state()==1){
			this.novel_str="连载中";
		}if(getNovel_state()==2){
			this.novel_str="已完成";
		}
		return novel_str;
	}
	public void setNovel_str(String novel_str) {
		this.novel_str = novel_str;
	}
	public void setSex_str(String sex_str) {
		this.sex_str = sex_str;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setHot_str(String hot_str) {
		this.hot_str = hot_str;
	}



	public String getNovel_name() {
		return novel_name;
	}
	public void setNovel_name(String novel_name) {
		this.novel_name = novel_name;
	}
	public String getNovel_imgurl() {
		return novel_imgurl;
	}
	public void setNovel_imgurl(String novel_imgurl) {
		this.novel_imgurl = novel_imgurl;
	}
	public String getNovel_brief() {
		return novel_brief;
	}
	public void setNovel_brief(String novel_brief) {
		this.novel_brief = novel_brief;
	}
	public String getNovel_author() {
		return novel_author;
	}
	public void setNovel_author(String novel_author) {
		this.novel_author = novel_author;
	}
	public int getSex_status() {
		return sex_status;
	}
	public void setSex_status(int sex_status) {
		this.sex_status = sex_status;
	}
	public int getType_status() {
		return type_status;
	}
	public void setType_status(int type_status) {
		this.type_status = type_status;
	}
	public int getNovel_state() {
		return novel_state;
	}
	public void setNovel_state(int novel_state) {
		this.novel_state = novel_state;
	}
	public String getNovel_new_chapter() {
		return novel_new_chapter;
	}
	public void setNovel_new_chapter(String novel_new_chapter) {
		this.novel_new_chapter = novel_new_chapter;
	}
	public Date getNovel_update_time() {
		return novel_update_time;
	}
	public void setNovel_update_time(Date novel_update_time) {
		this.novel_update_time = novel_update_time;
	}



	public int getVotes() {
		return votes;
	}



	public void setVotes(int votes) {
		this.votes = votes;
	}



	public String getNovel_page_url() {
		return novel_page_url;
	}
	public void setNovel_page_url(String novel_page_url) {
		this.novel_page_url = novel_page_url;
	}
	
	
}

