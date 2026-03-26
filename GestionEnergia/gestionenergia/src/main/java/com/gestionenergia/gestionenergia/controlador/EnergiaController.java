package com.gestionenergia.gestionenergia.controlador;

import com.gestionenergia.gestionenergia.modelo.RegistroEnergia;
import com.gestionenergia.gestionenergia.repositorio.EnergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/energia")
public class EnergiaController {

    @Autowired
    private EnergiaRepository repository;

    @PostMapping("/guardar")
    public RegistroEnergia guardarDatos(@RequestBody RegistroEnergia datos) {
        return repository.save(datos); 
    }

    @GetMapping("/listar")
    public List<RegistroEnergia> listarDatos(@RequestParam("mes") int mes, @RequestParam("anio") int anio) {
        // Llama a la base de datos usando la consulta que creamos en el repositorio
        return repository.buscarPorMesYAno(mes, anio);
    }
}
