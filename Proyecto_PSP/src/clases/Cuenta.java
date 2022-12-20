package clases;

import java.io.Serializable;

public class Cuenta implements Serializable {
    //Atributos
    private int id;
    private String numeroCuenta;
    private int idTitular;
    private double saldo;

    //Constructores
    public Cuenta(int id, String numeroCuenta, int idTitular, double saldo) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.idTitular = idTitular;
        this.saldo = saldo;
    }

    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public int getIdTitular() {
        return idTitular;
    }

    public void setIdTitular(int idTitular) {
        this.idTitular = idTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
