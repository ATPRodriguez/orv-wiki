package es.ATPRodriguez.orvWiki.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ATPRodriguez.orvWiki.exception.ResourceNotFoundException;
import es.ATPRodriguez.orvWiki.model.Scenario;
import es.ATPRodriguez.orvWiki.service.interfaces.ServiceInterface;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ScenarioController {

    private ServiceInterface<Scenario> scenarioService;

    @Autowired
    public void setScenarioRepository(ServiceInterface scenarioService) {
        this.scenarioService = scenarioService;
    }


    @Operation(summary = "Get all scenarios")
    @GetMapping("/scenarios/")
    public List<Scenario> getAllScenarios() {
        return scenarioService.getAll();
    }

    @Operation(summary = "Get scenario by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Scenario not found")
    })
    @GetMapping("/scenario/{id}")
    public ResponseEntity<Scenario> getScenarioById(@PathVariable(value = "id") int scenarioId) throws ResourceNotFoundException {
        Scenario scenario = scenarioService.getById(scenarioId);
        return ResponseEntity.ok().body(scenario);
    }

    @Operation(summary = "Insert scenario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scenario created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/scenario/")
    public Scenario createScenario(@Valid @RequestBody Scenario scenario) {
        return scenarioService.create(scenario);
    }

    @Operation(summary = "Update scenario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scenario updated successfully"),
            @ApiResponse(responseCode = "404", description = "Scenario not found")
    })
    @PutMapping("/update/scenario/{id}")
    public ResponseEntity<Scenario> updateScenario(@PathVariable(value = "id") int scenarioId,
                                           @Valid @RequestBody Scenario scenarioDetails) throws ResourceNotFoundException {
        final Scenario updatedScenario = scenarioService.update(scenarioId, scenarioDetails);
        return ResponseEntity.ok(updatedScenario);
    }

    @Operation(summary = "Delete scenario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scenario deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Scenario not found")
    })
    @DeleteMapping("/delete/scenario/{id}")
    public Map<String, Boolean> deleteScenario(@PathVariable(value = "id") int scenarioId) throws ResourceNotFoundException {
        scenarioService.delete(scenarioId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}