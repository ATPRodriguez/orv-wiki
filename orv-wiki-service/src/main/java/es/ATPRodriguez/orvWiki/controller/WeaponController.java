package es.ATPRodriguez.orvWiki.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ATPRodriguez.orvWiki.exception.ResourceNotFoundException;
import es.ATPRodriguez.orvWiki.model.Weapon;
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
public class WeaponController {

    private ServiceInterface<Weapon> weaponService;

    @Autowired
    public void setWeaponRepository(ServiceInterface weaponService) {
        this.weaponService = weaponService;
    }


    @Operation(summary = "Get all weapons")
    @GetMapping("/weapons/")
    public List<Weapon> getAllWeapons() {
        return weaponService.getAll();
    }

    @Operation(summary = "Get weapon by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Weapon not found")
    })
    @GetMapping("/weapon/{id}")
    public ResponseEntity<Weapon> getWeaponById(@PathVariable(value = "id") int weaponId) throws ResourceNotFoundException {
        Weapon weapon = weaponService.getById(weaponId);
        return ResponseEntity.ok().body(weapon);
    }

    @Operation(summary = "Insert weapon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weapon created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/weapon/")
    public Weapon createWeapon(@Valid @RequestBody Weapon weapon) {
        return weaponService.create(weapon);
    }

    @Operation(summary = "Update weapon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weapon updated successfully"),
            @ApiResponse(responseCode = "404", description = "Weapon not found")
    })
    @PutMapping("/update/weapon/{id}")
    public ResponseEntity<Weapon> updateWeapon(@PathVariable(value = "id") int weaponId,
                                           @Valid @RequestBody Weapon weaponDetails) throws ResourceNotFoundException {
        final Weapon updatedWeapon = weaponService.update(weaponId, weaponDetails);
        return ResponseEntity.ok(updatedWeapon);
    }

    @Operation(summary = "Delete weapon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weapon deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Weapon not found")
    })
    @DeleteMapping("/delete/weapon/{id}")
    public Map<String, Boolean> deleteWeapon(@PathVariable(value = "id") int weaponId) throws ResourceNotFoundException {
        weaponService.delete(weaponId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}