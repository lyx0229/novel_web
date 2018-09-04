package com.novel.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.api.entity.RechargeDetail;
import com.novel.api.entity.RechargeTemplate;
import com.novel.api.repository.RechargeDetailRepository;
import com.novel.api.repository.RechargeTemplateRepository;

@Service
public class RechargeService {
	@Autowired
	private RechargeTemplateRepository template;
	
	@Autowired
	private RechargeDetailRepository detail;
	

	public List<RechargeTemplate> findRechargeTemplate() {
		return template.findAll();
	}
	
	public RechargeTemplate findRechargeTemplateByID(int id) {
		return template.findOne(id);
	}
	
	
	public List<RechargeDetail> findRechargeDetailByUserID(int userid) {
		return detail.findRechargeDetailByUserID(userid);
	}
	
	
	public RechargeDetail findRechargeDetailByOrderNo(String order_no) {
		return detail.findRechargeDetailByOrderNo(order_no);
	}
	
	public RechargeDetail saveAndUpdate(RechargeDetail po){
		return detail.saveAndFlush(po);
	}
	
}
