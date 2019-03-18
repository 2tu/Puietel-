package com.foxhis.itf.ctrlroom.puietel.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ContantsUtils {
	
	private static final String CKIN_ACTION = "checkIn";
	private static final String CKOT_ACTION = "checkOut";
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getCkinData(Map<String, Object> input) throws Exception
	{
		String content = getContent(input,CKIN_ACTION);
		return  content;
		
	}
	
	public static String getCkotData(Map<String, Object> input) throws Exception
	{
		String content = getCkotContent(input,CKOT_ACTION);
		return  content;
		
	}
	
	private static String getCkotContent(Map<String, Object> input,String action) throws Exception
	{
		 JSONObject content = new JSONObject();
		 content.put("cmd", action);
		 content.put("room", repNullToStr(input.get("roomno")).trim());
		 content.put("checkOutTime", SDF.format((Date)input.get("checkouttime")));
		 return content.toJSONString();
	}
	
	private static String getContent(Map<String, Object> input,String action) throws Exception
	{
		 JSONObject content = new JSONObject();
		 content.put("cmd", action);
		 content.put("room", repNullToStr(input.get("roomno")).trim());
		 content.put("roomSuiteType", "");
		 content.put("checkinTime", SDF.format((Date)input.get("checkintime")));
		 content.put("expireTime", SDF.format((Date)input.get("checkouttime")));
		 
		 JSONObject guestinfo = new JSONObject();
         guestinfo.put("mobile", repNullToStr(input.get("mobile")).trim());
         guestinfo.put("lastName", repNullToStr(input.get("lastName")).trim());
         guestinfo.put("firstName",repNullToStr(input.get("firstName")).trim());
         guestinfo.put("sex", repNullToStr(input.get("gendername")).trim());
         guestinfo.put("idCard", repNullToStr(input.get("guestid")).trim());
       
         JSONArray guestinfoArray = new JSONArray();
         guestinfoArray.add(guestinfo);
         
         
         content.put("guestList", guestinfoArray);
         
         
		 return  content.toJSONString();
 
	}
	
	public static String repNullToStr(Object obj)
	{
		return obj==null?"":(String)obj;
	}
	
	public static Map<String, Object> jsonToMap(String jsonstr)
	{	
		Map<String, Object> reMap =new HashMap<String, Object>();
		try {
			reMap = JSONObject.parseObject(jsonstr);
		}
		catch (Exception e) {
			// TODO: handle exception
			reMap = null;
		}
		return reMap;
		
	}

}
