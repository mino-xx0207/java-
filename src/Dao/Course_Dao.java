package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class Course_Dao.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Course_Dao {
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new course dao.
	 *
	 * @param driverName the driver name
	 * @param uri the uri
	 */
	public Course_Dao(String driverName, String uri) {
		conn = DBConnection.getConnection(driverName, uri);
	}
	
	/**
	 * Save course info.
	 *
	 * @param sql the sql
	 * @param course_id the course id
	 * @param course_name the course name
	 * @param hour the hour
	 * @param credit the credit
	 * @param course_nature the course nature
	 * @param course_category the course category
	 */
	//增加课程信息
	public void saveCourseInfo(String sql, String course_id, String course_name, int hour, int credit, String course_nature,String course_category) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course_id);
			pstmt.setString(2, course_name);
			pstmt.setInt(3, hour);
			pstmt.setInt(4, credit);
			pstmt.setString(5, course_nature);
			pstmt.setString(6, course_category);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete course info.
	 *
	 * @param sql the sql
	 * @return the int
	 */
	//从数据库中删除
		public int deleteCourseInfo(String sql) {
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
		//查找信息
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
	 * Update course info.
	 *
	 * @param sql the sql
	 * @param course_name the course name
	 * @param hour the hour
	 * @param credit the credit
	 * @param course_nature the course nature
	 * @param course_category the course category
	 * @return the int
	 */
	//更新信息
	public int updateCourseInfo(String sql, String course_name, int hour, int credit, String course_nature,String course_category) {
		int flag = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course_name);
			pstmt.setInt(2, hour);
			pstmt.setInt(3, credit);
			pstmt.setString(4, course_nature);
			pstmt.setString(5, course_category);
			flag = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
