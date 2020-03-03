package Administration;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import OsTools.DBConnection;
import OsTools.FaceRecoginze;

import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
//

public class AdminUI1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea; 
	private JScrollPane scrollPane ;
	private String filePath = null;;
	private String floder = null;;
//	private boolean isStart = new Boolean(false);
	private Adminstrator admin ;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminUI1 frame = new AdminUI1();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AdminUI1() {
		setType(Type.UTILITY);
		setTitle("\u56FE\u50CF\u4E0A\u4F20");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 703, 396);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 685, 84);
		contentPane.add(panel);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("\u70B9\u51FB\u9009\u62E9\u56FE\u7247\u8DEF\u5F84");
		textField_1.setColumns(40);
		
		JButton btnFind1 = new JButton("\u67E5\u627E");
		btnFind1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				JFileChooser jf = new JFileChooser();
				jf.setFileSelectionMode(jf.FILES_ONLY);
				jf.setFileFilter(new FileFilter() {

					@Override
					public boolean accept(File f) {
						String name = f.getName();  
				        if(name.endsWith(".jpg") || name.endsWith(".png") || f.isDirectory())  
				            return true;  
				        else  
				            return false;  
					}

					@Override
					public String getDescription() {
						
						return null;
					}
					
				});
				jf.showOpenDialog(null);
				File f = jf.getSelectedFile();
				String s=null;
				if(f!=null) {
					s = f.getAbsolutePath();
					filePath=s;
				}
					
				textField_1.setText(s);
			}
		});
		btnFind1.setHorizontalAlignment(SwingConstants.RIGHT);
		btnFind1.setActionCommand("open");
		btnFind1.setBackground(Color.WHITE);
		btnFind1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(40);
		textField_2.setText("\u70B9\u51FB\u9009\u62E9\u56FE\u50CF\u6587\u4EF6\u5939");
		
		JButton btnFind2 = new JButton("\u67E5\u627E");
		btnFind2.setHorizontalAlignment(SwingConstants.RIGHT);
		btnFind2.setActionCommand("open");
		
		btnFind2.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent arg0) {
				
				if(arg0.getActionCommand().equals("open")) {
					JFileChooser jf = new JFileChooser();
					jf.setFileSelectionMode(jf.DIRECTORIES_ONLY);
					jf.showOpenDialog(null);
					File f = jf.getSelectedFile();
					String s=null;
					if(f!=null) {
						s = f.getAbsolutePath();
						floder = s;
					}
					 
					textField_2.setText(s);
				}
			}
		});
		btnFind2.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JButton btnUp_1 = new JButton("\u5355\u5F20\u4E0A\u4F20"); //单张上传
		btnUp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(admin.isStart()&&filePath!=null) {
					String back = null;
						back = admin.uploadByOne(filePath);
						textArea.append(back+"\n");
					
				}
			}
		});
		btnUp_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton btnUp_2 = new JButton("\u6279\u91CF\u4E0A\u4F20");  //批量上传
		btnUp_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(admin.isStart()&&floder!=null) {
					admin.upLoadByAny(floder, textArea);;
				}
			}
		});
		btnUp_2.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnUp_1)
						.addComponent(btnUp_2))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFind1))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFind2)))
					.addContainerGap(167, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFind1)
						.addComponent(btnUp_1))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFind2)
						.addComponent(btnUp_2))
					.addGap(9))
		);
		panel.setLayout(gl_panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 84, 685, 239);
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea("正在初始化中....\n");
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setDropMode(DropMode.INSERT);
		scrollPane.setViewportView(textArea);
		
		JLabel lbljpg = new JLabel("\u7167\u7247\u547D\u540D\u683C\u5F0F\u4E3A\uFF1A\u5B66\u53F7.jpg");
		lbljpg.setFont(new Font("宋体", Font.PLAIN, 20));
		lbljpg.setBounds(0, 323, 685, 26);
		contentPane.add(lbljpg);
		
		admin = new Adminstrator();
		textArea.append("初始化成功\n");
		
	}
}
