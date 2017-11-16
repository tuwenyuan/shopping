package com.twy.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtil {
	//把表单的数据封装到JavaBean中
//	public static Object findFormData(Class clazz,HttpServletRequest request){
//		try {
//			Object obj = clazz.newInstance();//JavaBean的对象
//			BeanUtils.populate(obj, request.getParameterMap());//表单的字段名要与JavaBean的属性一致
//			return obj;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
	public static <T>T findFormData(Class<T> clazz,HttpServletRequest request){
		try {
			T obj = clazz.newInstance();//JavaBean的对象
			BeanUtils.populate(obj, request.getParameterMap());//表单的字段名要与JavaBean的属性一致
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
