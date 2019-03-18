package com.foxhis.itf.ctrlroom.puietel;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.foxhis.itf.ctrlroom.puietel.utils.HttpServerUtils;
import com.foxhis.itf.handler.abstractimpl.AbstractCtrlRoomHandler;

/**
 * 普杰房控接口!
 *@author tq
 *@version 1.0
 */
public class CtrlRoom_Puietel extends AbstractCtrlRoomHandler
{
	
	private static Log logger = LogFactory.getLog(CtrlRoom_Puietel.class);
	
	//启动httpserver服务
	static
	{
		HttpServerUtils.start0();
	}

	@Override
	public Map<String, Object> ctrlroomCkin(Map<String, Object> input) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String, Object> ctrlroomCkot(Map<String, Object> input) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
