package Modelo;

public class Inversor extends DatosComun {

    private double vertida;
    private double consumoReal;
    private double consumoTotal;
    private double solar;

    public Inversor(String nombre, String fecha, double vertida, double consumoReal, double consumoTotal, double solar) {
        super(nombre, fecha);
        this.vertida = vertida;
        this.consumoReal = consumoReal;
        this.consumoTotal = consumoTotal;
        this.solar = solar;
    }

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
