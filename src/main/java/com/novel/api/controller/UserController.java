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
import com.novel.api.entity.ExchangeDetail;
import com.novel.api.entity.JSONResponse;
import com.novel.api.entity.PlayTourGoods;
import com.novel.api.entity.WeixinUserInfo;
import com.novel.api.service.CommonService;
import com.novel.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class); 
	@Autowired
	UserService userService;
	@Autowired
	CommonService commonService;
	
	/**
	 * 个人中心
	 */
	
	@RequestMapping(value = "/main", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse main(int user_id) {
		logger.info("user_id："+user_id);
		if(user_id==0){
			JSONResponse.error("未登陆");
		}
		WeixinUserInfo user=userService.findUserById(user_id);
		return JSONResponse.success(user);
	}
	
	/**
	 * 兑换书豆接口
	 */
	
	@RequestMapping(value = "/exchange", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse exchange(int user_id) {
		logger.info("进入兑换书豆");
		if(user_id==0){
			JSONResponse.error("未登陆");
		}
		WeixinUserInfo user=userService.findUserById(user_id);
		List<ExchangeDetail> exchange_list=commonService.findExchangeDetailByUserid(user_id);
		JSONObject json=new JSONObject();
		json.put("user", user);
		json.put("exchange_list", exchange_list);
		return JSONResponse.success(json);
	}
	

	  /**
	   * 打赏礼物列表
	   */
		@RequestMapping(value = "/get_goods", method = {RequestMethod.POST,RequestMethod.GET})
		@ResponseBody
		public JSONResponse get_goods() {
			logger.info("进入打赏礼物列表");
			List<PlayTourGoods> goods_list=commonService.findPlayTourGoods();
			return JSONResponse.success(goods_list);
		}
		
		 /**
		   * 打赏小說
		   */
		@RequestMapping(value = "/paly_tour", method = {RequestMethod.POST,RequestMethod.GET})
		@ResponseBody
		public JSONResponse paly_tour(int novel_id,int user_id,int goods_id) {
			logger.info("打赏小說");
			logger.info("novel_id"+novel_id+"user_id"+user_id+"goods_id");
			return  commonService.savePlayTour(novel_id, user_id, goods_id);
		}

}
