<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload=function(){
		//用js代码从cookie中读取cookie的信息
		var uname="${cookie.remname.value}";
		//alert(uname);
		document.forms[0].userName.value=decodeURI(uname);
		//alert(document.cookie);
		//用js代码给cookie存值,带过期时间
		//var date=new Date();
		//date.setTime(date.getTime()+3*3600*1000);
		//document.cookie="ac=ac; Expires="+date.toGMTString();
		//处理自动登录
		//取出autologin对应的值,用户名#密码
		var autologin_value="${cookie.autologin.value}";
		if(autologin_value!=""){
			var infos=autologin_value.split("#");
			//把用户名解码后放在用户名文本框中
			document.forms[0].userName.value=decodeURI(infos[0]);
			//把密码用base64解码后放在密码框中
			document.forms[0].userPassword.value=window.atob(infos[1]);	
			document.forms[0].submit();
		}
	}
</script>
</head>
<body>
 <div style="text-align:center;">
 	<div>在线人数:${applicationScope.onLine}</div>
 	<div>在线人数:${applicationScope.onLineUser}</div>
 	<div style="font-size:30px;font-weight:bold;">用户登录</div>
 	<form action="login" method="post">
 	<table border="1px" align="center">
 		<tr>
 			<td>用户名</td>
 			<td><input required="required" type="text" name="userName"/></td>
 		</tr>
 		<tr>
 			<td>密&nbsp;&nbsp;码</td>
 			<td><input required="required" type="password" name="userPassword"/></td>
 		</tr>
 			<tr>
 			<td>验证码</td>
 			<td>
 			<input required="required" type="text" name="validateCode" size=5/>
 			<img src="CodeServlet" onclick="this.src='CodeServlet?'+Math.random();" title="点击更换" style="cursor:pointer;" >
 			<span style="color:red;">${requestScope.messagecode}</span>
 			</td>
 			</tr>
 		<tr>
 		</tr>
 			<tr>
 			<td>选项</td>
 			<td>
 			<input type="checkbox" name="remembername" value="true"/>记住用户名
 			<input type="checkbox" name="autologin" value="true"/>十天自动登录
 			
 			</td>
 			</tr>
 		<tr>
 			<td colspan="2" align="center">
 			<input type="submit" value="登录"/>
 			<input type="reset" value="重置"/>
 			</td>
 		</tr>
 	</table>
 	</form>
 </div>
</body>
</html>