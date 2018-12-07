package soict.distribuitedSystem.repositoriesA;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import soict.distribuitedSystem.entities.Withdraw;

@Repository
public interface WithdrawRepositoryA extends CrudRepository<Withdraw, Integer> {

}
