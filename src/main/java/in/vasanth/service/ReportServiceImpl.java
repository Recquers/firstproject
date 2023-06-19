package in.vasanth.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import in.vasanth.model.IData;
import in.vasanth.repository.InsuranceRepository;
import in.vasanth.search.SearchRequest;
import in.vasanth.utility.EmailService;
import in.vasanth.utility.PdfGenerator;
import in.vasanth.utility.WriteExcel;
@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private InsuranceRepository repo;
	
	@Autowired
	private WriteExcel excel;
	
	@Autowired
	private PdfGenerator generate;
	
	@Autowired
	private EmailService eservice;

	@Override
	public List<String> getPlanNames() {
		return  repo.getPlanNames();
		
	}

	@Override
	public List<String> getPlanStatuses() {
		return repo.getPlanStatuses();
		 
	}

	@Override
	public List<IData> search(SearchRequest request) {
		IData entity=new IData();
		if(null !=request.getPlanName()&&!"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if(null !=request.getPlanStatus()&&!"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if(null !=request.getGender()&&!"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		if(null !=request.getStartDate()&&!"".equals(request.getStartDate())) {
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate=LocalDate.parse(request.getStartDate(), formatter);
			entity.setPlanStartDate(localDate);
			
		}
		if(null !=request.getEndDate()&&!"".equals(request.getEndDate())) {
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate=LocalDate.parse(request.getEndDate(), formatter);
			entity.setPlanEndDate(localDate);
			
		}
		
		return repo.findAll(Example.of(entity));
	}	
	
	@Override	
	public List<IData> getCitizenList(){
		return repo.findAll();
		
	}
	@Override
	public boolean exportExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=plans.xlsx");
		File file=new File("Plans.xlsx");
		
		excel.export(response,repo.findAll(),file);
		eservice.sendMailWithAttachment("vasanth49918@gmail.com","subject", "This is Test Email","Plans.xlsx", file);
		file.delete();
		return true;
				
	}
	@Override
	public boolean exportPdf(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=plans.pdf");
		
		File file=new File("Plans.pdf");
		generate.exportPdf(response,repo.findAll(),file);
			
		eservice.sendMailWithAttachment("vasanth49918@gmail.com","subject", "This is Test Email","Plans.pdf", file);
		file.delete();
		
		return true;
	
	}
	
		
	

	

}
