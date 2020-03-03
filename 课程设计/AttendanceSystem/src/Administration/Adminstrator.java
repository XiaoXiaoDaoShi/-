package Administration;

import java.io.File;

import javax.swing.JTextArea;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import OsTools.DBConnection;
import OsTools.FaceRecoginze;
import OsTools.tfutil;;
public class Adminstrator {
	static {
		//在使用OpenCV前必须加载Core.NATIVE_LIBRARY_NAME类,否则会报错
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	private String name = "管理员";
	private FaceRecoginze fr = null;
	private DBConnection db = null;
	private boolean flag_1 = false;
	private boolean flag_2 = false;
	private boolean isStart=false;
	
	
	public Adminstrator() {
		fr = FaceRecoginze.getInstance();
		if(fr.isLoadSuccessfully())
			flag_1 = true;
		db = new DBConnection();
		if(db.isLoadSuccessfully())
			flag_2 = true;
		if(flag_1&&flag_2)
			isStart=true;
	}
	
//	public Adminstrator getAdmin() {
//		
//		
//	}
	
	
	public boolean getDBIsSuccessfully() {
		return flag_1;
	}
	
	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public boolean getFrIsSuccessfully() {
		return flag_2;                                               
	}
//	
	public String uploadByOne(String filename) {
		String studentId = null;
		String state = null;
		studentId = tfutil.findStudentId(filename);
		Mat imageMat = tfutil.readImage(filename);
		if(imageMat != null) {
				double [][] imageInfo = new double[1][tfutil.DIMENSIONALITY];
			imageInfo = fr.imageTodoubleArray(imageMat);
			
			if(db.studentIsExists(studentId)) {
				db.upData(studentId, imageInfo);	
				state = " 已更新";
					
			}
			else {
				db.insertData(studentId, imageInfo);
				state = " 已添加";
			}
		}
		
		else {
			state = "不合法";
		}
		
		return studentId + state;
	}
	
	public void upLoadByAny(String flodername, JTextArea textArea) {
		String filename=null;
		String studentId = null;
		String state = null;
		double [][] imageInfo = new double[1][tfutil.DIMENSIONALITY];
		File file = new File(flodername);
		String[] fileList = file.list();
		for(int i=0;i<fileList.length;i++) {
			fileList[i] = flodername + "\\" +fileList[i];
			filename = fileList[i];
			studentId = tfutil.findStudentId(filename);
			Mat imageMat = tfutil.readImage(filename);
			if(imageMat != null) {
				imageInfo = fr.imageTodoubleArray(imageMat);
				if(db.studentIsExists(studentId)) {
					db.upData(studentId, imageInfo);	
					state = " 已更新";
				}
				else {
					db.insertData(studentId, imageInfo);
					state = " 已添加";
			}
			textArea.append(studentId + state +"\n");
			}
			
			else {
				state = "不和法";
				textArea.append(studentId + state +"\n");
			}
		}	
	}
	
	public void addStudent(String studentId, String name, Mat imageMat) {
//		imageMat = tfutil.resizeImage(imageMat);
		double[][] imageInfo = fr.imageTodoubleArray(imageMat);
		db.insertStudentInfo(studentId, name);
		db.insertData(studentId, imageInfo);
	}
	
}
