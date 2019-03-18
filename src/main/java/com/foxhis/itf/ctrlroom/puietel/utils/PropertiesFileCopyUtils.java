package com.foxhis.itf.ctrlroom.puietel.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesFileCopyUtils {

	private static final String foxprofile = "ctrlroom.properties";
	private static Log logger = LogFactory.getLog(PropertiesFileCopyUtils.class);

	private static Properties properties;
	
	public static Properties getProperties(){
		
		return properties;
	}
	/**
	 * 初始化房控配置文件
	 * @return
	 */
	public static boolean iniProfile()
	{
		String filename = System.getProperty("user.dir") + File.separator +foxprofile;
		File f = new File(filename);
		boolean isFirstTime = !f.exists();
		//如果文件不存在属于首次加载，则将包中的配置内容拷贝到当前目录
		if (isFirstTime) {
			try {
				InputStream in = loadFile(PropertiesFileCopyUtils.class, "/com/foxhis/itf/ctrlroom/ostv/config/"+foxprofile);
				FileUtils.copyInputStreamToFile(in, f);
			} catch (Exception e) {
				String message = MessageFormat.format("无法创建客控配置[{0}]，请联系系统管理员", new Object[] { foxprofile });
				logger.error(message,e);
				return false;
			}
		}
		Properties foxproperties = new Properties();
		try {
			foxproperties.load(new FileInputStream(f));
			properties = foxproperties;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String message = MessageFormat.format("无法载入客控配置[{0}]，请联系系统管理员", new Object[] { foxprofile });
			logger.error(message,e);
			return false;
		}
		return true;
	}
	
	private static InputStream loadFile(Class<?> clazz, String resource)
	{
		return clazz.getResourceAsStream(resource);

	}
	
}






