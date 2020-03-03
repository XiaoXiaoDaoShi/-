import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.highgui.ImageWindow;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.tensorflow.Output;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;

import Administration.Adminstrator;
import OsTools.DBConnection;
import OsTools.FaceRecoginze;
import OsTools.tfutil;
import User.Camera;
import User.ClassRoom;
import User.Machine;
import User.MyTime;
import User.MyTimeThread;
import User.Student;


public class Demo {
	static {
		//在使用OpenCV前必须加载Core.NATIVE_LIBRARY_NAME类,否则会报错
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		String DBDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//		String url = "jdbc:sqlserver://localhost:1433;databaseName=AttendanceSystem;integratedSecurity=true;";
		String image1 = "src1.jpg";
		String image2 = "src2.jpg";
		Mat imageMat1 = Imgcodecs.imread(image1,1);
		Mat imageMat2 = Imgcodecs.imread(image2,1);
		
		FaceRecoginze fr = FaceRecoginze.getInstance();
		System.out.println(tfutil.linagNorm(fr.imageTodoubleArray(imageMat1), fr.imageTodoubleArray(imageMat2)));
//		double[][] arr = fr.imageTodoubleArray(imageMat1);
//		for(int i=0;i<arr[0].length;i++)
//		System.out.println(arr[0][i]);
//		Imgcodecs.imwrite("lll.jpg", imageMat1);
//		System.out.println(tfutil.MatToMatrix(imageMat1)[0][0][0][0]);
	}
}
