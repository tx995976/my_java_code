package com.qst.dms.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.JOptionPane;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.qst.dms.service.UserService;
import com.qst.dms.entity.User;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txt_id;
	private JPasswordField txt_password;
	private UserService userService;
	private static User user; 

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
		
		JLabel userlabel = new JLabel("用户名:");
		userlabel.setFont(new Font("黑体", Font.PLAIN, 16));
		userlabel.setBounds(99, 72, 75, 30);
		contentPane.add(userlabel);
		
		JLabel passwordLabel = new JLabel("密 码:");
		passwordLabel.setFont(new Font("黑体", Font.PLAIN, 16));
		passwordLabel.setBounds(99, 143, 75, 30);
		contentPane.add(passwordLabel);
		
		txt_id = new JTextField();
		txt_id.setBounds(184, 78, 170, 20);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		txt_password = new JPasswordField();
		txt_password.setBounds(184, 149, 170, 20);
		contentPane.add(txt_password);
		
		JButton bt_login = new JButton("登录");
		bt_login.addActionListener(new bt_login_list());
		bt_login.setToolTipText("");
		bt_login.setFont(new Font("黑体", Font.PLAIN, 14));
		bt_login.setBounds(66, 230, 100, 30);
		contentPane.add(bt_login);
		
		JButton bt_clear = new JButton("重置");
		bt_clear.setFont(new Font("黑体", Font.PLAIN, 14));
		bt_clear.setToolTipText("");
		bt_clear.addActionListener(new bt_clear_list());
		bt_clear.setBounds(190, 230, 100, 30);
		contentPane.add(bt_clear);
		
		JButton bt_reg = new JButton("注册");
		bt_reg.addActionListener(new bt_reg_list());
		bt_reg.setFont(new Font("黑体", Font.PLAIN, 14));
		bt_reg.setBounds(318, 230, 100, 30);
		contentPane.add(bt_reg);
	}
	
	public class bt_reg_list implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new RegistFrame();
		}
	}
	public class bt_login_list implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String name = txt_id.getText();
			userService = new UserService();
			user = userService.findUserByName(name);
			if(user == null){
				JOptionPane.showMessageDialog(null,"用户不存在","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String pass = new String(txt_password.getPassword());
			if(user.getPassword().equals(pass)){
				JOptionPane.showMessageDialog(null,"登录成功！","成功",JOptionPane.PLAIN_MESSAGE);
				Login.this.setVisible(false);
				new MainFrame_temp();
			}
			else{
				JOptionPane.showMessageDialog(null,"密码错误","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
	public class bt_clear_list implements ActionListener{
		public void actionPerformed(ActionEvent e){
			txt_id.setText("");
			txt_password.setText("");
		}
	}
}
	
	