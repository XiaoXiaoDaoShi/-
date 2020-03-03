package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import pojo.*;
import util.DBUtil;

public class UserDaoImp implements UserDao {

	Connection conn=null;
    PreparedStatement pstmt=null;
    Statement stmt =null;
    ResultSet rs=null;
	/**
	 * 
	 * @param user
	 */
	public int addUser(User user) {							//添加一个新成员
		int count = -1;
		String sql = "insert into user(userName,userPassword,userGender,email,phone) values(?,?,?,?,?)";
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setInt(3, user.getUserGender());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhone());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(conn, rs, pstmt, pstmt);
		}
		return count;
	}

	/**
	 * 
	 * @param user
	 */
	public int login(User user) {					//登录 返回userId
		int count = -1;
		String sql = "select userId as uid from user where userName=? and UserPassword=?";
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("uid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(conn, rs, pstmt, pstmt);
		}
		
		return count;
	}

	public List<User> findAllUser() {				//找到所有User
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT userId,userName,userPassword,userGender,email,phone from user";
		conn = DBUtil.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				User user = new User(
						String.valueOf(rs.getInt("userId")),
						rs.getString("userName"),
						rs.getString("userPassword"),
						rs.getInt("userGender"),
						rs.getString("email"),
						rs.getString("phone")
						);
				userList.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return userList;
	}

	/**
	 * 
	 * @param uid
	 */
	public int deleteUser(String uid) {				//根据Id删除user
		int count =-1;
		String sql = "delete from user where userId=?";
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.valueOf(uid));
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 
	 * @param uid
	 */
	public User findUserById(String uid) {					//根据ID查找user
		User user = null;
		String sql="SELECT userId,userName,userPassword,userGender,email,phone from user where userId=?";
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.valueOf(uid));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				user = new User(
						String.valueOf(rs.getInt("userId")),
						rs.getString("userName"),
						rs.getString("userPassword"),
						rs.getInt("userGender"),
						rs.getString("email"),
						rs.getString("phone")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return user;
	}

	/**
	 * 
	 * @param uid
	 * @param newDish
	 */
	public int modifyUser(String uid, User newUser) {				//根据Id 更新user
		int count = -1;
		String sql = "UPDATE user set userName=?,userPassword=?,userGender=?,email=?,phone=? where userId=?";
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newUser.getUserName());
			pstmt.setString(2, newUser.getUserPassword());
			pstmt.setInt(3, newUser.getUserGender());
			pstmt.setString(4, newUser.getEmail());
			pstmt.setString(5, newUser.getPhone());
			pstmt.setInt(6, Integer.valueOf(uid));
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return count;
	}

	@Override
	public boolean isUserExistedByName(String userName) {			//根据用户名查找 判断是存在
		String sql = "select count(1) as unum from user where userName=?";
		conn = DBUtil.getConnection();
		int count=-1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("unum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		if(count>0) {
			return true;
		}
		return false;
	}
	
	public int modifyPasswordById(String uid, String newPassword) {				//根据id 修改user
		int count = -1;
		String sql = "update user set userPassword=? where userId=?";
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, Integer.valueOf(uid));
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return count;
		
	}

}