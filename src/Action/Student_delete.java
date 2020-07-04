package Action;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.log.Log;

import Dao.Grade_Dao;
import Dao.Stu_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Student_delete.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Student_delete {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Stu_Dao userDB = new Stu_Dao(driverName, uri);

	/** The frame student delete. */
	JFrame frame_student_delete;

	/** The btn OK. */
	JButton btn_OK;
	
	/** The btn cancle. */
	JButton btn_cancle;

	/** The lb. */
	JLabel lb;
	
	/** The txt. */
	JTextField txt;

	/**
	 * Instantiates a new student delete.
	 */
	public Student_delete() {

		frame_student_delete = new JFrame("删除学生信息");
		lb = new JLabel("请输入要删除学生的学号");
		btn_OK = new JButton("确定");
		btn_cancle = new JButton("取消");
		txt = new JTextField(12);

		frame_student_delete.setSize(400, 150);
		frame_student_delete.setLocation(800, 300);

		frame_student_delete.setLayout(new FlowLayout());

		frame_student_delete.add(lb);
		frame_student_delete.add(txt);
		frame_student_delete.add(btn_OK);
		frame_student_delete.add(btn_cancle);

		frame_student_delete.setVisible(true);
		frame_student_delete.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		btn_OK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 删除之前查询是否存在此学生
				String sql2 = "select stu_id from student where stu_id ='" + txt.getText() + "';";
				ResultSet rs = userDB.search_info(sql2);
				try {
					if (rs.next()) {
						String sql = "delete from student where stu_id = '" + txt.getText() + "';";
						int flag = userDB.deleteStuInfo(sql);
						if (flag == 1) {
							JOptionPane.showMessageDialog(null, "删除成功");
							// 学生删了之后，她的成绩也应该被删掉
							String sql1 = "delete from score where stu_id = '" + txt.getText() + "';";
							new Grade_Dao(driverName, uri).deleteScoreInfo(sql1);
							txt.setText("");
						}

					} else {
						JOptionPane.showMessageDialog(null, "该学生不存在");
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
				frame_student_delete.setVisible(false);
			}

		});
	}
}
