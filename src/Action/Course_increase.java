package Action;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

import Dao.Course_Dao;
import Dao.Teacher_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Course_increase.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Course_increase {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The course DB. */
	public Course_Dao courseDB = new Course_Dao(driverName, uri);

	/** The frame increase course. */
	JFrame frame_increase_course;

	/** The lb course id. */
	JLabel lb_course_id;
	
	/** The lb course name. */
	JLabel lb_course_name;
	
	/** The lb hour. */
	JLabel lb_hour;
	
	/** The lb credit. */
	JLabel lb_credit;
	
	/** The lb course nature. */
	JLabel lb_course_nature;
	
	/** The lb course category. */
	JLabel lb_course_category;

	/** The txt course id. */
	JTextField txt_course_id;
	
	/** The txt course name. */
	JTextField txt_course_name;
	
	/** The txt hour. */
	JTextField txt_hour;
	
	/** The txt credit. */
	JTextField txt_credit;
	
	/** The txt course nature. */
	JTextField txt_course_nature;
	
	/** The txt course category. */
	JTextField txt_course_category;

	/** The btn save. */
	JButton btn_save;
	
	/** The btn cancle. */
	JButton btn_cancle;

	/** The panel center. */
	JPanel panelCenter;
	
	/** The panel south. */
	JPanel panelSouth;

	/**
	 * Instantiates a new course increase.
	 */
	public Course_increase() {
		frame_increase_course = new JFrame("添加课程信息");
		
		lb_course_id = new JLabel("课程号");
		lb_course_name = new JLabel("课程名");
		lb_hour = new JLabel("课时");
		lb_credit = new JLabel("学分");
		lb_course_nature = new JLabel("课程性质");
		lb_course_category = new JLabel("课程类别");

		txt_course_id = new JTextField();
		txt_course_name = new JTextField();
		txt_hour = new JTextField();
		txt_credit = new JTextField();
		txt_course_nature = new JTextField();
		txt_course_category = new JTextField();

		btn_save = new JButton("保存");
		btn_cancle = new JButton("取消");

		panelCenter = new JPanel();
		panelSouth = new JPanel();
		
		frame_increase_course.setSize(500, 300);
		frame_increase_course.setLocation(800, 300);

		frame_increase_course.setLayout(new BorderLayout());
		
		panelCenter.setLayout(new GridLayout(6, 2));
		panelSouth.setLayout(new FlowLayout());
		
		frame_increase_course.add(panelCenter, BorderLayout.CENTER);
		frame_increase_course.add(panelSouth, BorderLayout.SOUTH);
		
		panelCenter.add(lb_course_id);
		panelCenter.add(txt_course_id);
		panelCenter.add(lb_course_name);
		panelCenter.add(txt_course_name);
		panelCenter.add(lb_hour);
		panelCenter.add(txt_hour);
		panelCenter.add(lb_credit);
		panelCenter.add(txt_credit);
		panelCenter.add(lb_course_nature);
		panelCenter.add(txt_course_nature);
		panelCenter.add(lb_course_category);
		panelCenter.add(txt_course_category);

		panelSouth.add(btn_save);
		panelSouth.add(btn_cancle);

		frame_increase_course.setVisible(true);
		frame_increase_course.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// 保存课程信息
				btn_save.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String id = txt_course_id.getText();// 得到输入课程号
						String sql = "select course_id from course where course_id='" + id + "';";
						ResultSet rs = courseDB.search_info(sql);
						try {
							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "课程号重复，请重新添加");
								// 置空所有文本框
								txt_course_id.setText("");
								txt_course_name.setText("");
								txt_hour.setText("");
								txt_credit.setText("");
								txt_course_nature.setText("");
								txt_course_category.setText("");

							} else {
								String sql1 = "insert into course(course_id,course_name,hour,credit,course_nature,course_category) "
										+ "values(?,?,?,?,?,?)";

								String course_id = txt_course_id.getText();
								String course_name = txt_course_name.getText();
								int hour = Integer.parseInt(txt_hour.getText());
								int credit = Integer.parseInt(txt_credit.getText());
								String course_nature = txt_course_nature.getText();
								String course_category = txt_course_category.getText();
								
								courseDB.saveCourseInfo(sql1, course_id, course_name, hour, credit, course_nature,course_category);
								
								JOptionPane.showMessageDialog(null, "保存成功！");
								// 保存成功之后置空所有文本框以便保存下一门课程
								txt_course_id.setText("");
								txt_course_name.setText("");
								txt_hour.setText("");
								txt_credit.setText("");
								txt_course_nature.setText("");
								txt_course_category.setText("");
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
				// 取消按钮事件
				btn_cancle.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frame_increase_course.setVisible(false);
					}

				});
	}
}
