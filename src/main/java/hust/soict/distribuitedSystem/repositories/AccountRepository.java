package hust.soict.distribuitedSystem.repositories;

import org.springframework.data.repository.CrudRepository;

import hust.soict.distribuitedSystem.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
