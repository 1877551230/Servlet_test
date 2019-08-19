package cn.tedu.dao;

import java.util.List;

import cn.tedu.entity.User;

public interface UserDao {
	//登陆的数据库方法
	public int login(User user);
	//注册的数据库方法
	public int register(User user);
	//查询所有用户的数据库方法
	public List<User> findAllUsers();
	//删除用户的数据库方法
	public int deleteUser(int id);
	//根据用户id查询用户的信息的数据库方法
	public User findUserById(int id);
	//更新用户信息的数据库方法
	public int updateUser(User user);
	//分页+模糊
	public int getCount(String[] keywords);
	public List<User> getUsersByPage(int currentPage, int pageSize, String[] keywords);
	//查询用户名的数据库方法
	public int findUserByName(String uname);
	
}
