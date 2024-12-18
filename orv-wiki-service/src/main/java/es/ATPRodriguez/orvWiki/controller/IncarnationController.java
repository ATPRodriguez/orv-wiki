package es.ATPRodriguez.orvWiki.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ATPRodriguez.orvWiki.exception.ResourceNotFoundException;
import es.ATPRodriguez.orvWiki.model.Incarnation;
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
public class IncarnationController {

    private ServiceInterface<Incarnation> incarnationService;

    @Autowired
    public void setIncarnationService(ServiceInterface<Incarnation> incarnationService) {
        this.incarnationService = incarnationService;
    }


    @Operation(summary = "Get all incarnations")
    @GetMapping("/incarnations/")
    public List<Incarnation> getAllIncarnations() {
        return incarnationService.getAll();
    }

    @Operation(summary = "Get incarnation by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Incarnation not found")
    })
    @GetMapping("/incarnation/{id}")
    public ResponseEntity<Incarnation> getIncarnationById(@PathVariable(value = "id") int incarnationId) throws ResourceNotFoundException {
        Incarnation incarnation = incarnationService.getById(incarnationId);
        return ResponseEntity.ok().body(incarnation);
    }

    @Operation(summary = "Insert incarnation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incarnation created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/incarnation/")
    public Incarnation createIncarnation(@Valid @RequestBody Incarnation incarnation) {
        return incarnationService.create(incarnation);
    }

    @Operation(summary = "Update incarnation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incarnation updated successfully"),
            @ApiResponse(responseCode = "404", description = "Incarnation not found")
    })
    @PutMapping("/update/incarnation/{id}")
    public ResponseEntity<Incarnation> updateIncarnation(@PathVariable(value = "id") int incarnationId,
                                           @Valid @RequestBody Incarnation incarnationDetails) throws ResourceNotFoundException {
        final Incarnation updatedIncarnation = incarnationService.update(incarnationId, incarnationDetails);
        return ResponseEntity.ok(updatedIncarnation);
    }

    @Operation(summary = "Delete incarnation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incarnation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Incarnation not found")
    })
    @DeleteMapping("/delete/incarnation/{id}")
    public Map<String, Boolean> deleteIncarnation(@PathVariable(value = "id") int incarnationId) throws ResourceNotFoundException {
        incarnationService.delete(incarnationId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}