package OsTools;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import java.util.Collections;
import java.util.HashMap;

import java.util.Map;

public class DBConnection {
	private String DBDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://localhost:1433;databaseName=AttendanceSystem;integratedSecurity=true;";
	private Connection conn=null;
	private Statement stmt = null;
	PreparedStatement pstmt = null;
	private boolean flag = true;
//	private PreparedStatement pstmt = null;
	public DBConnection(){
		
		try {
			Class.forName(DBDriver);
		}catch(Exception e) {
			e.printStackTrace();
			flag = false;
		}
		
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag = false;
		}
	}
	
	public  boolean isLoadSuccessfully() {
		if(flag)
			return true;
		else
			return false;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	
	
	public Statement getStmt() {
		return stmt;
	}


	public PreparedStatement getPstmt() {
		return pstmt;
	}


	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean studentIsExists(String studentId) {
		ResultSet rs =null;
		String flag = null;
		String sql = "select ISNULL(\r\n" + 
				"(select top(1) 1 \r\n" + 
				"from image\r\n" + 
				"where studentId=?),0\r\n" + 
				") ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentId);
			rs = pstmt.executeQuery();
			rs.next();
			flag = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag.equals("1"))
			return true;
		else
			return false;
		
	}
	
	public void upData(String studentId, double[][] imageInfo) {
		String col=null;
		double pix;	
		try {
//			conn.setAutoCommit(false);

			for(int i=1; i<=tfutil.DIMENSIONALITY;i++) {
				col = "[" + i + "]";
				String sql = "update image set " + col+ " = ? where studentId = ?";
				pstmt = conn.prepareStatement(sql);
				pix = imageInfo[0][i-1];
				pstmt.setDouble(1, pix);
				pstmt.setString(2, studentId);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertData(String studentId, double[][] imageInfo) {    //insert image(studentId, imageInfo)
//		PreparedStatement pstmt = null;
		String col = null;		
		String sql = null;
		double pix;
		String placehold = String.join(",", Collections.nCopies(513,"?"));
		StringBuffer colPlaceholde = new StringBuffer("studentId");
		for(int i=1;i<=tfutil.DIMENSIONALITY;i++) {
			col = "[" + i + "]";
			colPlaceholde.append(",");
			colPlaceholde.append(col);
		}
		
		sql = "insert into image(" + colPlaceholde + ") values(" + placehold + ")";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentId);
			for(int i=1;i<=tfutil.DIMENSIONALITY;i++) {
				pix = imageInfo[0][i-1];
				pstmt.setDouble(i+1, pix);
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tfutil.printInfo("fail to insert");
		}
		
	}
	
	public Map<Integer, Time> getTimeTable() {   //获得课程时间表
		Map<Integer, Time> timeTable = new HashMap<>();
		ResultSet rs ;
		String sql = "select * from time";
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int i = rs.getInt(1);
				Time date= rs.getTime(2);
			
				timeTable.put(i, date);
			}
			return timeTable;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertStudentInfo(String studentId, String name) {
		String sql = "insert into Student(studentId, name) values(?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentId);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
