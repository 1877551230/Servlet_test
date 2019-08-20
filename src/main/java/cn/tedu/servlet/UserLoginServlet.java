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
import cn.tedu.sysinit.CommonValue;

public class UserLoginServlet extends HttpServlet{
	public UserLoginServlet(){
		//相关的初始化
		
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserLoginServlet.service()");
		//String uname=request.getParameter("userName");
		//String upwd=request.getParameter("userPassword");
		//System.out.println(uname+"   "+upwd);
		super.service(request, response);
	}

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserLoginServlet.doGet()");
		String uname=request.getParameter("userName");
		String upwd=request.getParameter("userPassword");
		System.out.println(uname+"   "+upwd);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserLoginServlet.doPost()");
		/*InputStream is=request.getInputStream();
		String content="";
		DataInputStream dis=new DataInputStream(is);
		byte[] buffer=new byte[1024];
		int len=-1;
		while((len=dis.read(buffer))!=-1){
			content+=new String(buffer).substring(0,len);
		}
		dis.close();
		System.out.println("content="+content);*/
		
		//1.获取前端提交的数据
		String uname=request.getParameter("userName");
		String upwd=request.getParameter("userPassword");
		System.out.println(uname+"   "+upwd);
		User user=new User();
		user.setName(uname);
		user.setPassword(upwd);
		
		//获取记住用户名
		String rememberName=request.getParameter("remembername");
		System.out.println("rememberName="+rememberName);
		//获取10天自动登录
		String autologin=request.getParameter("autologin");
		
		//获取用户数据的验证码
		String validateCode=request.getParameter("validateCode");
		//从session中获取系统生成的验证码
		HttpSession session=request.getSession();
		session.setMaxInactiveInterval(800);
		
		String code=session.getAttribute("code").toString();
						
		//获取从客户端发送过来的所有的cookie
		boolean f=false;
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for(Cookie c: cookies){
				if("autologin".equals(c.getName())){
					//说明autologin的cookie是存在的,一定是自动登录
					f=true;
				}
			}
		}
		
		if(f || validateCode.equalsIgnoreCase(code)){
			//2.调用业务
			UserService userService=new UserServiceImpl();
			boolean flag=userService.login(user);		//3.根据业务的返回结果做响应工作
			
			if(flag){
				session.setAttribute("userName", uname);
				//记住用户名功能代码
				if("true".equals(rememberName)){
					System.out.println("你选中的记住用户名项");
					//开始记住用户名
					Cookie cookie=new Cookie("remname",URLEncoder.encode(uname,"utf-8"));
					cookie.setPath(request.getContextPath()+"/");
					cookie.setMaxAge(3600*5);//设置 cookie存活的时间时5个小时
					response.addCookie(cookie);
					
				}
				if("true".equals(autologin)){
					System.out.println("你选中的10天自动登录项");
					//创建cookie ,key是autologin 值是用户名#密码
					Cookie cookie=new Cookie("autologin",URLEncoder.encode(uname,"utf-8")+"#"+Base64.getEncoder().encodeToString(upwd.getBytes()));
					cookie.setPath(request.getContextPath()+"/");
					cookie.setMaxAge(3600*24*10);//设置 cookie存活的时间时5个小时
					response.addCookie(cookie);
				}
				//重定向到success.jsp
				//response.sendRedirect("success.jsp");
				//重定向到查询所有用户
				//response.sendRedirect("UserShowAllServlet");
				//重定向到分页+模糊查询
			    response.sendRedirect("FindUserByPageServlet");
			}else{
				response.sendRedirect("login.jsp");
			}
		}else{
			request.setAttribute("messagecode","验证码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		//OutputStream os=response.getOutputStream();
		//request.getRequestDispatcher("WEB-INF/html/test.html").forward(request, response);
		/*ServletContext context=this.getServletContext();
		context.setAttribute("aa", uname);
		
		response.sendRedirect("TestServlet");*/
		System.out.println("UserLoginServlet end");
	}

	@Override
	public void destroy() {
		System.out.println("UserLoginServlet.destroy()");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("UserLoginServlet.init()");
		
	}
	
}
