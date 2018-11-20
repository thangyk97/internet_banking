package hust.soict.distribuitedSystem.repositories;

import org.springframework.data.repository.CrudRepository;

import hust.soict.distribuitedSystem.entities.Account;

public interface UserRepository extends CrudRepository<Account, Integer> {

}
