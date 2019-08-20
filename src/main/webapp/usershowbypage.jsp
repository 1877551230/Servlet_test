<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div style="text-align:center">
 <a href="ExportExcelSErvlet?"></a>
 <a href="UserLogoutServlet">登出</a>
 	<form action="FindUserByPageServlet" method="post">
 		用户名:<input type="text" name="keyword1" value="${page.keywords[0]}"/>
 		地址:<input type="text" name="keyword2" value="${page.keywords[1]}"/>
 		<input type="submit" value="模糊查询"/>
 	</form>
 	<div style="font-size:30px;font-wight:bold;">显示用户信息</div>
 	<table border="1" align="center">
 		<tr>
 			<th>序号1</th>
 			<th>序号2</th>
 			<th>id</th>
 			<th>用户名</th>
 			<th>密码</th>
 			<th>年龄</th>
 			<th>地址</th>
 			<th>删除</th>
 			<th>修改</th>
 		</tr>
 	<c:forEach var="user" items="${requestScope.page.data}" varStatus="vs">
 		<tr>
 			<td>${vs.index}</td>
 			<td>${vs.count}</td>
 			<td>${user.id}</td>
 			<td>${user.name}</td>
 			<td>${user.password}</td>
 			<td>${user.age}</td>
 			<td>${user.address}</td>
 			<td>
 			<c:if test="${userName!=user.name}">
 			<a href="UserDeleteServlet?uid=${user.id}">删除</a>
 			</c:if>
 			</td>
 			<td><a href="UserFindById?uid=${user.id}">修改</a></td>
 		</tr>
 	</c:forEach>	
 	</table>
 	<!-- 分页条开始 -->
 	<c:if test="${requestScope.page.totalPage>1}">
 	[${page.currentPage}/${page.totalPage}]
 		<!-- 当前页为第一页的情况 -->
 		<c:if test="${requestScope.page.currentPage==1}">
 			<a href="FindUserByPageServlet?currentPage=${page.nextPage}&keyword1=${page.keywords[0]}&keyword2=${page.keywords[1]}">下一页</a>
 			<a href="FindUserByPageServlet?currentPage=${page.totalPage}&keyword1=${page.keywords[0]}&keyword2=${page.keywords[1]}">尾页</a>
 		</c:if>
 		<!-- 既不是第一页也不是最后一页 -->
 		<c:if test="${requestScope.page.currentPage>1 and requestScope.page.currentPage<requestScope.page.totalPage}">
 			<a href="FindUserByPageServlet?currentPage=1&keyword1=${page.keywords[0]}&keyword2=${page.keywords[1]}">首页</a>
 			<a href="FindUserByPageServlet?currentPage=${page.previousPage}&keyword1=${page.keywords[0]}&keyword2=${page.keywords[1]}">上一页</a>
 			<a href="FindUserByPageServlet?currentPage=${page.nextPage}&keyword1=${page.keywords[0]}&keyword2=${page.keywords[1]}">下一页</a>
 			<a href="FindUserByPageServlet?currentPage=${page.totalPage}&keyword1=${page.keywords[0]}&keyword2=${page.keywords[1]}">尾页</a>
 		</c:if>
 		<!-- 当前页为最后一页 -->
 		<c:if test="${requestScope.page.currentPage==requestScope.page.totalPage}">
 			<a href="FindUserByPageServlet?currentPage=1&keyword1=${page.keywords[0]}&keyword2=${page.keywords[1]}">首页</a>
 			<a href="FindUserByPageServlet?currentPage=${page.previousPage}&keyword1=${page.keywords[0]}&keyword2=${page.keywords[1]}">上一页</a>
 		</c:if>
 	</c:if>
 	<!-- 分页条结束 -->
 	<div>
 	<!-- 下拉列表跳转 -->
 	<form action="FindUserByPageServlet" method="post" id="form">
 	<select onchange="submitForm()" name="currentPage">
 		<c:forEach var="num" begin="1" end="${page.totalPage}" varStatus="vs">
 			<option value="${vs.count}">${vs.count}</option>
 		</c:forEach>
 		<option value="${page.currentPage}" selected="selected">当前页:${page.currentPage}</option>
 	</select>
 	</form>
 	<!-- 跳转页 -->
 	 <form action="FindUserByPageServlet" method="post">
 	<input type="text" value="${page.currentPage}" style="width:30px" name="currentPage"/>
 	<input type="submit" value="跳转"/>
 	</form>
 	</div>
 	<!-- js函数处理onchange事件 -->
 	<script type="text/javascript">
 	function submitForm(){
 		var form=document.getElementById("form");
 		form.submit();
 	}
 	</script>
 	
 	<!-- 分页条,一共显示7个码 -->
 	<c:if test="${page.totalPage>=7 and page.currentPage<=5}">
 		<c:forEach var="num" begin="1" end="7" varStatus="vs">
 			<a href="FindUserByPageServlet?currentPage=${vs.count}&keyword1=${page.keywords[0]}&keyword2=${page.keywords[1]}">${vs.count}</a>
 		</c:forEach>
 	</c:if>	
 	<!-- 当当前页大于5且后面还能再加两个,向前显示4个,向后显示2个 -->
	<c:if test="${page.totalPage>=7 and page.currentPage>5 and page.currentPage+2<=page.totalPage}">
 		<c:forEach var="num" begin="${page.currentPage-4}" end="${page.currentPage+2}" varStatus="vs">
 			<a href="FindUserByPageServlet?currentPage=${num}&keyword1=${page.keywords[0]}&keyword2=${page.keywords[1]}">${num}</a>
 		</c:forEach>
 	</c:if>	
 	<!-- 当当前页大于5且后面不能再加两个,向前显示少于7页的页数,向后显示到总页数 -->
 	<c:if test="${page.totalPage>=7 and page.currentPage>5 and page.currentPage+2>page.totalPage}">
 		<c:forEach var="num" begin="${page.currentPage-6+page.totalPage-page.currentPage}" end="${page.totalPage}" varStatus="vs">
 			<a href="FindUserByPageServlet?currentPage=${num}&keyword1=${page.keywords[0]}&keyword2=${page.keywords[1]}">${num}</a>
 		</c:forEach>
 	</c:if>	
 	<c:if test="${page.totalPage<7}">
 		<c:forEach var="num" begin="1" end="${page.totalPage}" varStatus="vs">
 			<a href="FindUserByPageServlet?currentPage=${vs.count}&keyword1=${page.keywords[0]}&keyword2=${page.keywords[1]}">${vs.count}</a>
 		</c:forEach>
 	</c:if>
 </div>
</body>
</html>