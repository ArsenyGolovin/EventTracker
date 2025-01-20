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
public interface EntryRepository extends CrudRepository<Entry, Long> {

	@Query("SELECT * FROM Client JOIN (SELECT parttaker_id FROM Entry WHERE event_id = :eventId) AS E ON Client.id = E.parttaker_id")
	List<User> findParttakersByEventId(Long eventId);

	@Query("SELECT * FROM Event JOIN (SELECT event_id FROM Entry WHERE parttaker_id = :parttakerId) AS E ON Event.id = E.event_id")
	List<Event> findEventsByParttakerId(Long parttakerId);

	@Modifying
	@Query("DELETE FROM Entry WHERE parttaker_id = :parttakerId AND event_id = :eventId")
	void deleteByParttakerIdAndEventId(Long parttakerId, Long eventId);
}