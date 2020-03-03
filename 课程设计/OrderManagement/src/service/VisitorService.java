package service;

import dao.UserDaoImp;
import pojo.User;

public class VisitorService {
	public int login(User user) {		//登录
		
		UserDaoImp dao = new UserDaoImp();
		int count = dao.login(user);
		return count;
	}
	
	public int register(User user) {		//注册
		 
		UserDaoImp dao = new UserDaoImp();
		int count = dao.addUser(user);
		return count;
	}
	
	public boolean isUserNameExisted(String userName) {		//注册时判断用户名是否已存在
		UserDaoImp dao = new UserDaoImp();
		boolean isExisted = false;
		isExisted = dao.isUserExistedByName(userName);
		return isExisted;
	}
	
}
