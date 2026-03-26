package com.gestionenergia.gestionenergia.repositorio;

import com.gestionenergia.gestionenergia.modelo.RegistroEnergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnergiaRepository extends JpaRepository<RegistroEnergia, Long> {

    @Query("SELECT r FROM RegistroEnergia r WHERE MONTH(r.fechaRegistro) = :mes AND YEAR(r.fechaRegistro) = :anio ORDER BY r.fechaRegistro ASC")
    List<RegistroEnergia> buscarPorMesYAno(@Param("mes") int mes, @Param("anio") int anio);
}
