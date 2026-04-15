package Modelo;

/**
 * Representa los datos de facturación y consumo eléctrico asociados a una Compañía Eléctrica.
 * Hereda de {@link DatosComun}.
 */
public class CompaniaElectrica extends DatosComun {

    private double punta;
    private double llano;
    private double valle;
    private double euros;
    private double vertida;
    private double kwhTotal;

    /**
     * Constructor con fecha explícita para registrar el consumo en la compañía eléctrica.
     *
     * @param nombre Nombre de la compañía eléctrica.
     * @param fecha Fecha del registro.
     * @param punta Consumo en periodo punta (kWh).
     * @param llano Consumo en periodo llano (kWh).
     * @param valle Consumo en periodo valle (kWh).
     * @param vertida Energía vertida a la red (kWh).
     * @param kwhTotal Total de kWh contabilizados.
     * @param variables Objeto con los precios y tarifas para calcular el coste en euros.
     */
    public CompaniaElectrica(String nombre, String fecha, double punta, double llano, double valle, double vertida, double kwhTotal, Variables variables) {
        super(nombre, fecha);
        this.punta = punta;
        this.llano = llano;
        this.valle = valle;
        this.euros = calculoDeEuros(variables);
        this.vertida = vertida;
        this.kwhTotal = kwhTotal;
    }

    /**
     * Constructor con fecha automática (día actual) para registrar el consumo en la compañía eléctrica.
     *
     * @param nombre Nombre de la compañía eléctrica.
     * @param punta Consumo en periodo punta (kWh).
     * @param llano Consumo en periodo llano (kWh).
     * @param valle Consumo en periodo valle (kWh).
     * @param vertida Energía vertida a la red (kWh).
     * @param kwhTotal Total de kWh contabilizados.
     * @param variables Objeto con los precios y tarifas para calcular el coste en euros.
     */
    public CompaniaElectrica(String nombre, double punta, double llano, double valle, double vertida, double kwhTotal, Variables variables) {
        super(nombre);
        this.punta = punta;
        this.llano = llano;
        this.valle = valle;
        this.euros = calculoDeEuros(variables);
        this.vertida = vertida;
        this.kwhTotal = kwhTotal;
    }

    public double getPunta() {
        return punta;
    }

    public void setPunta(double punta) {
        this.punta = punta;
    }

    public double getLlano() {
        return llano;
    }

    public void setLlano(double llano) {
        this.llano = llano;
    }

    public double getValle() {
        return valle;
    }

    public void setValle(double valle) {
        this.valle = valle;
    }

    public double getEuros() {
        return euros;
    }

    public void setEuros(double euros) {
        this.euros = euros;
    }

    public double getVertida() {
        return vertida;
    }

    public void setVertida(double vertida) {
        this.vertida = vertida;
    }

    public double getKwhTotal() {
        return kwhTotal;
    }

    public void setKwhTotal(double kwhTotal) {
        this.kwhTotal = kwhTotal;
    }

    /**
     * Calcula el coste total en euros del consumo eléctrico aplicando los precios
     * de los periodos, la potencia contratada, el descuento por excedentes y el IVA.
     *
     * @param variables Objeto que contiene las tarifas y el IVA actuales.
     * @return El importe total calculado en euros.
     */
    private double calculoDeEuros(Variables variables) {
        return ((((this.punta*variables.getEurosPunta())+(this.valle*variables.getEurosValle())
                +(this.llano*variables.getEurosLlano()))+variables.totalPotencia())-
                (this.vertida*variables.getEurosExcedentes()))*variables.getIva();
    }
}
