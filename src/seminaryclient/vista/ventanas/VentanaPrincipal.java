/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryclient.vista.ventanas;



import javax.swing.JOptionPane;
import seminaryclient.controlador.ConexionSocketController;
import seminaryclient.logica.modelo.ControlConexion;
import seminaryclient.logica.util.ClienteAtributo;
import seminaryclient.logica.util.Notificador;
import seminaryclient.logica.util.UpdateConstant;
import seminaryclient.vista.paneles.PanelChat;
/**
 *
 * @author Ariel Arnedo
 */
public class VentanaPrincipal extends VentanaRoot implements java.util.Observer{

    private final PanelChat PANEL_CHAT = new PanelChat();
    
    public VentanaPrincipal() {
        super("Host Cliente");
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
        botonConectar = createButtonMenu();
        botonDesconectar = createButtonMenu();
        panelWork = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(getBackground());
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        botonConectar.setText("Conectar con el Servidor");
        botonConectar.addActionListener(new ConexionSocketController());

        botonDesconectar.setText("Desconectar");
        botonDesconectar.setEnabled(false);
        botonDesconectar.addActionListener(new ConexionSocketController());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonConectar, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(botonDesconectar, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonDesconectar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelWork.setBackground(getBackground());
        panelWork.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        setPanelWorkCenter(panelWork);
        panelWork.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelWork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelWork, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Notificador.getNotificador().añadirObsevador(this);
    }//GEN-LAST:event_formWindowOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonConectar;
    private javax.swing.JButton botonDesconectar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelWork;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(java.util.Observable o, Object arg) {
        updateWindow(arg);
    }
    
    private void updateWindow(Object obj){
        if(obj instanceof Byte){
            Byte bite = new Byte(String.valueOf(obj));
            switch(bite){
                case UpdateConstant.ERROR_CONEXION:{
                    botonConectar.setEnabled(true);
                    JOptionPane.showMessageDialog(rootPane, "Servidor Inalcansable");
//                    System.err.println("ERROR_CONEXION");
                    break;
                }
                case UpdateConstant.CONEXION:{
                    botonConectar.setText("Conectado");
                    botonConectar.setEnabled(false);
                    botonDesconectar.setEnabled(true);
                    changePanelWork(PANEL_CHAT);
                    break;
                }
                case UpdateConstant.DESCONEXION:{
                    System.out.println("UPDATE_DESCONEXION");
                    break;
                }
                //empieza 
                case UpdateConstant.START_WAITING:{
                    botonConectar.setEnabled(false);
                    waitingPanel();
                    break;
                }
                case UpdateConstant.STOP_WAITING:{
                    stoppingWaitPanel();
                    break;
                }
                case UpdateConstant.BLOQUEADO:{
                    setEnableWindow(false);
                    ControlConexion.getInstace().enviarMensaje(ClienteAtributo.MENSAJE_DE_BLOQUEO, "BLOQUEAR");
                    break;
                }
                case UpdateConstant.DESBLOQUEADO:{
                    setEnableWindow(true);
                    ControlConexion.getInstace().enviarMensaje(ClienteAtributo.MENSAJE_DE_BLOQUEO, "DESBLOQUEADO");//notifica al server que la pantalla
                    //esta bloqueado
                    break;
                }
                default:{
                    throw new RuntimeException("Opcion no programada");
                }
            }
        }
    }
    
}