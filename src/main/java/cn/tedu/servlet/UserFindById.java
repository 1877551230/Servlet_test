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
 * Servlet implementation class UserFindById
 */
public class UserFindById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFindById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CommonValue.encoding);
		//1.获取用户的id
		String id=request.getParameter("uid");
		//2.根据id查询用户信息
		User user=new UserServiceImpl().findUserById(Integer.parseInt(id));
		//3.把查询到的数据转发给update.jsp
		//绑定数据给request
		request.setAttribute("user", user);
		//转发request到新的目标url
		request.getRequestDispatcher("update.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
