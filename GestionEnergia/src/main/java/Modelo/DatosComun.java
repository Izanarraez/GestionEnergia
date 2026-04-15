package Modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatosComun {

    private String nombre;
    private String fecha;

    public DatosComun(String nombre, String fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public DatosComun(String nombre) {
        this.nombre = nombre;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fecha = LocalDate.now().format(formato);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }


}
