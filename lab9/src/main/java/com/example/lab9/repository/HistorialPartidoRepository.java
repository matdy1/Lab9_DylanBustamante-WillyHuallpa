package com.example.lab9.repository;

import com.example.lab9.entity.Historialpartido;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialPartidoRepository extends JpaRepository<Historialpartido,Integer>{

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `historialpartidos` (`partido_idpartido`, `deporte_iddeporte`, `horaFecha`) VALUES (?, ?, current_timestamp());",
            nativeQuery = true)
    void agregarPartido(int idpartido,int iddeporte);
}
