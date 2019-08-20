package cn.tedu.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 统计在线用户人数
 * @author PC
 *
 */
public class OnLineListener implements HttpSessionListener {
	private int count=0;
    /**
     * Default constructor. 
     */
    public OnLineListener() {
        System.out.println("统计在线人数的监听器生命周期开始");
    }

	/**
	 * 只有session创建了才会自动调用此方法
	 * 每执行一次此方法就要给一个变量累加1
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	count++;
    	//把count的值传送给页面显示,跨浏览器显示,所以要借助application/servletContext
    	HttpSession session=se.getSession();
    	ServletContext application=session.getServletContext();
    	application.setAttribute("onLine", count);
    	
    	
    }

	/**
	 * 只有session销毁了才会自动调用此方法
	 * 每执行一次此方法就要给一个变量累减1
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  {
    	count--;
    	//把count的值传送给页面显示,跨浏览器显示,所以要借助application/servletContext
    	HttpSession session=se.getSession();
    	ServletContext application=session.getServletContext();
    	application.setAttribute("onLine", count);
    	
    	
    }
	
}
