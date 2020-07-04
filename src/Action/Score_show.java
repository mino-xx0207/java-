package Action;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.log.Log;

import Dao.Grade_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Score_show.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Score_show {
	
	/** The driver name. */
	private String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Grade_Dao userDB = new Grade_Dao(driverName, uri);

	/** The frame score. */
	JFrame frame_score;
	//�����Ļ�Ӧ�ò��ᱨ�����ǲ��á�
	/** The info. */
	//���������飬���øտ�ʼ�ͳ�ʼ�����ȸ�Null�ɡ��������ɣ������ݾͲ��ᱨ�������ĵط������
	Object[][] info = new Object[10000][5];
	
	/** The i. */
	int i = 0;
	
	/** The scroll pane. */
	JScrollPane scrollPane;// �������������
	
	/** The table. */
	JTable table;


	//������ֱ���ϰ������ر𲻺á��������Լ����Ǻܶ��ˣ���ϲ����ɶ���㵽���������档//�տ�ʼ��Ҳ���࣬�����Ҹо�̫������
	//���Ƿֲ���������⡣����Щ���룬����Ҫд�ڹ��������档��Ͳ���д�ڷ������档
	//��������Ҳ��͸���Ľṹ�ˡ��������Ĵ����дϰ�ߣ�����ʵ�����У���ֱû�����ܣ��ᱻ������
	/**
	 * Instantiates a new score show.
	 */
	//�ڹ��������治Ҫд���߰���ģ��ѹ������������á�
	public Score_show() {
		frame_score = new JFrame("�ɼ�ҳ��");
		
		frame_score.setSize(700, 300);
		frame_score.setLocation(800, 300);
		frame_score.setLayout(new BorderLayout());
		//���Լ���sql�޸��£��ӱ�����
		
		//����û��չʾ���ݾ��Ǳ�����û�����ݡ�����
		//�����������ݣ��ǵ�������������һ���ģ�Ҳ��û�����ݡ����ǣ����ǵ���ʦ��Ȼ����Ȼû�����ݣ�����Ϊʲô����GUI����Ҳû�а����༶��רҵ
		String sql = "select stu.stu_name,stu.class,stu.major,c.course_name,s.score"
				+ " from score as s,student as stu,course as c where s.course_id = c.course_id and "
				+ "stu.stu_id = s.stu_id ;";
		ResultSet rs = userDB.search_info(sql);
		try {
			while (rs.next()) {
				info[i][0] = rs.getString(1);
				info[i][1] = rs.getString(2);
				info[i][2] = rs.getString(3);
				info[i][3] = rs.getString(4);
				info[i][4] = rs.getDouble(5);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] columnTitle = { "����","�༶","רҵ","�γ���", "�ɼ�" };
		//���ڲ���ʾ������Ϊ�����ˡ�info =null��Jtable�ڵ���getColumn,getRow��ʱ��ͻᱨ��ָ��
		table = new JTable(info, columnTitle);
		
		table.setRowHeight(30);// �����и�
		table.setFont(new Font("����", Font.BOLD, 20));// ��������
		table.getTableHeader().setFont(new Font("����", Font.BOLD, 20));// ���ñ�ͷ����
		
		scrollPane = new JScrollPane(table);
	
		frame_score.add(scrollPane, BorderLayout.CENTER);

		frame_score.setVisible(true);
		frame_score.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// userDB.closeAll();

	}

}
