package mx.qbits.plank.api.model;

public class CambiaClave {
    private String usr;
    private String nuevaClave;
    private String claveAnterior;
    
    public CambiaClave() {}
    
    public String getNuevaClave() {
        return nuevaClave;
    }
    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }
    public String getClaveAnterior() {
        return claveAnterior;
    }
    public void setClaveAnterior(String claveAnterior) {
        this.claveAnterior = claveAnterior;
    }
    public String getUsr() {
        return usr;
    }
    public void setUsr(String usr) {
        this.usr = usr;
    }
}
