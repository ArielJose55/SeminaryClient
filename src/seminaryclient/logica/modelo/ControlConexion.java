/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryclient.logica.modelo;

import java.util.logging.Level;
import java.util.logging.Logger;
import seminaryclient.vista.ventanas.VentanaRoot;

/**
 *
 * @author Ariel Arnedo
 */
public class ControlConexion {
    
    private static ControlConexion conexionControl;
    
    
    private HostCliente hostCliente;
    
    private ControlConexion(){
        
    }
    
    public static ControlConexion getInstace(){
        if(conexionControl == null){
            conexionControl = new ControlConexion();
        }
        return conexionControl;
    }
    
    //--------------------------------------------------------------------------------------------------------------------------------
    
    /**
     * crea un host de cliente 
     * 
     * @param ip direccion IP del servidor
     * @param puerto puerto del servidor
     * @throws RuntimeException se lanza si algunos de los parametros son incorrectos
     */
    public void createHostCliente(String ip, Integer puerto) throws RuntimeException{
        hostCliente = new HostCliente(ip, puerto);
    }
    
    /**
     * 
     * @throws RuntimeException se lanza si a conexion no fue exitosa
     */
    public void conectarConServidor() throws RuntimeException{
        hostCliente.conectarConServidor();
    }
    
    /**
     * envia un mensaje hacia el servidor
     * 
     * @param tipo puede ser de tipo chat, en cuyo caso se envia un mensaje de texto al servidor, o de tipo bloqueo de pantalla, y 
     * sirve para notificar al servidor del estado del cliente
     * @param mensaje el mensaje como tal.
     * @throws RuntimeException 
     */
    public void enviarMensaje(int tipo, String mensaje) throws RuntimeException{
        hostCliente.enviarMensaje(tipo, mensaje);
    }
    
    /**
     * 
     * @throws RuntimeException 
     */
    public void desconectarConServidor() throws RuntimeException{
        hostCliente.desconectarDelServidor();
    }
    
    //------------------------------------------------------------------------------------------------------------------------------
    
    public static void runDialog(final Class dialogClass){
        Runnable run = new Runnable(){
            @Override
            public void run(){
                try {
                    javax.swing.JDialog dialog = (javax.swing.JDialog)dialogClass.newInstance();
                    dialog.setVisible(true);
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(VentanaRoot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        runThread(run);
    }
    
    static void runThread(Runnable run){
        new Thread(run).start();
    }
}
