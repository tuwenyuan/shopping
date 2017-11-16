package com.twy.util;

import java.util.ResourceBundle;

public class PropertyUtil {
	public static String getValue(String key){
		ResourceBundle rb = ResourceBundle.getBundle("merchantInfo");
		return rb.getString(key);
	}
}
