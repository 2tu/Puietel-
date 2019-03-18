package com.foxhis.itf.ctrlroom.puietel.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.foxhis.itf.handler.ICtrlRoomHandler;

@Path("ctrlroom/")
public class FetchRmStaController {
	
	private static ICtrlRoomHandler ctrlRoomHandler;
	
	static
	{
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("remoteContext.xml");
		ctrlRoomHandler = (ICtrlRoomHandler)applicationContext.getBean("rmsta");
	}
	
	
	@POST
	@Path("fetchPmsRoomStatus")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON )
	public String fetchRmSta(Request req)
	{
		System.out.println("cmd="+req.getQuery());
		String result_json = ctrlRoomHandler.getRmSta("fetchPmsRoomStatus");
		System.out.println(result_json);
		return result_json;
	}
	

}
