package cn.tedu.service;

import cn.tedu.entity.User;

public interface UserService {
	//登录的业务接口方法
	public boolean login(User user);

	public boolean register(User user);

}
