package test;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Dao.Grade_Dao;
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
 * The Class ExcelTestScore.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class ExcelTestScore {
	
	/** The Constant exportFilePath. */
	private final static String exportFilePath = "F://Desktop//杂//Java程序设计-实训//java程序设计实训-吉首大学资料分享//java程序设计实训-吉首大学资料分享//";
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Grade_Dao userDB = new Grade_Dao(driverName, uri);

	/**
	 * Export excel.
	 */
	// 成绩只要导出
	public void exportExcel() {
			WritableWorkbook workBook = null;
			// 导出的文件
			String content = JOptionPane.showInputDialog(null, "请输入你要导出的目的文件名", "导出数据", JOptionPane.INFORMATION_MESSAGE);
			File file = new File(exportFilePath+content);
			if (content != null) {
				int n = JOptionPane.showConfirmDialog(null, "确认导出吗?", "确认导出框", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					try {
						workBook = Workbook.createWorkbook(file);
						WritableSheet sheet = workBook.createSheet("成绩信息", 0);// 创建一个学生信息的sheet页
						Label stuName = new Label(0, 0, "姓名");
						Label courseName = new Label(1, 0, "课程");
						Label score = new Label(2, 0, "成绩");

						sheet.addCell(stuName);
						sheet.addCell(courseName);
						sheet.addCell(score);
						// 查询数据库表数据
						
						String sql = "select stu.stu_name,cour.course_name,s.score from student as stu,course as cour"
								+ ",score as s where stu.stu_id = s.stu_id and s.course_id = cour.course_id";
						ResultSet rs = userDB.search_info(sql);
						int rowNumber = 1;

						while(rs.next()) {
							Label studentName = new Label(0, rowNumber, rs.getString(1));
							Label courseName1 = new Label(1, rowNumber, rs.getString(2));
							Label score1 = new Label(2, rowNumber, rs.getString(3));
						
							sheet.addCell(studentName);
							sheet.addCell(courseName1);
							sheet.addCell(score1);
							
							rowNumber++;
						} 
						// 写入数据
						workBook.write();
						JOptionPane.showMessageDialog(null, "导出成功！");
					}catch (Exception e) {
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

