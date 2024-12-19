package es.ATPRodriguez.orvWiki.service;

import java.util.List;

import es.ATPRodriguez.orvWiki.exception.ResourceNotFoundException;
import es.ATPRodriguez.orvWiki.model.Weapon;
import es.ATPRodriguez.orvWiki.repository.WeaponRepository;
import es.ATPRodriguez.orvWiki.service.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@Component
public class WeaponService implements ServiceInterface<Weapon> {

    private WeaponRepository weaponRepository;

    @Autowired
    public void setWeaponRepository(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    public List<Weapon> getAll() {
        return weaponRepository.findAll();
    }

    public Weapon getById(@PathVariable(value = "id") int weaponId) throws ResourceNotFoundException {
        return weaponRepository.findById(weaponId)
                .orElseThrow(() -> new ResourceNotFoundException("Weapon not found for this id :: " + weaponId));
    }

    public Weapon create(@Valid @RequestBody Weapon weapon) {
        return weaponRepository.save(weapon);
    }

    public Weapon update(@PathVariable(value = "id") int weaponId,
                              @Valid @RequestBody Weapon weaponDetails) throws ResourceNotFoundException {
        Weapon weapon = weaponRepository.findById(weaponId)
                .orElseThrow(() -> new ResourceNotFoundException("Weapon not found for this id :: " + weaponId));

        weapon.setName(weaponDetails.getName());
        weapon.setGrade(weaponDetails.getGrade());
        weapon.setLore(weaponDetails.getLore());
        weapon.setEffects(weaponDetails.getEffects());
        return weaponRepository.save(weapon);
    }

    public void delete(@PathVariable(value = "id") int weaponId) throws ResourceNotFoundException {
        Weapon weapon = weaponRepository.findById(weaponId)
                .orElseThrow(() -> new ResourceNotFoundException("Weapon not found for this id :: " + weaponId));

        weaponRepository.delete(weapon);
    }

}