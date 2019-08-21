package cn.tedu.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.User;
import cn.tedu.service.UserService;
import cn.tedu.service.impl.UserServiceImpl;
import cn.tedu.util.UploadUtil;

/**
 * Servlet implementation class s
 */
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding(CommonValue.encoding);
		
		/*String uname=request.getParameter("userName");
		String upwd=request.getParameter("userPassword");
		String uage=request.getParameter("age");
		String uaddress=request.getParameter("address");
		User user=new User();
		user.setName(uname);
		user.setPassword(upwd);
		user.setAddress(uaddress);
		user.setAge(Integer.parseInt(uage));*/
		//1.获取注册的数据
		User user=new UploadUtil().uploadFile(request, response);
		//2.注册的业务
		UserService userService=new UserServiceImpl();
		boolean flag=userService.register(user);
		//3.根据业务的返回结果做响应
		if(flag){
			response.sendRedirect("login.jsp");
		}else{
			//删除上传的文件
			String realPath=request.getServletContext().getRealPath("/images");
			File file = new File(realPath,user.getHeadimage());
			if(file.exists()){
				file.delete();
			}
			response.sendRedirect("register.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
