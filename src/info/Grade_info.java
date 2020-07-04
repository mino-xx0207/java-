package info;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.log.Log;

import Dao.Grade_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Grade_info.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Grade_info {
//也要记得隐藏
	// 可使用JDialog ，点击老师名字弹出窗口可显示老师的基本信息
	/** The driver name. */
// X掉回到主选择界面
	private String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Grade_Dao userDB = new Grade_Dao(driverName, uri);

	/** The frame score. */
	JFrame frame_score;
	//这样的话应该不会报错，但是不好。
	/** The info. */
	//这里是数组，不好刚开始就初始化。先给Null吧。先这样吧，有数据就不会报错。导出的地方在哪里？
	Object[][] info = new Object[10000][6];
	
	/** The i. */
	int i = 0;
	
	/** The scroll pane. */
	JScrollPane scrollPane;// 带滚动条的面板
	
	/** The table. */
	JTable table;
	
	/** The dialog. */
	JDialog dialog;

	//你的这种编码习惯真的特别不好。不过你自己，是很多人，都喜欢把啥都搞到构造器里面。//刚开始我也分类，但是我感觉太多了类
	//不是分不分类的问题。你这些代码，干嘛要写在构造器里面。你就不能写在方法里面。
	//这个代码我不就给你改结构了。你这样的代码编写习惯，在真实开发中，简直没法忍受，会被喷死的
	/**
	 * Instantiates a new grade info.
	 */
	//在构造器里面不要写算七八糟的，把构造器当方法用。
	public Grade_info() {
		frame_score = new JFrame("成绩页面");
		frame_score.setSize(650, 300);
		frame_score.setLocation(800, 300);
		frame_score.setLayout(new BorderLayout());
		//你自己把sql修改下，加别名。
		
		//现在没有展示数据就是本来就没有数据。正常
		//导不出来数据，是导这个表的数据吗？一样的，也是没有数据。不是，我是导教师，然后虽然没有数据，但是为什么它的GUI界面也没有啊
		String sql = "select c.course_name,t.tea_name,s.score,c.credit,c.hour,c.course_category "
				+ "from score as s,teacher as t,course as c where s.course_id = c.course_id and "
				+ "s.tea_id = t.tea_id and s.stu_id='" + Log.userName.getText() + "';";
		ResultSet rs = userDB.search_info(sql);
		try {
			while (rs.next()) {
				info[i][0] = rs.getString(1);
				info[i][1] = rs.getString(2);
				info[i][2] = rs.getDouble(3);
				info[i][3] = rs.getInt(4);
				info[i][4] = rs.getInt(5);
				info[i][5] = rs.getString(6);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] columnTitle = { "课程", "教师", "成绩", "学分", "课时", "课程类别" };
		//现在不显示，是因为报错了。info =null。Jtable在调用getColumn,getRow的时候就会报空指针
		table = new JTable(info, columnTitle);
		
		table.setRowHeight(30);// 设置行高
		table.setFont(new Font("宋体", Font.BOLD, 20));// 设置字体
		table.getTableHeader().setFont(new Font("宋体", Font.BOLD, 20));// 设置表头字体
		
		scrollPane = new JScrollPane(table);
		frame_score.add(scrollPane, BorderLayout.CENTER);

		frame_score.setVisible(true);
		frame_score.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// userDB.closeAll();

	}


}
