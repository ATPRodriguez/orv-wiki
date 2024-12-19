package es.ATPRodriguez.orvWiki.repository;

import es.ATPRodriguez.orvWiki.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Integer> {

}
