package Manage;

import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Action.Stu_increase;
import Action.Student_delete;
import Choice.Manager_choice;
import Dao.Course_Dao;
import Dao.Stu_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class ManageStu.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class ManageStu {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Stu_Dao userDB = new Stu_Dao(driverName, uri);

	/** The frame stu manage. */
	JFrame frame_stu_manage;
	
	/** The lb. */
	JLabel lb;

	/** The btn increase. */
	JButton btn_increase;
	
	/** The btn delete. */
	JButton btn_delete;
	
	/** The btn search. */
	JButton btn_search;
	
	/** The btn change. */
	JButton btn_change;
	
	/** The btn back. */
	JButton btn_back;// 返回按钮可返回选择管理主界面

	/** The panel north. */
	JPanel panelNorth;
	
	/** The panel center. */
	JPanel panelCenter;
	
	/** The panel south. */
	JPanel panelSouth;

	/**
	 * Instantiates a new manage stu.
	 */
	public ManageStu() {
		frame_stu_manage = new JFrame("学生信息");
		lb = new JLabel("请选择你要进行的操作");
		lb.setFont(new Font("宋体", Font.BOLD, 17));

		btn_increase = new JButton("增加学生信息");
		btn_delete = new JButton("删除学生信息");
		btn_search = new JButton("查找学生信息");
		btn_change = new JButton("修改学生信息");
		btn_back = new JButton("返回");

		panelNorth = new JPanel();
		panelCenter = new JPanel();
		panelSouth = new JPanel();

		frame_stu_manage.setSize(300, 200);
		frame_stu_manage.setLocation(800, 300);

		frame_stu_manage.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelCenter.setLayout(new GridLayout(2, 2));
		panelSouth.setLayout(new FlowLayout());

		frame_stu_manage.add(panelNorth, BorderLayout.NORTH);
		frame_stu_manage.add(panelCenter, BorderLayout.CENTER);
		frame_stu_manage.add(panelSouth, BorderLayout.SOUTH);

		panelNorth.add(lb);
		panelCenter.add(btn_increase);
		panelCenter.add(btn_delete);
		panelCenter.add(btn_search);
		panelCenter.add(btn_change);
		panelSouth.add(btn_back);

		frame_stu_manage.setVisible(true);
		frame_stu_manage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		btn_increase.addActionListener(new ActionListener() {// 增加

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Stu_increase();
			}

		});
		btn_delete.addActionListener(new ActionListener() {// 删除

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Student_delete();
			}

		});
		btn_search.addActionListener(new ActionListener() {// 查找

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String content = JOptionPane.showInputDialog(null, "请输入你要查找学生的学号", "查找学生",
						JOptionPane.INFORMATION_MESSAGE);
				if (content != null) {
					JFrame frame_stu_search = new JFrame("查询学生信息");

					JLabel lb_stu_id = new JLabel("学号");
					JLabel lb_stu_name = new JLabel("姓名");
					JLabel lb_sex = new JLabel("性别");
					JLabel lb_age = new JLabel("年龄");
					JLabel lb_college = new JLabel("学院");
					JLabel lb_major = new JLabel("专业");
					JLabel lb_grade = new JLabel("年级");
					JLabel lb_class = new JLabel("班级");
					JLabel lb_address = new JLabel("家住地址");
					JLabel lb_telephone = new JLabel("电话号码");

					JTextField txt_stu_id = new JTextField();
					JTextField txt_stu_name = new JTextField();
					JTextField txt_sex = new JTextField();
					JTextField txt_age = new JTextField();
					JTextField txt_college = new JTextField();
					JTextField txt_major = new JTextField();
					JTextField txt_grade = new JTextField();
					JTextField txt_class = new JTextField();
					JTextField txt_address = new JTextField();
					JTextField txt_telephone = new JTextField();

					JPanel panelCenter1 = new JPanel();

					frame_stu_search.setSize(500, 300);
					frame_stu_search.setLocation(800, 300);

					frame_stu_search.setLayout(new BorderLayout());

					panelCenter1.setLayout(new GridLayout(5, 4));

					frame_stu_search.add(panelCenter1, BorderLayout.CENTER);

					panelCenter1.add(lb_stu_id);
					panelCenter1.add(txt_stu_id);
					panelCenter1.add(lb_stu_name);
					panelCenter1.add(txt_stu_name);
					panelCenter1.add(lb_sex);
					panelCenter1.add(txt_sex);
					panelCenter1.add(lb_age);
					panelCenter1.add(txt_age);
					panelCenter1.add(lb_college);
					panelCenter1.add(txt_college);
					panelCenter1.add(lb_major);
					panelCenter1.add(txt_major);
					panelCenter1.add(lb_grade);
					panelCenter1.add(txt_grade);
					panelCenter1.add(lb_class);
					panelCenter1.add(txt_class);
					panelCenter1.add(lb_address);
					panelCenter1.add(txt_address);
					panelCenter1.add(lb_telephone);
					panelCenter1.add(txt_telephone);

					String sql = "select * from student where stu_id = '" + content + "';";
					ResultSet rs = userDB.search_info(sql);
					try {
						if (rs.next()) {
							txt_stu_id.setText(rs.getString(1));
							txt_stu_name.setText(rs.getString(2));
							txt_sex.setText(rs.getString(3));
							txt_age.setText(String.valueOf(rs.getInt(4)));
							txt_college.setText(rs.getString(5));
							txt_major.setText(rs.getString(6));
							txt_grade.setText(rs.getString(7));
							txt_class.setText(rs.getString(8));
							txt_address.setText(rs.getString(9));
							txt_telephone.setText(rs.getString(10));
							frame_stu_search.setVisible(true);
							frame_stu_search.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						} else {
							JOptionPane.showMessageDialog(null, "该学生不存在");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		}

		);
		btn_change.addActionListener(new ActionListener() {// 修改

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				String content = JOptionPane.showInputDialog(null, "请输入你要更新学生资料的学号", "更新学生资料",
						JOptionPane.INFORMATION_MESSAGE);
				if (content != null) {
					JFrame frame_stu_change = new JFrame("更新学生信息");

					JLabel lb_stu_id = new JLabel("学号");
					JLabel lb_stu_name = new JLabel("姓名");
					JLabel lb_sex = new JLabel("性别");
					JLabel lb_age = new JLabel("年龄");
					JLabel lb_college = new JLabel("学院");
					JLabel lb_major = new JLabel("专业");
					JLabel lb_grade = new JLabel("年级");
					JLabel lb_class = new JLabel("班级");
					JLabel lb_address = new JLabel("家住地址");
					JLabel lb_telephone = new JLabel("电话号码");
					JButton btn_OK1 = new JButton("确定");
					
					JTextField txt_stu_id = new JTextField();
					JTextField txt_stu_name = new JTextField();
					JTextField txt_sex = new JTextField();
					JTextField txt_age = new JTextField();
					JTextField txt_college = new JTextField();
					JTextField txt_major = new JTextField();
					JTextField txt_grade = new JTextField();
					JTextField txt_class = new JTextField();
					JTextField txt_address = new JTextField();
					JTextField txt_telephone = new JTextField();

					JPanel panelCenter1 = new JPanel();
					JPanel panelSouth1 = new JPanel();
					
					frame_stu_change.setSize(500, 300);
					frame_stu_change.setLocation(800, 300);
					frame_stu_change.setLayout(new BorderLayout());

					panelCenter1.setLayout(new GridLayout(5, 4));
					panelSouth1.setLayout(new FlowLayout());
					frame_stu_change.add(panelCenter1, BorderLayout.CENTER);
					frame_stu_change.add(panelSouth1, BorderLayout.SOUTH);
					panelCenter1.add(lb_stu_id);
					panelCenter1.add(txt_stu_id);
					panelCenter1.add(lb_stu_name);
					panelCenter1.add(txt_stu_name);
					panelCenter1.add(lb_sex);
					panelCenter1.add(txt_sex);
					panelCenter1.add(lb_age);
					panelCenter1.add(txt_age);
					panelCenter1.add(lb_college);
					panelCenter1.add(txt_college);
					panelCenter1.add(lb_major);
					panelCenter1.add(txt_major);
					panelCenter1.add(lb_grade);
					panelCenter1.add(txt_grade);
					panelCenter1.add(lb_class);
					panelCenter1.add(txt_class);
					panelCenter1.add(lb_address);
					panelCenter1.add(txt_address);
					panelCenter1.add(lb_telephone);
					panelCenter1.add(txt_telephone);

					panelSouth1.add(btn_OK1);
					String sql = "select * from student where stu_id = '" + content + "';";
					ResultSet rs = userDB.search_info(sql);
					try {
						if (rs.next()) {
							txt_stu_id.setText(rs.getString(1));
							txt_stu_name.setText(rs.getString(2));
							txt_sex.setText(rs.getString(3));
							txt_age.setText(String.valueOf(rs.getInt(4)));
							txt_college.setText(rs.getString(5));
							txt_major.setText(rs.getString(6));
							txt_grade.setText(rs.getString(7));
							txt_class.setText(rs.getString(8));
							txt_address.setText(rs.getString(9));
							txt_telephone.setText(rs.getString(10));
							frame_stu_change.setVisible(true);
							frame_stu_change.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							btn_OK1.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									//开始更新
									String sql1 = "update student set stu_name=?,sex=? ,age=?,college=?,major=?,grade=?,class=?,address=?,telephone=?"
											+ "where stu_id = '" + content+ "';";
									int flag = userDB.updateStuInfo(sql1, txt_stu_name.getText(),txt_sex.getText(), Integer.parseInt(txt_age.getText()),
											txt_college.getText(), txt_major.getText(), txt_grade.getText(),txt_class.getText(),txt_address.getText(),txt_telephone.getText());
									if (flag == 1) {
										JOptionPane.showMessageDialog(null, "更新成功");
										frame_stu_change.setVisible(false);
									} else {
										JOptionPane.showMessageDialog(null, "更新失败");
									}
								}
								
							});
						} else {
							JOptionPane.showMessageDialog(null, "该学生不存在");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			
			}

		});
		// 返回按钮返回上一界面
		btn_back.addActionListener(new ActionListener() {// 返回

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame_stu_manage.setVisible(false);
				new Manager_choice();
			}

		});
	}
}
