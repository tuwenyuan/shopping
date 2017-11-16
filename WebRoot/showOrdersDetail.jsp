<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
	<br/>
	<h3>订单详情</h3>
	收货地址：${sessionScope.user.address }<br/>
	收件人：${sessionScope.user.nick }<br/>
	电话：${sessionScope.user.phonenum }<br/><br/>
    <table style="font-size: 12px" width="68%" border="1">
	    	<tr>
	    		<th>商品名称</th>
	    		<th>单价</th>
	    		<th>数量</th>
	    		<th>小计</th>
	    		<th>操作</th>
	    	</tr>
	    	<c:forEach items="${items}" var="i" varStatus="vs">
	    		<tr bgcolor="${vs.index%2==0?'#ADADAD':'#7DD96C'}">
		    		<td>
		    			<a href="#">
		    				<img src="${pageContext.request.contextPath}/images/${i.book.photo}" width="54" height="54"/>
		    				${i.book.name}
		    			</a>
		    		</td>
		    		<td>${i.book.price}</td>
		    		<td>${i.num}</td>
		    		<td>${i.price}</td>
		    		<td>
		    			[<a href="#">修改</a>]
		    			[<a href="#">删除</a>]
		    		</td>
		    	</tr>
	    	</c:forEach>
	    </table>
  </body>
</html>
