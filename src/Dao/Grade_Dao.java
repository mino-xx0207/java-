package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class Grade_Dao.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Grade_Dao {
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new grade dao.
	 *
	 * @param driverName the driver name
	 * @param uri the uri
	 */
	public Grade_Dao(String driverName, String uri) {
		conn = DBConnection.getConnection(driverName, uri);
	}
	
	/**
	 * Save score info.
	 *
	 * @param sql the sql
	 * @param student_id the student id
	 * @param course_id the course id
	 * @param tea_id the tea id
	 * @param score the score
	 */
	// 保存学生信息
		public void saveScoreInfo(String sql, String student_id, String course_id, String tea_id,double score) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, student_id);
				pstmt.setString(2, course_id);
				pstmt.setString(3, tea_id);
				pstmt.setDouble(4, score);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	 * Delete score info.
	 *
	 * @param sql the sql
	 * @return the int
	 */
	//从数据库中删除
		public int deleteScoreInfo(String sql) {
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
		 * Update score info.
		 *
		 * @param sql the sql
		 * @param score the score
		 * @return the int
		 */
		//更新信息
		public int updateScoreInfo(String sql,double score) {
			int flag = 0;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setDouble(1, score);
			   flag= pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}
	
	/**
	 * Close all.
	 */
	// 关闭资源
		public void closeAll() {
			try {
				if (!(rs == null)) {
					rs.close();
					pstmt.close();
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
