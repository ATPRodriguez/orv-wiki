package es.ATPRodriguez.orvWiki.service;

import java.util.List;

import es.ATPRodriguez.orvWiki.exception.ResourceNotFoundException;
import es.ATPRodriguez.orvWiki.model.Constellation;
import es.ATPRodriguez.orvWiki.repository.ConstellationRepository;
import es.ATPRodriguez.orvWiki.service.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@Component
public class ConstellationService implements ServiceInterface<Constellation> {

    private ConstellationRepository constellationRepository;

    @Autowired
    public void setConstellationRepository(ConstellationRepository constellationRepository) {
        this.constellationRepository = constellationRepository;
    }

    public List<Constellation> getAll() {
        return constellationRepository.findAll();
    }

    public Constellation getById(@PathVariable(value = "id") int constellationId) throws ResourceNotFoundException {
        return constellationRepository.findById(constellationId)
                .orElseThrow(() -> new ResourceNotFoundException("Constellation not found for this id :: " + constellationId));
    }

    public Constellation create(@Valid @RequestBody Constellation constellation) {
        return constellationRepository.save(constellation);
    }

    public Constellation update(@PathVariable(value = "id") int constellationId,
                              @Valid @RequestBody Constellation constellationDetails) throws ResourceNotFoundException {
        Constellation constellation = constellationRepository.findById(constellationId)
                .orElseThrow(() -> new ResourceNotFoundException("Constellation not found for this id :: " + constellationId));

        constellation.setModifiers(constellationDetails.getModifiers());
        constellation.setRank(constellationDetails.getRank());
        constellation.setNebula(constellationDetails.getNebula());
        return constellationRepository.save(constellation);
    }

    public void delete(@PathVariable(value = "id") int constellationId) throws ResourceNotFoundException {
        Constellation constellation = constellationRepository.findById(constellationId)
                .orElseThrow(() -> new ResourceNotFoundException("Constellation not found for this id :: " + constellationId));

        constellationRepository.delete(constellation);
    }

}