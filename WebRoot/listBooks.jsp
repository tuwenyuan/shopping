<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
    <table style="font-size: 12px">
    	<tr>
    		<c:forEach items="${page.records}" var="b">
	    		<td>
	    			<img src="${pageContext.request.contextPath}/images/${b.photo}" alt="${b.name}"></img><br/>
	    			书名：${b.name}<br/>
	    			作者：${b.author}<br/>
	    			原价：<strike>888</strike><br/>
	    			跳楼价：${b.price}<br/>
	    			<a href="${pageContext.request.contextPath}/client/ClientServlet?operation=buy&bookId=${b.id}">购买</a>
	    		</td>
    		</c:forEach>
    	</tr>
    </table>
    <%@include file="/commons/page.jsp"%>
  </body>
</html>
