package soict.distribuitedSystem.repositoriesB;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import soict.distribuitedSystem.entities.Account;

@Repository
public interface AccountRepositoryB extends CrudRepository<Account, Integer> {

}
