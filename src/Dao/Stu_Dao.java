package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class Stu_Dao.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Stu_Dao {
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new stu dao.
	 *
	 * @param driverName the driver name
	 * @param uri the uri
	 */
	public Stu_Dao(String driverName, String uri) {
		conn = DBConnection.getConnection(driverName, uri);
	}

	/**
	 * Save stu info.
	 *
	 * @param sql the sql
	 * @param id the id
	 * @param name the name
	 * @param sex the sex
	 * @param age the age
	 * @param college the college
	 * @param major the major
	 * @param grade the grade
	 * @param cls the cls
	 * @param address the address
	 * @param telephone the telephone
	 */
	// 保存学生信息
	public void saveStuInfo(String sql, String id, String name, String sex, int age, String college, String major,
			String grade, String cls, String address, String telephone) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, sex);
			pstmt.setInt(4, age);
			pstmt.setString(5, college);
			pstmt.setString(6, major);
			pstmt.setString(7, grade);
			pstmt.setString(8, cls);
			pstmt.setString(9, address);
			pstmt.setString(10, telephone);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Delete stu info.
     *
     * @param sql the sql
     * @return the int
     */
    //从数据库中删除
	public int deleteStuInfo(String sql) {
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
	 * Update stu info.
	 *
	 * @param sql the sql
	 * @param name the name
	 * @param sex the sex
	 * @param age the age
	 * @param college the college
	 * @param major the major
	 * @param grade the grade
	 * @param cls the cls
	 * @param address the address
	 * @param telephone the telephone
	 * @return the int
	 */
	//更新信息
		public int updateStuInfo(String sql,String name, String sex, int age, String college, String major,
				String grade, String cls, String address, String telephone) {
			int flag = 0;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, sex);
				pstmt.setInt(3, age);
				pstmt.setString(4, college);
				pstmt.setString(5, major);
				pstmt.setString(6, grade);
				pstmt.setString(7, cls);
				pstmt.setString(8, address);
				pstmt.setString(9, telephone);
			    flag = pstmt.executeUpdate();
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
