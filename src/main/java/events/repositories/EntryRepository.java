package events.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import events.dataClasses.Entry;
import events.dataClasses.Event;
import events.dataClasses.User;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Integer> {

	@Query("SELECT * FROM CLIENTS INNER JOIN  (SELECT parttaker_id FROM ENTRIES WHERE event_id = :eventId) AS EVENTS ON CLIENTS.id = EVENTS.parttaker_id")
	List<User> findParttakersByEventId(int eventId);

	List<Event> findEventsByParttakerId(int parttakerId);

	@Modifying
	@Query("DELETE FROM ENTRIES WHERE parttaker_id = :parttakerId AND event_id = :eventId")
	void deleteByParttakerIdAndEventId(int parttakerId, int eventId);
}