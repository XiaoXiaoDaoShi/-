package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dispatch.Dispatch;
import dispatch.FCFS;
import dispatch.HRRN;
import dispatch.PSA;
import dispatch.RR;
import system.OperateSystem;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;

public class MainUI extends JFrame {

	private JPanel contentPane;
	private JTextArea readyTextArea = new JTextArea();
	private JTextArea runTextArea = new JTextArea();
	private JTextArea waitTextArea = new JTextArea();
	private JTextArea finishedTextArea = new JTextArea();
	private OperateSystem os;
	private JTextField initialProcessTextField;
	
	private Dispatch dis;
	
	private boolean isStart = false;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainUI frame = new MainUI();
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
	public MainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane readyScrollPane = new JScrollPane();
		readyScrollPane.setBounds(14, 32, 168, 327);
		contentPane.add(readyScrollPane);
		
		
		readyScrollPane.setViewportView(readyTextArea);
		
		JLabel label = new JLabel("\u5C31\u7EEA\u961F\u5217");
		readyScrollPane.setColumnHeaderView(label);
		
		JScrollPane runScrollPane = new JScrollPane();
		runScrollPane.setBounds(196, 32, 159, 327);
		contentPane.add(runScrollPane);
		
		JLabel label_1 = new JLabel("\u8FD0\u884C\u961F\u5217");
		runScrollPane.setColumnHeaderView(label_1);
		
		
		runScrollPane.setViewportView(runTextArea);
		
		JScrollPane waitScrollPane = new JScrollPane();
		waitScrollPane.setBounds(369, 32, 150, 327);
		contentPane.add(waitScrollPane);
		
		JLabel label_2 = new JLabel("\u963B\u585E\u961F\u5217");
		waitScrollPane.setColumnHeaderView(label_2);
		
		
		waitScrollPane.setViewportView(waitTextArea);
		
		final JButton askIoBtn = new JButton("\u8BF7\u6C42IO");
		askIoBtn.setBounds(533, 166, 132, 27);
		askIoBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==askIoBtn) {
					os.askForIO();
				}
				
			}
		});
		contentPane.add(askIoBtn);
		
		JButton overIoBtn = new JButton("IO\u7ED3\u675F");
		overIoBtn.setBounds(533, 217, 132, 27);
		overIoBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				os.ioIsOver();
				
			}
		});
		contentPane.add(overIoBtn);
		
		JButton addProcessBtn = new JButton("\u6DFB\u52A0\u4E00\u4E2A\u8FDB\u7A0B");
		addProcessBtn.setBounds(533, 314, 132, 45);
		addProcessBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {			
				os.createOneProcess();		
			}
		});
		contentPane.add(addProcessBtn);
		
		JScrollPane finishedScrollPane = new JScrollPane();
		finishedScrollPane.setBounds(14, 372, 505, 146);
		contentPane.add(finishedScrollPane);
		
		
		finishedScrollPane.setViewportView(finishedTextArea);
		
		JLabel finishedLabel = new JLabel("\u7ED3\u675F\u7684\u8FDB\u7A0B\u7684\u53CA\u5176\u5468\u8F6C\u65F6\u95F4");
		finishedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		finishedScrollPane.setColumnHeaderView(finishedLabel);
		
		JButton startBtn = new JButton("start");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int processNum =  Integer.valueOf(initialProcessTextField.getText());
				if(!isStart && dis!=null) {
					isStart = true;
					os = OperateSystem.getInstace(dis);
					
					os.setReadyTextArea(readyTextArea);
					os.setRunTextArea(runTextArea);
					os.setWaitTextArea(waitTextArea);
					os.setFinishedTextArea(finishedTextArea);
					os.createAnyProcess(processNum, true);
					os.open();
					os.start();
				}
				
			}
		});
		startBtn.setBounds(533, 126, 132, 27);
		contentPane.add(startBtn);
		
		JButton closeBtn = new JButton("close");
		closeBtn.setBounds(533, 386, 132, 27);
		closeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				os.close();
			}
		});
		contentPane.add(closeBtn);
		
		JLabel initalProcessLabel = new JLabel("\u521D\u59CB\u5316\u8FDB\u7A0B\u6570:");
		initalProcessLabel.setBounds(533, 33, 129, 18);
		contentPane.add(initalProcessLabel);
		
		initialProcessTextField = new JTextField();
		initialProcessTextField.setText("10");
		initialProcessTextField.setBounds(533, 51, 86, 24);
		contentPane.add(initialProcessTextField);
		initialProcessTextField.setColumns(2);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(533, 89, 129, 24);
		comboBox.addItem("选择调度算法");
		comboBox.addItem("先进先出");
		comboBox.addItem("时间片轮转");
		comboBox.addItem("优先级调度");
		comboBox.addItem("高响应比");
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(ItemEvent.SELECTED == e.getStateChange()) {
					String type = e.getItem().toString();
					if(type.equals("先进先出")) {
						dis=new FCFS();
					}
					else if(type.equals("时间片轮转")) {
						dis = new RR(1);
					}
					else if(type.equals("优先级调度")) {
						dis = new PSA();
					}
					else if(type.equals("高响应比")) {
						dis = new HRRN();
					}
					else {
						;
					}
				}
				
				
				
			}
		});
		contentPane.add(comboBox);
		
		this.readyTextArea.append("123");
	}

	private void setOS(OperateSystem os) {
	
		os.setReadyTextArea(readyTextArea);
		os.setRunTextArea(runTextArea);
		os.setWaitTextArea(waitTextArea);
		os.setFinishedTextArea(finishedTextArea);
	}
}
