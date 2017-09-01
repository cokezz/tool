package web.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import web.bean.PostExamItemStat;

import com.kec.smartuc.shared.constants.Gender;
import com.kec.smartuc.shared.po.UserClassRef;
import com.taoxeo.commons.lang.DateUtils;
import com.taoxeo.commons.lang.StringUtils;

/**
 * The Class ExportUtils.
 *
 * @author Administrator
 * @version 1.0, 2014-10-15
 */
public class ExportUtils {

	/** 字节数组缓冲大小,单位byte*/
	private static final int BUFFER_SIZE = 1024 * 10;
	
	/** The style. */
	private static HSSFCellStyle style;//单元格样式
	
	/**
	 * Inits the.
	 *
	 * @param wb the wb
	 */
	private static void init(HSSFWorkbook wb) {
		Font font = wb.createFont();
	    font.setFontHeightInPoints((short)12);
	    font.setFontName("宋体");
	    
	    style = wb.createCellStyle();
	    //设置边框
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setFont(font);
	}
	
	/**
	 * To excel.
	 *
	 * @param cname the cname
	 * @param userClassRefs the user class refs
	 * @param out the out
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] toExcel(String cname, List<UserClassRef> userClassRefs) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
		HSSFWorkbook workbook = new HSSFWorkbook();
		init(workbook);
		HSSFSheet sheet = workbook.createSheet(cname); // 创建工作表
		sheet.setDefaultRowHeightInPoints(20);
		transformClassUserToExcel(cname, sheet, userClassRefs);
		workbook.write(out);
		byte[] bytes = out.toByteArray();
		out.close();
		return bytes;
	}
	
	/**
	 * To excel.
	 *
	 * @param cname the cname
	 * @param userClassRefs the user class refs
	 * @param out the out
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] toPostExitemStatExcel(String cname, List<PostExamItemStat> stats) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
		HSSFWorkbook workbook = new HSSFWorkbook();
		init(workbook);
		HSSFSheet sheet = workbook.createSheet(cname); // 创建工作表
		sheet.setDefaultRowHeightInPoints(20);
		transformtExamItemStatToExcel(cname, sheet, stats);
		workbook.write(out);
		byte[] bytes = out.toByteArray();
		out.close();
		return bytes;
	}
	
	
	
	/**
	 * 转换用户课程统计信息为Excel
	 * @param cname
	 * @param sheet
	 * @param stats
	 */
	private static void transformtExamItemStatToExcel(String cname, HSSFSheet sheet, List<PostExamItemStat> stats) {
		String[] columns = new String[]{"序号", "单位名称", "姓名", "报名时间", "当前进程", "完成率", "正确率","备注"};
		int index = setHeader(sheet, cname + "共有" + stats.size() + "人", columns);
		
		int n = 1;//序号
		for(PostExamItemStat stat : stats) {
			HSSFRow row = sheet.createRow(index++);
			setHSSFCell(row, 0, String.valueOf(n++));
			setHSSFCell(row, 1, stat.getCname());
			setHSSFCell(row, 2, stat.getUname());
			setHSSFCell(row, 3, "--");
			setHSSFCell(row, 4, "--");
			setHSSFCell(row, 5, stat.getFinishRate());
			setHSSFCell(row, 6, stat.getYesRate());
			setHSSFCell(row, 7, "--");
		}
		//设置列宽自适应
		for(int i=0; i<columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
	}

	/**
	 * Transform class user to excel.
	 *
	 * @param cname the cname
	 * @param sheet the sheet
	 * @param userClassRefs the user class refs
	 */
	private static void transformClassUserToExcel(String cname, HSSFSheet sheet, List<UserClassRef> userClassRefs) {
		String[] columns = new String[]{"序号", "帐号", "姓名", "性别", "身份证", "身份", "加入时间"};
		int index = setHeader(sheet, cname + "共有" + userClassRefs.size() + "人", columns);
		
		int n = 1;//序号
		for(UserClassRef ucr : userClassRefs) {
			HSSFRow row = sheet.createRow(index++);
			setHSSFCell(row, 0, String.valueOf(n++));
			setHSSFCell(row, 1, ucr.getUser().getUsername());
			setHSSFCell(row, 2, ucr.getUser().getName());
			setHSSFCell(row, 3, ucr.getUser().getGender().equals(Gender.M.name()) ? Gender.M.getDisplay() : Gender.F.getDisplay());
			setHSSFCell(row, 4, ucr.getUser().getIdCard());
			setHSSFCell(row, 5, getIdentityName(ucr.getIdentity()));
			setHSSFCell(row, 6, DateUtils.format(ucr.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			
		}
		//设置列宽自适应
		for(int i=0; i<columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		
	}
	
	/**
	 * Gets the identity name.
	 *
	 * @param identity the identity
	 * @return the identity name
	 */
	private static String getIdentityName(String identity){
		/*if(identity.equals(Identity.student.name())) {
			return Identity.student.getDisplay();
		} else if(identity.equals(Identity.teacher.name())) {
			return Identity.teacher.getDisplay();
		} else if(identity.equals(Identity.teacher_master.name())) {
			return Identity.teacher_master.getDisplay();
		} else if(identity.equals(Identity.teacher_admin.name())) {
			return Identity.teacher_master.getDisplay();
		}  else if(identity.equals(Identity.school_admin.name())) {
			return Identity.school_admin.getDisplay();
		}*/
		return "";
	}
	
	/**
	 * 创建表头,返回当前表创建的行数.
	 *
	 * @param sheet 工作簿
	 * @param title 表的标题
	 * @param colNames 表头列名
	 * @return the int
	 */
	private static int setHeader(HSSFSheet sheet, String title, String... colNames) {
		int index = 0;
		if(colNames != null && colNames.length > 0) {
			int length = colNames.length;
			HSSFRow row = sheet.createRow(index++);
			for(int i=0; i<length; i++) {
				setHSSFCell(row, i, "");
			}
			setHSSFCell(row, 0, title);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, length - 1));
			//第二行
			row = sheet.createRow(index++);
			for(int i=0; i<length; i++) {
				setHSSFCell(row, i, colNames[i]);
			}
			
		}
		return index;
	}
	
	/**
	 * 设置单元格内容.
	 *
	 * @param row the row
	 * @param column the column
	 * @param content the content
	 */
	private static void setHSSFCell(HSSFRow row, int column, String content) {
		HSSFCell cell = row.createCell(column);
		cell.setCellStyle(style);
		if(StringUtils.isBlank(content)) {
			cell.setCellValue("");
		} else {
			cell.setCellValue(content);
		}
	}
}
