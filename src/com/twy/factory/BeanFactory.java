package com.twy.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



//单例类面试必面
public class BeanFactory {
	private static Properties props = new Properties();
	static{
		InputStream in =BeanFactory.class.getClassLoader().getResourceAsStream("dao.properties");
		try {
			props.load(in);
		} catch (IOException e) {
			throw new ExceptionInInitializerError("Read the config file 'dao.properties' war wrong!");
		}
	}
	
	private BeanFactory(){}
	private static final BeanFactory instance = new BeanFactory();
	public static BeanFactory getInstance(){
		return instance;
	}
	
	public <T>T getDaoImpl(String key,Class<T> clazz){
		try {
			return (T)Class.forName(props.getProperty(key)).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
//	public CategoryDao getCategoryDao(){
//		try {
//			return (CategoryDao)Class.forName(props.getProperty("CategoryDao")).newInstance();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
//	public BookDao getBookDao(){
//		try {
//			return (BookDao)Class.forName(props.getProperty("BookDao")).newInstance();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
//	public UserDao getUserDao(){
//		try {
//			return (UserDao)Class.forName(props.getProperty("UserDao")).newInstance();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
//	public OrdersDao getOrdersDao(){
//		try {
//			return (OrdersDao)Class.forName(props.getProperty("OrdersDao")).newInstance();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
}
