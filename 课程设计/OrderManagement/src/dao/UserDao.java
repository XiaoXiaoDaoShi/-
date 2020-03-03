package dao;

import java.util.List;

import pojo.*;

public interface UserDao {
	
	/**
	 * 
	 * @param user
	 */
	int addUser(User user);

	/**
	 * 
	 * @param user
	 */
	int login(User user);

	boolean isUserExistedByName(String userName);

	/**
	 * 
	 * @param uid
	 */
	int deleteUser(String uid);

	/**
	 * 
	 * @param uid
	 */
	User findUserById(String uid);

	/**
	 * 
	 * @param uid
	 * @param newDish
	 */
	int modifyUser(String uid, User newUser);
	
	List<User> findAllUser();

}