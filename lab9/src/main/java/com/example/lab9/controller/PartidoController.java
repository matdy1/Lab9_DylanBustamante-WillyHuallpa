package com.example.lab9.controller;

import com.example.lab9.RegistroDeporteRequest;
import com.example.lab9.entity.Deporte;
import com.example.lab9.entity.Historialpartido;
import com.example.lab9.entity.Partido;
import com.example.lab9.repository.HistorialPartidoRepository;
import com.example.lab9.repository.PartidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/partido")
public class PartidoController {

    final HistorialPartidoRepository historialPartidoRepository;
    final PartidoRepository partidoRepository;

    public PartidoController(HistorialPartidoRepository historialPartidoRepository, PartidoRepository partidoRepository) {
        this.historialPartidoRepository = historialPartidoRepository;
        this.partidoRepository = partidoRepository;
    }

    @PostMapping("/registro")
    public ResponseEntity<HashMap<String, Object>> registrarDeporte(
            @RequestBody RegistroDeporteRequest request,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();


        partidoRepository.save(request.getPartido());
        historialPartidoRepository.agregarPartido(request.getPartido().getId(), request.getHistorialpartido().getDeporteIddeporte().getId());
        if (fetchId) {
            responseJson.put("id", request.getPartido().getId());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

}
