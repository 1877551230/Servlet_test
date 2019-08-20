package cn.tedu.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CheckLoginFilter
 */
public class CheckLoginFilter implements Filter {
	//如果没有登录,访问其他资源就跳转到登录页面上
	private String redirectURL;
	//session中是否存储用户,如果session中存储了用户名,说明已经登录了
	private String sessionKey=null;
	//所有资源都拦截/过滤,但下面集合中的url是可以通过的
	private List<String> notCheckURLList=new ArrayList<String>();
	
    /**
     * Default constructor. 
     */
    public CheckLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		//1.强制转关类型
		HttpServletRequest request=(HttpServletRequest)servletRequest;
		HttpServletResponse response=(HttpServletResponse)servletResponse;
		//2.获取当前浏览器对应的session
		HttpSession session=request.getSession();
		//3.如果sessionKey=null,表示直接访问一个过滤器
		//或直接请求资源,相当于空,走了一个过滤器
		if(this.sessionKey==null||this.sessionKey.equals("")){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		//4.检查是否登录过
		//只要是免检列表之外的和session对象中的以userName为Key中没有存储用户名
		if(!checkRequestURLNotFilterList(request) && 
				session.getAttribute("userName")==null){
			//能进入此if,相当于一定要跳转到rediectURL=login.jsp
			//http://localhost:8080/testmavenwebproject/login.jsp
			response.sendRedirect(request.getContextPath()+this.redirectURL);
			return;
			
		}
		//5.上面两个if的条件都没有成立,则需要访问下一个过滤器
		//可能是登录过的,也有可能是免检列表之内的
		chain.doFilter(servletRequest, servletResponse);
		
		
		
	}

	private boolean checkRequestURLNotFilterList(HttpServletRequest request) {
		String uri=request.getServletPath()+
				(request.getPathInfo()==null?"":request.getPathInfo());
		System.out.println("uri="+uri);
		return this.notCheckURLList.contains(uri);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//在此init方法中获取初始化的参数,
		//login.jsp
		this.redirectURL=fConfig.getInitParameter("redirectURL");
		//userName
		this.sessionKey=fConfig.getInitParameter("checkSessionKey");
		///login.jsp;/register.jsp;/login;/register;/js/register.js;/CodeServlet;/CheckNameServlet
		String notCheckURL=fConfig.getInitParameter("notCheckURL");
		if(notCheckURLList!=null){
			//标准写法:
//			String[] urls=notCheckURLList.split(";");
			//另一种写法:
			StringTokenizer st=new StringTokenizer(notCheckURL,";");
			this.notCheckURLList.clear();
			while(st.hasMoreElements()){
				this.notCheckURLList.add(st.nextToken());
			}
		}
		System.out.println("redirectURL="+redirectURL);
		System.out.println("checkSessionKey="+sessionKey);
		System.out.println("redirectURL="+notCheckURLList);

	}

}
