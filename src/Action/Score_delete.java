package Action;

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

import Dao.Grade_Dao;
import Dao.Stu_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Score_delete.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Score_delete {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Grade_Dao userDB = new Grade_Dao(driverName, uri);

	/** The frame score delete. */
	JFrame frame_score_delete;

	/** The btn OK. */
	JButton btn_OK;
	
	/** The btn cancle. */
	JButton btn_cancle;

	/** The lb. */
	JLabel lb;
	
	/** The txt stu id. */
	JTextField txt_stu_id;
	
	/** The txt course id. */
	JTextField txt_course_id;
	
	/**
	 * Instantiates a new score delete.
	 */
	public Score_delete() {

		frame_score_delete = new JFrame("删除成绩信息");
		lb = new JLabel("学生的学号以及课程的课程号");
		btn_OK = new JButton("确定");
		btn_cancle = new JButton("取消");
		txt_stu_id = new JTextField(12);
		txt_course_id= new JTextField(12);
		
		frame_score_delete.setSize(600, 150);
		frame_score_delete.setLocation(800, 300);

		frame_score_delete.setLayout(new FlowLayout());

		frame_score_delete.add(lb);
		frame_score_delete.add(txt_stu_id);
		frame_score_delete.add(txt_course_id);
		
		frame_score_delete.add(btn_OK);
		frame_score_delete.add(btn_cancle);

		frame_score_delete.setVisible(true);
		frame_score_delete.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		btn_OK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 删除之前查询是否存在此学生，此课程
				String sql2 = "select stu_id from student where stu_id ='" + txt_stu_id.getText() + "';";
				String sql3 = "select course_id from course where course_id ='" + txt_course_id.getText() + "';";
				
				ResultSet rs = userDB.search_info(sql2);
				ResultSet rs1 = userDB.search_info(sql3);
				
				try {
					if (rs.next()&&rs1.next()) {
						String sql = "delete from score where stu_id = '" + txt_stu_id.getText() +
								"'and course_id ='"+txt_course_id.getText()+"';";
						int flag = userDB.deleteScoreInfo(sql);
						if (flag == 1) {
							JOptionPane.showMessageDialog(null, "删除成功");
							txt_stu_id.setText("");
							txt_course_id.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(null, "该成绩不存在");
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
				frame_score_delete.setVisible(false);
			}
		});
	}
}
