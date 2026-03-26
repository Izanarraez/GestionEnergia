package com.gestionenergia.GestionEnergia.modelo;

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

    // --- GETTERS Y SETTERS ---
    // (Añade aquí todos los getters y setters para cada una de las variables o usa Lombok)
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Double getAeroKwhTotal() { return aeroKwhTotal; }
    public void setAeroKwhTotal(Double aeroKwhTotal) { this.aeroKwhTotal = aeroKwhTotal; }
    
    // ... Generar el resto de Getters y Setters en tu editor de código (Click derecho -> Generate -> Getters and Setters)
}
