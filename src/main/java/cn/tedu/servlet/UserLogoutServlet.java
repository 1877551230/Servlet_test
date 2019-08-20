package cn.tedu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLogoutServlet
 */
public class UserLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		//session对象存在,但session中的userName的值不存在(推荐)
		//session.removeAttribute("userName");
		//另一种写法(不推荐)
		//session.setAttribute("userName", null);
		//session对象不存在了,session的内容肯定没有了
		session.invalidate();
		//清除指定的cookie(推荐)
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for(Cookie c:cookies){
				if("autologin".equals(c.getName()) ||
						"remname".equals(c.getName())){
					c.setMaxAge(0);//清除指定的cookie
					c.setPath(request.getContextPath()+"/");
					response.addCookie(c);
				}
			}
		}
		//另一种写法(不推荐)
		/*Cookie cookie=new Cookie("autologin","aa");
		cookie.setMaxAge(0);//销毁cookie
		cookie.setPath(request.getContextPath()+"/");
		response.addCookie(cookie);*/
		
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
