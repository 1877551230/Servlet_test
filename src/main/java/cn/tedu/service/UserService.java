package cn.tedu.service;

import java.util.List;

import cn.tedu.entity.User;
import cn.tedu.vo.Page;

public interface UserService {
	//登录的业务接口方法
	public boolean login(User user);
    //注册的业务方法
	public boolean register(User user);
	//查询所有用户信息
	public List<User> findAllUsers();
	//删除用户的业务
	public boolean deleteUser(int id);
	//根据用户的id查询用户信息
	public User findUserById(int id);
	//更新用户信息
	public boolean updateUser(User user);
	//分页+模糊的业务方法
	public Page findUserByPage(int currentPage, int pageSize, String[] keywords);
	//根据用户名查询用户名是否存在   
	public boolean findUserByName(String uname);
	//导出excel表格
	public byte[] exportUser();
	
}
