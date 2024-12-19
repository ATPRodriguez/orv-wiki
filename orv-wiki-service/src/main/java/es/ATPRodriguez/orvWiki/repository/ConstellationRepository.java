package es.ATPRodriguez.orvWiki.repository;

import es.ATPRodriguez.orvWiki.model.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstellationRepository extends JpaRepository<Constellation, Integer> {

}
