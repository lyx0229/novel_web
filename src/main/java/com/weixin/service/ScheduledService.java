package com.weixin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.api.entity.WeixinUserInfo;
import com.novel.api.repository.UserRepository;
@Service
public class ScheduledService {
	private static	Logger logger=LoggerFactory.getLogger(ScheduledService.class);
	
	@Autowired
	private UserRepository userrep;
	
	public void updateVipStatus(){
		List<WeixinUserInfo> userlist=userrep.findUserInfoByVip();
		logger.info("vip到期的用户数："+userlist.size());
		if(userlist!=null && userlist.size()>0){
			for(WeixinUserInfo userinfo :userlist){
				logger.info("用户id："+userinfo.getId());
				userinfo.setVip_status(2);
				userrep.saveAndFlush(userinfo);
			}
		}
	}
}
