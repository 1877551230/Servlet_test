package cn.tedu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.service.UserService;
import cn.tedu.service.impl.UserServiceImpl;
import cn.tedu.sysinit.CommonValue;
import cn.tedu.util.PropertyUtil;
import cn.tedu.vo.Page;

/**
 * Servlet implementation class FindUserByPageServlet
 */
public class FindUserByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUserByPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.获取数据(当前页号,模糊条件的用户名和地址)
		
		//获取currentPage
		int currentPage=1;
		String currentPage_String=request.getParameter("currentPage");
		if(currentPage_String!=null){
			currentPage=Integer.parseInt(currentPage_String);
		}
		
		//获取模糊关键
		String keyword1=request.getParameter("keyword1");
		String keyword2=request.getParameter("keyword2");
		String kw1=(keyword1==null)? "" : keyword1;
		String kw2=(keyword2==null)? "" : keyword2;
		String[] keywords=new String[]{kw1,kw2};
		//获取每页显示记录条数pageSize(page.properties)
		int pageSize=Integer.parseInt(new PropertyUtil("page.properties").getProperty("pageSize"));
		
		//2.调用分页的业务
		UserService userSevice=new UserServiceImpl();
		Page page=userSevice.findUserByPage(currentPage,pageSize,keywords);
	    
		//3.把查询到的分页信息绑定给request,转发给usershowbypage.jsp
		request.setAttribute("page", page);
		request.getRequestDispatcher("usershowbypage.jsp").forward(request, response);
		//HttpSession session=request.getSession();
		//session.setAttribute("page", page);
		//response.sendRedirect("usershowbypage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
