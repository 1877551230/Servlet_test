package cn.tedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.tedu.service.impl.UserServiceImpl;
import cn.tedu.sysinit.CommonValue;
import cn.tedu.vo.Result;

/**
 * Servlet implementation class CheckNameServlet
 */
public class CheckNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CommonValue.encoding);
		//获取用户名信息
		String uname=request.getParameter("uname");
		System.out.println("uname="+uname);
		//调用查询用户名是否存在的业务方法
		boolean flag=new UserServiceImpl().findUserByName(uname);
		//3.根据业务的返回结果做相应的信息响应
		String responseText="";
		Result result=new Result();
		if(flag){
			result.setStatus(0);
			result.setMessage("用户名被占用");
			//responseText="{\"msg\":\"用户名被占用\"}";
		}else{
			result.setStatus(1);
			result.setMessage("用户名可用");
			//responseText="{\"msg\":\"用户名可用\"}";
		}
		//把信息响应给客户端js
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out=response.getWriter();
	    responseText=JSON.toJSONString(result);
		out.print(responseText);
	    out.flush();
	    out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
