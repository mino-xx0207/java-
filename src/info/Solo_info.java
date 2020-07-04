package info;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.log.Log;

import Choice.Stu_choice;
import Dao.Stu_Dao;
import Dao.User_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Solo_info.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Solo_info {

	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Stu_Dao userDB = new Stu_Dao(driverName, uri);

	/** The frame solo info. */
	JFrame frame_solo_info;
	
	/** The stu id. */
	JLabel stu_id;
	
	/** The stu name. */
	JLabel stu_name;
	
	/** The sex. */
	JLabel sex;
	
	/** The age. */
	JLabel age;
	
	/** The college. */
	JLabel college;
	
	/** The major. */
	JLabel major;
	
	/** The grade. */
	JLabel grade;
	
	/** The cls. */
	JLabel cls;
	
	/** The address. */
	JLabel address;
	
	/** The telephone. */
	JLabel telephone;

	/** The stu id 1. */
	JTextField stu_id1;
	
	/** The stu name 1. */
	JTextField stu_name1;
	
	/** The sex 1. */
	JTextField sex1;
	
	/** The age 1. */
	JTextField age1;
	
	/** The college 1. */
	JTextField college1;
	
	/** The major 1. */
	JTextField major1;
	
	/** The grade 1. */
	JTextField grade1;
	
	/** The cls 1. */
	JTextField cls1;
	
	/** The address 1. */
	JTextField address1;
	
	/** The telephone 1. */
	JTextField telephone1;

	/** The btn back. */
	JButton btn_back;

	/** The panel center. */
	JPanel panelCenter;
	
	/** The panel south. */
	JPanel panelSouth;

	/**
	 * Instantiates a new solo info.
	 */
	public Solo_info() {
		frame_solo_info = new JFrame("查看个人基本信息");

		stu_id = new JLabel("学号");
		stu_name = new JLabel("姓名");
		sex = new JLabel("性别");
		age = new JLabel("年龄");
		college = new JLabel("学院");
		major = new JLabel("专业");
		grade = new JLabel("年级");
		cls = new JLabel("班级");
		address = new JLabel("家住地址");
		telephone = new JLabel("电话号码");
		btn_back = new JButton("返回");
		btn_back.setFont(new Font("宋体", Font.PLAIN, 24));
		btn_back.setForeground(Color.RED);
		btn_back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		panelCenter = new JPanel();
		panelSouth = new JPanel();

		stu_id1 = new JTextField();
		stu_name1 = new JTextField();
		sex1 = new JTextField();
		age1 = new JTextField();
		college1 = new JTextField();
		major1 = new JTextField();
		grade1 = new JTextField();
		cls1 = new JTextField();
		address1 = new JTextField();
		telephone1 = new JTextField();

		
		frame_solo_info.setSize(500, 300);
		frame_solo_info.setLocation(800, 300);

		frame_solo_info.setLayout(new BorderLayout());

		panelCenter.setLayout(new GridLayout(5, 4));
		panelSouth.setLayout(new FlowLayout());
		frame_solo_info.add(panelCenter, BorderLayout.CENTER);
		frame_solo_info.add(panelSouth, BorderLayout.SOUTH);

		panelCenter.add(stu_id);
		panelCenter.add(stu_id1);
		panelCenter.add(stu_name);
		panelCenter.add(stu_name1);
		panelCenter.add(sex);
		panelCenter.add(sex1);
		panelCenter.add(age);
		panelCenter.add(age1);
		panelCenter.add(college);
		panelCenter.add(college1);
		panelCenter.add(major);
		panelCenter.add(major1);
		panelCenter.add(grade);
		panelCenter.add(grade1);
		panelCenter.add(cls);
		panelCenter.add(cls1);
		panelCenter.add(address);
		panelCenter.add(address1);
		panelCenter.add(telephone);
		panelCenter.add(telephone1);

		panelSouth.add(btn_back);
		
		frame_solo_info.setVisible(true);
		frame_solo_info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		String sql = "select * from student where stu_id = '" + Log.userName.getText() + "';";
		ResultSet rs = userDB.search_info(sql);
		try {
			if(rs.next()) {
				stu_id1.setText(rs.getString(1));
				stu_name1.setText(rs.getString(2));
				sex1.setText(rs.getString(3));
				age1.setText(String.valueOf(rs.getInt(4)));
				college1.setText(rs.getString(5));
				major1.setText(rs.getString(6));
				grade1.setText(rs.getString(7));
				cls1.setText(rs.getString(8));
				address1.setText(rs.getString(9));
				telephone1.setText(rs.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回按钮事件
		userDB.closeAll();
		btn_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame_solo_info.setVisible(false);
				new Stu_choice();
			}
			
		});
	
	}
}
