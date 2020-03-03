package User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.opencv.core.Mat;

import OsTools.FaceRecoginze;
import OsTools.tfutil;

public class ClassRoom {
	private String classroomId;
	private String courseId;
	private String courseName = "暂无";
	private int studentNum = 0;
	private  int serialNum;
	private Machine machine= null;
	private  List<Student> studentList = new ArrayList<Student>();	
	private StudentQueue queue = new StudentQueue();
	public ClassRoom() {}	
	
	public ClassRoom(String classroomId, Machine machine) {
		super();
		this.classroomId = classroomId;
		this.machine = machine;
		this.machine.setClassroomId(classroomId);
	}
	
	//setter and getter
	
	public String getClassroomId() {
		return classroomId;
	}

	public StudentQueue getQueue() {
		return queue;
	}

	public void setQueue(StudentQueue queue) {
		this.queue = queue;
	}

	public void setClassroomId(String classroomId) {
		this.classroomId = classroomId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
//setter and getter
	
	public void reStart(int serialNum ) {
		setSerialNum(serialNum);
		studentList.clear();
		loadStudent();
	}
	
	public void loadCourseIdAndCourseName() {
		List<String> courseInfo = new ArrayList<String>();
		courseInfo = this.machine.getCourseByClassroomId(this.serialNum);
		this.courseId = courseInfo.get(0);
		this.courseName = courseInfo.get(1);
	}
	
	public void loadStudent() {
		studentList.clear();
//		System.out.println("load");
		queue.setStarted(true);
		List<Map> allInfo = new ArrayList<Map>();
		Map<String, double[][]> idToImage = new HashMap<>();
		Map<String, String> studentInfo = new HashMap<String, String>();
		
		double [][] imageInfo = new double[1][tfutil.DIMENSIONALITY];
		String studentId = null;
		String name=null;
		int count = 0;
		
		allInfo = this.machine.getClassInfoByClassroomId(this.serialNum);
		studentInfo = allInfo.get(0);
		idToImage = allInfo.get(1);
		
//		imageInfo = idToImage.get("00001");
//		for(int j=0;j<10;j++) {
//			System.out.print(imageInfo[0][j]);
//		}
		
		Set<String> keySet1 = studentInfo.keySet(); // studentInfo
		Set<String> keySet2 = idToImage.keySet();  // idToImage
		
//		Iterator<Map.Entry<String, String>> iter1 = studentInfo.entrySet().iterator(); // studentInfo
//		Iterator<Map.Entry<String, double[][]>> iter2 = idToImage.entrySet().iterator(); // studentInfo
		int len = keySet2.size();
		String [] temp = new String[len];
		keySet2.toArray(temp);
		
		
		
		for(int i=0;i<len;i++) {
//			 Map.Entry<String,String> entry1=iter1.next();
//			 Map.Entry<String,double[][]> entry2=iter2.next();
		
			 studentId =temp[i];
			 name = studentInfo.get(studentId);
			 imageInfo = idToImage.get(studentId);
				
			// studentId = the key of map
			
			if(imageInfo!=null) {
				addStudent(new Student(studentId, name, imageInfo));
//				System.out.println("\n"+i +": "+studentId);	
				count++;
			}
			
				
			
		}
		this.studentNum=count;
//		System.out.println(studentNum);
	}
	
	public void addStudent(Student stu) {
		this.studentList.add(stu);
	}

	public void signIn() {   //开始签到
		double [][] imageInfo_1 = new double[1][tfutil.DIMENSIONALITY];
		double [][] imageInfo_2 = null;
		FaceRecoginze fr = machine.getFr();
		while(studentNum>0) {
			Mat imageMat = queue.getImageMat();     //从采集队列中获得图像
			imageInfo_1 = fr.imageTodoubleArray(imageMat); //图像处理
			
			for(int i=0;i<studentNum;i++) {
				imageInfo_2 = studentList.get(i).getImgInfo();
//				System.out.print(studentList.get(i).getStudentId() + ": \n");
				if(machine.compared(imageInfo_1, imageInfo_2)) {
					
					studentList.remove(i);
					studentNum--;
				}
//													
			}			
		}	
		if(studentNum<=0) {
			queue.setStarted(false);
			queue.clear();
		}
	}
	
	public void showStudent(JTextArea textArea,JTextField courseNameField, JTextField studentNumField) {   //显示未签到的同学
		textArea.setText(null);
		courseNameField.setText(null);
		studentNumField.setText(null);
		for(int i=0;i<studentList.size();i++) {
			textArea.append(studentList.get(i).getName() + "\n");
		}
		courseNameField.setText(courseName);
		studentNumField.setText(String.valueOf(studentNum));
	}
	
	public void reLoad(int serialNum) {
		loadCourseIdAndCourseName();
		loadStudent();
	}
	
	public boolean signIsOver() {
		if(queue.isStarted() && studentNum<=0)
			return true;
		else
			return false;
	}
	
}
