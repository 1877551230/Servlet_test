package cn.tedu.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.service.UserService;
import cn.tedu.service.impl.UserServiceImpl;
import cn.tedu.util.PropertyUtil;

/**
 * Servlet implementation class ExportExcelServlet
 */
public class ExportExcelByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportExcelByPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取数据
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
				
		//2.调用查询所有数据的业务
		UserService userService=new UserServiceImpl();
		byte[] excelData=userService.exportUserByPage(currentPage,pageSize,keywords);
		//3.相应数据给excel表格
		//把excel的字节数据响应给客户端并下载
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment;filename=alluser.xls");
		response.setContentLength(excelData.length);
		OutputStream os=response.getOutputStream();
		os.write(excelData);
		os.flush();
		os.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
