package com.foxhis.itf.ctrlroom.puietel.utils;

import java.net.URI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class HttpServerUtils {

	private static final URI baseuri= URI.create("http://localhost:8080/xcs/"); 

	private static Log logger = LogFactory.getLog(HttpServerUtils.class);



	public static void start0()
	{
		ResourceConfig rc = new PackagesResourceConfig("com.foxhis.itf.ctrlroom.puietel.controller");
		rc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);  
		try {

			final HttpServer server = GrizzlyServerFactory.createHttpServer(baseuri, rc);

			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					server.stop();
					System.out.println("httpsever is closing");

				}
			}){

			});
			server.start();
			logger.info("启动httpserver");
			//Thread.currentThread().join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("启动httpserver失败",e);
		}

	}

	/*private static ResourceConfig create(){

		ResourceConfig config = new ResourceConfig(FetchRmStaController.class);
		return config;

	}*/
	public static void main(String[] args) {
		HttpServerUtils.start0();
	}

}
