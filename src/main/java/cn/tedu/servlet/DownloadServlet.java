package cn.tedu.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取要下载的文件名
				String fileName=request.getParameter("fileName");
				//检测文件在服务器中是否存在
				String realPath=this.getServletContext().getRealPath("/images");
				//拼装一个完整的路径
				String allPath=realPath+File.separator+fileName;
				System.out.println("allPath="+allPath);
				File file=new File(allPath);
				if(file.exists()){
					//文件存在可以下载
					//要想下载文件,必须给response对象设置一个消息头
					response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"ISO8859-1"));
					//开始下载文件
					OutputStream os=response.getOutputStream();
					//先从服务器本地把文件读入到内存中
					InputStream is=new FileInputStream(file);
					byte[] buf=new byte[1024];
					int len=-1;
					while((len=is.read(buf))!=-1){
						os.write(buf,0,len);
					}
					os.flush();
					os.close();
					is.close();
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
