package com.example.lab9.entity;

import com.example.lab9.entity.Equipo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "partido")
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpartido", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipoA", nullable = false)
    private Equipo equipoA;

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipoB", nullable = false)
    private Equipo equipoB;

    @Column(name = "scoreA", nullable = false)
    private Integer scoreA;

    @Column(name = "scoreB", nullable = false)
    private Integer scoreB;

}