<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
	<br/>
    <form action="${pageContext.request.contextPath}/client/ClientServlet?operation=regist" method="post">
    	*用户名：<input type="text" name="username"/><br/>
    	*昵称：<input type="text" name="nick"/><br/>
    	*密码：<input type="password" name="password"/><br/>
    	*电话：<input type="text" name="phonenum"/><br/>
    	*邮寄地址：<input type="text" name="address"/><br/>
    	*邮箱：<input type="text" name="email"/><br/>
    	<input type="submit" value="注册"/>
    </form>
  </body>
</html>
