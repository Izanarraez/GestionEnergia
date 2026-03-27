package Modelo;

import java.util.Date;

public class DatosComun {

    private String nombre;
    private String fecha;

    public DatosComun(String nombre, String fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public DatosComun(String nombre) {
        this.nombre = nombre;
        this.fecha = new Date().toString();
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
