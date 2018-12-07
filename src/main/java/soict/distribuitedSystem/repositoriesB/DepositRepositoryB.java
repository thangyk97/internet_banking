package soict.distribuitedSystem.repositoriesB;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import soict.distribuitedSystem.entities.Deposit;

@Repository
public interface DepositRepositoryB extends CrudRepository<Deposit, Integer> {

}
