package com.weixin.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.api.entity.RechargeDetail;
import com.novel.api.entity.RechargeTemplate;
import com.novel.api.entity.WeixinUserInfo;
import com.novel.api.repository.RechargeDetailRepository;
import com.novel.api.repository.RechargeTemplateRepository;
import com.novel.api.repository.UserRepository;
import com.novel.common.utils.DateUtils;

@Service
public class WXPayService {
	private static	Logger logger=LoggerFactory.getLogger(WXPayService.class);
	@Autowired
	private RechargeTemplateRepository template;
	
	@Autowired
	private RechargeDetailRepository detail;
	
	@Autowired
	private UserRepository userrep;

	/**
	 * 充值更新
	 * @param po
	 * @return
	 */
	public RechargeDetail saveRechargeAndUserInfo(RechargeDetail po){
		logger.info("进入充值成功回调更新");
		logger.info("user_id:"+po.getUser_id());
		WeixinUserInfo user= userrep.findOne(po.getUser_id());
		RechargeTemplate temple=template.findOne(po.getTemplate_id());
		int term=temple.getTerms();
		if(term>0){//当前不在vip时间内
			if(user.getVip_time()==null){
				user.setVip_status(1);
				user.setVip_time(new Date());
				Date endTime=DateUtils.monthAddFrist(new Date(), term);
				user.setVip_end_time(endTime);
			}else{//当前在vip时间内
				user.setVip_status(1);
				Date endTime=DateUtils.monthAddFrist(user.getVip_end_time(), term);
				user.setVip_end_time(endTime);
			}
		}else{
			user.setBook_peas(user.getBook_peas()+po.getBook_peas()+po.getPre_book_peas());
		}
		userrep.saveAndFlush(user);
		return detail.saveAndFlush(po);
	}
}
