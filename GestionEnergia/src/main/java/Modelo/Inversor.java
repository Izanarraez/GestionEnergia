package Modelo;

import java.lang.reflect.Field;

/**
 * Representa los datos de generación, vertido y consumo registrados por el Inversor solar.
 * Hereda de {@link DatosComun}.
 */
public class Inversor extends DatosComun {

    private double vertida;
    private double consumoReal;
    private double consumoTotal;
    private double solar;

    /**
     * Constructor con fecha explícita para el Inversor.
     *
     * @param nombre Nombre identificativo del Inversor.
     * @param fecha Fecha del registro.
     * @param vertida Energía vertida a la red eléctrica (kWh).
     * @param consumoReal Consumo real de la vivienda en ese momento (kWh).
     * @param consumoTotal Consumo total acumulado (kWh).
     * @param solar Energía generada por las placas solares (kWh).
     */
    public Inversor(String nombre, String fecha, double vertida, double consumoReal, double consumoTotal, double solar) {
        super(nombre, fecha);
        this.vertida = vertida;
        this.consumoReal = consumoReal;
        this.consumoTotal = consumoTotal;
        this.solar = solar;
    }

    /**
     * Constructor con fecha automática (día actual) para el Inversor.
     *
     * @param nombre Nombre identificativo del Inversor.
     * @param vertida Energía vertida a la red eléctrica (kWh).
     * @param consumoReal Consumo real de la vivienda en ese momento (kWh).
     * @param consumoTotal Consumo total acumulado (kWh).
     * @param solar Energía generada por las placas solares (kWh).
     */
    public Inversor(String nombre, double vertida, double consumoReal, double consumoTotal, double solar) {
        super(nombre);
        this.vertida = vertida;
        this.consumoReal = consumoReal;
        this.consumoTotal = consumoTotal;
        this.solar = solar;
    }

    public double getVertida() {
        return vertida;
    }

    public void setVertida(double vertida) {
        this.vertida = vertida;
    }

    public double getConsumoReal() {
        return consumoReal;
    }

    public void setConsumoReal(double consumoReal) {
        this.consumoReal = consumoReal;
    }

    public double getSolar() {
        return solar;
    }

    public void setSolar(double solar) {
        this.solar = solar;
    }

    public double getConsumoTotal() {
        return consumoTotal;
    }

    public void setConsumoTotal(double consumoTotal) {
        this.consumoTotal = consumoTotal;
    }

}
