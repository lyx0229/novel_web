package com.novel.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadTXT {

	/**
	 * 读取txt文件的内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	public static List<String> getFile(String urlPath) {
		List<String> contents = new ArrayList<String>();
		URL url;
		try {
			url = new URL(urlPath);
			URLConnection conn = url.openConnection();
			conn.connect();
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String line;
				while ((line = br.readLine()) != null) {
					contents.add(line);
				}
			} else {
				System.out.println("找不到文件 " + urlPath);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("完成读取文件");
		return contents;
	}

	/*public static List<String> GetParagraph(String urlPath) {
		ArrayList<String> res = new ArrayList<String>();// 段落切分结果
		StringBuilder sb = new StringBuilder();// 拼接读取的内容(while循环中不到断尾时,将字符一个个加入拼接)
		String temp = null;// 临时变量，存储sb去除空格的内容
		URL url;
		try {
			url = new URL(urlPath);
			URLConnection conn = url.openConnection();
			conn.connect();
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "gbk");
				BufferedReader reader = new BufferedReader(isr);
				int ch = 0;
				while ((ch = reader.read()) != -1) {// 注意文本最后一段末尾不是'\r'。所以ch=-1时，最后一段sb已经拼接的temp还没有存入res.
					temp = sb.toString().trim().replaceAll("\\s+", "");// 去除前后空格，之后去除中间空格
					if ((char) ch == '\r') {
						// 判断是否是空行
						if (!"".equals(temp)) {
							// 说明到了段落结尾，将其加入链表，并清空sb
							res.add(temp);
						}
						sb.delete(0, sb.length());// 清空sb
						temp = null;
					} else {
						// 说明没到段落结尾，将结果暂存
						sb.append((char) ch);
					}
				}
				// if (reader.read() == -1)
				// {//注意文本最后一段末尾没有'\r'。所以ch=-1时，最后一段temp还没有存入res.
				// System.out.println("哈哈，你读到了末尾嘞！");
				// }
				// 最后一段如果非空， 将最后一段加入，否则不处理
				if (!"".equals(temp)) {
					res.add(temp);
				}
			} else {
				System.out.println("找不到文件 " + urlPath);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("完成读取文件");
		return res;
	}*/

}
