package com.novel.api.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.novel.api.entity.WeixinUserInfo;
import com.novel.api.repository.UserRepository;
import com.novel.config.BusinessConfig;

@Service
public class UserService {
	Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository repository;
	@Autowired
	BusinessConfig  businessConfig;
	public WeixinUserInfo findUserById(Integer id) {
		return repository.findOne(id);
	}
	
	 public WeixinUserInfo findUserInfoByOpenID(String openID) {
		return repository.findUserInfoByOpenID(openID);
	}
	
	public WeixinUserInfo saveAndUpdate(WeixinUserInfo user){
		return repository.saveAndFlush(user);
	}
	
	public WeixinUserInfo saveOrUpdateUserInfo(JSONObject userInfo){
		logger.info(userInfo.toString());
		WeixinUserInfo weixinUserInfo=repository.findUserInfoByOpenID(userInfo.getString("openid"));
		if(weixinUserInfo==null){
			weixinUserInfo=new WeixinUserInfo();
			weixinUserInfo.setOpen_id(userInfo.getString("openid"));
			String	nick_name="";
			if(userInfo.getString("nickname")!=null){
			try {
				nick_name = Base64.encodeBase64String(userInfo.getString("nickname").getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}}
			weixinUserInfo.setNick_name(nick_name);
			weixinUserInfo.setCountry(userInfo.getString("country"));
			weixinUserInfo.setHead_imgurl(userInfo.getString("headimgurl"));
			weixinUserInfo.setSex(userInfo.getIntValue("sex"));
			weixinUserInfo.setProvince(userInfo.getString("province"));
			weixinUserInfo.setCity(userInfo.getString("city"));
			weixinUserInfo.setApp_id(businessConfig.APPID);			
			weixinUserInfo.setCreate_time(new Date());		
			weixinUserInfo.setChannel(userInfo.getString("channel"));
			weixinUserInfo.setScheme_id(userInfo.getIntValue("scheme_id"));
			weixinUserInfo=repository.saveAndFlush(weixinUserInfo);
		}
		return weixinUserInfo;
	}
	
	public int findUserByIdAndVip(Integer id) {
		return repository.findUserByIdAndVip(id);
	}
}
