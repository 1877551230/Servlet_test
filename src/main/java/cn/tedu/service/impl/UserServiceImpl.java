package cn.tedu.service.impl;

import cn.tedu.dao.UserDao;
import cn.tedu.dao.impl.UserDaoImpl;
import cn.tedu.entity.User;
import cn.tedu.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao=new UserDaoImpl();
	@Override
	public boolean login(User user) {
		boolean flag=false;
		int id=userDao.login(user);
		if(id>0){
			flag=true;
		}
		return flag;
	
	}
	@Override
	public boolean register(User user) {
		boolean flag=false;
		int rowAffect=userDao.register(user);
		if(rowAffect==1){
			flag=true;
		}
		return false;
	}
	

}
