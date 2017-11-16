<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/login/header.jsp"%>

<br/>
<br/>
	
	<form id="f1" action="${pageContext.request.contextPath}/manager/ManagerServlet?operation=showPayedOrders" method="post">
		状态查询：<select name="status" onchange="document.getElementById('f1').submit()">
			<option value="0" ${numStatus==0?'selected="selected"':''}>未付款</option>
			<option value="1" ${numStatus==1?'selected="selected"':''}>已付款</option>
			<option value="2" ${numStatus==2?'selected="selected"':''}>已发货</option>
		</select>
	</form>
	<c:if test="${empty os}">
		<h4>没有任何订单</h4>
	</c:if>
    <c:if test="${!empty os}">
    	<table style="font-size: 12px" width="68%" border="1">
	    	<tr>
	    		<th>客户姓名</th>
	    		<th>订单号</th>
	    		<th>数量</th>
	    		<th>订单金额</th>
	    		<th>订单状态</th>
	    		<th>操作</th>
	    	</tr>
	    	<c:forEach items="${os}" var="o" varStatus="vs">
	    		<tr bgcolor="${vs.index%2==0?'#ADADAD':'#7DD96C'}">
	    			<td>${o.user.nick}</td>
		    		<td><a href="#">${o.ordersnum}</a></td>
		    		<td>${o.num}</td>
		    		<td>${o.money}</td>
		    		<td>${o.status==0?'未付款':(o.status==1?'已付款':'已发货')}</td>
		    		<td>
		    			<c:if test="${o.status==1}">
		    				<a href="${pageContext.request.contextPath}/manager/ManagerServlet?operation=sendBook&ordersNum=${o.ordersnum}">发货</a>
		    			</c:if>
		    			<c:if test="${o.status!=1}">
		    				不可操作
		    			</c:if>
		    		</td>
		    	</tr>
	    	</c:forEach>
	    </table>
    </c:if>
    
  </body>
</html>
