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
        
        session.setAttribute("ses", "ses");
    
    %>
	<form action="requestEL1.jsp" method="post">
	   <input type="text" name="userName"/>
	   <input type="text" name="userAddress"/>
	   <input type="checkbox" name="userHobby" value="吃饭"/>吃饭
	   <input type="checkbox" name="userHobby" value="睡觉"/>睡觉
	   <input type="submit" value="提交"/>
	</form>
	<br />
	<a href="requestEL1.jsp?aa=aa1&bb=bb1">问号传值</a>
</body>
</html>