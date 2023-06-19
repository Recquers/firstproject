package in.vasanth.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import in.vasanth.model.IData;
@Component

public class WriteExcel {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	public WriteExcel() {
		workbook=new XSSFWorkbook();
	}
	
	
	
	private void writeHeaderLine() {
		sheet=workbook.createSheet();
		Row row=sheet.createRow(0);
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		
		createCell(row,0,"CitizenId",style);
		createCell(row,1,"CitizenName",style);
		createCell(row,2,"Gender",style);
		createCell(row,3,"PlanName",style);
		createCell(row,4,"PlanStatus",style);
		
		createCell(row,5,"PlanStartDate",style);
		createCell(row,6,"PlanEndDate",style);
		createCell(row,7,"BenefitAmount",style);
		createCell(row,8,"DenialReason",style);
		createCell(row,9,"TerminatedDate",style);
		createCell(row,10,"TerminatedReason",style);
		
		
		
	}
	
	private void createCell(Row row,int columnCount,Object value,CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		
		Cell cell=row.createCell(columnCount);
		if(null!=value) {
		if (value instanceof Integer) {
			cell.setCellValue((Integer)value);}
		else if (value instanceof LocalDate) {
			cell.setCellValue((LocalDate)value+"");}
		
		else if (value instanceof Double) {
			cell.setCellValue((Double)value);}
		
		
		else {
			cell.setCellValue((String)value);
			
		}}
		else {
			cell.setCellValue("N/A");
		}
		cell.setCellStyle(style);
	}
	private void writeDataLine(List<IData> listUsers) {
		int rowCount=1;
		
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		for (IData user:listUsers) {
			Row row=sheet.createRow(rowCount++);
			int columnCount=0;
			
			createCell(row,columnCount++,user.getCitizenId(),style);
			createCell(row,columnCount++,user.getCitizenName(),style);
			createCell(row,columnCount++,user.getGender(),style);
			createCell(row,columnCount++,user.getPlanName(),style);
			createCell(row,columnCount++,user.getPlanStatus(),style);
			
			createCell(row,columnCount++,user.getPlanStartDate(),style);
			createCell(row,columnCount++,user.getPlanEndDate(),style);
			createCell(row,columnCount++,user.getBenefitAmount(),style);
			createCell(row,columnCount++,user.getDenialReason(),style);
			createCell(row,columnCount++,user.getTerminatedDate(),style);
			createCell(row,columnCount++,user.getTerminatedReason(),style);
			
			
		}
		
	}
	public void export (HttpServletResponse response,List<IData> listUsers,File file) throws IOException{
		writeHeaderLine();
		writeDataLine(listUsers);
		FileOutputStream f=new FileOutputStream(new File("Plans.xlsx"));
		workbook.write(f);
		ServletOutputStream outputStream=response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
		
	}

}
