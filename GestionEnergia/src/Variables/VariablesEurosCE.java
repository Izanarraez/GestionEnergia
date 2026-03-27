package Variables;

public class VariablesEurosCE {

    private double eurosPunta;
    private double eurosLlano;
    private double eurosValle;

    private double potenciaContratada;
    private double eurosPotenciaPunta;
    private double eurosPotenciaValle;

    private double eurosExcedentes;
    private double iva;

    public VariablesEurosCE(double eurosPunta, double eurosLlano, double eurosValle,double potenciaContratada, double eurosPotenciaPunta, double eurosPotenciaValle,double eurosExcedentes, double iva) {
        this.eurosPunta = eurosPunta;
        this.eurosLlano = eurosLlano;
        this.eurosValle = eurosValle;
        this.potenciaContratada = potenciaContratada;
        this.eurosPotenciaPunta = eurosPotenciaPunta;
        this.eurosPotenciaValle = eurosPotenciaValle;
        this.eurosExcedentes = eurosExcedentes;
        this.iva = iva;
    }

    public VariablesEurosCE(double eurosPunta, double eurosLlano, double eurosValle, double eurosPotenciaPunta, double eurosPotenciaValle,double eurosExcedentes) {
        this.eurosPunta = eurosPunta;
        this.eurosLlano = eurosLlano;
        this.eurosValle = eurosValle;
        this.potenciaContratada = potenciaContratada;
        this.eurosPotenciaPunta = eurosPotenciaPunta;
        this.eurosPotenciaValle = eurosPotenciaValle;
        this.eurosExcedentes = eurosExcedentes;
        this.iva = 0.21;
    }

    public double getEurosLlano() {
        return eurosLlano;
    }

    public void setEurosLlano(double eurosLlano) {
        this.eurosLlano = eurosLlano;
    }

    public double getEurosPunta() {
        return eurosPunta;
    }

    public void setEurosPunta(double eurosPunta) {
        this.eurosPunta = eurosPunta;
    }

    public double getEurosValle() {
        return eurosValle;
    }

    public void setEurosValle(double eurosValle) {
        this.eurosValle = eurosValle;
    }

    public double getPotenciaContratada() {
        return potenciaContratada;
    }

    public void setPotenciaContratada(double potenciaContratada) {
        this.potenciaContratada = potenciaContratada;
    }

    public double getEurosPotenciaPunta() {
        return eurosPotenciaPunta;
    }

    public void setEurosPotenciaPunta(double eurosPotenciaPunta) {
        this.eurosPotenciaPunta = eurosPotenciaPunta;
    }

    public double getEurosPotenciaValle() {
        return eurosPotenciaValle;
    }

    public void setEurosPotenciaValle(double eurosPotenciaValle) {
        this.eurosPotenciaValle = eurosPotenciaValle;
    }

    public double getEurosExcedentes() {
        return eurosExcedentes;
    }

    public void setEurosExcedentes(double eurosExcedentes) {
        this.eurosExcedentes = eurosExcedentes;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double totalPotencia(){
        return (this.potenciaContratada*1*this.eurosPotenciaPunta)+
                (this.potenciaContratada*1*this.eurosPotenciaValle);
    }
}
