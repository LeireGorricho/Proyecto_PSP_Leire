/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package swing;

import javax.crypto.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author leiii
 */
public class CodigoSeguridad extends javax.swing.JPanel {

    ObjectOutputStream oos;
    ObjectInputStream ois;
    SecretKey key;
    JPanel panel;
    
    /**
     * Creates new form codigoseguridad
     */
    public CodigoSeguridad(JPanel panel, ObjectInputStream ois, ObjectOutputStream oos, SecretKey key) {
        initComponents();
        this.ois = ois;
        this.oos = oos;
        this.panel = panel;
        this.key = key;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        botonEnviarCodigo = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 22)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ENVIAR CÓDIGO DE SEGURIDAD");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 560, 40));

        botonEnviarCodigo.setBackground(new java.awt.Color(153, 0, 102));
        botonEnviarCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonEnviarCodigoMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Enviar");

        javax.swing.GroupLayout botonEnviarCodigoLayout = new javax.swing.GroupLayout(botonEnviarCodigo);
        botonEnviarCodigo.setLayout(botonEnviarCodigoLayout);
        botonEnviarCodigoLayout.setHorizontalGroup(
            botonEnviarCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        botonEnviarCodigoLayout.setVerticalGroup(
            botonEnviarCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonEnviarCodigoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(botonEnviarCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 120, 40));

        codigo.setBorder(null);
        add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 200, -1));

        jLabel2.setText("Código:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, -1, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 200, 10));
    }// </editor-fold>//GEN-END:initComponents

    private void botonEnviarCodigoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEnviarCodigoMousePressed
        try {
            //comprovamos que nos pase un codigo
            if (!codigo.getText().isBlank()) {
                //enviamos el codigo
                Cipher desCipher = Cipher.getInstance("DES");
                desCipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] codigoCifrado = desCipher.doFinal(codigo.getText().getBytes());
                oos.writeObject(codigoCifrado);
                //recogemos la respuesta de si el codigo es correcto
                if ((boolean) ois.readObject()) {
                    JOptionPane.showMessageDialog(null, "Numero correcto, realizando transferencia...");
                } else {
                    JOptionPane.showMessageDialog(null, "Numero incorrecto, operación cancelada");
                }
                NuevaTransferencia frame = new NuevaTransferencia(panel, key, ois, oos);
                frame.setSize(560, 450);
                frame.setLocation(0, 0);
                panel.removeAll();
                panel.add(frame, BorderLayout.CENTER);
                panel.revalidate();
                panel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Debes escribir el código de seguridad");
            }
        } catch (NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha surgiso un inesperado");
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(null, "No se ha especificado el algoritmo");
        } catch (InvalidKeyException ex) {
            JOptionPane.showMessageDialog(null, "La llave usada no es correcta");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar la clase para el casteo");
        }
    }//GEN-LAST:event_botonEnviarCodigoMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botonEnviarCodigo;
    private javax.swing.JTextField codigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
