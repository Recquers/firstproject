package in.vasanth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.vasanth.model.IData;

public interface InsuranceRepository extends JpaRepository<IData,Integer>{
	
	@Query("select distinct(planName) from IData")
	public List<String> getPlanNames();
	
	@Query("select distinct(planStatus) from IData")
	public List<String> getPlanStatuses();

}
