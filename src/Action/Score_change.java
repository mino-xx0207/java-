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

import Dao.Grade_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Score_change.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Score_change {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Grade_Dao userDB = new Grade_Dao(driverName, uri);

	/** The frame change score. */
	JFrame frame_change_score;
	
	/** The lb stu id. */
	JLabel lb_stu_id;
	
	/** The lb course id. */
	JLabel lb_course_id;
	
	/** The txt stu id. */
	JTextField txt_stu_id;
	
	/** The txt course id. */
	JTextField txt_course_id;

/** The panel center. */
JPanel panelCenter;

/** The panel south. */
JPanel panelSouth;
	
	/** The btn OK. */
	JButton btn_OK;

	/**
	 * Instantiates a new score change.
	 */
	public Score_change() {

		frame_change_score = new JFrame("修改课程成绩");
		lb_stu_id = new JLabel("输入学生学号");
		lb_course_id = new JLabel("输入课程号");
		txt_stu_id = new JTextField();
		txt_course_id = new JTextField();
		panelCenter = new JPanel();
		panelSouth = new JPanel();
		btn_OK = new JButton("确定");
		
		frame_change_score.setSize(300, 200);
		frame_change_score.setLocation(800, 300);

		frame_change_score.setLayout(new BorderLayout());

		panelCenter.setLayout(new GridLayout(2, 2));
		panelSouth.setLayout(new FlowLayout());
		frame_change_score.add(panelCenter, BorderLayout.CENTER);
		frame_change_score.add(panelSouth, BorderLayout.SOUTH);

		panelCenter.add(lb_stu_id);
		panelCenter.add(txt_stu_id);
		panelCenter.add(lb_course_id);
		panelCenter.add(txt_course_id);
	
		panelSouth.add(btn_OK);

		frame_change_score.setVisible(true);
		frame_change_score.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		btn_OK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql2 = "select stu_id from student where stu_id ='" + txt_stu_id.getText() + "';";
				String sql3 = "select course_id from course where course_id ='" + txt_course_id.getText() + "';";

				ResultSet rs = userDB.search_info(sql2);
				ResultSet rs1 = userDB.search_info(sql3);

				try {
					if (rs.next() && rs1.next()) {
						String content = JOptionPane.showInputDialog(null, "请输入你要修改为的成绩", "修改成绩",
								JOptionPane.INFORMATION_MESSAGE);
						String sql = "update score set score = ? where stu_id = '"+txt_stu_id.getText()+
								"'and course_id='"+txt_course_id.getText()+"';";
						int flag = userDB.updateScoreInfo(sql,Double.parseDouble(content));
						if (flag == 1) {
							JOptionPane.showMessageDialog(null, "修改成功");
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
	}

}
