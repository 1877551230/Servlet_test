package cn.tedu.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.service.UserService;
import cn.tedu.service.impl.UserServiceImpl;

/**
 * Servlet implementation class ExportPageExcelServlet
 */
public class ExportExcelByWordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportExcelByWordsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取数据
		String keyword1=request.getParameter("keyword1");
		String keyword2=request.getParameter("keyword2");
		String kw1=(keyword1==null)? "" : keyword1;
		String kw2=(keyword2==null)? "" : keyword2;
		String[] keywords=new String[]{kw1,kw2};
		//2.调用查找模糊查询所有数据
		UserService userService=new UserServiceImpl();
		byte[] excelData=userService.exportUser(keywords);
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
