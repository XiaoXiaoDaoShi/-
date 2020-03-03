package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.DaySale;
import util.DBUtil;

public class ChartDaoImp {
	Connection conn=null;
    PreparedStatement pstmt=null;
    Statement stmt =null;
    ResultSet rs=null;
	public List<DaySale> getDaySale(){
		List<DaySale> daySaleList = new ArrayList<DaySale>();
		String sql = "SELECT date,sale from daysale";
		conn = DBUtil.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				DaySale daySale = new DaySale(new java.util.Date(rs.getDate("date").getTime()), rs.getInt("sale"));
				daySaleList.add(daySale);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.closeAll(conn, rs, pstmt, pstmt);
		return daySaleList;
	}
	
	
}
