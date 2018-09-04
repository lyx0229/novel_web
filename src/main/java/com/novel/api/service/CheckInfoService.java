package com.novel.api.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.api.entity.CheckInfo;
import com.novel.api.entity.WeixinUserInfo;
import com.novel.api.repository.CheckInfoRepository;
import com.novel.common.utils.DateUtils;
import com.novel.config.BusinessConfig;


@Service
public class CheckInfoService {
	@Autowired
	 CheckInfoRepository repository;
	@Autowired
	 UserService userService;
	@Autowired
	CommonService comService;
	@Autowired
	BusinessConfig businessConfig;

	public CheckInfo findCheckInfoById(Integer id) {
		return repository.findOne(id);
	}
	
	public CheckInfo saveAndUpdate(CheckInfo po){
		return repository.saveAndFlush(po);
	}
	
	public List<CheckInfo> findCheckInfoByUserID(int userid) {
		return repository.findCheckInfoByUserID(userid);
	}
	
	
	public CheckInfo findByUserIDAndTime(int userid){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String startTime=sdf.format(DateUtils.getStartTime());
     return repository.findByUserIDAndTime(userid, startTime);
	}
	 /**
     * 更新签到信息
     * @param eventDetail
     * @return
     */
	@Transactional
	public WeixinUserInfo checkin(int userid){
		CheckInfo po =new CheckInfo();
		po.setCheck_time(new Date());
		po.setUserID(userid);
		po.setCheck_status(1);
		po.setCheck_peas(businessConfig.bookpeas);
		repository.saveAndFlush(po);
		WeixinUserInfo user =userService.findUserById(userid);
		user.setBook_peas(user.getBook_peas()+businessConfig.bookpeas);
		userService.saveAndUpdate(user);
		return user;
	}
}
