/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package swing;

import javax.crypto.*;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author leiii
 */
public class NuevaCuenta extends javax.swing.JPanel {

    private SecretKey key;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    
    /**
     * Creates new form NuevaCuenta
     */
    public NuevaCuenta(SecretKey key, ObjectInputStream ois, ObjectOutputStream oos) {
        initComponents();
        this.key = key;
        this.ois = ois;
        this.oos = oos;

        try {
            oos.writeObject(9);
            String usuarioCliente = (String) ois.readObject();
            titular.setText(usuarioCliente);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        jLabel2 = new javax.swing.JLabel();
        titular = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        botonLimpiar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        botonCrearCuenta = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        saldo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        numCuenta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NUEVA CUENTA");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 560, -1));

        jLabel2.setText("Titular:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, -1));

        titular.setEditable(false);
        titular.setBackground(new java.awt.Color(255, 255, 255));
        titular.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        titular.setBorder(null);
        add(titular, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 240, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 240, 10));

        botonLimpiar.setBackground(new java.awt.Color(153, 0, 102));
        botonLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonLimpiarMousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Limpiar");

        javax.swing.GroupLayout botonLimpiarLayout = new javax.swing.GroupLayout(botonLimpiar);
        botonLimpiar.setLayout(botonLimpiarLayout);
        botonLimpiarLayout.setHorizontalGroup(
            botonLimpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonLimpiarLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel4)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        botonLimpiarLayout.setVerticalGroup(
            botonLimpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonLimpiarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(botonLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 120, 30));

        botonCrearCuenta.setBackground(new java.awt.Color(153, 0, 102));
        botonCrearCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonCrearCuentaMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Crear cuenta");

        javax.swing.GroupLayout botonCrearCuentaLayout = new javax.swing.GroupLayout(botonCrearCuenta);
        botonCrearCuenta.setLayout(botonCrearCuentaLayout);
        botonCrearCuentaLayout.setHorizontalGroup(
            botonCrearCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, botonCrearCuentaLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(16, 16, 16))
        );
        botonCrearCuentaLayout.setVerticalGroup(
            botonCrearCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonCrearCuentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(botonCrearCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 120, 30));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, 50, 10));

        saldo.setEditable(false);
        saldo.setBackground(new java.awt.Color(255, 255, 255));
        saldo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        saldo.setText("0 €");
        saldo.setBorder(null);
        add(saldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 50, -1));

        jLabel6.setText("Saldo:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, -1, -1));
        add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 240, 10));

        numCuenta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        numCuenta.setBorder(null);
        add(numCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 240, -1));

        jLabel10.setText("Número de cuenta:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void botonLimpiarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonLimpiarMousePressed
        numCuenta.setText("");
    }//GEN-LAST:event_botonLimpiarMousePressed

    private void botonCrearCuentaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCrearCuentaMousePressed
        try {
            //configuranmos el pattern de la cuenta y comprovamos los campos
            Pattern pattern  = Pattern.compile("^([A-Za-z]{2}[0-9]{2} [0-9]{4} [0-9]{2} [0-9]{10})$");
            Matcher ncuenta = pattern.matcher(numCuenta.getText());
            if (!numCuenta.getText().isBlank() && ncuenta.find()) {
                //mandamos la opcion que hemos elegido
                oos.writeObject(5);
                Cipher desCipher = Cipher.getInstance("DES");
                desCipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] nencriptado = desCipher.doFinal(numCuenta.getText().getBytes());
                //mandamos la cuenta que queremos añadir y recuperamos la comprovacion
                oos.writeObject(nencriptado);
                if ((boolean) ois.readObject()) {
                    JOptionPane.showMessageDialog(null, "La cuenta se ha creado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Ya existe una cuenta con este número de cuenta");
                    numCuenta.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El número de cuenta está mal escrito o vacio");
            }
        } catch (IOException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException ex) {
            JOptionPane.showMessageDialog(null, "Ha surgido un error inesperado.");
        } catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(null, "No se ha especificado el algoritmo");
        } catch (InvalidKeyException ex) {
            JOptionPane.showMessageDialog(null, "La llave no es correcta");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se han podido cargar los datos");
        }
    }//GEN-LAST:event_botonCrearCuentaMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botonCrearCuenta;
    private javax.swing.JPanel botonLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField numCuenta;
    private javax.swing.JTextField saldo;
    private javax.swing.JTextField titular;
    // End of variables declaration//GEN-END:variables
}
