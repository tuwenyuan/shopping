package com.twy.util;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.twy.domain.User;

public class SendMail extends Thread {
	private User user;
	private String contextPath;
	public SendMail(User user, String contextPath) {
		this.user = user;
		this.contextPath =  contextPath;
	}
	public void run() {
		try {
			//得到Session
			Session session = MailSessionUtil.getSession();
			session.setDebug(true);
			//创建邮件内容
			MimeMessage msg = new MimeMessage(session);
//			msg.setFrom(new InternetAddress("itheimacloud@163.com"));//官方账户noreply@163.com webmaster@163.com
			msg.setFrom(new InternetAddress("master@itheima.com"));//官方账户noreply@163.com webmaster@163.com
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(user.getEmail()));
			msg.setSubject("欢迎加入小小书店，请激活您的帐号");
			
			msg.setContent("亲爱的会员："+user.getUsername()+"<br/>欢迎加入，请点击以下链接激活您的账户<br/><a href='http://localhost:8080"+contextPath+"/client/ClientServlet?operation=active&code="+user.getCode()+"'>激活</a><br/>这是一封系统邮件，请勿回复。", "text/html;charset=UTF-8");
			msg.saveChanges();
			
			//发送邮件
			Transport ts = session.getTransport();
//			ts.connect("itheimacloud", "iamsorry");//外网
			ts.connect("master", "123");
			ts.sendMessage(msg, msg.getAllRecipients());
			ts.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	

}
