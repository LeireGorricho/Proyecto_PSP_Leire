package clases;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloServidor extends Thread{
    //Atributos
    ServerSocket servidor;
    JTextArea texto;
    boolean activo;

    //Constructor
    public HiloServidor(ServerSocket servidor, JTextArea texto, boolean activo) {
        this.servidor = servidor;
        this.texto = texto;
        this.activo = activo;
    }

    @Override
    public void run() {
        try {
            //nos quedamos en espera de solicitudes
            while (activo) {
                Socket cliente = servidor.accept();
                HiloCliente hilo = new HiloCliente(cliente, texto, activo);
                hilo.start();
            }
        } catch (IOException ignored) {
        }
    }
}
