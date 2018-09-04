package com.novel.api.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.novel.api.entity.ChapterBuyInfo;
import com.novel.api.entity.ExchangeDetail;
import com.novel.api.entity.JSONResponse;
import com.novel.api.entity.PlayTourDetail;
import com.novel.api.entity.PlayTourGoods;
import com.novel.api.entity.WeixinUserInfo;
import com.novel.api.enums.EnumMethod;
import com.novel.api.pojo.PlayTourListView;
import com.novel.api.pojo.PlayTourView;
import com.novel.api.repository.ChapterBuyRepository;
import com.novel.api.repository.ExchangeDetailRepository;
import com.novel.api.repository.PlayTourDetailRepository;
import com.novel.api.repository.PlayTourGoodsRepository;
import com.novel.config.BusinessConfig;
import com.weixin.constants.WXConstants;
import com.weixin.utils.HttpRequestUtil;
import com.weixin.utils.WechatAccessToken;

@Service
public class CommonService {
	@Autowired
	ExchangeDetailRepository exchangeRep;
	@Autowired
	PlayTourGoodsRepository playTourGoodsRep;
	@Autowired
	PlayTourDetailRepository playTourDetailRep;
	@Autowired
	UserService userService;
	@Autowired
	ChapterBuyRepository chapterBuyRep;
	@Autowired
	BusinessConfig businessConfig;
	@Autowired
	private EntityManager entityManager;
	/**
	 * 更新兑换书豆信息
	 * 
	 * @param eventDetail
	 * @return
	 */
	public ExchangeDetail saveAndupdate(ExchangeDetail eventDetail) {
		return exchangeRep.saveAndFlush(eventDetail);
	}
	/**
	 * 购买章节
	 * 
	 * @param eventDetail
	 * @return
	 */
	public ChapterBuyInfo saveAndupdate(ChapterBuyInfo chapterBuyInfo) {
		return chapterBuyRep.saveAndFlush(chapterBuyInfo);
	}

	/**
	 * 兑换书豆记录查询
	 * 
	 * @param userid
	 * @return
	 */
	public List<ExchangeDetail> findExchangeDetailByUserid(int userid) {
		return exchangeRep.findExchangeDetailByUserid(userid);
	}

	/**
	 * 更新打赏信息
	 * 
	 * @param entity
	 * @return
	 */
	@Transactional
	public JSONResponse savePlayTour(int novel_id,int userid,int goods_id) {
		WeixinUserInfo user=userService.findUserById(userid);
		if(user==null){
		 return	JSONResponse.error("用户未注册");
		}
		int book_peas=user.getBook_peas();//剩余书豆
		PlayTourGoods goods=playTourGoodsRep.findOne(goods_id);
		int bookpeas=goods.getBookpeas();//礼物价值书豆
		if(book_peas<bookpeas){
			return	JSONResponse.error(-1,"剩余书豆不足，请充值");
		}
		user.setBook_peas(book_peas-bookpeas);
		userService.saveAndUpdate(user);
		PlayTourDetail entity= new PlayTourDetail();
		entity.setGoods_id(goods_id);
		entity.setNovel_id(novel_id);
		entity.setUser_id(userid);
		entity.setUpdate_time(new Date());
		playTourDetailRep.saveAndFlush(entity);
		
		return	JSONResponse.success(user);
	}

	/**
	 * 打赏礼物查询
	 * 
	 * @return
	 */
	public List<PlayTourGoods> findPlayTourGoods() {
		return playTourGoodsRep.findAll();
	}
	/**
	 * 小说打赏礼物列表
	 * 
	 * @return
	 */
	public	List<PlayTourView> findByPlayTourViewByNovelId(int novel_id) {
		
		String sql = "select p.*,u.nick_name,u.head_imgurl,n.goods_name from tbl_play_tour_detail p  "
	         +"left join  tbl_play_tour_goods n on  p.goods_id=n.id "
	         +"left join tbl_user u on p.user_id=u.id where p.novel_id=:novel_id order by p.update_time desc";
		// 执行原生SQL
		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter("novel_id", novel_id);

		// 指定返回对象类型
		nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(PlayTourView.class));
		// 返回对象 List<T>
		@SuppressWarnings("unchecked")
		List<PlayTourView> list = nativeQuery.getResultList();
		if (list.size() == 0) {
			return null;
		}
		return list;
	}
	/**
	 * 小说打赏礼物排行
	 * 
	 * @return
	 */
	public	List<PlayTourListView> findByPlayTourListViewByNovelId(int novel_id) {
		
		String sql = "select sum(bookpeas) as  bookpeas ,max(nick_name) nick_name,max(head_imgurl) as head_imgurl , a.* "
	         +" from tbl_play_tour_detail a left join tbl_play_tour_goods b on  a.goods_id = b.id  "
	         +" left join tbl_user c on a.user_id= c.id where a.novel_id=:novel_id  group by a.user_id order by bookpeas desc ";
			// 执行原生SQL
			Query nativeQuery = entityManager.createNativeQuery(sql);
			nativeQuery.setParameter("novel_id", novel_id);

			// 指定返回对象类型
			nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(PlayTourListView.class));
			// 返回对象 List<T>
			@SuppressWarnings("unchecked")
			List<PlayTourListView> list = nativeQuery.getResultList();
			if (list.size() == 0) {
				return null;
			}
			return list;
	}
	
	
	/**
	 * 章节购买更新信息
	 * 
	 * @param entity
	 * @return
	 */
	@Transactional
	public JSONResponse saveBuyChapter(WeixinUserInfo user,int novel_detail_id,int userid) {
		
		int book_peas=user.getBook_peas();//剩余书豆
		user.setBook_peas(book_peas-businessConfig.chapter_peas);
		userService.saveAndUpdate(user);
		ChapterBuyInfo entity= new ChapterBuyInfo();
		entity.setNovel_detail_id(novel_detail_id);
		entity.setUser_id(userid);
		entity.setUpdate_time(new Date());
		chapterBuyRep.saveAndFlush(entity);
		return	JSONResponse.success(user);
	}
	
	/**
	 * 打赏统计
	 * 
	 * @param id
	 * @return
	 */
	public int findGoodsByNovelId(Integer id) {
		return playTourDetailRep.findGoodsByNovelId(id);
	}
	
	/**
	 * 查询是否购买改章节
	 * 
	 * @param id
	 * @return
	 */
	public int findChapterBuyByNovelID(int novel_detail_id,int user_id) {
		return chapterBuyRep.findChapterBuyByNovelID(novel_detail_id,user_id);
	}
	/**
	 * 发送消息
	 * @param touser
	 * @param msg
	 */
	public void sendMsg(String touser, String msg){
		String sUrl;
		try {
			sUrl = WXConstants.SEND_MSG_URL.replace("ACCESS_TOKEN", WechatAccessToken.getAccessToken(businessConfig.APPID, businessConfig.APPSECRET).getToken());
			String param = "{\"touser\":\"" + touser + "\",\"msgtype\":\"text\","
					+ "\"text\":"
					+ "{\"content\":\"" + msg + "\"}}"
					;
			JSONObject jsonObject = HttpRequestUtil.httpRequest(sUrl, EnumMethod.POST.name(), param);
			boolean containsValue = jsonObject.containsKey("errcode");
			if(containsValue){
				String errcode = jsonObject.getString("errcode");
				System.err.println("SendMsgUtil|sendMsg|errcode:"+errcode);
				//errcode 为0表示成功
				//错误了就需要看返回码表了 http://mp.weixin.qq.com/wiki/10/6380dc743053a91c544ffd2b7c959166.html
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
