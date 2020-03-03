package User;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import OsTools.DBConnection;
import OsTools.FaceRecoginze;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.JTextField;

public class UserUI extends JFrame {
	
	private JPanel contentPane;
	private JLabel imageLabel;
	private Camera camera;
	private String classrommId;
	private JTextArea textArea;
	private JTextField courseNameField;
	private JTextField studentNumField;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserUI frame = new UserUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
	public UserUI(String classroomId) {
		setTitle("\u7B7E\u5230");
		this.classrommId=classroomId;
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 667);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(604, 71, 170, 529);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setLineWrap(true);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 25));
		textArea.setBorder(null);
		scrollPane_1.setViewportView(textArea);
		scrollPane_1.setBorder(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 529);
		contentPane.add(panel);
		
		imageLabel = new JLabel("");
		imageLabel.setBounds(0,0,0,0);
		
		panel.add(imageLabel);
		
		JLabel lblNewLabel = new JLabel("\u672A\u7B7E\u5230\u540C\u5B66");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 34));
		lblNewLabel.setBounds(604, 0, 170, 58);
		contentPane.add(lblNewLabel);
		
		JLabel courseNameLabel = new JLabel("\u5F53\u524D\u8BFE\u7A0B\uFF1A");
		courseNameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		courseNameLabel.setBounds(0, 538, 112, 34);
		contentPane.add(courseNameLabel);
		
		courseNameField = new JTextField();
		courseNameField.setForeground(Color.BLACK);
		courseNameField.setFont(new Font("宋体", Font.PLAIN, 20));
		courseNameField.setEditable(false);
		courseNameField.setBounds(121, 542, 273, 24);
		contentPane.add(courseNameField);
		courseNameField.setColumns(10);
		
		JLabel studentNumLabel = new JLabel("\u5B66\u751F\u4EBA\u6570\uFF1A");
		studentNumLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		studentNumLabel.setBounds(0, 579, 102, 24);
		contentPane.add(studentNumLabel);
		
		studentNumField = new JTextField();
		studentNumField.setEditable(false);
		studentNumField.setForeground(Color.BLACK);
		studentNumField.setFont(new Font("宋体", Font.PLAIN, 20));
		studentNumField.setBounds(121, 579, 273, 24);
		contentPane.add(studentNumField);
		studentNumField.setColumns(10);
		
		JLabel stateLabel = new JLabel("\u672A\u7B7E\u5230\r\n\u540C\u5B66");
		stateLabel.setFont(new Font("宋体", Font.PLAIN, 34));
		stateLabel.setBounds(604, 0, 170, 72);
		loadContent();
	}
	
	void loadContent() {
		DBConnection db = new DBConnection();
		FaceRecoginze fr = FaceRecoginze.getInstance();
		Machine machine = new Machine(fr, db);
		ClassRoom classRoom = new ClassRoom(classrommId, machine);
		MyTime myTime = new MyTime();
		myTime.readTableTime(db.getTimeTable());												//获得课表时间
		
		Camera camera = new Camera();              												//摄像头线程
		camera.setQueue(classRoom.getQueue());      											//图像队列
		camera.setImageLabel(imageLabel);
		MyTimeThread mtThread = new MyTimeThread(classRoom, myTime);     						//更新课表线程
		StateShowThread stateShowT = new StateShowThread(classRoom, textArea, courseNameField, studentNumField);    				//显示未签到线程
		
		SignInThread siThread = new SignInThread(classRoom);    									//签到线程
		camera.start();
		mtThread.start();
		stateShowT.start();
		siThread.start();
	}
}
