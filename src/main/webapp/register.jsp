<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/common/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/register.js"></script>
</head>
<body>
 <div style="text-align:center;">
 	<div style="font-size:30px;font-weight:bold;">用户注册</div>
 	<form action="register" method="post">
 	<table border="1px" align="center">
 		<tr>
 			<td>用户名</td>
 			<td>
 			<input type="text" name="userName" onblur="doCheckName();"/>
 			<span id="uname" style="color:red;"></span>
 			</td>
 		</tr>
 		<tr>
 			<td>密&nbsp;&nbsp;码</td>
 			<td><input type="password" name="userPassword"/></td>
 		</tr>
 		<tr>
 			<td>年龄</td>
 			<td><input type="text" name="age"/></td>
 		</tr>
 		<tr>
 			<td>地址</td>
 			<td><input type="text" name="address"/></td>
 		</tr>
 		<tr>
 			<td colspan="2" align="center">
 			<input type="submit" value="注册"/>
 			<input type="reset" value="重置"/>
 			</td>
 			
 		</tr>
 	</table>
 	</form>
 </div>
</body>
</html>