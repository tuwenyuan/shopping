package com.twy.util;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//ID生成器
public class IdGenerator {
	public static String genPK(){
//		return UUID.randomUUID().toString();//在网络上生成一个唯一的字符串
		return new BigInteger(165, new Random()).toString(36).toUpperCase();
	}
	//20130724 1231321321321
	public synchronized static String genOrdersNum(){
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(now);//20130724
		return date+now.getTime();
	}
}
