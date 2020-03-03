package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import OsTools.DBConnection;
import OsTools.FaceRecoginze;
import OsTools.tfutil;

public class Machine {
	private String classroomId;
	private FaceRecoginze fr = null;
	private DBConnection db = null;
//	private Camera camera = null;
	public Machine(FaceRecoginze fr, DBConnection db) {
		super();
		this.fr = fr;
		this.db = db;
	}
	
	
	
	
	public String getClassroomId() {
		return classroomId;
	}

	

	public void setClassroomId(String classroomId) {
		this.classroomId = classroomId;
	}

	


	public FaceRecoginze getFr() {
		return fr;
	}




	public void setFr(FaceRecoginze fr) {
		this.fr = fr;
	}




	public List<String> getCourseByClassroomId(int serialNum){
		Connection conn = db.getConnection();
		PreparedStatement pstmt = db.getPstmt();
		String sql=null;
		ResultSet rs = null;
		List<String> courseInfo = new ArrayList<String>();
		sql = "select courseId, courseName\r\n" + 
				"from course\r\n" + 
				"where classroomId = ? and serialNum=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classroomId);
			pstmt.setInt(2, serialNum);
			rs=pstmt.executeQuery();
			rs.next();
			courseInfo.add(rs.getString(1));
			courseInfo.add(rs.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rs=null;
		}
		//--courseId, courseName
		if(rs==null) {                       //can not find 
			return null;
		}
		return courseInfo;
	}
	
	public List<Map> getClassInfoByClassroomId(int serialNum) {
		Connection conn = db.getConnection();
		PreparedStatement pstmt = db.getPstmt();
		String sql=null;
		ResultSet rs = null;
		String placehold =null;
		Map<String, String> studentInfo = new HashMap<String, String>();//studentId, name
//		double imageInfo[][] = new double[1][tfutil.DIMENSIONALITY];//[tfutil.DIMENSIONALITY]double
		Map<String, double[][]> idToImage = new HashMap<>();//studentId, imageInfo
		List<Map> allInfo = new ArrayList<Map>(); 
		List<String> temp = new ArrayList<String>();
		double pix=0;
		try {
			temp = getCourseByClassroomId(serialNum);		
			if(temp == null) {				//can not find
				return null;
			}
			placehold = temp.get(0); 
		
			//-----------------------------
			//select studentId, name from Student
			sql="select studentId,name\r\n" + 
					"from Student\r\n" + 
					"where studentId in\r\n" + 
					"(select studentId\r\n" + 
					"from SC\r\n" + 
					"where  courseId =?\r\n" + 
					")";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, placehold);
			
			rs = pstmt.executeQuery();		
			while(rs.next()) {
				String studentId = rs.getString(1);
				String name = rs.getString(2);
				studentInfo.put(studentId, name);
			}
			
			//studentId, name
			
			//--------------------------------
			//select studentId image
			sql = "select * \r\n" + 
					"from image\r\n" + 
					"where exists  \r\n" + 
					"(select studentId,name\r\n" + 
					"from Student\r\n" + 
					"where studentId in\r\n" + 
					"(select studentId\r\n" + 
					"from SC\r\n" + 
					"where  courseId =?\r\n" + 
					"))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, placehold);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				double imageInfo[][] = new double[1][tfutil.DIMENSIONALITY];
				String studentId = rs.getString(1);
				for(int i=2; i<=513;i++) {
					pix = rs.getDouble(i);
					imageInfo[0][i-2] = pix;
				}
				
				idToImage.put(studentId, imageInfo);
			}
//			pstmt.close();
//			for(int j=0;j<imageInfo[])
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allInfo.add(studentInfo);
		allInfo.add(idToImage);
		
		return allInfo;  //list[studentInfo, idToImage]
	}
	
	public boolean compared(double [][] imageInfo_1, double[][] imageInfo_2) {
		double result;
		result = tfutil.linagNorm(imageInfo_1, imageInfo_2);
		
		if(result<0.37f) {
//			System.out.println(result);
			return true;
		}
			
		else
			return false;
	}
	
//	
}
