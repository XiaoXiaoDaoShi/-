package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.OS;

import pojo.*;
import util.DBUtil;

public class DishDaoImp implements DishDao {
	Connection conn=null;
    PreparedStatement pstmt=null;
    Statement stmt =null;
    ResultSet rs=null;
	/**
	 * 
	 * @param dish
	 */
	public int addDish(Dish dish) {					//向数据库插入一个新数据
		int count = -1;
		String sql = "insert into dish(dishName, dishPrice, dishCount, dishCookWay, dishFlavour ,dishImage) values(?,?,?,?,?,?)";
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dish.getDishName());
			pstmt.setInt(2, dish.getDishPrice());
			pstmt.setInt(3, dish.getDishCount());
			pstmt.setString(4, dish.getDishCookWay());
			pstmt.setString(5, dish.getDishFlavour());
			pstmt.setString(6, dish.getDishImage());
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return count;
		
	}

	/**
	 * 
	 * @param dish
	 * @param newDish
	 */
	public int modifyDish(String did, Dish newDish) {		//修改Id 为did的dish
		int count = -1;
		String sql = "update dish set dishName=?,dishPrice=?,dishCookWay=?,dishFlavour=?,dishImage=? where dishId =?";
		conn = DBUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newDish.getDishName());
			pstmt.setInt(2, newDish.getDishPrice());
			pstmt.setString(3, newDish.getDishCookWay());
			pstmt.setString(4, newDish.getDishFlavour());
			pstmt.setString(5, newDish.getDishImage());
			pstmt.setInt(6, Integer.valueOf(did));
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return count;
	}
	
	public int modifyDishNoImage(String did, Dish newDish) {		//修改Id 为did的dish 不包含图片
		int count = -1;
		String sql = "update dish set dishName=?,dishPrice=?,dishCookWay=?,dishFlavour=? where dishId =?";
		conn = DBUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newDish.getDishName());
			pstmt.setInt(2, newDish.getDishPrice());
			pstmt.setString(3, newDish.getDishCookWay());
			pstmt.setString(4, newDish.getDishFlavour());
			pstmt.setInt(5, Integer.valueOf(did));
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return count;
	}
	
	/**
	 * 
	 * @param did
	 */
	public int deleteDish(String did) {				//根据Id删除数据
		int count = -1;
		String sql = "delete from dish where dishId=?";
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.valueOf(did));
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return count;
	}
	
	public List<Dish> findAllDishes() {
		List<Dish> dishList = new ArrayList<Dish>();
		String sql = "select dishId, dishName, dishPrice, dishCount, dishCookWay, dishFlavour ,dishImage from dish";
		conn = DBUtil.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String dishId = rs.getString(1);
				String dishName = rs.getString(2);
				int dishPrice = rs.getInt(3);
				int dishCount = rs.getInt(4);
				String dishCookWay = rs.getString(5);
				String dishFlavour = rs.getString(6);
				String dishImage = rs.getString(7);
				Dish dish = new Dish(dishId, dishName, dishPrice, dishCount, dishCookWay, dishFlavour, dishImage);
				dishList.add(dish);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return dishList;
	}
	
	public List<Dish> findTopDishesByLimit(int limit){			//查找人数最多的前limit个
		List<Dish> dishList = new ArrayList<Dish>();
		String sql = "SELECT dishId, dishName, dishPrice, dishCount, dishCookWay, dishFlavour ,dishImage from dish ORDER BY dishCount DESC LIMIT ?";
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String dishId = rs.getString(1);
				String dishName = rs.getString(2);
				int dishPrice = rs.getInt(3);
				int dishCount = rs.getInt(4);
				String dishCookWay = rs.getString(5);
				String dishFlavour = rs.getString(6);
				String dishImage = rs.getString(7);
				Dish dish = new Dish(dishId, dishName, dishPrice, dishCount, dishCookWay, dishFlavour, dishImage);
				dishList.add(dish);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return dishList;
	}
	
	public Dish findDishById(int did) {				//根据Id 查找Dish
		Dish dish = null;
		String sql = "select dishId, dishName, dishPrice, dishCount, dishCookWay, dishFlavour, dishImage from dish where dishId=?";
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, did);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String dishId = rs.getString(1);
				String dishName = rs.getString(2);
				int dishPrice = rs.getInt(3);
				int dishCount = rs.getInt(4);
				String dishCookWay = rs.getString(5);
				String dishFlavour = rs.getString(6);
				String dishImage = rs.getString(7);
				dish = new Dish(dishId,dishName, dishPrice,dishCount, dishCookWay, dishFlavour, dishImage);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dish;
	}

}