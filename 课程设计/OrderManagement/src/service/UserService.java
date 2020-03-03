package service;

import java.util.List;

import dao.UserDaoImp;
import pojo.User;

public class UserService {
	public List<User> findAllUser() {				//找到所有User
		UserDaoImp dao = new UserDaoImp();
		List<User> userList = dao.findAllUser();
		return userList;
	}
	
	public int addUser(User user) {							//添加一个新成员
		UserDaoImp dao = new UserDaoImp();
		int count = dao.addUser(user);
		return count;
	}
	
	public User findUserById(String uid) {					//根据ID查找user
		UserDaoImp dao = new UserDaoImp();
		User user = dao.findUserById(uid);
		return user;
	}
	
	public int modifyUser(String uid, User newUser) {				//根据Id 更新user
		UserDaoImp dao = new UserDaoImp();
		int count = dao.modifyUser(uid, newUser);
		return count;
	}
	
	public int modifyPasswordById(String uid, String newPassword) {				//根据id 修改user
		UserDaoImp dao = new UserDaoImp();
		int count = dao.modifyPasswordById(uid, newPassword);
		return count;
		
	}
	
	public int deleteUser(String uid) {				//根据Id删除user
		UserDaoImp dao = new UserDaoImp();
		int count = dao.deleteUser(uid);
		return count;
	}
}
