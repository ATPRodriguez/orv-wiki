package es.ATPRodriguez.orvWiki.service;

import java.util.List;

import es.ATPRodriguez.orvWiki.exception.ResourceNotFoundException;
import es.ATPRodriguez.orvWiki.model.Scenario;
import es.ATPRodriguez.orvWiki.repository.ScenarioRepository;
import es.ATPRodriguez.orvWiki.service.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@Component
public class ScenarioService implements ServiceInterface<Scenario> {

    private ScenarioRepository scenarioRepository;

    @Autowired
    public void setScenarioRepository(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }

    public List<Scenario> getAll() {
        return scenarioRepository.findAll();
    }

    public Scenario getById(@PathVariable(value = "id") int scenarioId) throws ResourceNotFoundException {
        return scenarioRepository.findById(scenarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Scenario not found for this id :: " + scenarioId));
    }

    public Scenario create(@Valid @RequestBody Scenario scenario) {
        return scenarioRepository.save(scenario);
    }

    public Scenario update(@PathVariable(value = "id") int scenarioId,
                              @Valid @RequestBody Scenario scenarioDetails) throws ResourceNotFoundException {
        Scenario scenario = scenarioRepository.findById(scenarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Scenario not found for this id :: " + scenarioId));

        scenario.setNumber(scenarioDetails.getNumber());
        scenario.setName(scenarioDetails.getName());
        scenario.setObjective(scenarioDetails.getObjective());
        scenario.setCategory(scenarioDetails.getCategory());
        scenario.setDifficulty(scenarioDetails.getDifficulty());
        scenario.setTime(scenarioDetails.getTime());
        scenario.setReward(scenarioDetails.getReward());
        scenario.setPenalty(scenarioDetails.getPenalty());
        return scenarioRepository.save(scenario);
    }

    public void delete(@PathVariable(value = "id") int scenarioId) throws ResourceNotFoundException {
        Scenario scenario = scenarioRepository.findById(scenarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Scenario not found for this id :: " + scenarioId));

        scenarioRepository.delete(scenario);
    }

}