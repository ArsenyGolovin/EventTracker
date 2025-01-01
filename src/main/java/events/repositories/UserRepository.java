package events.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import events.dataClasses.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	Optional<User> findByName(String name);
}
