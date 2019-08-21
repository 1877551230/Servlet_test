package cn.tedu.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.tedu.entity.User;
/**
 * 导出的工具类
 * @author PC
 *
 */
public class ExportUtil {

	public static byte[] write2Excel(List<User> users) {
		byte[] data=null;
		//创建一个内存字节流,因为wb.write(OutputStream)
		ByteArrayOutputStream out=null;
		try{
			out=new ByteArrayOutputStream();
			//创建工作簿的对象(老版本.xls)
			Workbook wb=new HSSFWorkbook();
			//创建sheet对象
			Sheet sheet=wb.createSheet("allUsers");
			//创建行对象
			Row row=sheet.createRow(0);
			//创建一个字符串数组,用于存储列头信息
			String[] columns=new String[]{"id","用户名","密码","年龄","地址","头像"};
			//给第一行添加列头信息
			for(int i=0;i<6;i++){
				Cell cell=row.createCell(i);
				//设置样式
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				sheet.setColumnWidth(i, 6000);
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setBold(true);
				font.setColor(HSSFColor.RED.index);
				style.setFont(font);
				cell.setCellStyle(style);
				//给单元格添加文字数据
				cell.setCellValue(columns[i]);
			}
			//循环数据,创建excel表格的行
			for(int j=0;j<users.size();j++){
				User user=users.get(j);
				Row r=sheet.createRow(j+1);
				//给当前行的若干添加数据
				r.createCell(0).setCellValue(user.getId());;
				r.createCell(1).setCellValue(user.getName());;
				r.createCell(2).setCellValue(user.getPassword());;
				r.createCell(3).setCellValue(user.getAge());;
				r.createCell(4).setCellValue(user.getAddress());;
				r.createCell(5).setCellValue(user.getHeadimage());;
			}
			wb.write(out);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//把内存流中的数据转换成字节数组
		data=out.toByteArray();
		return data;
	}
	

}
