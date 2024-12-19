package es.ATPRodriguez.orvWiki.repository;

import es.ATPRodriguez.orvWiki.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Integer> {

}
