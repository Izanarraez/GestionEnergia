package com.gestionenergia.GestionEnergia.repositorio;

import com.gestionenergia.GestionEnergia.modelo.RegistroEnergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergiaRepository extends JpaRepository<RegistroEnergia, Long> {
}
