package cn.tedu.dao;

import cn.tedu.entity.User;

public interface UserDao {
	//登陆的数据库方法
	public int login(User user);
	//注册的数据库方法
	public int register(User user);
}
