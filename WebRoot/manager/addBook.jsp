<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/login/header.jsp"%>
	
	
    <form action="${pageContext.request.contextPath}/manager/ManagerServlet?operation=addBook" method="post" enctype="multipart/form-data">
  	<div class="divNormalClass">
  		书名：<input type="text" name="name" /><br/>
  		作者：<input type="text" name="author" /><br/>
  		单价：<input type="text" name="price" /><br/>
  		图片：<input type="file" name="image" /><br/>
  		描述：<textarea rows="3" cols="38" name="description"></textarea><br/>
  		所属分类：<select name="categoryId">
  			<c:forEach items="${applicationScope.cs}" var="c">
  				<option value="${c.id}">${c.name}</option>
  			</c:forEach>
  		</select>
  		<input type="submit" value="保存"/>
  	</div>
  </form> 
  </body>
</html>
