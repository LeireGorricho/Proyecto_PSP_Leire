package clases;

public class Transferencia {
    //Atributos
    private String remitente;
    private String destinatario;
    private String concepto;
    private double cantidad;
    private String fecha;

    //Constructores
    public Transferencia(String remitente, String destinatario, String concepto, double cantidad, String fecha) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Transferencia(String remitente, String destinatario, String concepto, double cantidad) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.concepto = concepto;
        this.cantidad = cantidad;
    }


    //Getters y Setters
    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
