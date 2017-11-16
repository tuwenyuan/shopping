<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/login/header.jsp"%>

  <form action="${pageContext.request.contextPath}/servlet/ManagerServlet?operation=addCategory" method="post">
  	<div class="divNormalClass">
  		分类名称：<input type="text" name="name" /><br/>
  		分类描述：<textarea rows="3" cols="38" name="description"></textarea><br/>
  		<input type="submit" value="保存"/>
  	</div>
  </form> 
  </body>
</html>
