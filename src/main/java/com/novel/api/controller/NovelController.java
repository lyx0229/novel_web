package com.novel.api.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.novel.api.entity.JSONResponse;
import com.novel.api.entity.Novel;
import com.novel.api.entity.NovelBuy;
import com.novel.api.entity.NovelDetail;
import com.novel.api.entity.NovelRecord;
import com.novel.api.entity.NovelStore;
import com.novel.api.entity.WeixinUserInfo;
import com.novel.api.pojo.NovelDetailView;
import com.novel.api.pojo.NovelRecordView;
import com.novel.api.pojo.NovelView;
import com.novel.api.pojo.PlayTourListView;
import com.novel.api.pojo.PlayTourView;
import com.novel.api.service.CommonService;
import com.novel.api.service.NovelService;
import com.novel.api.service.UserService;
import com.novel.common.utils.PageableTools;
import com.novel.common.utils.ReadTXT;
import com.novel.common.utils.SortDto;
import com.novel.config.BusinessConfig;

/**
 * 小说记录
 * 
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/novel")
public class NovelController {
	Logger logger = LoggerFactory.getLogger(NovelController.class);
	@Autowired
	NovelService novelService;
	@Autowired
	CommonService commonService;
	@Autowired
	UserService userService;
	@Autowired
	BusinessConfig businessConfig;
	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;
	/**
	 * 小说阅读页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/novel_index", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse novel_index(int sex_status) {
		logger.info("进入小说首页");
		logger.info("sex_status:"+sex_status);
		JSONObject json =new JSONObject();
		Novel param = new Novel();
		param.setSex_status(sex_status);
		/*'1:滚动list2:本周主打3:新书试读4:连载更新5:人气推荐'*/
		param.setHot_status(1);
		List<NovelView> gdlist = novelService.findNovelViewByConditionLimit(param);// 滚动list
		param.setHot_status(2);
		List<NovelView> zdlist = novelService.findNovelViewByConditionLimit(param);// 本周主打
		
		List<NovelView> phlist = novelService.findNovelViewLimit(param);// 排行榜
		param.setHot_status(3);
		List<NovelView> newlist = novelService.findNovelViewByConditionLimit(param);// 新书试读
		param.setHot_status(4);
		List<NovelView> lzlist = novelService.findNovelViewByConditionLimit(param);// 连载更新
		param.setHot_status(5);
		List<NovelView> rqlist = novelService.findNovelViewByConditionLimit(param);// 人气推荐

		json.put("gdlist", gdlist);// 滚动list
		json.put("zdlist", zdlist);// 本周主打
		json.put("phlist", phlist);// 排行榜
		json.put("newlist", newlist);// 新书试读
		json.put("lzlist", lzlist);// 连载更新
		json.put("rqlist", rqlist);// 人气推荐
		return JSONResponse.success(json);
	}

	/**
	 * 小说排行榜
	 * 
	 * @return
	 */
	@RequestMapping(value = "/novel_hot_list", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse novel_hot_list(int sex_status,int hot_status,int paramnum) {
		logger.info("进入排行榜");
		logger.info("sex_status:"+sex_status+"hot_status:"+hot_status);
		Novel param = new Novel();
		param.setSex_status(sex_status);
		/*'1:滚动list2:本周主打3:新书试读4:连载更新5:人气推荐'*/
		param.setHot_status(hot_status);
//		List<NovelView> list = novelService.findNovelViewByCondition(param);// 滚动list
		int pageSize = Integer.valueOf(10);
		int currentPage = Integer.valueOf(paramnum);
		Page<Novel> list = novelService.findNovelByCondition(param,
				PageableTools.basicPage(currentPage, pageSize, new SortDto("id")));
		if(hot_status==0){
		   list = novelService.findNovelPHByCondition(param,
					PageableTools.basicPage(currentPage, pageSize, new SortDto("votes")));// 排行榜
		}
		return JSONResponse.success(list);
	}
	/**
	 * 小说首页查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/novel_search", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse novel_search(String novel_name) {
		logger.info("进入小说搜索");
		Novel param = new Novel();
		try {
			 novel_name= new String(novel_name.getBytes("ISO-8859-1"),"UTF-8");
			 logger.info("转码novel_name"+novel_name);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		param.setNovel_name(novel_name);
		List<Novel> list = novelService.findSearch(param);
		return JSONResponse.success(list);
	}

	/**
	 * 小说阅读页书库
	 * 
	 * @return
	 */
	@RequestMapping(value = "/novel_store_index", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse novel_store_index(int sex_status, int type_status, int novel_state,int status,int paramnum) {
		logger.info("进入小说首页书库");
		Novel param = new Novel();
		logger.info("sex_status="+sex_status+"type_status="+type_status+"novel_state="+novel_state+"paramnum="+paramnum);
		param.setSex_status(sex_status);
		param.setType_status(type_status);
		param.setNovel_state(novel_state);
		int pageSize = Integer.valueOf(10);
		int currentPage = Integer.valueOf(paramnum);
		Page<Novel> list = novelService.findNovelByCondition(param,
				PageableTools.basicPage(currentPage, pageSize, new SortDto("id")));
		return JSONResponse.success(list);
	}

	/**
	 * 书籍详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/novel_main", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse novel_main(int user_id, int novel_id) {
		logger.info("进入书籍详情");
		logger.info("user_id：" + user_id + "novel_id:" + novel_id);
		JSONObject json = new JSONObject();
		JSONObject param = new JSONObject();
		param.put("user_id", user_id);
		param.put("novel_id", novel_id);
		int vip_status = userService.findUserByIdAndVip(user_id);// 是否在vip时间内
		List<NovelDetailView> detail_list = novelService.findNovelDetailViewByCondition(param);// 目录
		if (detail_list == null || detail_list.size() == 0) {
			return JSONResponse.error("该小说无内容");
		}
		
		Novel novel=novelService.findNovelByID(novel_id);
		novel.setVotes(novel.getVotes()+1);
		novelService.saveAndUpdate(novel);
		
		NovelView novelview = novelService.findNovelViewByID(novel_id);// 小说信息
		if(novelview==null){
			return JSONResponse.error("该小说不存在");
		}
		NovelDetailView detail = detail_list.get(detail_list.size() - 1);

		int book_store_num = novelService.findNovelStoreByNovelId(novel_id);// 添加书库统计
		BigInteger book_record_num = novelview.getNovel_number_votes();// 小说阅读统计
		int goods_num = commonService.findGoodsByNovelId(novel_id);// 小说打赏统计

		json.put("book_store_num", book_store_num);// 添加书库统计
		json.put("book_record_num", book_record_num);// 小说阅读统计
		json.put("goods_num", goods_num);// 小说打赏统计
		json.put("vip_status", vip_status);// 是否在vip时间
		json.put("novel", novelview);// 小说
		json.put("detail", detail);// 最新章节
		json.put("detail_list", detail_list);// 目录
		// 数据入库
		return JSONResponse.success(json);
	}

	/**
	 * 小说目录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/chapter_list", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse chapter_list(int novel_id,int paramnum) {
		JSONObject json=new JSONObject();
		logger.info("进入小说目录");
		NovelDetail param = new NovelDetail();
		logger.info("novel_id="+novel_id+"paramnum:"+paramnum);
		param.setNovel_id(novel_id);
		int pageSize = Integer.valueOf(30);
		int currentPage = Integer.valueOf(paramnum);
		Page<NovelDetail> list = novelService.findNovelDetailByCondition(param,
				PageableTools.basicPage(currentPage, pageSize, new SortDto("asc","id")));
		json.put("list", list);
		return JSONResponse.success(json);
	}

	/**
	 * 查看书库
	 * 
	 * @return
	 */
	@RequestMapping(value = "/novel_store", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse novel_store(int user_id) {
		logger.info("进入查看书库");
		logger.info("user_id" + user_id);
		if (user_id == 0) {
			return JSONResponse.error("请先注册");
		}
		List<NovelView> view_list = novelService.findByNovelViewByUserid(user_id);
		// 数据入库
		return JSONResponse.success(view_list);
	}

	/**
	 * 添加书到书库
	 * 
	 * @return
	 */
	@RequestMapping(value = "/novel_store_add", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse novel_store_add(int novel_id, int user_id) {
		logger.info("进入添加小说到书库");
		logger.info("novel_id" + novel_id + "user_id" + user_id);
		if (user_id == 0) {
			return JSONResponse.error("请先注册");
		}
		int store=novelService.findNovelStoreByNovelId(novel_id);
		if(store>0){
			return JSONResponse.success(null,2,"该小说已在书库");
		}
		NovelStore novelStore = new NovelStore();
		novelStore.setBook_state(1);
		novelStore.setNovel_id(novel_id);
		novelStore.setUser_id(user_id);
		novelStore.setUpdate_time(new Date());
		novelStore = novelService.saveAndUpdate(novelStore);
		return JSONResponse.success(novelStore);
	}

	/**
	 * 删除书架记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/novel_store_del", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse novel_store_del(String ids) {
		
		logger.info("进删除小说书架");
		logger.info("ids" + ids);
		if(ids==null || ids==""){
			return JSONResponse.error("传参错误");
		}
	
		List<String> storeids = java.util.Arrays.asList(ids.split(",")); 
		for(int i=0 ;i<storeids.size();i++){
			logger.info("id" + storeids.get(i));
			int id=Integer.valueOf(storeids.get(i));
			NovelStore novelStore =novelService.findNovelStoreByID(id);
			if(novelStore==null){
				return JSONResponse.error("该记录不存在");
			}
			novelService.delteteNovelStoreByID(id);
		}
		return JSONResponse.success("");
	}

	/**
	 * 小说打赏记录
	 */
	@RequestMapping(value = "/paly_tour_detail", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse paly_tour_detail(int novel_id) {
		logger.info("小说打赏记录");
		List<PlayTourView> list = commonService.findByPlayTourViewByNovelId(novel_id);
		return JSONResponse.success(list);
	}

	/**
	 * 小说打赏排行
	 */
	@RequestMapping(value = "/paly_tour_list", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse paly_tour_list(int novel_id) {
		logger.info("小说打赏排行");
		logger.info("novel_id=" + novel_id);
		List<PlayTourListView> list = commonService.findByPlayTourListViewByNovelId(novel_id);
		return JSONResponse.success(list);
	}

	/**
	 * 小说章节购买
	 */
	/*@RequestMapping(value = "/chapter_buy", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse chapter_buy(int novel_detail_id, int user_id) {
		logger.info("小说章节购买");
		logger.info("novel_detail_id=" + novel_detail_id + "user_id=" + user_id);
		if (user_id == 0) {
			return JSONResponse.error("请先注册");
		}
		WeixinUserInfo user=userService.findUserById(user_id);
		if(user.getBook_peas()<businessConfig.chapter_peas){
			return JSONResponse.error("书豆不足");
		}
		JSONResponse response = commonService.saveBuyChapter(novel_detail_id, user_id);
		return response;
	}*/

	/**
	 * 完本小说购买
	 */
	@RequestMapping(value = "/novel_buy", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse novel_buy(int novel_id, int user_id) {
		logger.info("完本小说购买");
		logger.info("novel_detail_id=" + novel_id + "user_id=" + user_id);
		if (user_id == 0) {
			return JSONResponse.error("请先注册");
		}
		NovelBuy novelBuy=new NovelBuy();
		novelBuy.setNovel_id(novel_id);
		novelBuy.setUser_id(user_id);
		novelBuy.setUpdate_time(new Date());
		novelBuy= novelService.saveAndUpdate(novelBuy);
		return JSONResponse.success(novelBuy);
	}
	/**
	 * 添加小说阅读记录
	 */
	@RequestMapping(value = "/novel_record_add", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse novel_record_add(int novel_detail_id, int user_id, int novel__id) {
		logger.info("添加小说阅读记录");
		logger.info("novel_detail_id=" + novel_detail_id + "user_id=" + user_id + "novel__id=" + novel__id);

		NovelRecord record = novelService.findNovelRecordByUserIDNovelId(novel__id, user_id);
		if (null != record) {
			record.setNovel_detail_id(novel_detail_id);
			record.setUpdate_time(new Date());
		} else {
			record = new NovelRecord();
			record.setNovel_id(novel__id);
			record.setUser_id(user_id);
			record.setNovel_detail_id(novel_detail_id);
			record.setUpdate_time(new Date());
		}
		record = novelService.saveAndUpdate(record);
		return JSONResponse.success(record);
	}

	/**
	 * 查询小说阅读记录
	 */
	@RequestMapping(value = "/novel_record_query", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse novel_record_query(int user_id) {
		logger.info("查询小说阅读记录");
		logger.info("user_id=" + user_id);
		if (user_id == 0) {
			return JSONResponse.error("请先注册");
		}
		List<NovelRecordView> record_list = novelService.findNovelRecordViewByUserId(user_id);
		return JSONResponse.success(record_list);
	}
	
	/**
	 * 删除阅读记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/novel_record_del", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONResponse novel_record_del(String ids) {
		logger.info("进删除小说阅读记录");
		logger.info("ids" + ids);
		if(ids==null || ids==""){
			return JSONResponse.error("传参错误");
		}
		List<String> storeids = java.util.Arrays.asList(ids.split(",")); 
		for(int i=0 ;i<storeids.size();i++){
			logger.info("id" + storeids.get(i));
			int id=Integer.valueOf(storeids.get(i));
			NovelRecord record= novelService.findNovelRecordByID(id);
			if(record==null){
				return JSONResponse.error("该记录已删除");	
			}
			novelService.delteteNovelRecordByID(id);
		}
		return JSONResponse.success("");
	}
	
	/**
	 * 判断小说是否收费并读取小说
	 * 
	 * @return
	 */
	@RequestMapping(value = "/novel_judge", method = {RequestMethod.POST,RequestMethod.GET})
	public JSONResponse novel_judge(int novel_detail_id,int user_id, int novel_id) {
		logger.info("进入判断小说是否收费,并读取小说");
		logger.info("novel_detail_id："+novel_detail_id+"user_id:"+user_id+"novel_id:"+novel_id);
		JSONObject json=new JSONObject();
		int pay_status=0;
		List<String> conment=new ArrayList<>();
		NovelDetail detail=novelService.findNovelDetailByID(novel_detail_id);
		NovelRecord record=novelService.findNovelRecordByUserIDNovelId(novel_id, user_id);
		int lastid=novelService.findLastID(novel_id,novel_detail_id);
		int nextid=novelService.findNextID(novel_id,novel_detail_id);
		logger.info("lastid："+lastid+"nextid:"+nextid);
		json.put("lastid", lastid);
		json.put("nextid", nextid);
		if(detail==null){
			 return	JSONResponse.error("章节不存在");
			}
		if(detail.getFree_state()==0){//免费
			 pay_status=1;
			 conment=ReadTXT.getFile(detail.getChapter_url());
			 json.put("conment", conment);
			 logger.info("保存阅读记录");
			 if(record==null){
				 record=new  NovelRecord();
				 record.setNovel_detail_id(novel_detail_id);
				 record.setNovel_id(novel_id);
				 record.setUser_id(user_id);
				 record.setUpdate_time(new Date());
			 }
			 record.setNovel_detail_id(novel_detail_id);
			 record.setUpdate_time(new Date());
			 novelService.saveAndUpdate(record);
			 
			return JSONResponse.success(json,pay_status,"读取小说成功");
		}
		//已购买过
		int vip_status = userService.findUserByIdAndVip(user_id);// 是否在vip时间内
		int buy_status = novelService.findNovelBuyByNovelID(novel_id);// 是否已购买本小说
		int chapter_status = commonService.findChapterBuyByNovelID(novel_detail_id,user_id);// 是否已购买本章节小说
		if(vip_status>0 ||buy_status>0 || chapter_status>0){
			 pay_status=1;
			 conment=ReadTXT.getFile(detail.getChapter_url());
			 json.put("conment", conment);
			 logger.info("保存阅读记录");
			 if(record==null){
				 record=new  NovelRecord();
				 record.setNovel_detail_id(novel_detail_id);
				 record.setNovel_id(novel_id);
				 record.setUser_id(user_id);
				 record.setUpdate_time(new Date());
			 }
			 record.setNovel_detail_id(novel_detail_id);
			 record.setUpdate_time(new Date());
			 novelService.saveAndUpdate(record);
			return JSONResponse.success(json,pay_status,"读取小说成功");
		}
		
		WeixinUserInfo user=userService.findUserById(user_id);
		if(user==null){
			 return	JSONResponse.error("用户未注册");
			}
		if(user.getBook_peas()<businessConfig.chapter_peas){
			return JSONResponse.error("书豆不足");
		}
		commonService.saveBuyChapter(user,novel_detail_id, user_id);
		 pay_status=1;
		 conment=ReadTXT.getFile(detail.getChapter_url());
		 json.put("conment", conment);
		 logger.info("保存阅读记录");
		 if(record==null){
			 record=new  NovelRecord();
			 record.setNovel_detail_id(novel_detail_id);
			 record.setNovel_id(novel_id);
			 record.setUser_id(user_id);
			 record.setUpdate_time(new Date());
		 }
		 record.setNovel_detail_id(novel_detail_id);
		 record.setUpdate_time(new Date());
		 novelService.saveAndUpdate(record);
		return JSONResponse.success(json,pay_status,"读取小说成功");
	}
	
	
	/**
	 * 读取小说(未用到)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/novel_read", method = {RequestMethod.POST,RequestMethod.GET})
	public JSONResponse novel_read(int novel_detail_id,int user_id, int novel_id) {
		logger.info("进入判断小说是否收费,并读取小说");
		logger.info("novel_detail_id："+novel_detail_id+"user_id:"+user_id+"novel_id:"+novel_id);
		int pay_status=0;
		String key = "novel_detail_"+novel_detail_id;
		NovelDetail detail=novelService.findNovelDetailByID(novel_detail_id);
		List<String> conment=new ArrayList<>();
	    ValueOperations<String,List<String>> operations = redisTemplate.opsForValue();
	    boolean  hasKey = redisTemplate.hasKey(key);
	    if(hasKey){
	    	 logger.info("从缓存中读取");
	    	 conment=operations.get(key);
	    }else{
	    	 logger.info("从本地读取");
	    	 conment=ReadTXT.getFile(detail.getChapter_url());
	    	 operations.set(key, conment,1,TimeUnit.HOURS);
	    }
		if(detail==null){
			 return	JSONResponse.error("章节不存在");
			}
		if(detail.getFree_state()==0){//免费
			 pay_status=1;
			return JSONResponse.success(conment,pay_status,"读取小说成功");
		}
		//已购买过
		int vip_status = userService.findUserByIdAndVip(user_id);// 是否在vip时间内
		int buy_status = novelService.findNovelBuyByNovelID(novel_id);// 是否已购买本小说
		int chapter_status = commonService.findChapterBuyByNovelID(novel_detail_id,user_id);// 是否已购买本章节小说
		if(vip_status>0 ||buy_status>0 || chapter_status>0){
			 pay_status=1;
			return JSONResponse.success(conment,pay_status,"读取小说成功");
		}
		
		WeixinUserInfo user=userService.findUserById(user_id);
		if(user==null){
			 return	JSONResponse.error("用户未注册");
			}
		if(user.getBook_peas()<businessConfig.chapter_peas){
			return JSONResponse.error("书豆不足");
		}
		commonService.saveBuyChapter(user,novel_detail_id, user_id);
		 pay_status=1;
		return JSONResponse.success(conment,pay_status,"读取小说成功");
	}
}
