/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryclient.controlador;

import java.awt.event.ActionEvent;

import seminaryclient.logica.modelo.ControlConexion;
import seminaryclient.vista.dialogos.DialogoConexion;


/**
 *
 * @author Ariel Arnedo
 */
public class ConexionSocketController implements java.awt.event.ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand){
            
            case "Conectar con el Servidor":{
                System.out.println(actionCommand);
                ControlConexion.runDialog(DialogoConexion.class);
                break;
            }
            
            case "Desconectar":{
                System.out.println(actionCommand);
                
                break;
            }
            
            default:{
                throw new RuntimeException("Boton no programado");
            }
        }
    } 
}
