package soict.distribuitedSystem.repositoriesB;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import soict.distribuitedSystem.entities.Withdraw;

@Repository
public interface WithdrawRepositoryB extends CrudRepository<Withdraw, Integer> {

}
