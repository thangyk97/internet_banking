package soict.distribuitedSystem.repositoriesB;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import soict.distribuitedSystem.entities.Withdraw;

@Repository
public interface WithdrawRepositoryB extends CrudRepository<Withdraw, Integer> {

	@Query("SELECT a FROM Withdraw a WHERE a.updatedAt=:updatedAt")
	List<Withdraw> getByDate(@Param("updatedAt") String updatedAt);
}
