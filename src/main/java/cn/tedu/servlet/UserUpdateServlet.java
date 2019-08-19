package cn.tedu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.User;
import cn.tedu.service.impl.UserServiceImpl;
import cn.tedu.sysinit.CommonValue;

/**
 * Servlet implementation class UserUpdateServlet
 */
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding(CommonValue.encoding);
		String uid=request.getParameter("userId");
		String uname=request.getParameter("userName");
		String upwd=request.getParameter("userPassword");
		String uage=request.getParameter("age");
		String uaddress=request.getParameter("address");
		User user=new User();
		user.setId(Integer.parseInt(uid));
		user.setAddress(uaddress);
		user.setName(uname);
		user.setPassword(upwd);
		user.setAge(Integer.parseInt(uage));
		boolean flag=new UserServiceImpl().updateUser(user);
		if(flag)
		response.sendRedirect("UserShowAllServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
