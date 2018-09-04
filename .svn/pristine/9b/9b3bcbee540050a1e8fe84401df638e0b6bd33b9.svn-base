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
    * 兑换书豆记录
    * Fri Jul 13 11:11:04 CST 2018 lyx
    */ 
 @Entity 
 @Table(name = "tbl_exchange_detail") 


public class ExchangeDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5378151367560024965L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	@Column 
	private int user_id;//用户id
	@Column 
	private int book_peas;//书豆
	@Column 
	private int read_points;//积分
	@Column 
	private Date create_time;//创建时间
	public long getId() {
		return id;
	}

	
	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getBook_peas() {
		return book_peas;
	}
	public void setBook_peas(int book_peas) {
		this.book_peas = book_peas;
	}
	public int getRead_points() {
		return read_points;
	}
	public void setRead_points(int read_points) {
		this.read_points = read_points;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	
}

