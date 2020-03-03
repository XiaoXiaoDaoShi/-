package Administration;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;

public class AdminUI2 extends JFrame {
	
	static {
		//在使用OpenCV前必须加载Core.NATIVE_LIBRARY_NAME类,否则会报错
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	private JPanel contentPane;
	private boolean isShot = new Boolean(false);
	private CameraAdmin cameraAdmin;
	private Adminstrator admin;
	private JTextArea textArea;
	private boolean isStart = new Boolean(false);
	private JLabel imageLabel;
//	private Mat faceImage = new Mat();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminUI2 frame = new AdminUI2();
//					frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AdminUI2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 605);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(5, 526, 590, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			 
				Mat temp = cameraAdmin.getFaceImage();
				if(temp!=null) {
				cameraAdmin.callWait();
					
				String studentId = JOptionPane.showInputDialog("请输入学生学号");
				
				String name = JOptionPane.showInputDialog("请输入学生姓名");
				if(studentId!=null&&name!=null) {
//					System.out.println(temp);
				Imgcodecs.imwrite("ui2.jpg", temp);
				admin.addStudent(studentId, name, temp);
				
				int n=JOptionPane.showConfirmDialog(null, "上传成功","提示信息",JOptionPane.DEFAULT_OPTION);
				
				}
				
				cameraAdmin.call();
				}
			}
		});
		imageLabel = new JLabel("");
		imageLabel.setBounds(0, 0, 595, 525);
		contentPane.add(btnNewButton);
		contentPane.add(imageLabel);
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setText("\u6B63\u5728\u521D\u59CB\u5316...");
		textArea.setBounds(599, 5, 75, 548);
		contentPane.add(textArea);
		
		admin = new Adminstrator();
//		AdminThread adminThread = new AdminThread(textArea, isStart);
		cameraAdmin = new CameraAdmin(imageLabel);		
		
		cameraAdmin.start();
		
//		adminThread.start();
//		cameraAdmin
	}
}
