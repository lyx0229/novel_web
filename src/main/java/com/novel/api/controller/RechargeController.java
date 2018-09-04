package com.novel.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.novel.api.entity.JSONResponse;
import com.novel.api.entity.RechargeDetail;
import com.novel.api.entity.RechargeTemplate;
import com.novel.api.service.RechargeService;

@RestController
@RequestMapping("/recharge")
public class RechargeController {
	Logger logger = LoggerFactory.getLogger(RechargeController.class); 
	@Autowired
	RechargeService rechargeService;

	/**
	 * 充值模板
	 * 
	 * @return
	 */
	@RequestMapping(value = "/template", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
    public JSONResponse template(){
		List<RechargeTemplate> templateList= rechargeService.findRechargeTemplate();
      return JSONResponse.success(templateList);
    } 
	/**
	 * 充值明细查询
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
    public JSONResponse detail(int user_id){
		List<RechargeDetail> detailList= rechargeService.findRechargeDetailByUserID(user_id);
      return JSONResponse.success(detailList);
    } 
}
