package User;

import OsTools.tfutil;

public class Student {
	private String studentId;
	private  String name;
	private  String grade=null;
	private  int className;
	private  double [][] imgInfo= new double[1][tfutil.DIMENSIONALITY];
	public Student(String id, String name) {
		super();
		this.studentId = id;
		this.name = name;
	}
	public Student(String id, String name, double[][] imgInfo) {
		super();
		this.studentId = id;
		this.name = name;
		this.imgInfo = imgInfo;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getClassName() {
		return className;
	}
	public void setClassName(int className) {
		this.className = className;
	}
	public double[][] getImgInfo() {
		return imgInfo;
	}
	public void setImgInfo(double[][] imgInfo) {
		this.imgInfo = imgInfo;
	}
	
	
	
}
