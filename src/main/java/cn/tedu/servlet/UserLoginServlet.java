package cn.tedu.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		//获取用户数据的验证码
		String validateCode=req.getParameter("validateCode");
		//获取前端提交的数据
		String uname=req.getParameter("userName");
		String upwd=req.getParameter("userPassword");
		System.out.println(uname+" "+upwd);
		User user=new User();
		user.setName(uname);
		user.setPassword(upwd);
		
		//获取记住用户名
		String rememberName=req.getParameter("remembername");
		//获取10天自动登录是否打勾
		String autologin=req.getParameter("autologin");
		
		
		//从session中获取系统生成的验证码
		HttpSession session=req.getSession();
		String code=session.getAttribute("code").toString();
		
		//获取从客户端发送过来的所有的cookie
		boolean f=false;
		Cookie[] cookies=req.getCookies();
		if(cookies!=null){
			for(Cookie c:cookies){
				if("autologin".equals(c.getName())){
					//说明autologin的cookie是存在的,自动登录
					f=true;
				}
			}
		}
		
		if(f || validateCode.equalsIgnoreCase(code)){
			//调用业务
			UserService userService=new UserServiceImpl();
			
			boolean flag=userService.login(user);
			//根据业务的返回结果做响应
			if(flag){
				//记住用户名功能代码
				if("true".equals(rememberName)){
					System.out.println("选中记住用户名项");
					//开始记住用户名
					Cookie cookie=new Cookie("remname",URLEncoder.encode(uname,"utf-8"));
					cookie.setPath(req.getContextPath()+"/");//把cookie存进根节点
					cookie.setMaxAge(3600*5);//设置cookie存活时间是5个小时
					resp.addCookie(cookie);
				}
				if("true".equals(autologin)){
					//创建cookie,key是autologin value是用户名#密码
					Cookie cookie=new Cookie("autologin",URLEncoder.encode(uname,"utf-8")+"#"+Base64.getEncoder().encodeToString(upwd.getBytes()));
					cookie.setPath(req.getContextPath()+"/");//把cookie存进根节点
					cookie.setMaxAge(3600*24*10);//设置cookie存活时间是10天
					resp.addCookie(cookie);
				}
				//把登录当前账号存到userName
				session.setAttribute("userName", uname);
				//重定向到分页+模糊
				resp.sendRedirect("FindUserByPageServlet");
			}else{
				resp.sendRedirect("login.jsp");
			}
		}else{
			req.setAttribute("messagecode", "验证码错误");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
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
