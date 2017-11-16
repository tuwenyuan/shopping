<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
	<br/>
    <form action="${pageContext.request.contextPath}/client/ClientServlet?operation=login" method="post">
    	*用户名：<input type="text" name="username"/><br/>
    	*密码：<input type="password" name="password"/><br/>
    	<input type="submit" value="登陆"/>
    </form>
    
  </body>
</html>
