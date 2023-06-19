package in.vasanth.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.vasanth.model.IData;
import in.vasanth.search.SearchRequest;

public interface ReportService {
	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<IData> search(SearchRequest request);
	
	public List<IData> getCitizenList();
	
	public boolean exportExcel(HttpServletResponse response) throws IOException;
	
	public boolean exportPdf(HttpServletResponse response) throws IOException;
	
	

}
