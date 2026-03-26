package com.gestionenergia.gestionenergia.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "registros_energia")
public class RegistroEnergia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aerotermia
    private Double aeroKwhTotal;
    private Double aeroAguaKw;
    private Double aeroCalefaccionKw;
    private Double aeroEuros;

    // Inversor
    private Double invVertida;
    private Double invConsumoRed;
    private Double invConsumoTotal;
    private Double invSolar;

    // Compañía Eléctrica
    private Double elecPunta;
    private Double elecLlano;
    private Double elecValle;
    private Double elecEuros;
    private Double elecVertida;
    private Double elecKwhTotal;

    private LocalDate fechaRegistro;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getAeroKwhTotal() { return aeroKwhTotal; }
    public void setAeroKwhTotal(Double aeroKwhTotal) { this.aeroKwhTotal = aeroKwhTotal; }

    public Double getAeroAguaKw() { return aeroAguaKw; }
    public void setAeroAguaKw(Double aeroAguaKw) { this.aeroAguaKw = aeroAguaKw; }

    public Double getAeroCalefaccionKw() { return aeroCalefaccionKw; }
    public void setAeroCalefaccionKw(Double aeroCalefaccionKw) { this.aeroCalefaccionKw = aeroCalefaccionKw; }

    public Double getAeroEuros() { return aeroEuros; }
    public void setAeroEuros(Double aeroEuros) { this.aeroEuros = aeroEuros; }

    public Double getInvVertida() { return invVertida; }
    public void setInvVertida(Double invVertida) { this.invVertida = invVertida; }

    public Double getInvConsumoRed() { return invConsumoRed; }
    public void setInvConsumoRed(Double invConsumoRed) { this.invConsumoRed = invConsumoRed; }

    public Double getInvConsumoTotal() { return invConsumoTotal; }
    public void setInvConsumoTotal(Double invConsumoTotal) { this.invConsumoTotal = invConsumoTotal; }

    public Double getInvSolar() { return invSolar; }
    public void setInvSolar(Double invSolar) { this.invSolar = invSolar; }

    public Double getElecPunta() { return elecPunta; }
    public void setElecPunta(Double elecPunta) { this.elecPunta = elecPunta; }

    public Double getElecLlano() { return elecLlano; }
    public void setElecLlano(Double elecLlano) { this.elecLlano = elecLlano; }

    public Double getElecValle() { return elecValle; }
    public void setElecValle(Double elecValle) { this.elecValle = elecValle; }

    public Double getElecEuros() { return elecEuros; }
    public void setElecEuros(Double elecEuros) { this.elecEuros = elecEuros; }

    public Double getElecVertida() { return elecVertida; }
    public void setElecVertida(Double elecVertida) { this.elecVertida = elecVertida; }

    public Double getElecKwhTotal() { return elecKwhTotal; }
    public void setElecKwhTotal(Double elecKwhTotal) { this.elecKwhTotal = elecKwhTotal; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
