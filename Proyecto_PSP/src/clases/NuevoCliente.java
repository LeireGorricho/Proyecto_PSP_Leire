package clases;

import java.io.Serializable;

public class NuevoCliente implements Serializable {
    //Atributos
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String usuario;
    private String contrasena;

    //Constructor
    public NuevoCliente(String nombre, String apellido, int edad, String email, String usuario, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public NuevoCliente(int id, String nombre, String apellido, int edad, String email, String usuario, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    //Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
