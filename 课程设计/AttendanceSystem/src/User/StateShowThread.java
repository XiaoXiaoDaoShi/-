package User;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StateShowThread extends Thread{
	private ClassRoom classRoom;
	private JTextArea textArea;
	private JTextField courseNameField;
	private JTextField studentNumField;
	
//	public StateShowThread(ClassRoom classRoom, JTextArea textArea) {
//		super();
//		this.classRoom = classRoom;
//		this.textArea = textArea;
//	}


	public StateShowThread(ClassRoom classRoom, JTextArea textArea, JTextField courseNameField,
			JTextField studentNumField) {
		super();
		this.classRoom = classRoom;
		this.textArea = textArea;
		this.courseNameField = courseNameField;
		this.studentNumField = studentNumField;
	}


	void show() {						
		//显示未签到的学生
		while(true) {
			classRoom.showStudent(textArea, courseNameField, studentNumField);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void run() {
		show();
	}
}
