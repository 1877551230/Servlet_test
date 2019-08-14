package cn.tedu.dao.impl;


import java.util.List;

import cn.tedu.dao.UserDao;
import cn.tedu.dao.common.CommonDao;
import cn.tedu.entity.User;
import cn.tedu.vo.CountVO;


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

	@Override
	public List<User> findAllUsers() {
		List<User> users=null;
		try {
			String sql = "select id,username name,userpassword password,age,address from t_user";
			users = CommonDao.executeQuery(User.class, sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public int deleteUser(int id) {
		int rowAffect=0;
		try {
			String sql="delete from t_user where id=?";
			rowAffect=CommonDao.executeUpdate(sql, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowAffect;
		
	}

	@Override
	public User findUserById(int id) {
		User user=null;
		try {
			String sql="select id,username name,userpassword password,age,address from t_user where id=?";
			List<User> users=CommonDao.executeQuery(User.class, sql,id);
			if(users!=null&&users.size()==1){
				user=users.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public int updateUser(User user) {
		int rowAffect=0;
		try {
			String sql="update t_user set username=?,userpassword=?,age=?,address=? where id=?";
			rowAffect=CommonDao.executeUpdate(sql, user.getName(),user.getPassword(),user.getAge(),user.getAddress(),user.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowAffect;
	}

	@Override
	public int getCount(String[] keywords) {
		int count=0;
		try {
			String sql="select count(id) geshu from t_user "
					+ "where username like ? and address like ?";
			Object[] params=new Object[]{"%"+keywords[0]+"%","%"+keywords[1]+"%"};
			List<CountVO> counts=CommonDao.executeQuery(CountVO.class, sql, params);
			if(counts!=null&&counts.size()==1){
				count=counts.get(0).getGeshu();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<User> getUsersByPage(int currentPage, int pageSize, String[] keywords) {
		List<User> users=null;
		try {
			String sql="select id,username name,userpassword password,age,address from t_user where username like ? and address like ? limit ?,?";
			Object[] params=new Object[]{"%"+keywords[0]+"%","%"+keywords[1]+"%",(currentPage-1)*pageSize,pageSize};
			users=CommonDao.executeQuery(User.class, sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
 
}
