package com.qst.dms.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名:");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 16));
		lblNewLabel.setBounds(99, 72, 75, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密 码:");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(99, 143, 75, 30);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(184, 78, 170, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(184, 149, 170, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setToolTipText("");
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 14));
		btnNewButton.setBounds(66, 230, 100, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 14));
		btnNewButton_1.setToolTipText("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(190, 230, 100, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("注册");
		btnNewButton_2.setFont(new Font("黑体", Font.PLAIN, 14));
		btnNewButton_2.setBounds(318, 230, 100, 30);
		contentPane.add(btnNewButton_2);
	}
}
