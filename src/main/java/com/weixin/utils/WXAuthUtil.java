package com.weixin.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WXAuthUtil {
	static Logger logger = LoggerFactory.getLogger(WXAuthUtil.class); 
    private static final String TOKEN = "novel";
    public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
        JSONObject jsonObject =null;
//        DefaultHttpClient client = new DefaultHttpClient();
        HttpClient client = HttpClientBuilder.create().build();

        HttpGet httpGet =new HttpGet(url);
        HttpResponse response =  client.execute(httpGet);
        HttpEntity entity =response.getEntity();
        if(entity!=null)
        {
            //把返回的结果转换为JSON对象
            String result =EntityUtils.toString(entity, "UTF-8");
            jsonObject =JSON.parseObject(result);
        }
        httpGet.releaseConnection();
        return jsonObject;
    }
  	
  	/** 
       * 验证签名 
       * @param signature 
       * @param timestamp 
       * @param nonce 
       * @return 
       */  
  	public static boolean  checkSignature(String signature, String timestamp, String nonce){
  		String[] arr = new String[] { TOKEN, timestamp, nonce };  
  		// 将token、timestamp、nonce三个参数进行字典序排序  
  		Arrays.sort(arr);  
  		StringBuilder content = new StringBuilder();  
  		for (int i = 0; i < arr.length; i++) {  
  			content.append(arr[i]);  
  		}  
  		MessageDigest md = null;  
  		String tmpStr = null;  
   
  		try {  
  			//创建 MessageDigest对象,MessageDigest 通过其getInstance系列静态函数来进行实例化和初始化。
  			md = MessageDigest.getInstance("SHA-1");  
  			// 将三个参数字符串拼接成一个字符串进行sha1加密  
  			byte[] digest = md.digest(content.toString().getBytes());  
  			tmpStr = byteToStr(digest);  
  		} catch (NoSuchAlgorithmException e) {  
  			e.printStackTrace();  
  		}  
  		logger.info("执行微信签名加密认证");
  		content = null;  
  		// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信  
  		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;  
  	}
  		      
  		   
  	/** 
       * 将字节数组转换为十六进制字符串 
       * @param byteArray 
       * @return 
       */  
  	private static String byteToStr(byte[] byteArray) {  
  	
  		String strDigest = "";  
  		for (int i = 0; i < byteArray.length; i++) {  
   
  			strDigest += byteToHexStr(byteArray[i]);  
  		}  
  		return strDigest;  
  	}  
  			  
   
  	/** 
       * 将字节转换为十六进制字符串 
       * @param mByte 
       * @return 
       */  
  	private static String byteToHexStr(byte mByte) {  
   
  		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
  		char[] tempArr = new char[2];  
  		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
  		tempArr[1] = Digit[mByte & 0X0F];  
   
  		String s = new String(tempArr);  
  		return s;  
  	}  

}
