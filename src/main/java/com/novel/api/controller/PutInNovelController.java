package com.novel.api.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.novel.api.entity.Novel;
import com.novel.api.entity.NovelDetail;
import com.novel.api.service.CommonService;
import com.novel.api.service.NovelService;

/**
 * 导入小说
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/input_novel")
public class PutInNovelController {
	Logger logger = LoggerFactory.getLogger(PutInNovelController.class);
	@Autowired
	NovelService novelService;
	@Autowired
	CommonService commonService;
	/**
	 * 生成小说文件夹
	 * 
	 * @param novel
	 * @return 成功生成返回文件夹名，已存在返回null
	 */
	/*private  String genarateFolder(File novel) {
		if (!novel.isFile() || !novel.getAbsolutePath().endsWith(".txt")) {
			return null;
		}
		String novelName = novel.getAbsolutePath();
		String folderName = novelName.substring(0, novelName.indexOf(".txt"));
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdirs();
			return folderName;
		}
		return null;
	}*/
	/**
	 * 生成小说文件夹
	 * 
	 * @param novel
	 * @return 成功生成返回文件夹名，已存在返回null
	 */
	private  String genarateFolderNum(File novel, int novel_id) {
		if (!novel.isFile() || !novel.getAbsolutePath().endsWith(".txt")) {
			return null;
		}
		String path="D:\\book\\"+novel_id;
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
			return path;
		}
		return null;
	}
	/**
	 * 输出html文件
	 * 
	 * @param bodyContent
	 * @param currentFileName
	 * @param currentPageIndex
	 * @throws Exception
	 */
	private  void generateChapterHtmlFile(int currentPageIndex, String content, List<Object> chapterList,
			String folderName ,int novel_id) throws Exception {
		String pageContent = content;
		NovelDetail detail=new NovelDetail();
		detail.setChapter_name(chapterList.get(currentPageIndex).toString());
		detail.setNovel_id(novel_id);
		detail.setFree_state(1);
		detail.setUpdate_time(new Date());
		currentPageIndex=currentPageIndex+1;
		String filePath = folderName + "\\" + currentPageIndex + ".txt";
		
		String url="http://xs.ylnnt.com/upload/books/" +novel_id+"/"+ currentPageIndex + ".txt";
		detail.setChapter_url(url);
		novelService.saveAndUpdate(detail);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
		out.print(pageContent);
		out.flush();
		out.close();
	}

	/**
	 * 获取章节列表
	 * 
	 * @param novel
	 * @throws Exception
	 */
	private   JSONObject getChapterList(File novel) throws Exception {
		JSONObject json=new JSONObject();
		List<String> chapterList = new ArrayList<String>();
		FileInputStream fileInputStream = new FileInputStream(novel);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, getCharsetOfNovel(novel));
		BufferedReader novelbr = new BufferedReader(inputStreamReader);
		int currentIndex = 1;
		String line = novelbr.readLine();
		while (line != null) {
//			line = line.replaceAll("\\s*", "");
//			if ((line.indexOf("章") == 0 && line.indexOf("节") == 1 && appearNumber(line, "章") > 1)
//					|| (line.indexOf("章") == 0 && line.indexOf("节") == 1 && line.indexOf("第") != -1)) {
			
//			System.out.println(line.indexOf("第"));
//			System.out.println(appearNumber(line, "章"));
				if ((line.indexOf("第") == 0  && appearNumber(line, "章") > 0)) {
				System.out.println(line);
//				chapterList.add(line.substring(line.indexOf("章") + 2));
				chapterList.add(line);
				currentIndex++;
			}
			line = novelbr.readLine();
		}
		String fileName=novel.getName();
		fileName=fileName.substring(0, fileName.indexOf(".txt"));
		Novel info =new Novel();
		info.setNovel_name(fileName);
		info.setNovel_update_time(new Date());
		info.setSex_status(0);
		info.setHot_status(4);
		info.setNovel_state(1);
		info.setType_status(1);
		info=novelService.saveAndUpdate(info);
		System.out.println(chapterList);
		novelbr.close();
		fileInputStream.close();
		json.put("chapterList", chapterList);
		json.put("novel_id", info.getId());
		return json;
	}
public  void generate(File novel) throws Exception {
		
		JSONObject json=getChapterList(novel);
		List<Object> chapterList = json.getJSONArray("chapterList");
		int novel_id=json.getIntValue("novel_id");
		
		String folderName = genarateFolderNum(novel,novel_id);
		if (folderName == null) {
			return;
		}
		
		FileInputStream fileInputStream = new FileInputStream(novel);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, getCharsetOfNovel(novel));
		BufferedReader novelbr = new BufferedReader(inputStreamReader);
		int currentPageIndex = -1;
		StringBuilder content = new StringBuilder();
		String line = novelbr.readLine();
		while (line != null) {
//			if ((line.indexOf("章") == 0 && line.indexOf("节") == 1 && appearNumber(line, "章") > 1)
//					|| (line.indexOf("章") == 0 && line.indexOf("节") == 1 && line.indexOf("第") != -1)) {
			if ((line.indexOf("第") == 0  && appearNumber(line, "章") > 0)) {
				if (currentPageIndex > -1) {
					generateChapterHtmlFile(currentPageIndex, content.toString(), chapterList, folderName,novel_id);
					content.delete(0, content.length());
				}
				currentPageIndex++;
			}
			else if (currentPageIndex > -1) {
				content.append(line + "\r\n");
			}
			line = novelbr.readLine();
		}
		novelbr.close();
		fileInputStream.close();
	}
	/**
	 * 判断TXT文件编码方式
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private  String getCharsetOfNovel(File novel) throws IOException {
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(novel));
		byte[] head = new byte[3];
		bin.read(head, 0, head.length);
		String encoding = "gb2312";
		if (head[0] == -1 && head[1] == -2)
			encoding = "UTF-16";
		if (head[0] == -2 && head[1] == -1)
			encoding = "Unicode";
		if (head[0] == -17 && head[1] == -69 && head[2] == -65)
			encoding = "UTF-8";
		return encoding;
	}

	

	/**
	 *  * 获取指定字符串出现的次数  *  * @param srcText 源字符串  * @param findText 要查找的字符串
	 *  * @return  
	 */
	public  int appearNumber(String srcText, String findText) {
		int count = 0;
		Pattern p = Pattern.compile(findText);
		Matcher m = p.matcher(srcText);
		while (m.find()) {
			count++;
		}
		return count;
	}

	/**
	 * 小说添加
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/add_novel", method = RequestMethod.POST)
	public String add_novel(HttpServletRequest request) throws Exception {
		File folder = new File("D:\\books");
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			this.generate(files[i]);
		}
		return null;
	}
}
