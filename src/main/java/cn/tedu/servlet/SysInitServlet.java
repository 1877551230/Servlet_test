package cn.tedu.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.tedu.sysinit.CommonValue;

public class SysInitServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		//局部读取
		/*String ipRange=this.getInitParameter("ipRange");
		String encoding=this.getInitParameter("encoding");
		CommonValue.ipRange=ipRange;
		CommonValue.encoding=encoding;
		System.out.println(CommonValue.ipRange);
		System.out.println(CommonValue.encoding);*/
		//读取全局数据
		String ipRange=this.getServletContext().getInitParameter("globalIpRange");
		String encoding=this.getServletContext().getInitParameter("globalEncoding");
		CommonValue.ipRange=ipRange;
		CommonValue.encoding=encoding;
		System.out.println(CommonValue.ipRange);
		System.out.println(CommonValue.encoding);
		
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init(ServletConfig)");
	
		//局部读取
		/*String ipRange=config.getInitParameter("ipRange");
		String encoding=config.getInitParameter("encoding");
		CommonValue.ipRange=ipRange;
		CommonValue.encoding=encoding;
		System.out.println(CommonValue.ipRange);
		System.out.println(CommonValue.encoding);*/
		//读取全局数据
		String ipRange=config.getServletContext().getInitParameter("globalIpRange");
		String encoding=config.getServletContext().getInitParameter("globalEncoding");
		CommonValue.ipRange=ipRange;
		CommonValue.encoding=encoding;
		System.out.println(CommonValue.ipRange);
		System.out.println(CommonValue.encoding);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
