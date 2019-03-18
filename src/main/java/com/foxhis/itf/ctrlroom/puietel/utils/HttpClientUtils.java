package com.foxhis.itf.ctrlroom.puietel.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 使用java原始的http请求
 * @author Administrator
 *
 */
public class HttpClientUtils {
	
	private static Log logger = LogFactory.getLog(HttpClientUtils.class);
	
	/**
	 * Http的post请求
	 * @param url 请求的url地址
	 * @param jsonparam 请求的参数
	 * @param supid 厂商标志
	 * @param hotelid 厂商编码
	 * @param anqcode 安全码
	 * @return 返回请求结果
	 */
	public static String doPost(String url,String jsonparam) {
		String retStr = null;
		HttpURLConnection connection = null;
		try {
			URL requesturl = new URL(url);
			connection =(HttpURLConnection) requesturl.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			//设置允许用户交互
			connection.setAllowUserInteraction(true);
			//设置请求内容格式
			connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			//超时8秒
			connection.setConnectTimeout(8000);
			OutputStream  outputStream = connection.getOutputStream();
			logger.info(MessageFormat.format("请求的json数据{0}", jsonparam));
			outputStream.write(jsonparam.getBytes("utf-8"));
			outputStream.flush();
			outputStream.close();
			
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			StringBuffer buffer = new StringBuffer();
			String strMessage = "";
			while ((strMessage = bufferedReader.readLine()) != null) {
		        buffer.append(strMessage);
		      }
			strMessage = buffer.toString();
			bufferedReader.close();
			logger.info(MessageFormat.format("请求返回{0}", strMessage));
			return retStr=strMessage.trim();
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("doPost error",e);
		}
		return retStr;
	}

}
