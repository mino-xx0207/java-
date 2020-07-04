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
//ҲҪ�ǵ�����
	// ��ʹ��JDialog �������ʦ���ֵ������ڿ���ʾ��ʦ�Ļ�����Ϣ
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
	Object[][] info = new Object[10000][6];
	
	/** The i. */
	int i = 0;
	
	/** The scroll pane. */
	JScrollPane scrollPane;// �������������
	
	/** The table. */
	JTable table;
	
	/** The dialog. */
	JDialog dialog;


	/**
	 * Instantiates a new grade info.
	 */

	public Grade_info() {
		frame_score = new JFrame("�ɼ�ҳ��");
		frame_score.setSize(650, 300);
		frame_score.setLocation(800, 300);
		frame_score.setLayout(new BorderLayout());
		//���Լ���sql�޸��£��ӱ�����
		
		//����û��չʾ���ݾ��Ǳ�����û�����ݡ�����
		//�����������ݣ�һ���ģ�Ҳ��û������
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
		String[] columnTitle = { "�γ�", "��ʦ", "�ɼ�", "ѧ��", "��ʱ", "�γ����" };
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
