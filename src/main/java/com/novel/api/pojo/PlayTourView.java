package com.novel.api.pojo;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
/**
 * 打赏记录
 * @author Administrator
 *
 */

public class PlayTourView{
	private BigInteger id;
	private BigInteger novel_id;
	private BigInteger user_id;
	private BigInteger goods_id;
	private Date update_time;
	private String nick_name;
	private String head_imgurl;
	private String goods_name;
	
	public BigInteger getId() {
		return id;
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
	public BigInteger getUser_id() {
		return user_id;
	}
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	public BigInteger getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(BigInteger goods_id) {
		this.goods_id = goods_id;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getHead_imgurl() {
		return head_imgurl;
	}
	public void setHead_imgurl(String head_imgurl) {
		this.head_imgurl = head_imgurl;
	}
	public String getNick_name() {
		try {
			nick_name = new String(Base64.decodeBase64(nick_name.getBytes()), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	
}
