package com.qst.dms.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class register extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private final ButtonGroup sex = new ButtonGroup();
	private final ButtonGroup hobby = new ButtonGroup();
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register frame = new register();
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
	public register() {
		setTitle("注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel.setBounds(38, 39, 80, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(38, 81, 80, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("确认密码");
		lblNewLabel_2.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(38, 121, 80, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("性别");
		lblNewLabel_3.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(38, 168, 80, 20);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("爱好");
		lblNewLabel_4.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(38, 215, 80, 20);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("地址");
		lblNewLabel_5.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(38, 279, 80, 20);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("学历");
		lblNewLabel_6.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(38, 373, 42, 20);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnNewButton.setBounds(96, 430, 91, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setBounds(239, 429, 91, 23);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(128, 39, 120, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(128, 81, 120, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(128, 121, 120, 20);
		contentPane.add(passwordField_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("男");
		sex.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(124, 168, 50, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("女");
		sex.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(198, 168, 50, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("阅读");
		chckbxNewCheckBox.setBounds(96, 215, 60, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("上网");
		chckbxNewCheckBox_1.setBounds(170, 215, 60, 23);
		contentPane.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("游泳");
		chckbxNewCheckBox_2.setBounds(239, 215, 60, 23);
		contentPane.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("旅游");
		chckbxNewCheckBox_3.setBounds(315, 215, 60, 23);
		contentPane.add(chckbxNewCheckBox_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(96, 277, 279, 74);
		contentPane.add(textArea);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"小学", "初中", "高中", "学士", "硕士", "博士"}));
		comboBox.setBounds(96, 372, 91, 22);
		contentPane.add(comboBox);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
