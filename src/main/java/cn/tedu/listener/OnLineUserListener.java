package cn.tedu.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class OnLineUserListener
 * 在线用户数据
 */
public class OnLineUserListener implements HttpSessionAttributeListener {
	private int count=0;

    /**
     * Default constructor. 
     */
    public OnLineUserListener() {
    	System.out.println("统计在线人数的监听器的生命周期开始");
    	
    }

	/**
	 * 执行session.setAttribute方法时触发此方法
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
    	String name=event.getName();
    	if("userName".equals(name)){
    		count++;
    		//把count数据显示在页面
    		HttpSession session=event.getSession();
    		ServletContext application=session.getServletContext();
    		application.setAttribute("onLineUser",count);
    	}
    	
    }

	/**
	 * 执行session.removeAttribute方法时触发此方法
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  {
    	String name=event.getName();
    	if("userName".equals(name)){
    		count--;
    		//把count数据显示在页面
    		HttpSession session=event.getSession();
    		ServletContext application=session.getServletContext();
    		application.setAttribute("onLineUser",count);
    	} 
    	
    }

	/**
	 * 执行session.replaceAttribute方法时触发此方法
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
    	
    }
	
}
