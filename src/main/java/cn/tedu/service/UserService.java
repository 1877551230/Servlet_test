package cn.tedu.service;

import java.util.List;

import cn.tedu.entity.User;
import cn.tedu.vo.Page;

public interface UserService {
	//登录的业务接口方法
	public boolean login(User user);

	public boolean register(User user);
	
	public List<User> findAllUsers();
	public boolean deleteUser(int id);
	//根据用户id查询用户信息
	public User findUserById(int id);
	//更新用户信息
	public boolean updateUser(User user);
	//分页+模糊查询的业务方法
	public Page findUserByPage(int currentPage, int pageSize, String[] keywords);
	//根据用户名查询用户
	public boolean findUserByName(String uname) ;
}
