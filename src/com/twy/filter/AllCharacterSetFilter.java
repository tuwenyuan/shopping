package com.twy.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class AllCharacterSetFilter implements Filter {
	private FilterConfig filterConfig;
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		String encoding = filterConfig.getInitParameter("encoding");
		if(encoding==null)
			encoding = "UTF-8";
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		request.setCharacterEncoding(encoding);//POST请求方式的中文乱码
		response.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset="+encoding);//输出中文时木有乱码
		
		
		MyHttpServletRequest1 mrequest = new MyHttpServletRequest1(request);
		
		chain.doFilter(mrequest, response);
	}

	public void destroy() {

	}

}
class MyHttpServletRequest1 extends HttpServletRequestWrapper{
	
	public MyHttpServletRequest1(HttpServletRequest request){
		super(request);
	}
	//改写getParameter方法

	public String getParameter(String name) {
		String value = super.getParameter(name);
		if(value==null)
			return null;
		//判断是否是get请求方式，如果是：手工编码
		if("get".equalsIgnoreCase(super.getMethod())){//针对get方式：手工编码
			try {
				value = new String(value.getBytes("ISO-8859-1"),super.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
}
