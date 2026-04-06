package Modelo;

import Variables.Variables;

public class CompaniaElectrica extends DatosComun {

    private double punta;
    private double llano;
    private double valle;
    private double euros;
    private double vertida;
    private double kwhTotal;

    public CompaniaElectrica(String nombre, String fecha, double punta, double llano, double valle, double vertida, double kwhTotal, Variables variables) {
        super(nombre, fecha);
        this.punta = punta;
        this.llano = llano;
        this.valle = valle;
        this.euros = calculoDeEuros(variables);
        this.vertida = vertida;
        this.kwhTotal = kwhTotal;
    }

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

    private double calculoDeEuros(Variables variables) {
        return ((((this.punta*variables.getEurosPunta())+(this.valle*variables.getEurosValle())
                +(this.llano*variables.getEurosLlano()))+variables.totalPotencia())-
                (this.vertida*variables.getEurosExcedentes()))*variables.getIva();
    }
}
