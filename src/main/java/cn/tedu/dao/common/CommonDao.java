package cn.tedu.dao.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.util.PropertyUtil;

/**
 * 开连接
 * 关连接
 * 通用dml
 * 通用dql
 * @author PC
 *
 */
public class CommonDao {
	private static PropertyUtil pu=new PropertyUtil("mysql.properties");
	private static String driverClass=pu.getProperty("jdbc_driverClass");
	private static String url=pu.getProperty("jdbc_url");
	private static String username=pu.getProperty("jdbc_username");
	private static String userpassword=pu.getProperty("jdbc_userpassword");
	/**
	 * 获取连接的公共方法
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection()throws Exception{
		Connection con=null;
		Class.forName(driverClass);
		con=DriverManager.getConnection(
				url,
				username,
				userpassword);
		return con;
	}
	/**
	 * 关闭资源的公共方法
	 * @param con
	 * @throws Exception
	 */
	public static void closeAll(Connection con,PreparedStatement pstmt,ResultSet rs)throws Exception{
		if(con!=null){
			con.close();
		}
		if(pstmt!=null){
			pstmt.close();
		}
		if(rs!=null){
			rs.close();
		}
	}
	/**
	 * 通用dml操作
	 * @param sql dml sql语句
	 * @param params dml sql语句的问号?占位符
	 * @return 受影响的行数
	 */
	public static int executeUpdate(String sql,Object...params)throws Exception{
		int rowAffect=0;
		Connection con=getConnection();
		PreparedStatement pstmt=con.prepareStatement(sql);
		if(params!=null){
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
		}
		rowAffect=pstmt.executeUpdate();
		closeAll(con,pstmt,null);
		return rowAffect;
	}
	/**
	 * 反射版本的通用查询
	 * 必须要求结果集的列名字跟实体中的setter方法匹配
	 * @param clazz 
	 * @param sql  dql的sql语句
	 * @param params  dql的参数
	 * @return List集合
	 * @throws Exception
	 */
	public static <T> List<T> executeQuery(Class<T> clazz,String sql,Object...params)throws Exception{
		List<T> list = new ArrayList<T>();
		Connection con=getConnection();
		PreparedStatement pstmt=con.prepareStatement(sql);
		if(params!=null){
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
		}
		//获取结果集对象
		ResultSet rs=pstmt.executeQuery();
		//获取结果集的元数据(meta data)
		
		ResultSetMetaData rsmd=rs.getMetaData();
		//从结果集的元数据中获取相应的元数据,比如结果集的列名字
		List<String> columnNames=new ArrayList<String>();
		for(int i=0;i<rsmd.getColumnCount();i++){
			columnNames.add(rsmd.getColumnLabel(i+1));
		}
		//select id,username name,userpassword password,age,address from t_user
		//columnNames:[id,name,password,age,address]
		while(rs.next()){
			T t=clazz.newInstance();//实例化实体对象,类比为user
			//利用反射机制给t对象set数据,数据从rs中getObject()
			for(String columnName:columnNames){
				//此for循环,一次循环构建一个setter方法
				//setId setName setPassword setAge setAddress
				String setterName="set"+columnName.substring(0,1).toUpperCase()+columnName.substring(1);
				Field field = clazz.getDeclaredField(columnName);//反射获取该属性(数据库列名和实体属性名一样)
				Method method=clazz.getDeclaredMethod(setterName, field.getType());//反射得到该方法
				Object value=rs.getObject(columnName);
				
				/*//遍历所有方法,找到对应的方法,循环的方法效率较低
				Method[] methods=clazz.getDeclaredMethods();
				for(Method method:methods){
					if(setterName.equals(method.getName())){
						//执行到这说明能找到实体类中对应的setter方法
						//从rs中当前行指定columnName列的数据
						Object value=rs.getObject(columnName);*/
				
						//处理value是聚合函数的值,因为value在结果集中是Long类型
						if(value instanceof Long){
							Long l=(Long)value;
							value=l.intValue();
						}
						
						//处理Oracle的id字段为number类型,进入到结果集中就变成了BigDecimal
						if(value instanceof BigDecimal){
							BigDecimal bd=(BigDecimal)value;
							value=bd.intValue();
						}
						
						
						//调用setter方法把结果集中的数据存到t对象
						method.invoke(t, value);
					
				
				
			}
			list.add(t);
		}
		closeAll(con,pstmt,rs);
		return list;
	}

}
