/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package swing;

/**
 *
 * @author leiii
 */
public class NuevaCuenta extends javax.swing.JPanel {

    /**
     * Creates new form NuevaCuenta
     */
    public NuevaCuenta() {
        initComponents();
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
        botonCancelar = new javax.swing.JPanel();
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

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NUEVA CUENTA");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        jLabel2.setText("Titular:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, -1));

        titular.setBorder(null);
        add(titular, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 240, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 240, 10));

        botonCancelar.setBackground(new java.awt.Color(153, 0, 102));
        botonCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonCancelarMousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cancelar");

        javax.swing.GroupLayout botonCancelarLayout = new javax.swing.GroupLayout(botonCancelar);
        botonCancelar.setLayout(botonCancelarLayout);
        botonCancelarLayout.setHorizontalGroup(
            botonCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonCancelarLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        botonCancelarLayout.setVerticalGroup(
            botonCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonCancelarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(botonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 120, 30));

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
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(20, 20, 20))
        );
        botonCrearCuentaLayout.setVerticalGroup(
            botonCrearCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonCrearCuentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(botonCrearCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 120, 30));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 50, 10));

        saldo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        saldo.setText("0 €");
        saldo.setBorder(null);
        add(saldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 50, -1));

        jLabel6.setText("Saldo:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, -1, -1));
        add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 240, 10));

        numCuenta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        numCuenta.setBorder(null);
        add(numCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 240, -1));

        jLabel10.setText("Número de cuenta:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCancelarMousePressed
        //Limpiar el número de cuenta
    }//GEN-LAST:event_botonCancelarMousePressed

    private void botonCrearCuentaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCrearCuentaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonCrearCuentaMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botonCancelar;
    private javax.swing.JPanel botonCrearCuenta;
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
