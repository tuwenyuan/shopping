<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/login/header.jsp"%>

	<table frame="border" width="68%">
		<tr>
			<th>选择</th>
			<th>序号</th>
			<th>分类名称</th>
			<th>描述</th>
			<th>操作</th>
		</tr>
		 <c:forEach items="${applicationScope.cs}" var="c" varStatus="vs">
	    	<tr bgcolor="${vs.index%2==0?'#ADADAD':'#7DD96C'}">
				<td>
					<input type="checkbox" name="ids" value="${c.id}"/>
				</td>
				<td>${vs.count}</td>
				<td>${c.name}</td>
				<td>${c.description}</td>
				<td>
					[<a href="#">修改</a>]
					[<a href="#">删除</a>]
				</td>
			</tr>
    	</c:forEach>
	</table>
  </body>
</html>
