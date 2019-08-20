<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   String uname=request.getParameter("userName");
   String[] hobbys=request.getParameterValues("userHobby");
%>
    ${sessionScope.ses}<br />
<!-- 只要能用request.getParameter("key")
     或者 request.getParameterValues("key")
     这两种写法都可以用${param.key}或者${paramValues.key}取值
 -->
	用户名:${param.userName}<br />
	地址:${param.userAddress}<br />
	爱好:${paramValues.userHobby[0]}<br />
	aa:${param.aa}<br />
	bb:${param.bb}<br />
</body>
</html>