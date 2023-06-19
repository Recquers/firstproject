package in.vasanth.controller;




import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.vasanth.model.IData;
import in.vasanth.search.SearchRequest;
import in.vasanth.service.ReportService;



@Controller
public class InsuranceController {
	
	@Autowired
	private ReportService service;
	
	
	
	
	@GetMapping("/")
	public String indexPage(Model model) {
		
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	
	}
	private void init(Model model) {
		
		
		model.addAttribute("names",service.getPlanNames() );
		model.addAttribute("status",service.getPlanStatuses());
	}
	
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest request,Model model) {
		System.out.println(request);
		List<IData> plans = service.search(request);
		model.addAttribute("plans",plans);
		init(model);
		return "index";
		
	}
	@GetMapping("/exportExcel")
	public void exportExcelFile(HttpServletResponse response) throws IOException {
		
		service.exportExcel(response);
		
		
	}
	
	@GetMapping("/exportPdf")
	public void exportPdf(HttpServletResponse response) throws IOException{
		service.exportPdf(response);
		
		
		
	}
	
		
		
	}
	
	


