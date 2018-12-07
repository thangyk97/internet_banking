package soict.distribuitedSystem.repositoriesA;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import soict.distribuitedSystem.entities.Deposit;

@Repository
public interface DepositRepositoryA extends CrudRepository<Deposit, Integer> {
	
	@Query("SELECT a FROM Deposit a WHERE a.closeDate=:closeDate")
	List<Deposit> getByDate(@Param("closeDate") String closeDate);
}
