package soict.distribuitedSystem.repositoriesA;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import soict.distribuitedSystem.entities.Account;

@Repository
public interface AccountRepositoryA extends CrudRepository<Account, Integer> {

}
