package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.weixin.constants.SpringUtil;
import com.weixin.service.ScheduledService;

/**
 * @author lyx
 * 定时器
 *
 */
@Component
public class ScheduledTasks {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
	 /**
	 * 更新vip状态
	 * 每天凌晨1点执行一次
	 * 秒-分-时-日-月-年
	 */
	@Scheduled(cron = "0 0 1 * * *")
	 public void createOverdueInterestScheduled() {
		logger.info("启动【更新vip状态】定时任务......");
		SpringUtil.getBean(ScheduledService.class).updateVipStatus();
		logger.info("【更新vip状态】定时任务执行完毕......");
	 }

}
