package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class Teacher_Dao.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Teacher_Dao {
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new teacher dao.
	 *
	 * @param driverName the driver name
	 * @param uri the uri
	 */
	public Teacher_Dao(String driverName, String uri) {
		conn = DBConnection.getConnection(driverName, uri);
	}
	
	/**
	 * Save stu info.
	 *
	 * @param sql the sql
	 * @param tea_id the tea id
	 * @param tea_name the tea name
	 * @param college the college
	 * @param job_title the job title
	 * @param job_time the job time
	 */
	//增加教师信息
	public void saveStuInfo(String sql, String tea_id, String tea_name, String college, String job_title, String job_time) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tea_id);
			pstmt.setString(2, tea_name);
			pstmt.setString(3, college);
			pstmt.setString(4, job_title);
			pstmt.setString(5, job_time);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete tea info.
	 *
	 * @param sql the sql
	 * @return the int
	 */
	//从数据库中删除
		public int deleteTeaInfo(String sql) {
			int i = 0;
			try {
				pstmt = conn.prepareStatement(sql);
				i = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return i;
		
		}
	
	/**
	 * Search info.
	 *
	 * @param sql the sql
	 * @return the result set
	 */
	public ResultSet search_info(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return rs;
		}
	}
	
	/**
	 * Update teacher info.
	 *
	 * @param sql the sql
	 * @param tea_name the tea name
	 * @param college the college
	 * @param job_title the job title
	 * @param job_time the job time
	 * @return the int
	 */
	//更新信息
		public int updateTeacherInfo(String sql,String tea_name, String college, String job_title, String job_time) {
			int flag = 0;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, tea_name);
				pstmt.setString(2, college);
				pstmt.setString(3, job_title);
				pstmt.setString(4, job_time);
			    flag = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}
	
}
