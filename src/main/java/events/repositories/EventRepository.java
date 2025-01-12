package events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import events.dataClasses.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

	List<Event> findByCreatorId(int creatorId);
}