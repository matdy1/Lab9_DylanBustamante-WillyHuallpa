package com.example.lab9.controller;

import com.example.lab9.RegistroDeporteRequest;
import com.example.lab9.entity.Historialpartido;
import com.example.lab9.repository.HistorialPartidoRepository;
import com.example.lab9.repository.PartidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<HashMap<String, Object>> registrarPartido(
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

    @GetMapping("/gethistorialpartidos")
    public List<Historialpartido> listaHistorialPartidos(){
        return historialPartidoRepository.findAll();
    }

    @GetMapping(value = "/gethistorialpartidos/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarProducto(@PathVariable("id") String idStr) {

        HashMap<String, Object> respuesta = new HashMap<>();
        try {
            int id = Integer.parseInt(idStr);
            List<Historialpartido> list = historialPartidoRepository.obtenerHistorial(id,id);



            if (!list.isEmpty()) {
                respuesta.put("result", "ok");
                respuesta.put("producto", list);
            } else {
                respuesta.put("result", "este equipo no ha sido registrado");
            }
            return ResponseEntity.ok(respuesta);
        } catch (NumberFormatException e) {
            respuesta.put("error", "tiene que ser un numero entero");
            return ResponseEntity.badRequest().body(respuesta);
        }
    }

}
