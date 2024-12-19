package es.ATPRodriguez.orvWiki.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ATPRodriguez.orvWiki.exception.ResourceNotFoundException;
import es.ATPRodriguez.orvWiki.model.Constellation;
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
public class ConstellationController {

    private ServiceInterface<Constellation> constellationService;

    @Autowired
    public void setConstellationService(ServiceInterface<Constellation> constellationService) {
        this.constellationService = constellationService;
    }


    @Operation(summary = "Get all constellations")
    @GetMapping("/constellations/")
    public List<Constellation> getAllConstellations() {
        return constellationService.getAll();
    }

    @Operation(summary = "Get constellation by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Constellation not found")
    })
    @GetMapping("/constellation/{id}")
    public ResponseEntity<Constellation> getConstellationById(@PathVariable(value = "id") int constellationId) throws ResourceNotFoundException {
        Constellation constellation = constellationService.getById(constellationId);
        return ResponseEntity.ok().body(constellation);
    }

    @Operation(summary = "Insert constellation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Constellation created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/constellation/")
    public Constellation createConstellation(@Valid @RequestBody Constellation constellation) {
        return constellationService.create(constellation);
    }

    @Operation(summary = "Update constellation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Constellation updated successfully"),
            @ApiResponse(responseCode = "404", description = "Constellation not found")
    })
    @PutMapping("/update/constellation/{id}")
    public ResponseEntity<Constellation> updateConstellation(@PathVariable(value = "id") int constellationId,
                                           @Valid @RequestBody Constellation constellationDetails) throws ResourceNotFoundException {
        final Constellation updatedConstellation = constellationService.update(constellationId, constellationDetails);
        return ResponseEntity.ok(updatedConstellation);
    }

    @Operation(summary = "Delete constellation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Constellation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Constellation not found")
    })
    @DeleteMapping("/delete/constellation/{id}")
    public Map<String, Boolean> deleteConstellation(@PathVariable(value = "id") int constellationId) throws ResourceNotFoundException {
        constellationService.delete(constellationId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}