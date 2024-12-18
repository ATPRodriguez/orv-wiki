package es.ATPRodriguez.orvWiki.service;

import java.util.List;

import es.ATPRodriguez.orvWiki.exception.ResourceNotFoundException;
import es.ATPRodriguez.orvWiki.model.Incarnation;
import es.ATPRodriguez.orvWiki.repository.IncarnationRepository;
import es.ATPRodriguez.orvWiki.service.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@Component
public class IncarnationService implements ServiceInterface<Incarnation> {

    private IncarnationRepository incarnationRepository;

    @Autowired
    public void setIncarnationRepository(IncarnationRepository incarnationRepository) {
        this.incarnationRepository = incarnationRepository;
    }

    public List<Incarnation> getAll() {
        return incarnationRepository.findAll();
    }

    public Incarnation getById(@PathVariable(value = "id") int incarnationId) throws ResourceNotFoundException {
        return incarnationRepository.findById(incarnationId)
                .orElseThrow(() -> new ResourceNotFoundException("Incarnation not found for this id :: " + incarnationId));
    }

    public Incarnation create(@Valid @RequestBody Incarnation incarnation) {
        return incarnationRepository.save(incarnation);
    }

    public Incarnation update(@PathVariable(value = "id") int incarnationId,
                       @Valid @RequestBody Incarnation incarnationDetails) throws ResourceNotFoundException {
        Incarnation incarnation = incarnationRepository.findById(incarnationId)
                .orElseThrow(() -> new ResourceNotFoundException("Incarnation not found for this id :: " + incarnationId));

        incarnation.setName(incarnationDetails.getName());
        incarnation.setSurname(incarnationDetails.getSurname());
        incarnation.setAffiliation(incarnationDetails.getAffiliation());
        return incarnationRepository.save(incarnation);
    }

    public void delete(@PathVariable(value = "id") int incarnationId) throws ResourceNotFoundException {
        Incarnation incarnation = incarnationRepository.findById(incarnationId)
                .orElseThrow(() -> new ResourceNotFoundException("Incarnation not found for this id :: " + incarnationId));

        incarnationRepository.delete(incarnation);
    }

}