package User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String classroomId = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseUI frame = new ChooseUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChooseUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("ËÎÌו", Font.PLAIN, 19));
		textField.setBounds(137, 76, 162, 24);
		contentPane.add(textField);
		textField.setColumns(10);
//		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u6559\u5BA4");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		lblNewLabel.setBounds(137, 32, 162, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				classroomId = textField.getText();
				if(arg0.getSource()==btnNewButton) {
					dispose();
					new UserUI(classroomId);
				}
								
			}
		});
		btnNewButton.setBounds(162, 138, 113, 27);
		contentPane.add(btnNewButton);
	}
}
