package soict.distribuitedSystem.repositoriesB;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import soict.distribuitedSystem.entities.User;

@Repository
public interface UserRepositoryB extends CrudRepository<User, Integer>{
	@Query("SELECT a FROM User a WHERE a.username=:username and a.password=:password")
    List<User> fetchUserBy(@Param("username") String username,
    						@Param("password") String password);
	@Query("SELECT a FROM User a where a.username=:username")
	List<User> getByUsername(@Param("username") String username);
}
