package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Dao.Stu_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentDaoTest.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class StudentDaoTest {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root"
			+ "&password=123456&useSSL=true";
	
	/** The user DB. */
	public Stu_Dao userDB = new Stu_Dao(driverName, uri);
	
    /**
     * Test delete student.
     */
    @Test
	public void testDeleteStudent() {
		int num = userDB.deleteStuInfo("delete from student where stu_id='20009'");
		assertEquals(1, num);
	}
    
    /**
     * Test update student.
     */
    @Test
	public void testUpdateStudent() {
		String sql = "update student set stu_name = ?,sex= ?, "
				+ "age= ?, college= ?, "
				+ "major= ?, grade= ?, class= ?, address= ?, telephone= ? where stu_id ='20001';";
		int num = userDB.updateStuInfo(sql, "Joy", "��", 19, "����ѧԺ", 
				"��������", "16��", "5��", "Ӣ������˹��", null);
		assertEquals(1, num);
	}

}
