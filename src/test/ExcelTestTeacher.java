package test;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Dao.Teacher_Dao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelTestTeacher.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class ExcelTestTeacher {
	
	/** The Constant exportFilePath. */
	private final static String exportFilePath="F://Desktop//杂//Java程序设计-实训//java程序设计实训-吉首大学资料分享//java程序设计实训-吉首大学资料分享//";
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";

	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Teacher_Dao userDB = new Teacher_Dao(driverName, uri);

	/**
	 * Import excel.
	 */
	public void importExcel() {
		Workbook workbook = null;
		Sheet sheet = null;
		String content = JOptionPane.showInputDialog(null, "请输入你要导入的文件名", "导入数据", JOptionPane.INFORMATION_MESSAGE);
		// Excel文件
		if (content != null) {
			File file = new File(exportFilePath+content);
			// 创建workbook
			try {
				workbook = Workbook.getWorkbook(file);
				// 获取sheet页
				sheet = workbook.getSheet(0);
			
				int rows = sheet.getRows();
				// 初始化student对象
				// 在for循环/while 循环不要初始化对象。
				for (int i = 1; i <= rows; i++) {
					// 直接获取单元格
					Cell teacher_id_cell = sheet.getCell(0, i);
					Cell tea_name_cell = sheet.getCell(1, i);
					Cell college_cell = sheet.getCell(2, i);
					Cell job_title_cell = sheet.getCell(3, i);
					Cell job_time_cell = sheet.getCell(4, i);
					// 获取单元格里面的内容
					String teacherIdstr = teacher_id_cell.getContents();
					String teacherNamestr = tea_name_cell.getContents();
					String collegeStr = college_cell.getContents();
					String job_titleStr = job_title_cell.getContents();
					String job_timeStr = job_time_cell.getContents();

					String sql1 = "insert into teacher(tea_id,tea_name,college,job_title,job_time) "
							+ "values(?,?,?,?,?)";
					userDB.saveStuInfo(sql1, teacherIdstr, teacherNamestr, collegeStr, job_titleStr, job_timeStr);
				}
				JOptionPane.showMessageDialog(null, "导入成功！");
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Export excel.
	 */
	public void exportExcel() {
		WritableWorkbook workBook = null;
		// 导出的文件
		//输入的文件名称。你输入了吗？重来。可以的，那那个文件名
		String content = JOptionPane.showInputDialog(null, "请输入你要导出的目的文件名", "导出数据", JOptionPane.INFORMATION_MESSAGE);
		File file = new File(exportFilePath+content);
		if (content != null) {
			int n = JOptionPane.showConfirmDialog(null, "确认导出吗?", "确认导出框", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				try {
					workBook = Workbook.createWorkbook(file);
					WritableSheet sheet = workBook.createSheet("教师信息", 0);// 创建一个学生信息的sheet页
					Label teacherNoHeader = new Label(0, 0, "工号");
					Label teacherNameHeader = new Label(1, 0, "姓名");
					Label teacherCollege = new Label(2, 0, "学院");
					Label teacher_job_title = new Label(3, 0, "职称");
					Label teacher_job_time = new Label(4, 0, "入职时间");

					sheet.addCell(teacherNoHeader);
					sheet.addCell(teacherNameHeader);
					sheet.addCell(teacherCollege);
					sheet.addCell(teacher_job_title);
					sheet.addCell(teacher_job_time);
					// 查询数据库表数据
					String sql = "select * from teacher";
					ResultSet rs = userDB.search_info(sql);
					int rowNumber = 1;

					while(rs.next()) {
						Label teacherNO = new Label(0, rowNumber, rs.getString(1));
						Label teacherName = new Label(1, rowNumber, rs.getString(2));
						Label college = new Label(2, rowNumber, rs.getString(3));
						Label job_title = new Label(3, rowNumber, rs.getString(4));
						Label job_time = new Label(4, rowNumber, rs.getString(5));

						sheet.addCell(teacherNO);
						sheet.addCell(teacherName);
						sheet.addCell(college);
						sheet.addCell(job_title);
						sheet.addCell(job_time);
						rowNumber++;
					}
					// 写入数据
					workBook.write();
					JOptionPane.showMessageDialog(null, "导出成功！");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						workBook.close();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if (n == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, "取消导出");
			}
		}
	}
}
