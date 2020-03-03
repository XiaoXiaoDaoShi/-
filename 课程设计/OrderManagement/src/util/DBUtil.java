package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

public class DBUtil {
	public static final String DRIVER="com.mysql.cj.jdbc.Driver";
	public static final String URL="jdbc:mysql://127.0.0.1:3306/ordermanagement?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
	public static final String USER="root";
	public static final String PWD="123456";
	
	public static Connection getConnection() {
		Connection conn = null;
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PWD);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
	}
	
	public static void closeAll(Connection conn, ResultSet rs, PreparedStatement pstmt, Statement stmt) {
		
		
		try {
			
			if(conn!=null) {
			conn.close();
			}
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
