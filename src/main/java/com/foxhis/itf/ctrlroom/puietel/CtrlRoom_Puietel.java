package com.foxhis.itf.ctrlroom.puietel;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.foxhis.itf.ctrlroom.puietel.utils.ContantsUtils;
import com.foxhis.itf.ctrlroom.puietel.utils.HttpClientUtils;
import com.foxhis.itf.ctrlroom.puietel.utils.HttpServerUtils;
import com.foxhis.itf.ctrlroom.puietel.utils.PropertiesFileCopyUtils;
import com.foxhis.itf.handler.abstractimpl.AbstractCtrlRoomHandler;

/**
 * 普杰房控接口!
 *@author tq
 *@version 1.0
 */
public class CtrlRoom_Puietel extends AbstractCtrlRoomHandler
{
	
	private static Log logger = LogFactory.getLog(CtrlRoom_Puietel.class);
	private String url;
	private Properties properties;
	private int success = 0;
	static
	{
		//启动httpserver服务
		HttpServerUtils.start0();
		//检查房控配置参数
	    PropertiesFileCopyUtils.iniProfile();	
		
	}
	
	public CtrlRoom_Puietel() {
		// TODO Auto-generated constructor stub
		properties = PropertiesFileCopyUtils.getProperties();
		checkPropertiesNotEmtry();
		this.url = properties.getProperty("url");
		logger.info(MessageFormat.format("配置参数为：url={0}", new Object[] {url}));
	}

	private void checkPropertiesNotEmtry()
	{
		if(properties == null) {
			logger.error("客控配置参数不能为空");
			throw new NullPointerException();
		}
	}
	
	@Override
	public Map<String, Object> ctrlroomCkin(Map<String, Object> input) {
		// TODO Auto-generated method stub
		Map<String, Object> reMap = new HashMap<String, Object>();
		try {
			String ckindata = ContantsUtils.getCkinData(input);
			String response  = HttpClientUtils.doPost(url, ckindata);
		    Map<String, Object> result = ContantsUtils.jsonToMap(response);
		    if(null!=result && !result.isEmpty())
		    { 
		    	int resultCode = (Integer)result.get("resultCode");
		    	if(success==resultCode)
				{
					reMap.put("result", true);
				}
				else {
					reMap.put("result", false);
				}
				reMap.put("msg", result.get("msg"));
				logger.info(reMap);
				return reMap;
		    }
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Ckin request fail",e);
			reMap.put("result", false);
			reMap.put("msg",e.getMessage());
		}
		return reMap;
		
	}
	
	@Override
	public Map<String, Object> ctrlroomCkot(Map<String, Object> input) {
		// TODO Auto-generated method stub
		Map<String, Object> reMap = new HashMap<String, Object>();
		try {
			String ckotdata = ContantsUtils.getCkotData(input);
			String response  = HttpClientUtils.doPost(url, ckotdata);
		    Map<String, Object> result = ContantsUtils.jsonToMap(response);
		    if(null!=result && !result.isEmpty())
		    { 
		    	int resultCode = (Integer)result.get("resultCode");
		    	if(success==resultCode)
				{
					reMap.put("result", true);
				}
				else {
					reMap.put("result", false);
				}
				reMap.put("msg", result.get("msg"));
				logger.info(reMap);
				return reMap;
		    }
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Ckot request fail",e);
			reMap.put("result", false);
			reMap.put("msg",e.getMessage());
		}
		return reMap;
	}
    
}
