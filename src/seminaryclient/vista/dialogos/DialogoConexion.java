/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryclient.vista.dialogos;


import javax.swing.JOptionPane;
import seminaryclient.logica.modelo.ControlConexion;
import seminaryclient.logica.util.Notificador;
import seminaryclient.logica.util.UpdateConstant;

/**
 *
 * @author Ariel Arnedo
 */
public class DialogoConexion extends MainDialog {

    /**
     * Creates new form DialogoConexion
     */
    public DialogoConexion() {
        super();
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoIPServer = createTextField();
        campoPuertoServer = new javax.swing.JTextField();
        botonAceptar = createButtonSecondary();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(getBackground());
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        campoIPServer.setForeground(new java.awt.Color(102, 102, 102));
        campoIPServer.setText(/*"Direccion IP de Servidor"*/"127.0.0.1");
        campoIPServer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        campoIPServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoIPServerMouseClicked(evt);
            }
        });

        campoPuertoServer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoPuertoServer.setForeground(new java.awt.Color(102, 102, 102));
        campoPuertoServer.setText(/*"Puerto de Servidor"*/"8888");
        campoPuertoServer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        campoPuertoServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoPuertoServerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(campoIPServer, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(campoPuertoServer, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoPuertoServer, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(campoIPServer))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonAceptar.setText("Aceptar");
        getRootPane().setDefaultButton(botonAceptar);
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoIPServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campoIPServerMouseClicked
        fieldClicked(evt);
    }//GEN-LAST:event_campoIPServerMouseClicked

    private void campoPuertoServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campoPuertoServerMouseClicked
        fieldClicked(evt);
    }//GEN-LAST:event_campoPuertoServerMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        botonAceptar.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        if(validarCampos().length() != 0){
            JOptionPane.showMessageDialog(rootPane, validarCampos().toString() ,"Advertenca",JOptionPane.ERROR_MESSAGE);
            return;
        }
        Notificador.getNotificador().notificar(UpdateConstant.START_WAITING);
        ControlConexion.getInstace().createHostCliente(campoIPServer.getText(), Integer.parseInt(campoPuertoServer.getText()));
        ControlConexion.getInstace().conectarConServidor();
        doClose();
    }//GEN-LAST:event_botonAceptarActionPerformed

    

    private void fieldClicked(java.awt.event.MouseEvent evt){
        javax.swing.JTextField campo = (javax.swing.JTextField) evt.getSource();
        if(campo.equals(campoIPServer)){
            if(!confirmarIP(campoIPServer.getText())){
                campoIPServer.setText("");
            }
        }else if(campo.equals(campoPuertoServer)){
            try{
                Integer.parseInt(campoPuertoServer.getText());
            }catch(NullPointerException ex){}catch(NumberFormatException ex){
                campoPuertoServer.setText("");
            }
        }
    }

    private StringBuilder validarCampos(){
        StringBuilder sb = new StringBuilder();
        if(!confirmarIP(campoIPServer.getText())){
            sb.append("Direccion IP de Servidor es nula o no es valida");
            sb.append("\n");
        }
        if(campoPuertoServer.getText().compareTo("") == 0){
            sb.append("Numero de puerto de servidor no debe estar vacío");
        }else{
            try{
                Integer.parseInt(campoPuertoServer.getText());
            }catch(NullPointerException ex){}catch(NumberFormatException ex){
                sb.append("El formato del numero de puerto debe ser numerico");
                sb.append("\n");
            }
        }
        return sb;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JTextField campoIPServer;
    private javax.swing.JTextField campoPuertoServer;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}