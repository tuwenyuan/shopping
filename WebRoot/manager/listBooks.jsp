<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/login/header.jsp"%>
	<table frame="border" width="68%">
		<tr>
			<th>书名</th>
			<th>作者</th>
			<th>单价</th>
			<th>描述</th>
			<th>所属分类</th>
			<th>图片</th>
			<th>操作</th>
		</tr>
		 <c:forEach items="${page.records}" var="b" varStatus="vs">
	    	<tr bgcolor="${vs.index%2==0?'#ADADAD':'#7DD96C'}">
				<td nowrap="nowrap">${b.name}</td>
				<td nowrap="nowrap">${b.author}</td>
				<td nowrap="nowrap">${b.price}</td>
				<td nowrap="nowrap">${b.description}</td>
				<td nowrap="nowrap">${myfn:showCategoryName(b.categoryId)}</td>
				<td nowrap="nowrap">
					<a href="${pageContext.request.contextPath}/images/${b.photo}">查看图片</a>
				</td>
				<td nowrap="nowrap">
					[<a href="#">修改</a>]
					[<a href="#">删除</a>]
				</td>
			</tr>
    	</c:forEach>
	</table>
    <%@include file="/commons/page.jsp" %>
  </body>
</html>
