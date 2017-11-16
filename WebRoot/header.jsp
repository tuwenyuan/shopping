<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.twy.com/jsp/jstl/myfn" prefix="myfn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>欢迎光临</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css"></link>
</head>

<body>
	${message}
	<br/>
	<h1>欢迎光临本小站</h1><br/>
	<a href="${pageContext.request.contextPath}">首页</a>
	<c:if test="${sessionScope.user==null}">
		<a href="${pageContext.request.contextPath}/regist.jsp">注册</a>
		<a href="${pageContext.request.contextPath}/login.jsp">登陆</a>
	</c:if>
	<c:if test="${sessionScope.user!=null}">
		欢迎您：${sessionScope.user.nick}
		<a href="${pageContext.request.contextPath}/client/ClientServlet?operation=logout">注销</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/client/ClientServlet?operation=showSelfOrders">我的订单</a>
	<a href="${pageContext.request.contextPath}/showCart.jsp">购物车</a><br/><br/>
	商品分类：
		<c:forEach items="${applicationScope.cs}" var="c">
  				<a href="${pageContext.request.contextPath}/client/ClientServlet?operation=showCategoryBooks&categoryId=${c.id}">${c.name}</a>
  		</c:forEach>
	<br/>
	<br/>
	
