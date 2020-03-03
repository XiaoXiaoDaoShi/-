package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import pojo.OrderForm;
import util.DBUtil;

public class OrderFormDaoImp implements OrderFormDao {
	Connection conn=null;
    PreparedStatement pstmt=null;
    Statement stmt =null;
    ResultSet rs=null;
	
	@Override
	public List<OrderForm> findAllOrderForm() {					//查询所有order
		List<OrderForm> orderFormList = new ArrayList<OrderForm>();
		conn = DBUtil.getConnection();
		String sql = "SELECT orderId,orderNumber,orderPrice,orderGuestName,orderGuestPhone,orderGetWay,orderRemark,orderState,orderDate from orderform";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int orderId = rs.getInt("orderId");
				int orderNumber = rs.getInt("orderNumber");
				int orderPrice = rs.getInt("orderPrice");
				String orderGuestName = rs.getString("orderGuestName");
				String orderGuestPhone = rs.getString("orderGuestPhone");
				int orderGetWay = rs.getInt("orderGetWay");
				String orderRemark = rs.getString("orderRemark");
				int orderState = rs.getInt("orderState");
				Date orderDate = rs.getDate("orderDate");
				
				
				
				OrderForm orderForm = new OrderForm(orderId, orderNumber, orderPrice, orderGuestName,orderGuestPhone,orderGetWay,orderDate, orderRemark, orderState);
				orderFormList.add(orderForm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return orderFormList;
	}

	@Override
	public OrderForm findOrderFormById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addOrderForm(OrderForm orderForm) {				//添加order
		int count = -1;
		String sql="insert into orderform(orderNumber,orderPrice,orderGuestName,orderGuestPhone,orderGetWay,orderRemark,orderState,orderDate) VALUES(?,?,?,?,?,?,?,?)";
		int number = this.getTotal();
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number+1);
			pstmt.setInt(2, orderForm.getorderPrice());
			pstmt.setString(3, orderForm.getOrderGuestName());
			pstmt.setString(4, orderForm.getOrderGuestPhone());
			pstmt.setInt(5, orderForm.getOrderGetWay());
			pstmt.setString(6, orderForm.getRemark());
			pstmt.setInt(7, orderForm.getOrderState());
			pstmt.setDate(8, orderForm.getDate());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return count;
	}

	@Override
	public int deleteOrderForm(String id) {			//根据id删除
		String sql = "delete from orderform where orderId=?";
		int count = -1;
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.valueOf(id));
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return count;
	}

	@Override
	public int modifyOrderForm(String id, OrderForm orderForm) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTotal() {						//order总数
		String sql = "SELECT count(1) as num from orderform";
		int count = -1;
		conn = DBUtil.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt("num");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return count;
	}
	
	public int finishOrderForm(String id) {			//完成订单
		String sql = "update orderform set orderState=1 where orderId=?";
		int count = -1;
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.valueOf(id));
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return count;
	}
}
