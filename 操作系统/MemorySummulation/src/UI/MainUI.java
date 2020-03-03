package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;

import dispatch.Dispatch;
import dispatch.FIFO;
import dispatch.LRUByCount;
import dispatch.LRUByStack;
import memory.Memory;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class MainUI extends JFrame {

	private JPanel contentPane;
	private JTextArea backQueueTextArea = new JTextArea();
	private JTextArea stayTextArea = new JTextArea();
	private JTextArea weedOutTextArea = new JTextArea();
	private	JTextField lackTextField = new JTextField();
	private JTextField hitTextField = new JTextField();
	
	private Memory memory;
	private Dispatch model;
	
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
		setBounds(100, 100, 626, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane backQueueScrollPane = new JScrollPane();
		backQueueScrollPane.setBounds(24, 26, 118, 364);
		contentPane.add(backQueueScrollPane);
		
		JLabel backQueueLabel = new JLabel("\u540E\u5907\u961F\u5217");
		backQueueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backQueueScrollPane.setColumnHeaderView(backQueueLabel);
		
		
		backQueueScrollPane.setViewportView(backQueueTextArea);
		
		JScrollPane stayScrollPane = new JScrollPane();
		stayScrollPane.setBounds(184, 26, 118, 364);
		contentPane.add(stayScrollPane);
		
		JLabel stayLabel = new JLabel("\u9A7B\u7559\u96C6");
		stayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stayScrollPane.setColumnHeaderView(stayLabel);
		
		
		stayScrollPane.setViewportView(stayTextArea);
		
		JButton createPageBtn = new JButton("\u521B\u5EFA\u9875\u9762");
		createPageBtn.setBounds(402, 117, 149, 27);
		createPageBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				memory.createOnePage();
				memory.listen(backQueueTextArea, stayTextArea, hitTextField, lackTextField);
			}
		});
		contentPane.add(createPageBtn);
		
		JButton exchangeBtn = new JButton("\u7F6E\u6362\u4E00\u4E2A\u9875\u9762");
		exchangeBtn.setBounds(402, 203, 149, 27);
		exchangeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				memory.addOnePage();
				memory.listen(backQueueTextArea, stayTextArea, hitTextField,lackTextField);		
			}
		});
		contentPane.add(exchangeBtn);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(402, 35, 149, 24);
		comboBox.addItem("选择置换算法");
		comboBox.addItem("FIFO");
		comboBox.addItem("LRU计数法");
		comboBox.addItem("LRU栈");
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(ItemEvent.SELECTED == arg0.getStateChange()) {
					String type = arg0.getItem().toString();
					System.out.println(type);
				if(type.equals("FIFO")){
					model = new FIFO();
				}
				else if(type.equals("LRU计数法")){
					model = new LRUByCount();
				}
				else if(type.equals("LRU栈")) {
					model = new LRUByStack();
				}
				else{
					;
				}
				}
				
			}
		});
		contentPane.add(comboBox);
		
		JScrollPane weedOutScrollPane = new JScrollPane();
		weedOutScrollPane.setBounds(24, 403, 278, 87);
		contentPane.add(weedOutScrollPane);
		
		
		weedOutScrollPane.setViewportView(weedOutTextArea);
		
		JLabel weedOutLabel = new JLabel("\u6DD8\u6C70\u9875\u9762");
		weedOutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		weedOutScrollPane.setColumnHeaderView(weedOutLabel);
		
		JButton startOnePageBtn = new JButton("\u968F\u673A\u8FD0\u884C\u9875\u9762");
		startOnePageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				memory.randomUseOnePage();
				memory.listen(backQueueTextArea, stayTextArea, hitTextField, lackTextField);
			}
		});
		startOnePageBtn.setBounds(402, 157, 149, 27);
		contentPane.add(startOnePageBtn);
		
		JButton startBtn = new JButton("start");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				memory = new Memory(5);
				memory.setModel(model);
				memory.setWeedOutTextArea(weedOutTextArea);
			}
		});
		startBtn.setBounds(402, 72, 149, 27);
		contentPane.add(startBtn);
		
		JLabel hitLabel = new JLabel("\u547D\u4E2D\u7387:");
		hitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		hitLabel.setBounds(316, 264, 72, 18);
		contentPane.add(hitLabel);
		
		hitTextField.setBounds(395, 261, 86, 24);
		contentPane.add(hitTextField);
		hitTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u7F3A\u9875\u7387\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(316, 303, 72, 18);
		contentPane.add(lblNewLabel);
		
		
		lackTextField.setBounds(395, 300, 86, 24);
		contentPane.add(lackTextField);
		lackTextField.setColumns(10);
	}
	
	public void setMemory(Memory memeory){
		this.memory = memeory;
	}
}
