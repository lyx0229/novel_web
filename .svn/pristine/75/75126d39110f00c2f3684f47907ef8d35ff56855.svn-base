package com.weixin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.novel.api.enums.EnumMethod;
import com.weixin.constants.WXConstants;
import com.weixin.pojo.AccessToken;
import com.weixin.pojo.WXjsTicket;


/**
 * 公众平台通用接口工具类
 * 
 */
public class WechatAccessToken {
	
	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static  AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;
		String requestUrl = WXConstants.ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
		
		JSONObject jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
		if(jsonObject==null){
			jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
		}
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
			}
		}
		return accessToken;
	}
	private static Logger log = LoggerFactory.getLogger(WechatAccessToken.class);
	
	
	public static WXjsTicket getWXjsTicket(String accessToken) {
		WXjsTicket wXjsTicket = null;
		String requestUrl= WXConstants.JSAPI_URL.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求获取凭证
		JSONObject jsonObject = HttpRequestUtil.httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wXjsTicket = new WXjsTicket();
				wXjsTicket.setJsTicket(jsonObject.getString("ticket"));
				wXjsTicket.setJsTicketExpiresIn(jsonObject.getIntValue("expires_in"));
			} catch (JSONException e) {
				wXjsTicket = null;
				// 获取wXjsTicket失败
				log.error("获取wXjsTicket失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return wXjsTicket;
	}
	
	

}