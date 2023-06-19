package in.vasanth.utility;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.vasanth.model.IData;

@Component

public class PdfGenerator {
	
	
	
	private void writeTableHeader(PdfPTable table) {
		
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.magenta);
		cell.setPadding(7);
		
		Font font=FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(Color.WHITE);
		cell.setPhrase(new Phrase("CitizenId",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("CitizenName",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Gender",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PlanName",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PlanStatus",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("StartDate",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("EndDate",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("BenefitAmount",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("DenialReason",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("TerminatedDate",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Reason",font));
		table.addCell(cell);
	}
	
	private void writeTableData(PdfPTable table,List<IData> listUsers) {
		for (IData user:listUsers) {
			table.addCell(user.getCitizenId().toString());
			table.addCell(user.getCitizenName());
			table.addCell(user.getGender());
			table.addCell(user.getPlanName());
			table.addCell(user.getPlanStatus());
			if(null!=user.getPlanStartDate()) {
			table.addCell(String.valueOf(user.getPlanStartDate()));
			}
			else {
				table.addCell("N/A");
			}
			if(null!=user.getPlanEndDate()) {
			table.addCell(String.valueOf(user.getPlanEndDate()));
			}
			else {
				table.addCell("N/A");
			}
			if(null!=user.getBenefitAmount()) {
			table.addCell(String.valueOf(user.getBenefitAmount()));
			}
			else {
				table.addCell("N/A");
			}
			if(null!=user.getDenialReason()) {
			table.addCell(user.getDenialReason());
			}
			else {
				table.addCell("N/A");
			}
			if(null!=user.getTerminatedDate()) {
			table.addCell(String.valueOf(user.getTerminatedDate()));
			}
			else {
				table.addCell("N/A");
			}
			if(null!=user.getTerminatedReason()) {
			table.addCell(user.getTerminatedReason());
			}
			else {
				table.addCell("N/A");
			}
			
			
		}
	}
	
	public void exportPdf(HttpServletResponse response,List<IData> listUsers,File file) throws IOException{
		Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document,response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.RED);
		
		Paragraph p=new Paragraph("List of Users",font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
		PdfPTable table=new PdfPTable(11);
				table.setSpacingBefore(10);
		
		writeTableHeader(table);
		writeTableData(table,listUsers);
		
		document.add(table);
		document.close();
		
		
		
		
	}
		
		
	

}
