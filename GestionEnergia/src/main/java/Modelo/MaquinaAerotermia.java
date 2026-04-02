package Modelo;

public class MaquinaAerotermia extends DatosComun{

    private double kwhTotal;
    private double AguaKwh;
    private double kalefKw;
    private double euros;

    public MaquinaAerotermia(String nombre, String fecha, double kwhTotal, double kalefKw, double euros) {
        super(nombre, fecha);
        this.kwhTotal = kwhTotal;
        this.kalefKw = kalefKw;
        this.euros = euros;
    }

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
