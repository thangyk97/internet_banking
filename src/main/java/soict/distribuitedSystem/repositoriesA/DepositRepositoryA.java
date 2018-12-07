package soict.distribuitedSystem.repositoriesA;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import soict.distribuitedSystem.entities.Deposit;

@Repository
public interface DepositRepositoryA extends CrudRepository<Deposit, Integer> {

}
