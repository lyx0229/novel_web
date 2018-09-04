package com.novel.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {

	@SuppressWarnings("static-access")
	public static int toInt(String strNum) {
		if (strNum == null || "".equals(strNum)) {
			return 0;
		}
		Integer integer = new Integer(strNum);
		return integer.parseInt(strNum);
	}
	
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
	
	public static Date getDate(){
		// 取得本地时间：
		Calendar cal = Calendar.getInstance();
		//取得指定时区的时间：　　　　　　
		TimeZone zone = TimeZone.getTimeZone("GMT+8:00");
		 cal = Calendar.getInstance(zone);
		return cal.getTime();
	}
}
