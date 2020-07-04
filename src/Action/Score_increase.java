package Action;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.Grade_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Score_increase.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Score_increase {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Grade_Dao userDB = new Grade_Dao(driverName, uri);
	
	/** The frame score increase. */
	JFrame frame_score_increase;
	
	/** The lb stu id. */
	JLabel lb_stu_id;
	
	/** The lb course id. */
	JLabel lb_course_id;
	
	/** The lb tea id. */
	JLabel lb_tea_id;
	
	/** The lb score. */
	JLabel lb_score;
	
	/** The txt stu id. */
	JTextField txt_stu_id;
	
	/** The txt course id. */
	JTextField txt_course_id;
	
	/** The txt tea id. */
	JTextField txt_tea_id;
	
	/** The txt score. */
	JTextField txt_score;
	
	/** The btn save. */
	JButton btn_save;
	
	/** The btn cancle. */
	JButton btn_cancle;
	
	
	/** The panel center. */
	JPanel panelCenter;
	
	/** The panel south. */
	JPanel panelSouth;
	
	/**
	 * Instantiates a new score increase.
	 */
	public Score_increase() {
		 frame_score_increase = new JFrame("添加成绩信息");
		 
		lb_stu_id = new JLabel("学号");
		lb_course_id = new JLabel("课程号");
		lb_tea_id = new JLabel("教师工号");
		lb_score = new JLabel("成绩");
		
		 txt_stu_id = new JTextField();
		 txt_course_id = new JTextField();
		 txt_tea_id = new JTextField();
		 txt_score = new JTextField();
		
		btn_save = new JButton("保存");
		btn_cancle = new JButton("取消");

		panelCenter = new JPanel();
		panelSouth = new JPanel();
		
		frame_score_increase.setSize(400, 150);
		frame_score_increase.setLocation(800, 300);

		frame_score_increase.setLayout(new BorderLayout());
		
		panelCenter.setLayout(new GridLayout(2, 2));
		panelSouth.setLayout(new FlowLayout());

		frame_score_increase.add(panelCenter, BorderLayout.CENTER);
		frame_score_increase.add(panelSouth, BorderLayout.SOUTH);
		
		panelCenter.add(lb_stu_id);
		panelCenter.add(txt_stu_id);
		panelCenter.add(lb_course_id);
		panelCenter.add(txt_course_id);
		panelCenter.add(lb_tea_id);
		panelCenter.add(txt_tea_id);
		panelCenter.add(lb_score);
		panelCenter.add(txt_score);
		
		panelSouth.add(btn_save);
		panelSouth.add(btn_cancle);

		frame_score_increase.setVisible(true);
		frame_score_increase.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// 保存成绩信息
				btn_save.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String stu_id = txt_stu_id.getText();// 得到输入学号
						String course_id = txt_course_id.getText();
						String teacher_id = txt_tea_id.getText();
						
						String sql = "select student_id,course_id,tea_id from score "
								+ "where student_id='" + stu_id + "'and course_id ='" + course_id + "'and tea_id = '" + teacher_id + "';";
						ResultSet rs = userDB.search_info(sql);
						try {
							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "此学号成绩重复上传，请重新添加");
								// 置空所有文本框
								txt_stu_id.setText("");
								txt_course_id.setText("");
								txt_tea_id.setText("");
								txt_score.setText("");

							} else {
								String sql1 = "insert into score(student_id,course_id,tea_id,score) "
										+ "values(?,?,?,?)";
								String stu_id1 = txt_stu_id.getText();// 得到输入学号
								String course_id1 = txt_course_id.getText();
								String teacher_id1 = txt_tea_id.getText();
								double score1 = Double.parseDouble(txt_score.getText());
								userDB.saveScoreInfo(sql1, stu_id1, course_id1, teacher_id1,score1);
								JOptionPane.showMessageDialog(null, "保存成功！");
								// 保存成功之后置空所有文本框以便保存下一课程成绩
								txt_stu_id.setText("");
								txt_course_id.setText("");
								txt_tea_id.setText("");
								txt_score.setText("");
								
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
						frame_score_increase.setVisible(false);
					}

				});
	}
	
}
