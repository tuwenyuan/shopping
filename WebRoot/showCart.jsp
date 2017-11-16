<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
	<br/>
	<h3>您购买的商品如下</h3>
	
	<c:if test="${empty sessionScope.cart.items}">
		<h4>您还没有购买任何商品</h4>
	</c:if>
	<c:if test="${!empty sessionScope.cart.items}">
	    <table style="font-size: 12px" width="68%" border="1">
	    	<tr>
	    		<th>书名</th>
	    		<th>作者</th>
	    		<th>单价</th>
	    		<th>数量</th>
	    		<th>小计</th>
	    		<th>操作</th>
	    	</tr>
	    	<c:forEach items="${sessionScope.cart.items}" var="me" varStatus="vs">
	    		<tr bgcolor="${vs.index%2==0?'#ADADAD':'#7DD96C'}">
		    		<td>${me.value.book.name}</td>
		    		<td>${me.value.book.author}</td>
		    		<td>${me.value.book.price}</td>
		    		<td>${me.value.num}</td>
		    		<td>${me.value.price}</td>
		    		<td>
		    			[<a href="#">删除</a>]
		    		</td>
		    	</tr>
	    	</c:forEach>
	    	<tr>
	    		<td colspan="6">
	    			总计数量：${sessionScope.cart.totalNum}&nbsp;&nbsp;
	    			应付款：${sessionScope.cart.totalPrice}&nbsp;&nbsp;
	    			<a href="${pageContext.request.contextPath}/client/ClientServlet?operation=genOrders">生成订单</a>
	    		</td>
	    	</tr>
	    </table>
    </c:if>
  </body>
</html>
