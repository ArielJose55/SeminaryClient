/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryclient.logica.modelo;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import seminaryclient.logica.util.ClienteAtributo;
import seminaryclient.logica.util.Notificador;
import seminaryclient.logica.util.UpdateConstant;
import static seminaryclient.logica.util.UpdateConstant.STOP_WAITING;

/**
 *
 * @author Ariel Arnedo
 */
public class HostCliente implements UpdateConstant{
    
    private final String ipServer;
    private final Integer puertoServer;
    private Byte estado;
    
    
    private java.net.Socket socket;

    public HostCliente(String ipServer, Integer puertoServer) {
        this.ipServer = ipServer;
        this.puertoServer = puertoServer;
    } 
    /**
     * conecta con el servidor pasandole la ipServer y el puerto
     */
    void conectarConServidor(){
        Runnable run = new Runnable(){
            public void run(){
                try{
                    socket = new java.net.Socket(ipServer, puertoServer);
                    estado = UpdateConstant.BLOQUEADO;
                    enviarInformacionCliente();
//                    escucharInformacion();
                    Notificador.getNotificador().notificar(BLOQUEADO);
                }catch(java.io.IOException ex){
                    Notificador.getNotificador().notificar(ERROR_CONEXION);
                }finally{
                    Notificador.getNotificador().notificar(STOP_WAITING);
                    if(socket != null && socket.isConnected()){
                        Notificador.getNotificador().notificar(CONEXION);
                    }
                }
            }
        };
        ControlConexion.runThread(run);
    }
    
    /**
     * metodo para enviar informacion desde el cliente
     * la informacion corresponde al nombre de host, estado y la direccion ip
     */
    private void enviarInformacionCliente(){
        Runnable run = new Runnable(){
            public void run(){
                try {
                    java.io.DataOutputStream salida = null;
                    StringBuilder sb = new StringBuilder();
                    sb.append(java.net.InetAddress.getLocalHost().getHostAddress());
                    sb.append(" ");
                    sb.append(java.net.InetAddress.getLocalHost().getCanonicalHostName());
                    sb.append(" ");
                    sb.append(String.valueOf(estado));
                    salida = new java.io.DataOutputStream(socket.getOutputStream());
                    salida.writeUTF(sb.toString());
                    salida.flush();
                                    
                } catch (UnknownHostException ex) {
                    Logger.getLogger(HostCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(HostCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        };
        ControlConexion.runThread(run);
    }
    
    
    
    private void escucharInformacion(){
        Runnable run = new Runnable() {

            @Override
            public void run() {
                while(socket.isBound()){
                    java.io.DataInputStream entrada = null;
                    try {
                        entrada = new java.io.DataInputStream(socket.getInputStream());
                        String mensaje = entrada.readUTF();
                        procesarInformacion(mensaje);
                    } catch (Exception e) {
                    }
                }
            }
        };
        ControlConexion.runThread(run);
    }
    
    void desconectarDelServidor() throws RuntimeException{
        Runnable run = new Runnable(){
            public void run(){
                if(socket != null && socket.isConnected()){
                    try {
                        socket.close();
                        socket = null;
                    } catch (IOException ex) {
                        Logger.getLogger(HostCliente.class.getName()).log(Level.SEVERE, null, ex);
                        throw new RuntimeException(ex.getMessage());
                    }
                    return;
                }
                throw new RuntimeException("El Host no esta conectado");
            }
        };
    }
    
    private void procesarInformacion(String mensaje) throws Exception{
        if(mensaje.startsWith("ApagarÑÑÑ")){
            ClienteAtributo.getAtributoCliente().apagar();
        }else if(mensaje.startsWith("BloquearÑÑÑ")){
            Notificador.getNotificador().notificar(UpdateConstant.BLOQUEADO);
        }else if(mensaje.startsWith("DesbloquearÑÑÑ")){
            Notificador.getNotificador().notificar(UpdateConstant.DESBLOQUEADO);
        }else if(mensaje.startsWith("DesconexionÑÑÑ")){
            socket.close();
            Notificador.getNotificador().notificar(UpdateConstant.DESCONEXION);
        }else{
            Notificador.getNotificador().notificar(informacionAdicional(false, mensaje));
        }
    }
    
    void enviarMensaje(final int tipo, final String mensaje){
        Runnable run = new Runnable() {

            @Override
            public void run() {
                if(socket.isConnected()){
                    java.io.DataOutputStream salida = null;
                    try {
                        salida = new java.io.DataOutputStream(socket.getOutputStream());
                        salida.writeUTF(ponerCabezado(tipo, mensaje));
                        salida.flush();
                        
                    } catch (Exception ex) {
                        Logger.getLogger(HostCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        ControlConexion.runThread(run);
    }
    
    private String ponerCabezado(int tipo , String mensaje){
        StringBuilder sb = new StringBuilder();
        if(tipo == ClienteAtributo.MENSAJE_DE_CHAT){
               sb.append("$$&&$$");
               sb.append(mensaje);
               System.out.println(mensaje);
               Notificador.getNotificador().notificar(informacionAdicional(true,mensaje));

        }else if(tipo == ClienteAtributo.MENSAJE_DE_BLOQUEO){
               sb.append("$$%%$$");
               sb.append(mensaje);
        }else{
            throw new RuntimeException("Opcion no valida");
        }
        return sb.toString();
    }
    
    private String informacionAdicional(boolean isWrite, String mensaje){
        StringBuilder sb = new StringBuilder();
        sb.append( (isWrite) ? "Yo: " : "Server: ");
        sb.append(mensaje);
        sb.append("\n");
        return sb.toString();
    }
}
