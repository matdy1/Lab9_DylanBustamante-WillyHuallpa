package com.example.lab9.controller;

import com.example.lab9.entity.Equipo;
import com.example.lab9.repository.EquipoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/equipo")
public class EquipoController {

    final EquipoRepository equipoRepository;

    public EquipoController(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @PostMapping("/registro")
    public ResponseEntity<HashMap<String, Object>> registrarEquipo(
            @RequestBody Equipo equipo,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        equipoRepository.save(equipo);
        if (fetchId) {
            responseJson.put("id", equipo.getId());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

}
