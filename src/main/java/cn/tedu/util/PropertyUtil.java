package cn.tedu.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	private Properties p=new Properties();
	public PropertyUtil(String fileName){
		try {
			InputStream is = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
			p.load(is);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String getProperty(String name){
		return p.getProperty(name);
	}

}
