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

	@Query("SELECT * FROM client JOIN (SELECT parttaker_id FROM entry WHERE event_id = :eventId) AS e ON client.id = e.parttaker_id")
	List<User> findParttakersByEventId(int eventId);

	@Query("SELECT * FROM event JOIN (SELECT event_id FROM entry WHERE parttaker_id = :parttakerId) AS e ON event.id = e.event_id")
	List<Event> findEventsByParttakerId(int parttakerId);

	@Modifying
	@Query("DELETE FROM entry WHERE parttaker_id = :parttakerId AND event_id = :eventId")
	void deleteByParttakerIdAndEventId(int parttakerId, int eventId);
}