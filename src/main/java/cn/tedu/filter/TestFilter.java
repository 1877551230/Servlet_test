package cn.tedu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class TestFilter
 */
public class TestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TestFilter() {
       System.out.println("TestFilter.constructor()");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("TestFilter.destroy()");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		System.out.println("TestFilter.doFilter()");
		HttpServletRequest  request=(HttpServletRequest)servletRequest;
		HttpServletResponse  response=(HttpServletResponse)servletResponse;
		
		// TODO Auto-generated method stub
		// place your code here
        System.out.println("begin");
		// pass the request along the filter chain
		//找下一个过滤器,如果有就进入下一个过滤器,如果没有,就直接找请求的资源
        //if(条件成立)
		  chain.doFilter(servletRequest, servletResponse);
       // else{
        	//response.sendRedirect("error.jsp");
        //}
		System.out.println("end");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("TestFilter.init()");
	}

}
