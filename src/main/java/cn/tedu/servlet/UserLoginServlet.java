package cn.tedu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.User;
import cn.tedu.service.UserService;
import cn.tedu.service.impl.UserServiceImpl;

public class UserLoginServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserLoginServlet.service");
		super.service(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String uname=req.getParameter("userName");
		String upwd=req.getParameter("userPassword");
		System.out.println(uname+" "+upwd);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取前端提交的数据
		String uname=req.getParameter("userName");
		String upwd=req.getParameter("userPassword");
		System.out.println(uname+" "+upwd);
		User user=new User();
		user.setName(uname);
		user.setPassword(upwd);
		//调用业务
		UserService userService=new UserServiceImpl();
		boolean flag=userService.login(user);
		//根据业务的返回结果做响应
		if(flag){
			resp.sendRedirect("success.jsp");
		}else{
			resp.sendRedirect("login.jsp");
		}
	}

	@Override
	public void destroy() {
		System.out.println("UserLoginServlet.destroy");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("UserLoginServlet.init");
	}
	
}
