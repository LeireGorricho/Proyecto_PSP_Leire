/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package swing;

import java.awt.Color;
import java.awt.Component;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.crypto.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clases.Transferencia;
import scrollbar.ScrollBarCustom;
import table.TableHeader;

/**
 *
 * @author leiii
 */
public class InfoCuenta extends javax.swing.JPanel {
    String[] nombreColumnas = {"Remitente", "Destinatario", "Cantidad", "Fecha", "Concepto"};
    JPanel panel;
    private SecretKey key;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    /**
     * Creates new form InfoCuenta
     */
    public InfoCuenta(JPanel panel, ObjectInputStream ois, ObjectOutputStream oos, SecretKey key, String numero, String saldoCantidad) {
        initComponents();
        this.key = key;
        this.oos = oos;
        this.ois = ois;
        this.panel = panel;
        
        tablaTransferencias.setShowHorizontalLines(true);
        tablaTransferencias.setGridColor(new Color(230,230,230));
        tablaTransferencias.setRowHeight(30);
        tablaTransferencias.getTableHeader().setReorderingAllowed(true);
        tablaTransferencias.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value + "");
                if(column==nombreColumnas.length){
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
       });
       jScrollPane1.getViewport().setBackground(Color.WHITE);
       jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
       fixtable(jScrollPane1);
       numCuenta.setText(numero);
       saldo.setText(saldoCantidad);
        try {
            oos.writeObject(9);
            String usuarioCliente = (String) ois.readObject();
            titular.setText(usuarioCliente);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
       cargarDatos(numero);
    }

    public void fixtable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }
    
    public void cargarDatos(String numeroCuenta){
        try {
            //mandamos la opcion que hemos escogido
            oos.writeObject(4);
            Cipher desCipher = Cipher.getInstance("DES");
            desCipher.init(Cipher.ENCRYPT_MODE, key);
            //enviamos el número de cuanta para que recoga sus transferencias
            oos.writeObject(desCipher.doFinal(numeroCuenta.getBytes()));
            desCipher.init(Cipher.DECRYPT_MODE, key);
            //recuperamos la lista de movimientos
            byte[] movimientosCifradas = (byte[]) ois.readObject();
            byte[] movimientosBytes = desCipher.doFinal(movimientosCifradas);
            ByteArrayInputStream bis = new ByteArrayInputStream(movimientosBytes);
            ObjectInputStream oisbytes = new ObjectInputStream(bis);
            List<Transferencia> transferencias = (List<Transferencia>) oisbytes.readObject();
            bis.close();
            oisbytes.close();
            //cargamos los datos en la tabla
            int cantidad = transferencias.size();
            String[][] d = new String[cantidad][5];
            for (int i = 0; i < transferencias.size(); i++) {
                d[i][0] = String.valueOf(transferencias.get(i).getRemitente());
                d[i][1] = String.valueOf(transferencias.get(i).getDestinatario());
                d[i][2] = String.valueOf(transferencias.get(i).getCantidad());
                d[i][3] = String.valueOf(transferencias.get(i).getFecha());
                d[i][4] = String.valueOf(transferencias.get(i).getConcepto());
            }
            tablaTransferencias.setModel(new DefaultTableModel(d, nombreColumnas));
        } catch (IOException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            JOptionPane.showMessageDialog(null, "Ha surgido un error inesperado¡");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "No se ha especficado el algoritmo");
        } catch (InvalidKeyException e) {
            JOptionPane.showMessageDialog(null, "La llave no es correcta");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se han podido cargar los datos");
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
        numCuenta = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        titular = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        saldo = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTransferencias = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel1.setText("Número de cuenta:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        numCuenta.setEditable(false);
        numCuenta.setBackground(new java.awt.Color(255, 255, 255));
        numCuenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        numCuenta.setBorder(null);
        add(numCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 210, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 210, 10));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel2.setText("Titular:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        titular.setEditable(false);
        titular.setBackground(new java.awt.Color(255, 255, 255));
        titular.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        titular.setBorder(null);
        add(titular, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 210, -1));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 210, 10));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel3.setText("Saldo:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        saldo.setEditable(false);
        saldo.setBackground(new java.awt.Color(255, 255, 255));
        saldo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        saldo.setBorder(null);
        add(saldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 200, -1));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 210, 10));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText(" €");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 20, -1));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("TRANSFERENCIAS");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        tablaTransferencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaTransferencias);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 210, 510, 250));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField numCuenta;
    private javax.swing.JTextField saldo;
    private javax.swing.JTable tablaTransferencias;
    private javax.swing.JTextField titular;
    // End of variables declaration//GEN-END:variables
}
