package cn.tedu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.User;
import cn.tedu.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserShowAllServlet
 */
public class UserShowAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserShowAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取数据(无)
		//2.调用查询所有用户信息的业务方法
		List<User> users=new UserServiceImpl().findAllUsers();
		//3.根据业务的返回结果绑定给request对象,把带有数据的request对象转发给usershowall.jsp
		//绑定数据到request对象中
		request.setAttribute("allusers", users);
		//把带有新数据的request对象转发给下一个目的地usershowall.jsp
		RequestDispatcher rd=request.getRequestDispatcher("usershowall.jsp");
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
