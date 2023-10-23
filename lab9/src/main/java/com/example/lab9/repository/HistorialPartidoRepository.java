package com.example.lab9.repository;

import com.example.lab9.entity.Historialpartido;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialPartidoRepository extends JpaRepository<Historialpartido,Integer>{

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `historialpartidos` (`partido_idpartido`, `deporte_iddeporte`, `horaFecha`) VALUES (?, ?, current_timestamp());",
            nativeQuery = true)
    void agregarPartido(int idpartido,int iddeporte);


    @Query(value = "SELECT hp.*, d.nombreDeporte, e1.nombreEquipo AS equipoA, e2.nombreEquipo AS equipoB,e1.idequipo,e2.idequipo\n" +
            "FROM historialpartidos hp\n" +
            "INNER JOIN deporte d ON hp.deporte_iddeporte = d.iddeporte\n" +
            "INNER JOIN partido p ON hp.partido_idpartido = p.idpartido\n" +
            "INNER JOIN equipo e1 ON p.equipoA = e1.idequipo\n" +
            "INNER JOIN equipo e2 ON p.equipoB = e2.idequipo\n" +
            "where e2.idequipo=? or e1.idequipo=? ",
            nativeQuery = true)
    List<Historialpartido> obtenerHistorial(int id,int id1);
}
