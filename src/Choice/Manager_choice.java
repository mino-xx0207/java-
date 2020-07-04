package Choice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Manage.ManageAccount;
import Manage.ManageCourse;
import Manage.ManageScore;
import Manage.ManageStu;
import Manage.ManageTea;

// TODO: Auto-generated Javadoc
/**
 * The Class Manager_choice.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Manager_choice {
	
	/** The frame manager choice. */
	JFrame frame_manager_choice;
	
	/** The lb. */
	JLabel lb;
	
	/** The btn stu. */
	JButton btn_stu;
	
	/** The btn course. */
	JButton btn_course;
	
	/** The btn teacher. */
	JButton btn_teacher;
	
	/** The btn score. */
	JButton btn_score;
	
	/** The btn account. */
	JButton btn_account;

	/** The panel north. */
	JPanel panelNorth;
	
	/** The panel center. */
	JPanel panelCenter;
	
	/** The panel south. */
	JPanel panelSouth;

	/**
	 * Instantiates a new manager choice.
	 */
	public Manager_choice() {
		frame_manager_choice = new JFrame("学生信息系统");

		lb = new JLabel("请选择你想要的操作");
		lb.setFont(new Font("宋体", Font.PLAIN, 30));
		lb.setForeground(Color.BLUE);

		btn_stu = new JButton("学生基本信息管理");
		btn_course = new JButton("课程信息管理");
		btn_teacher = new JButton("教师信息管理");
		btn_score = new JButton("成绩信息管理");
		btn_account = new JButton("账号信息管理");

		panelNorth = new JPanel();
		panelCenter = new JPanel();
		panelSouth = new JPanel();

		frame_manager_choice.setSize(500, 300);
		frame_manager_choice.setLocation(800, 300);
		frame_manager_choice.setLayout(new BorderLayout());

		panelNorth.setLayout(new FlowLayout());
		panelCenter.setLayout(new FlowLayout());
		// panelSouth.setLayout(new FlowLayout());

		frame_manager_choice.add(panelNorth, BorderLayout.NORTH);
		frame_manager_choice.add(panelCenter, BorderLayout.CENTER);
		frame_manager_choice.add(panelSouth, BorderLayout.SOUTH);
		panelNorth.add(lb);
		panelCenter.add(btn_stu);
		panelCenter.add(btn_course);
		panelCenter.add(btn_teacher);
		panelCenter.add(btn_score);
		panelCenter.add(btn_account);
		frame_manager_choice.setVisible(true);
		frame_manager_choice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 增删查改。导入（可返回上一页重新选择操作）
		btn_stu.addActionListener(new ActionListener() {//进入学生管理界面

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManageStu();
				frame_manager_choice.setVisible(false);
			}
			
		});
		btn_course.addActionListener(new ActionListener() {//进入课程管理界面（增删查改）

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManageCourse();
				frame_manager_choice.setVisible(false);
			}
			
		});
		btn_teacher.addActionListener(new ActionListener() {//进入老师管理界面（增删查改）

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManageTea();
				frame_manager_choice.setVisible(false);
			}
		
		});
		btn_score.addActionListener(new ActionListener() {//进入成绩管理界面（增删查改）

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManageScore();
				frame_manager_choice.setVisible(false);
			}
		
		});
		btn_account.addActionListener(new ActionListener() {//进入账户管理界面（增删查改）

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManageAccount();
				frame_manager_choice.setVisible(false);
			}
		
		});
		// 可分第一页，第二页，
	}
}
