<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
	<br/>
	<h3>您的订单信息如下</h3>
	
	<c:if test="${empty os}">
		<h4>您还没有下过任何订单</h4>
	</c:if>
    <c:if test="${!empty os}">
    	<table style="font-size: 12px" width="68%" border="1">
	    	<tr>
	    		<th>订单号</th>
	    		<th>数量</th>
	    		<th>订单金额</th>
	    		<th>订单状态</th>
	    		<th>操作</th>
	    	</tr>
	    	<c:forEach items="${os}" var="o" varStatus="vs">
	    		<tr bgcolor="${vs.index%2==0?'#ADADAD':'#7DD96C'}">
		    		<td><a href="${pageContext.request.contextPath}/client/ClientServlet?operation=showOrdersDetail&ordersId=${o.id}">${o.ordersnum}</a></td>
		    		<td>${o.num}</td>
		    		<td>${o.money}</td>
		    		<td>${o.status==0?'未付款':(o.status==1?'已付款':'已发货')}</td>
		    		<td>
		    			[<a href="#">修改</a>]
		    			[<a href="#">删除</a>]
		    			[<a href="http://localhost:8080/day21_onlinepay/pay.jsp?ordersnum=${o.ordersnum}&money=0.01">付款</a>]
		    		</td>
		    	</tr>
	    	</c:forEach>
	    </table>
    </c:if>
  </body>
</html>
