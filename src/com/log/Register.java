package com.log;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Dao.User_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Register.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Register {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	User_Dao userDB = new User_Dao(driverName, uri);

	/** The frame register. */
	private JFrame frame_register;
	
	/** The lb register. */
	private JLabel lb_register;
	
	/** The lb user. */
	private JLabel lb_user;
	
	/** The lb pswd. */
	private JLabel lb_pswd;
	
	/** The lb again. */
	private JLabel lb_again;
	
	/** The btn sure. */
	private JButton btn_sure;
	
	/** The btn cancle. */
	private JButton btn_cancle;
	
	/** The panel north. */
	private JPanel panelNorth;
	
	/** The panel center. */
	private JPanel panelCenter;
	
	/** The panel south. */
	private JPanel panelSouth;
	
	/** The txt user. */
	private JTextField txt_user;
	
	/** The txt pswd. */
	private JPasswordField txt_pswd;
	
	/** The txt again. */
	private JPasswordField txt_again;
	
	/** The cb 1. */
	private JComboBox<String> cb1;

	/**
	 * Instantiates a new register.
	 */
	Register() {

		frame_register = new JFrame("注册账号");
		frame_register.setSize(300, 200);
		frame_register.setLocation(800, 300);
		lb_register = new JLabel("注册账号");
		lb_user = new JLabel("账号");
		lb_pswd = new JLabel("密码");
		lb_again = new JLabel("再次输入密码");
		btn_sure = new JButton("确定");
		btn_cancle = new JButton("取消");
		txt_user = new JTextField(10);
		txt_pswd = new JPasswordField();
		txt_again = new JPasswordField();
		cb1 = new JComboBox<String>();
		cb1.addItem("学生");
		cb1.addItem("管理员");

		panelNorth = new JPanel();
		panelCenter = new JPanel();
		panelSouth = new JPanel();

		frame_register.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelCenter.setLayout(new GridLayout(3, 2));
		panelSouth.setLayout(new FlowLayout());

		frame_register.add(panelNorth, BorderLayout.NORTH);
		frame_register.add(panelCenter, BorderLayout.CENTER);
		frame_register.add(panelSouth, BorderLayout.SOUTH);
		// 向嵌入式容器加入控制组件
		panelNorth.add(lb_register);

		panelCenter.add(lb_user);
		panelCenter.add(txt_user);
		panelCenter.add(lb_pswd);
		panelCenter.add(txt_pswd);
		panelCenter.add(lb_again);
		panelCenter.add(txt_again);
		panelSouth.add(cb1);
		panelSouth.add(btn_sure);
		panelSouth.add(btn_cancle);

		frame_register.setVisible(true);
		frame_register.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 确定按钮事件
		btn_sure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String user_name = txt_user.getText();
				String privilege = cb1.getSelectedItem().toString();// 获取注册用户选择的管理权限，存入数据库中
				String sql = "select * from user_account where user_id='" + user_name + "';";// 这个sql写错了吧。你是要干嘛？
				// 是不是要先查询下，根据user-id?
				try {
					// psmt = new Dao().getConnection().prepareStatement(sql);
					ResultSet rs = userDB.queryUserInfo(sql);
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "用户名重复，请重新输入");
						// 置空所有文本框
						txt_user.setText("");
						txt_pswd.setText("");
						txt_again.setText("");
					} else if (!(txt_pswd.getText().equals(txt_again.getText()))) {
						JOptionPane.showMessageDialog(null, "密码不一致，请重新输入密码");
						txt_pswd.setText("");
						txt_again.setText("");
					} else {
						String sql2 = "insert into user_account(user_id,pswd,power) values(?,?,?)";
						String user_name1 = txt_user.getText();
						String pswd1 = txt_pswd.getText();
						String privilege1 = cb1.getSelectedItem().toString();
						userDB.saveUserInfo(sql2, user_name1, pswd1, privilege1);
						JOptionPane.showMessageDialog(null, "注册成功！");
						//注册成功之后回到登录界面
						frame_register.setVisible(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					userDB.closeAll();
				}

			}

		});
		
		//取消按钮事件
		btn_cancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame_register.setVisible(false);
			}
			
		});

	}
}
