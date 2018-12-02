package hust.soict.distribuitedSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import hust.soict.distribuitedSystem.entities.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	@Query("SELECT a FROM User a WHERE a.username=:username and a.password=:password")
    List<User> fetchUserBy(@Param("username") String username,
    						@Param("password") String password);
	@Query("SELECT a FROM User a where a.username=:username")
	List<User> getByUsername(@Param("username") String username);
}
