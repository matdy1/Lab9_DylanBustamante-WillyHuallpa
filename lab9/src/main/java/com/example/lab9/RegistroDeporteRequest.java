package com.example.lab9;

import com.example.lab9.entity.Historialpartido;
import com.example.lab9.entity.Partido;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistroDeporteRequest {

    private Partido partido;
    private Historialpartido historialpartido;

}
