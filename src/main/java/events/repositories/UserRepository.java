package events.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import events.dataClasses.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByName(String name);

}
