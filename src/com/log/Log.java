package com.log;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Choice.Manager_choice;
import Choice.Stu_choice;
import Dao.User_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Log.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Log {

	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public User_Dao userDB = new User_Dao(driverName, uri);

	/** The user name. */
	// 用户名和密码
	public static JTextField userName;
	
	/** The pass word. */
	private JPasswordField passWord;

	/** The frame log. */
	private JFrame frame_log;// 大容器

	/** The panel north. */
	private JPanel panelNorth;// 面板
	
	/** The panel center. */
	private JPanel panelCenter;
	
	/** The panel south. */
	private JPanel panelSouth;
	
	/** The title. */
	// 标签
	private JLabel title;// 大标题横幅
	
	/** The lb 1 user. */
	private JLabel lb1_user;
	
	/** The lb 2 psd. */
	private JLabel lb2_psd;
	
	/** The lb 3 remember psd. */
	private JLabel lb3_rememberPsd;
	
	/** The lb 4 log. */
	private JLabel lb4_log;

	/** The bt 1 register. */
	// 按钮
	private JButton bt1_register;
	
	/** The bt 2 log. */
	private JButton bt2_log;

/** The cb 1. */
//列表框
	private JComboBox<String> cb1;

	/**
	 * Instantiates a new log.
	 */
	public Log() {
		// 准备组件
		frame_log = new JFrame("学生信息系统");

		panelNorth = new JPanel();
		title = new JLabel("学生信息系统");
		title.setFont(new Font("宋体", Font.PLAIN, 50));

		panelCenter = new JPanel();
		lb1_user = new JLabel("用户名：");
		lb1_user.setFont(new Font("宋体", Font.BOLD, 17));
		userName = new JTextField(10);
		lb2_psd = new JLabel("密码：");
		lb2_psd.setFont(new Font("宋体", Font.BOLD, 17));
		passWord = new JPasswordField();

		panelSouth = new JPanel();
		cb1 = new JComboBox<String>();
		cb1.addItem("学生");
		cb1.addItem("管理员");

		bt1_register = new JButton("注册");

		bt2_log = new JButton("登录");
		bt2_log.setFont(new Font("宋体", Font.PLAIN, 24));
		bt2_log.setForeground(Color.RED);
		bt2_log.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// 设置容器
		frame_log.setSize(500, 300);
		frame_log.setLocation(800, 300);
		frame_log.setResizable(false);
		// 设置容器布局类型
		frame_log.setLayout(new BorderLayout());// 边框布局
		panelNorth.setLayout(new FlowLayout());
		panelCenter.setLayout(new GridLayout(3, 2));
		panelSouth.setLayout(new FlowLayout());
		// 向顶层容器加入嵌入式容器
		frame_log.add(panelNorth, BorderLayout.NORTH);
		frame_log.add(panelCenter, BorderLayout.CENTER);
		frame_log.add(panelSouth, BorderLayout.SOUTH);
		// 向嵌入式容器加入控制组件
		panelNorth.add(title);

		panelCenter.add(lb1_user);
		panelCenter.add(userName);
		panelCenter.add(lb2_psd);
		panelCenter.add(passWord);

		panelSouth.add(cb1);
		panelSouth.add(bt1_register);

		panelSouth.add(bt2_log);

		// 显示顶层容器
		frame_log.setVisible(true);
		frame_log.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 注册事件
		bt1_register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {// 进入注册面板
				// TODO Auto-generated method stub
				new Register();
				//frame_log.setVisible(false);
			}

		});
		// 登录事件
		bt2_log.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {// 要从数据库中读账号密码一一匹配
				// TODO Auto-generated method stub
				// PreparedStatement psmt = null;
				// User user = new User();
				String user_name = userName.getText();
				String pswd = passWord.getText();
				String privilege = cb1.getSelectedItem().toString();
				String sql = "select * from user_account";
				// user.setUserName(user_name);
				// user.setPassword(pswd);
				try {
					int flag = 0;
					// psmt = new Dao().getConnection().prepareStatement(sql);
					ResultSet rs = userDB.queryUserInfo(sql);
					while (rs.next()) {
						if (user_name.equalsIgnoreCase(rs.getString(1)) && pswd.equalsIgnoreCase(rs.getString(2))
								&& privilege.equalsIgnoreCase(rs.getString(3))) {
							flag = 1;
							break;
						}
					}
					if (flag == 1) {
						// JOptionPane.showMessageDialog(null, "登录成功");
						// 登录成功就进入学生管理界面(根据 权限来分，分别进入不同端的界面)
						if (cb1.getSelectedItem().toString().equalsIgnoreCase("学生")) {
							new Stu_choice();
							frame_log.setVisible(false);
                            
						} else if (cb1.getSelectedItem().toString().equalsIgnoreCase("管理员")) {
							new Manager_choice();
							frame_log.setVisible(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "用户名或密码或权限错误！");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
	}
}
