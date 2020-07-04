package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Dao.Stu_Dao;
import Dao.Teacher_Dao;

/**
 * The Class TeacherDaoTest.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class TeacherDaoTest {

	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root"
			+ "&password=123456&useSSL=true";
	
	/** The user DB. */
	public Teacher_Dao userDB = new Teacher_Dao(driverName, uri);
	
    /**
     * Test delete teacher.
     */
    @Test
	public void testDeleteTeacher() {
		int num = userDB.deleteTeaInfo("delete from teacher where tea_id='10975'");
		assertEquals(1, num);
	}
    
    /**
     * Test update student.
     */
    @Test
	public void testUpdateTeacher() {
		String sql = "update teacher set tea_name = ?,college = ?, "
				+ "job_title = ?, job_time = ?"
				+ "where tea_id = '10985';";
		int num = userDB.updateTeacherInfo(sql, "Jess", "国际教育学院", "讲师", "2018-06-01");
		assertEquals(1, num);
	}
}
