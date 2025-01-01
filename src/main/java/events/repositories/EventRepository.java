package events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import events.dataClasses.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
	
	List<Event> findEventsByCreatorId(int creatorId);
	
	//@Query("SELECT * FROM Clients RIGHT JOIN (SELECT client_id FROM Entries WHERE event_id = :eventId) ON id = client_id")
	//public List<User> findUsersByEventId(int eventId);
}