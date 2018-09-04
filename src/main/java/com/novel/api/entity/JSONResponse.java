package com.novel.api.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 返回实体类
 * 
 * @author Administrator
 *
 */
public class JSONResponse {
	static	Logger logger = LoggerFactory.getLogger(JSONResponse.class); 
	
	private int status;
	private String desc;
	private Object data;

	public static JSONResponse success(Object data) {
		JSONResponse response = new JSONResponse();
		response.setStatus(1);
		response.setDesc("操作成功");
		response.setData(data);
		return response;
	}

	public static JSONResponse error(String desc) {
		JSONResponse response = new JSONResponse();
		response.setStatus(0);
		response.setDesc(desc);
		return response;
	}

	public static JSONResponse error(int status ,String desc) {
		JSONResponse response = new JSONResponse();
		response.setStatus(status);
		response.setDesc(desc);
		return response;
	}
	
	public static JSONResponse success(Object data,int status ,String desc) {
		JSONResponse response = new JSONResponse();
		response.setStatus(status);
		response.setDesc(desc);
		response.setData(data);
		return response;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JSONResponse [status=" + status + ", desc=" + desc + ", data=" + data + "]";
	}
}
