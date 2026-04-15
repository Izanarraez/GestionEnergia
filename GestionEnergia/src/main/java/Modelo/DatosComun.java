package Modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase base que encapsula los datos identificativos comunes a todas las
 * entidades de gestión de energía (nombre del equipo/compañía y la fecha del registro).
 */
public class DatosComun {

    private String nombre;
    private String fecha;

    /**
     * Constructor con nombre y fecha específica.
     *
     * @param nombre Nombre identificativo de la entidad.
     * @param fecha Fecha asociada al registro en formato dd/MM/yyyy.
     */
    public DatosComun(String nombre, String fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    /**
     * Constructor con nombre que asigna automáticamente la fecha actual del sistema.
     *
     * @param nombre Nombre identificativo de la entidad.
     */
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
