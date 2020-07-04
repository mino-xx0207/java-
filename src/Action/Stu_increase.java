package Action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.Stu_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Stu_increase.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Stu_increase {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Stu_Dao userDB = new Stu_Dao(driverName, uri);
	
	/** The frame increase stu. */
	JFrame frame_increase_stu;
	
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

	/** The btn save. */
	JButton btn_save;
	
	/** The btn cancle. */
	JButton btn_cancle;

	/** The panel center. */
	JPanel panelCenter;
	
	/** The panel south. */
	JPanel panelSouth;
	
	/**
	 * Instantiates a new stu increase.
	 */
	public Stu_increase() {
		frame_increase_stu = new JFrame("添加学生信息");

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
		
		btn_save = new JButton("保存");
		btn_cancle = new JButton("取消");

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
		
		frame_increase_stu.setSize(500, 300);
		frame_increase_stu.setLocation(800, 300);

		frame_increase_stu.setLayout(new BorderLayout());

		panelCenter.setLayout(new GridLayout(5, 4));
		panelSouth.setLayout(new FlowLayout());
		frame_increase_stu.add(panelCenter, BorderLayout.CENTER);
		frame_increase_stu.add(panelSouth, BorderLayout.SOUTH);

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

		panelSouth.add(btn_save);
		panelSouth.add(btn_cancle);
		
		frame_increase_stu.setVisible(true);
		frame_increase_stu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		btn_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = stu_id1.getText();//得到输入学号
				String sql = "select stu_id from student where stu_id='" + id + "';";
				ResultSet rs = userDB.search_info(sql);
				try {
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "学号重复，请重新添加");	
						//置空所有文本框
						stu_id1.setText(""); 
						stu_name1.setText("");  
						sex1.setText("");  
						age1.setText("");  
						college1.setText("");  
						major1.setText("");  
						grade1.setText("");  
						cls1.setText("");  
						address1.setText("");  
						telephone1.setText("");  
					}else {
						String sql1 = "insert into student(stu_id,stu_name,sex,age,college,major,grade,class,address,telephone) "
								+ "values(?,?,?,?,?,?,?,?,?,?)";
						String id1 = stu_id1.getText();
						String name = stu_name1.getText();
                        String stu_sex = sex1.getText();
                        int stu_age = Integer.parseInt(age1.getText());
                        String stu_college = college1.getText();
                        String stu_major = major1.getText();
                        String stu_grade = grade1.getText();
                        String stu_cls = cls1.getText();
                        String stu_address = address1.getText();
                        String stu_telephone = telephone1.getText();
                        userDB.saveStuInfo(sql1, id1, name, stu_sex, stu_age, stu_college, stu_major, stu_grade, stu_cls, stu_address, stu_telephone);
                        JOptionPane.showMessageDialog(null, "保存成功！");
                      //保存成功之后置空所有文本框以便保存下一个学生
						stu_id1.setText(""); 
						stu_name1.setText("");  
						sex1.setText("");  
						age1.setText("");  
						college1.setText("");  
						major1.setText("");  
						grade1.setText("");  
						cls1.setText("");  
						address1.setText("");  
						telephone1.setText(""); 
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		//取消按钮事件
		btn_cancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame_increase_stu.setVisible(false);
			}
			
		});
	}
}
