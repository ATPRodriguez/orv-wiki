package es.ATPRodriguez.orvWiki.repository;

import es.ATPRodriguez.orvWiki.model.Incarnation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncarnationRepository extends JpaRepository<Incarnation, Integer> {

}
