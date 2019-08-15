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
 	<c:forEach var="user" items="${requestScope.allusers}" varStatus="vs">
 		<tr>
 			<td>${vs.index}</td>
 			<td>${vs.count}</td>
 			<td>${user.id}</td>
 			<td>${user.name}</td>
 			<td>${user.password}</td>
 			<td>${user.age}</td>
 			<td>${user.address}</td>
 			<td><a href="UserDeleteServlet?uid=${user.id}">删除</a></td>
 			<td><a href="UserFindById?uid=${user.id}">修改</a></td>
 		</tr>
 	</c:forEach>	
 	</table>
 </div>
</body>
</html>