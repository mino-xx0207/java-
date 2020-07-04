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

import Dao.Stu_Dao;
import Dao.Teacher_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Teacher_increase.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Teacher_increase {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Teacher_Dao userDB = new Teacher_Dao(driverName, uri);

	/** The frame increase tea. */
	JFrame frame_increase_tea;

	/** The lb tea id. */
	JLabel lb_tea_id;
	
	/** The lb tea name. */
	JLabel lb_tea_name;
	
	/** The lb college. */
	JLabel lb_college;
	
	/** The lb job title. */
	JLabel lb_job_title;
	
	/** The lb job time. */
	JLabel lb_job_time;

	/** The txt tea id. */
	JTextField txt_tea_id;
	
	/** The txt tea name. */
	JTextField txt_tea_name;
	
	/** The txt college. */
	JTextField txt_college;
	
	/** The txt job title. */
	JTextField txt_job_title;
	
	/** The txt job time. */
	JTextField txt_job_time;

	/** The btn save. */
	JButton btn_save;
	
	/** The btn cancle. */
	JButton btn_cancle;

	/** The panel center. */
	JPanel panelCenter;
	
	/** The panel south. */
	JPanel panelSouth;

	/**
	 * Instantiates a new teacher increase.
	 */
	public Teacher_increase() {
		frame_increase_tea = new JFrame("添加教师信息");

		lb_tea_id = new JLabel("工号");
		lb_tea_name = new JLabel("姓名");
		lb_college = new JLabel("学院");
		lb_job_title = new JLabel("职称");
		lb_job_time = new JLabel("入职时间");

		btn_save = new JButton("保存");
		btn_cancle = new JButton("取消");

		panelCenter = new JPanel();
		panelSouth = new JPanel();

		txt_tea_id = new JTextField();
		txt_tea_name = new JTextField();
		txt_college = new JTextField();
		txt_job_title = new JTextField();
		txt_job_time = new JTextField();

		frame_increase_tea.setSize(500, 300);
		frame_increase_tea.setLocation(800, 300);

		frame_increase_tea.setLayout(new BorderLayout());

		panelCenter.setLayout(new GridLayout(5, 2));
		panelSouth.setLayout(new FlowLayout());

		frame_increase_tea.add(panelCenter, BorderLayout.CENTER);
		frame_increase_tea.add(panelSouth, BorderLayout.SOUTH);

		panelCenter.add(lb_tea_id);
		panelCenter.add(txt_tea_id);
		panelCenter.add(lb_tea_name);
		panelCenter.add(txt_tea_name);
		panelCenter.add(lb_college);
		panelCenter.add(txt_college);
		panelCenter.add(lb_job_title);
		panelCenter.add(txt_job_title);
		panelCenter.add(lb_job_time);
		panelCenter.add(txt_job_time);

		panelSouth.add(btn_save);
		panelSouth.add(btn_cancle);

		frame_increase_tea.setVisible(true);
		frame_increase_tea.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 保存教师信息
		btn_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = txt_tea_id.getText();// 得到输入工号
				String sql = "select tea_id from teacher where tea_id='" + id + "';";
				ResultSet rs = userDB.search_info(sql);
				try {
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "工号重复，请重新添加");
						// 置空所有文本框
						txt_tea_id.setText("");
						txt_tea_name.setText("");
						txt_college.setText("");
						txt_job_title.setText("");
						txt_job_time.setText("");

					} else {
						String sql1 = "insert into teacher(tea_id,tea_name,college,job_title,job_time) "
								+ "values(?,?,?,?,?)";

						String tea_id = txt_tea_id.getText();
						String tea_name = txt_tea_name.getText();
						String college = txt_college.getText();
						String job_title = txt_job_title.getText();
						String job_time = txt_job_time.getText();
						userDB.saveStuInfo(sql1, tea_id, tea_name, college, job_title, job_time);
						JOptionPane.showMessageDialog(null, "保存成功！");
						// 保存成功之后置空所有文本框以便保存下一个学生
						txt_tea_id.setText("");
						txt_tea_name.setText("");
						txt_college.setText("");
						txt_job_title.setText("");
						txt_job_time.setText("");
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
				frame_increase_tea.setVisible(false);
			}

		});
	}
}
