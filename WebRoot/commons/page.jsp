<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 分页开始 -->
    第${page.pagenum}页/共${page.totalpage }页&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}${page.url}&pagenum=${page.pagenum-1>0?page.pagenum-1:1}">上一页</a>&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}${page.url}&pagenum=${page.pagenum+1>=page.totalpage?page.totalpage:page.pagenum+1}">下一页</a>
    <!-- 分页结束-->