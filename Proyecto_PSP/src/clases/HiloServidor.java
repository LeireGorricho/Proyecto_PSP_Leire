package clases;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
            //configuramos el log
            Logger logger = Logger.getLogger("MyLog");
            FileHandler fh = new FileHandler("./src/MyLogFile.log", true);
            logger.setUseParentHandlers(false);
            SimpleFormatter formato = new SimpleFormatter();
            fh.setFormatter(formato);
            logger.setLevel(Level.ALL);
            //nos quedamos en espera de solicitudes
            while (activo) {
                Socket cliente = servidor.accept();
                HiloCliente hilo = new HiloCliente(cliente, texto, activo, logger, fh);
                hilo.start();
            }
        } catch (IOException ignored) {
        }
    }
}
