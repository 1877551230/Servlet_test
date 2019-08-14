package cn.tedu.dao.impl;


import java.util.List;

import cn.tedu.dao.UserDao;
import cn.tedu.dao.common.CommonDao;
import cn.tedu.entity.User;


public class UserDaoImpl implements UserDao {

	@Override
	public int login(User user) {
		int id=0;
		try {
			String sql = "select id from t_user where username=? and userpassword=?";
			Object[] params = new Object[] { user.getName(), user.getPassword() };
			List<User> users = CommonDao.executeQuery(User.class, sql, params);
			if (users != null && users.size() == 1) {
				id = users.get(0).getId();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public int register(User user) {
		int rowAffect=0;
		try {
			String sql = "insert into t_user (username,userpassword,age,address) values(?,?,?,?)";
			rowAffect = CommonDao.executeUpdate(sql,
					new Object[] { user.getName(), user.getPassword(), user.getAge(), user.getAddress() });
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rowAffect;
	}
 
}
