package cn.tedu.service.impl;

import java.util.List;

import cn.tedu.dao.UserDao;
import cn.tedu.dao.impl.UserDaoImpl;
import cn.tedu.entity.User;
import cn.tedu.service.UserService;
import cn.tedu.util.ExportUtil;
import cn.tedu.vo.Page;

public class UserServiceImpl implements UserService {
	private UserDao userDao=new  UserDaoImpl();
	
	
	
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
		return flag;
	}
	public List<User> findAllUsers() {
		
		return userDao.findAllUsers();
	}
	public boolean deleteUser(int id) {
		boolean flag=false;
		int rowAffect=userDao.deleteUser(id);
		if(rowAffect==1){
			flag=true;
		}
		return flag;
	}
	public User findUserById(int id) {
		User user=null;
		user=userDao.findUserById(id);
		return user;
	}
	public boolean updateUser(User user) {
		boolean flag=false;
		int rowAffect=userDao.updateUser(user);
		if(rowAffect==1){
			flag=true;
		}
		return flag;
	}
	@Override
	public Page findUserByPage(int currentPage, int pageSize, String[] keywords) {
		Page page=new Page();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setKeywords(keywords);
		
		//查询数据库获取带有模糊条件的总记录数
		int totalCount=userDao.getCount(keywords);
		page.setTotalCount(totalCount);
		
		//计算总页数
		int totalPage=(totalCount%pageSize==0)? (totalCount/pageSize):(totalCount/pageSize)+1;
		page.setTotalPage(totalPage);
		
		//计算前一页
		if(currentPage==1){
			page.setPreviousPage(1);
		}else{
			page.setPreviousPage(currentPage-1);
		}
		//计算下一页
		if(currentPage==totalPage){
			page.setNextPage(totalPage);
		}else{
			page.setNextPage(currentPage+1);
		}
		//从数据库获取当前页的数据
		List<User> users=userDao.getUsersByPage(currentPage,pageSize,keywords);
		page.setData(users);
		
		return page;
	}
	public boolean findUserByName(String uname) {
		boolean flag=false;
		int  id=userDao.findUserByName(uname);
		if(id>0){
			flag=true;
		}
		return flag;
	}
	//当前页用户数据
	@Override
	public byte[] exportUserByPage(int currentPage, int pageSize, String[] keywords) {
		byte[] data=null;
		List<User> users=userDao.getUsersByPage(currentPage, pageSize, keywords);
		if(users!=null && users.size()>0){
			data=ExportUtil.write2Excel(users);
		}
		return data;
	}
	//导出符合条件的用户
	@Override
	public byte[] exportUser(String[] keywords) {
		byte[] data=null;
		//获取查询到的用户数据
		List<User> users=userDao.getUsersByWords(keywords);
		if(users!=null&&users.size()>0){
			data=ExportUtil.write2Excel(users);
		}
		return data;
	}

}
