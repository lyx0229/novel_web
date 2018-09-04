package com.novel.api.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GenerationType;
import java.util.Date;
   /**
    *  ֧支付订单表
    * Mon Jul 30 17:18:42 CST 2018 lyx
    */ 
 @Entity 
 @Table(name = "tbl_recharge_detail") 


public class RechargeDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2765173620578124351L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	@Column 
	private int user_id;
	@Column 
	private int template_id;
	@Column 
	private Date recharge_time;
	@Column 
	private double recharge_money;
	@Column 
	private int book_peas;
	@Column 
	private int pre_book_peas;
	@Column 
	private int result;
	@Column 
	private String order_no;
	@Column 
	private String remark;
	@Column 
	private Date result_time;
	@Column 
	private String result_info;
	@Column 
	private String result_code;
	@Transient
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return "充值"+recharge_money+"元";
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(int template_id) {
		this.template_id = template_id;
	}
	public Date getRecharge_time() {
		return recharge_time;
	}
	public void setRecharge_time(Date recharge_time) {
		this.recharge_time = recharge_time;
	}
	public double getRecharge_money() {
		return recharge_money;
	}
	public void setRecharge_money(double recharge_money) {
		this.recharge_money = recharge_money;
	}
	public int getBook_peas() {
		return book_peas;
	}
	public void setBook_peas(int book_peas) {
		this.book_peas = book_peas;
	}
	public int getPre_book_peas() {
		return pre_book_peas;
	}
	public void setPre_book_peas(int pre_book_peas) {
		this.pre_book_peas = pre_book_peas;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getResult_time() {
		return result_time;
	}
	public void setResult_time(Date result_time) {
		this.result_time = result_time;
	}
	public String getResult_info() {
		return result_info;
	}
	public void setResult_info(String result_info) {
		this.result_info = result_info;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	
}

