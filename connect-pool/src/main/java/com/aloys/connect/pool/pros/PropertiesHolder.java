package com.aloys.connect.pool.pros;


import java.io.InputStream;
import java.util.Properties;

public class PropertiesHolder extends Properties{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//定义属性文件名称
	private final static String PROPERTY_FILE = "datasource.properties";
	
	private static PropertiesHolder propertiesHolder = new PropertiesHolder();
	
	private PropertiesHolder(){
		try(InputStream input = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE)){
			this.load(input);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public static PropertiesHolder getInstance(){
		return propertiesHolder;
	}
}
