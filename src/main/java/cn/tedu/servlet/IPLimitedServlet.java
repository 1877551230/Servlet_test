package cn.tedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.sysinit.CommonValue;

public class IPLimitedServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//10.8.38.1-10.8.38.100
		String ipRange=CommonValue.ipRange;
		String[] ips=ipRange.split("-");
		int ip1=Integer.parseInt(ips[0].substring(ips[0].lastIndexOf(".")+1));
		int ip2=Integer.parseInt(ips[1].substring(ips[1].lastIndexOf(".")+1));;
		System.out.println(ip1+"   "+ip2);
		//获取客户端访问的真实ip
		String realIp=req.getRemoteAddr();
		int clientIp=Integer.parseInt(realIp.substring(realIp.lastIndexOf(".")+1));
		System.out.println("realIp="+realIp);
		if(clientIp>ip1 && clientIp<ip2){
			resp.sendRedirect("login.html");
		}else{
			PrintWriter out=resp.getWriter();
			out.append("ni meiyou quanli fangwen;your ip"+realIp);
			out.close();
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() throws ServletException {
		
	}

}
