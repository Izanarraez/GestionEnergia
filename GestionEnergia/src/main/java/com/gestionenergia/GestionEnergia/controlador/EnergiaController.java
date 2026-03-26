package com.gestionenergia.GestionEnergia.controlador;

import com.gestionenergia.GestionEnergia.modelo.RegistroEnergia;
import com.gestionenergia.GestionEnergia.repositorio.EnergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/energia")
public class EnergiaController {

    @Autowired
    private EnergiaRepository repository;

    @PostMapping("/guardar")
    public RegistroEnergia guardarDatos(@RequestBody RegistroEnergia datos) {
        return repository.save(datos); 
    }
}
