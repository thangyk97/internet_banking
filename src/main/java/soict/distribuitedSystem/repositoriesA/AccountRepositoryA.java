package soict.distribuitedSystem.repositoriesA;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import soict.distribuitedSystem.entities.Account;

@Repository
public interface AccountRepositoryA extends CrudRepository<Account, Integer> {

	@Query("SELECT a FROM Account a WHERE a.openDate = :openDate")
	List<Account> getByDate(@Param("openDate") String openDate);
}
