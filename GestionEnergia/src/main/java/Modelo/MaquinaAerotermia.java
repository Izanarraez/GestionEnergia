package Modelo;

import java.lang.reflect.Field;

/**
 * Representa el consumo energético de la Máquina de Aerotermia, desglosado
 * por usos (agua caliente y calefacción). Hereda de {@link DatosComun}.
 */
public class MaquinaAerotermia extends DatosComun{

    private double kwhTotal;
    private double AguaKwh;
    private double kalefKw;
    private double euros;

    /**
     * Constructor con fecha explícita para la máquina de aerotermia.
     *
     * @param nombre Nombre de la máquina.
     * @param fecha Fecha del registro.
     * @param kwhTotal Consumo eléctrico total de la máquina (kWh).
     * @param kalefKw Consumo eléctrico destinado a calefacción (kWh).
     * @param euros Coste estimado asociado al consumo de la máquina.
     */
    public MaquinaAerotermia(String nombre, String fecha, double kwhTotal, double kalefKw, double euros) {
        super(nombre, fecha);
        this.kwhTotal = kwhTotal;
        this.kalefKw = kalefKw;
        this.euros = euros;
    }

    /**
     * Constructor con fecha automática (día actual) para la máquina de aerotermia.
     *
     * @param nombre Nombre de la máquina.
     * @param kwhTotal Consumo eléctrico total de la máquina (kWh).
     * @param kalefKw Consumo eléctrico destinado a calefacción (kWh).
     * @param euros Coste estimado asociado al consumo de la máquina.
     */
    public MaquinaAerotermia(String nombre, double kwhTotal, double kalefKw, double euros) {
        super(nombre);
        this.kwhTotal = kwhTotal;
        this.kalefKw = kalefKw;
        this.euros = euros;
    }

    public double getAguaKwh() {
        return AguaKwh;
    }

    public void setAguaKwh(double aguaKwh) {
        AguaKwh = aguaKwh;
    }

    public double getKwhTotal() {
        return kwhTotal;
    }

    public void setKwhTotal(double kwhTotal) {
        this.kwhTotal = kwhTotal;
    }

    public double getKalefKw() {
        return kalefKw;
    }

    public void setKalefKw(double kalefKw) {
        this.kalefKw = kalefKw;
    }

    public double getEuros() {
        return euros;
    }

    public void setEuros(double euros) {
        this.euros = euros;
    }

}
