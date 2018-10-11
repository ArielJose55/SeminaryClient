/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryclient.logica.util;

/**
 *
 * @author Ariel Arnedo
 */
public class ClienteAtributo {
    
    public static final int MENSAJE_DE_CHAT = 0;
    public static final int MENSAJE_DE_BLOQUEO = 1;
    
    private static ClienteAtributo cliente = null;
    
    private PropertyHost sistemaOperativo;

    public ClienteAtributo() {
        String os = System.getProperty("os.name");
        if(os.startsWith("Windows")){
            sistemaOperativo = PropertyHost.WINDOWS;
        }else if(os.compareTo("Linux i386") == 0 || os.compareTo("Linux amd64") == 0){
            sistemaOperativo = PropertyHost.LINUX;
        }else if(os.compareTo("Mac OS X") == 0){
            sistemaOperativo = PropertyHost.MAC;
        }
    }
    
    public static ClienteAtributo getAtributoCliente(){
        if(cliente == null){
            cliente = new ClienteAtributo();
        }
        return cliente;
    }
    
    
    public void apagar() throws Exception{
        if(sistemaOperativo.equals(PropertyHost.WINDOWS)){
            Runtime.getRuntime().exec("shutdown -s");
        }else if(sistemaOperativo.equals(PropertyHost.LINUX)){
            Runtime.getRuntime().exec("halt");
        }else if(sistemaOperativo.equals(PropertyHost.MAC)){
            Runtime.getRuntime().exec("sudo halt");
        }else{
            throw new Exception("Sistema Operativo no compatible");
        }
    }
    
    public void reiniciar() throws Exception{
        if(sistemaOperativo.equals(PropertyHost.WINDOWS)){
            Runtime.getRuntime().exec("shutdown -r");
        }else if(sistemaOperativo.equals(PropertyHost.LINUX)){
            Runtime.getRuntime().exec("shutdown -r now");
        }else if(sistemaOperativo.equals(PropertyHost.MAC)){
            Runtime.getRuntime().exec("sudo reboot");
        }else{
            throw new Exception("Sistema Operativo no compatible");
        }
    }
    
    public PropertyHost getSistemaOperativo(){
        return sistemaOperativo;
    }

}
