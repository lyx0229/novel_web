package com.novel.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.novel.api.entity.CheckInfo;
import com.novel.api.entity.JSONResponse;
import com.novel.api.entity.WeixinUserInfo;
import com.novel.api.enums.EnumMethod;
import com.novel.api.service.CheckInfoService;
import com.novel.api.service.CommonService;
import com.novel.api.service.UserService;
import com.novel.config.BusinessConfig;
import com.weixin.constants.WXConstants;
import com.weixin.utils.HttpRequestUtil;
import com.weixin.utils.WechatAccessToken;

/**
 * 签到
 * 
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/check")
public class CheckController {
	Logger logger = LoggerFactory.getLogger(CheckController.class); 
	@Autowired
	CheckInfoService checkInfoService;
	@Autowired
	CommonService comService;
	@Autowired
	BusinessConfig businessConfig;
	@Autowired
	UserService userService;
	/**
	 * 签到
	 * 
	 * @param password
	 * @param wx_name
	 * @param wxid
	 * @return
	 */
	@RequestMapping(value = "/checkin", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse checkin(int user_id) {
		logger.info("签到"+"user_id:"+user_id);
		//判断当日是否已签到
		CheckInfo checkInfo= checkInfoService.findByUserIDAndTime(user_id);
		if(checkInfo!=null){
		return JSONResponse.error("今日已签到");
		}
		WeixinUserInfo wxuser =userService.findUserById(user_id);
		String open_id=null;
		String msg="本日签到成功，获得"+businessConfig.bookpeas+"书豆,关注我们(零点小说驿站)后每日签到可获得书豆。上万本书，总有你喜欢的，期待您的阅读~";
		if(wxuser!=null){
			open_id=wxuser.getOpen_id();
			comService.sendMsg(open_id,msg);
		}
		// 数据入库
		WeixinUserInfo user = checkInfoService.checkin(user_id);
		return JSONResponse.success(user);
	}
	/**
	 * 签到记录
	 * 
	 * @param password
	 * @param wx_name
	 * @param wxid
	 * @return
	 */
	@RequestMapping(value = "/check_list", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse check_list(int user_id) {
		logger.info("签到记录"+"user_id:"+user_id);
		//判断当日是否已签到
		List<CheckInfo> list= checkInfoService.findCheckInfoByUserID(user_id);
		return JSONResponse.success(list);
	}
	
	
}
