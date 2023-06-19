package in.vasanth.loader;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.vasanth.model.IData;
import in.vasanth.repository.InsuranceRepository;
@Component
public class AppRunner implements ApplicationRunner {
	@Autowired
	private InsuranceRepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
		repo.deleteAll();
		IData c1=new IData();
		//cash plan
		c1.setCitizenName("joseph");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmount(5000.00);
		
		IData c2=new IData();
		c2.setCitizenName("Stalin");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		c2.setDenialReason("Rental Income");
		
		IData c3=new IData();
		c3.setCitizenName("Stewart");
		c3.setGender("Fe-Male");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setTerminatedDate(LocalDate.now());
		c3.setTerminatedReason("Employed");
		
		
		IData c4=new IData();
		//Food plan
		c4.setCitizenName("Anand");
		c4.setGender("Male");
		c4.setPlanName("Food");
		c4.setPlanStatus("Approved");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setBenefitAmount(4000.00);
		
		IData c5=new IData();
		c5.setCitizenName("Robert");
		c5.setGender("Male");
		c5.setPlanName("Food");
		c5.setPlanStatus("Denied");
		c5.setDenialReason("Salaried person");
		
		IData c6=new IData();
		c6.setCitizenName("Angel");
		c6.setGender("Fe-Male");
		c6.setPlanName("Food");
		c6.setPlanStatus("Terminated");
		c6.setPlanStartDate(LocalDate.now().minusMonths(4));
		c6.setPlanEndDate(LocalDate.now().plusMonths(6));
		c6.setTerminatedDate(LocalDate.now());
		c6.setTerminatedReason("Improved income");
		
		

		IData c7=new IData();
		//Medical plan
		c7.setCitizenName("Charles");
		c7.setGender("Male");
		c7.setPlanName("Medical");
		c7.setPlanStatus("Approved");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setBenefitAmount(6000.00);
		
		IData c8=new IData();
		c8.setCitizenName("Thor");
		c8.setGender("Male");
		c8.setPlanName("Medical");
		c8.setPlanStatus("Denied");
		c8.setDenialReason("Salaried person");
		
		IData c9=new IData();
		c9.setCitizenName("Sarah");
		c9.setGender("Fe-Male");
		c9.setPlanName("Medical");
		c9.setPlanStatus("Terminated");
		c9.setPlanStartDate(LocalDate.now().minusMonths(4));
		c9.setPlanEndDate(LocalDate.now().plusMonths(6));
		c9.setTerminatedDate(LocalDate.now());
		c9.setTerminatedReason("Improved income");
		
		IData c10=new IData();
		//Employment plan
		c10.setCitizenName("Smith");
		c10.setGender("Male");
		c10.setPlanName("Employment");
		c10.setPlanStatus("Approved");
		c10.setPlanStartDate(LocalDate.now());
		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
		c10.setBenefitAmount(6000.00);
		
		IData c11=new IData();
		c11.setCitizenName("Johnson");
		c11.setGender("Male");
		c11.setPlanName("Employment");
		c11.setPlanStatus("Denied");
		c11.setDenialReason("Salaried person");
		
		IData c12=new IData();
		c12.setCitizenName("Julie");
		c12.setGender("Fe-Male");
		c12.setPlanName("Employment");
		c12.setPlanStatus("Terminated");
		c12.setPlanStartDate(LocalDate.now().minusMonths(4));
		c12.setPlanEndDate(LocalDate.now().plusMonths(6));
		c12.setTerminatedDate(LocalDate.now());
		c12.setTerminatedReason("Improved income");
		
		
		List<IData> list=Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
		
		repo.saveAll(list);
	}

}
