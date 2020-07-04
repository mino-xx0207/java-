package Manage;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Action.Teacher_increase;
import Choice.Manager_choice;
import Dao.Stu_Dao;
import Dao.Teacher_Dao;
import test.ExcelTestTeacher;

// TODO: Auto-generated Javadoc
/**
 * The Class ManageTea.
 *
 * @date 2020-7-4
 * @author lisai
 * @version v1.0
 */
public class ManageTea {

	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";

	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";

	/** The user DB. */
	public Teacher_Dao userDB = new Teacher_Dao(driverName, uri);

	/** The frame tea manage. */
	JFrame frame_tea_manage;

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

	/** The btn import. */
	JButton btn_import;

	/** The btn export. */
	JButton btn_export;

	/** The panel north. */
	JPanel panelNorth;

	/** The panel center. */
	JPanel panelCenter;

	/** The panel south. */
	JPanel panelSouth;

	/**
	 * Instantiates a new manage tea.
	 */
	public ManageTea() {
		frame_tea_manage = new JFrame("教师信息");
		lb = new JLabel("请选择你要进行的操作");
		lb.setFont(new Font("宋体", Font.BOLD, 17));

		btn_increase = new JButton("增加教师信息");
		btn_delete = new JButton("删除教师信息");
		btn_search = new JButton("查找教师信息");
		btn_change = new JButton("修改教师信息");
		btn_import = new JButton("导入教师信息");
		btn_export = new JButton("导出教师信息");
		
		btn_back = new JButton("返回");

		panelNorth = new JPanel();
		panelCenter = new JPanel();
		panelSouth = new JPanel();

		frame_tea_manage.setSize(300, 200);
		frame_tea_manage.setLocation(800, 300);

		frame_tea_manage.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelCenter.setLayout(new GridLayout(3, 2));
		panelSouth.setLayout(new FlowLayout());

		frame_tea_manage.add(panelNorth, BorderLayout.NORTH);
		frame_tea_manage.add(panelCenter, BorderLayout.CENTER);
		frame_tea_manage.add(panelSouth, BorderLayout.SOUTH);

		panelNorth.add(lb);
		panelCenter.add(btn_increase);
		panelCenter.add(btn_delete);
		panelCenter.add(btn_search);
		panelCenter.add(btn_change);
		panelCenter.add(btn_import);
		panelCenter.add(btn_export);
		
		panelSouth.add(btn_back);
      
		frame_tea_manage.setVisible(true);
		frame_tea_manage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		btn_increase.addActionListener(new ActionListener() {// 增加

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Teacher_increase();
			}

		});
		btn_delete.addActionListener(new ActionListener() {// 删除

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 删之前应该查找是否存在
				String content = JOptionPane.showInputDialog(null, "请输入你要删除教师对象的工号", "删除教师",
						JOptionPane.INFORMATION_MESSAGE);
				if (content != null) {
				String sql1 = "select tea_id from teacher where tea_id='" + content + "';";
				ResultSet rs = userDB.search_info(sql1);
				try {
					if (rs.next()) {// 存在就删掉
					
							int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "确认删除框", JOptionPane.YES_NO_OPTION);
							if (n == JOptionPane.YES_OPTION) {
								String sql = "delete from teacher where tea_id = '" + content + "';";
								int flag = userDB.deleteTeaInfo(sql);
								if (flag == 1) {
									JOptionPane.showMessageDialog(null, "删除成功");
									content = null;
								}
									else {
										JOptionPane.showMessageDialog(null, "删除失败");
									}
								}
							else if (n == JOptionPane.NO_OPTION) {
								JOptionPane.showMessageDialog(null, "取消删除");
							}
						}else {
							JOptionPane.showMessageDialog(null, "该教师不存在");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btn_search.addActionListener(new ActionListener() {// 查找
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String content = JOptionPane.showInputDialog(null, "请输入你要查找老师的工号", "查找老师",
						JOptionPane.INFORMATION_MESSAGE);
				if (content != null) {
					JFrame frame_teacher_search = new JFrame("查询老师信息");

					JLabel lb_tea_id = new JLabel("工号");
					JLabel lb_tea_name = new JLabel("姓名");
					JLabel lb_college = new JLabel("学院");
					JLabel lb_job_title = new JLabel("职称");
					JLabel lb_job_time = new JLabel("入职时间");

					JTextField txt_tea_id = new JTextField();
					JTextField txt_tea_name = new JTextField();
					JTextField txt_college = new JTextField();
					JTextField txt_job_title = new JTextField();
					JTextField txt_job_time = new JTextField();

					JPanel panelCenter1 = new JPanel();

					frame_teacher_search.setSize(500, 300);
					frame_teacher_search.setLocation(800, 300);

					frame_teacher_search.setLayout(new BorderLayout());

					panelCenter1.setLayout(new GridLayout(6, 2));

					frame_teacher_search.add(panelCenter1, BorderLayout.CENTER);

					panelCenter1.add(lb_tea_id);
					panelCenter1.add(txt_tea_id);
					panelCenter1.add(lb_tea_name);
					panelCenter1.add(txt_tea_name);
					panelCenter1.add(lb_college);
					panelCenter1.add(txt_college);
					panelCenter1.add(lb_job_title);
					panelCenter1.add(txt_job_title);
					panelCenter1.add(lb_job_time);
					panelCenter1.add(txt_job_time);

					String sql = "select * from teacher where tea_id = '" + content + "';";
					ResultSet rs = userDB.search_info(sql);
					try {
						if (rs.next()) {
							txt_tea_id.setText(rs.getString(1));
							txt_tea_name.setText(rs.getString(2));
							txt_college.setText(rs.getString(3));
							txt_job_title.setText(rs.getString(4));
							txt_job_time.setText(rs.getString(5));

							frame_teacher_search.setVisible(true);
							frame_teacher_search.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						} else {
							JOptionPane.showMessageDialog(null, "该教师不存在");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btn_change.addActionListener(new ActionListener() {// 修改

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String content = JOptionPane.showInputDialog(null, "请输入你要更新教师的工号", "更新教师",
						JOptionPane.INFORMATION_MESSAGE);
				if (content != null) {
					JFrame frame_teacher_change = new JFrame("更新教师信息");

					JLabel lb_tea_id = new JLabel("工号");
					JLabel lb_tea_name = new JLabel("姓名");
					JLabel lb_college = new JLabel("学院");
					JLabel lb_job_title = new JLabel("职称");
					JLabel lb_job_time = new JLabel("入职时间");
					JButton btn_OK1 = new JButton("确定");

					JTextField txt_tea_id = new JTextField();
					JTextField txt_tea_name = new JTextField();
					JTextField txt_college = new JTextField();
					JTextField txt_job_title = new JTextField();
					JTextField txt_job_time = new JTextField();

					JPanel panelCenter1 = new JPanel();
					JPanel panelSouth1 = new JPanel();
					frame_teacher_change.setSize(500, 300);
					frame_teacher_change.setLocation(800, 300);

					frame_teacher_change.setLayout(new BorderLayout());

					panelCenter1.setLayout(new GridLayout(6, 2));
					panelSouth1.setLayout(new FlowLayout());

					frame_teacher_change.add(panelCenter1, BorderLayout.CENTER);
					frame_teacher_change.add(panelSouth1, BorderLayout.SOUTH);

					panelCenter1.add(lb_tea_id);
					panelCenter1.add(txt_tea_id);
					panelCenter1.add(lb_tea_name);
					panelCenter1.add(txt_tea_name);
					panelCenter1.add(lb_college);
					panelCenter1.add(txt_college);
					panelCenter1.add(lb_job_title);
					panelCenter1.add(txt_job_title);
					panelCenter1.add(lb_job_time);
					panelCenter1.add(txt_job_time);
					panelSouth1.add(btn_OK1);

					String sql = "select * from teacher where tea_id = '" + content + "';";
					ResultSet rs = userDB.search_info(sql);
					try {
						if (rs.next()) {
							txt_tea_id.setText(rs.getString(1));
							txt_tea_name.setText(rs.getString(2));
							txt_college.setText(rs.getString(3));
							txt_job_title.setText(rs.getString(4));
							txt_job_time.setText(rs.getString(5));

							frame_teacher_change.setVisible(true);
							frame_teacher_change.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							btn_OK1.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									// 开始更新
									String sql1 = "update teacher set tea_name=?,college=? ,job_title=?,job_time=?"
											+ "where tea_id = '" + content + "';";
									int flag = userDB.updateTeacherInfo(sql1, txt_tea_name.getText(), txt_college.getText(),
											txt_job_title.getText(),txt_job_time.getText());
									if (flag == 1) {
										JOptionPane.showMessageDialog(null, "更新成功");
										frame_teacher_change.setVisible(false);
									} else {
										JOptionPane.showMessageDialog(null, "更新失败");
									}
								}

							});
						} else {
							JOptionPane.showMessageDialog(null, "该教师不存在");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_import.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			new ExcelTestTeacher().importExcel();
			
			}	
		});
		btn_export.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			new ExcelTestTeacher().exportExcel();
			}	
		});
		// 返回按钮返回上一界面
		btn_back.addActionListener(new ActionListener() {// 返回

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame_tea_manage.setVisible(false);
				new Manager_choice();
			}

		});
	}
}
