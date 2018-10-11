/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryclient.vista.ventanas;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

/**
 *
 * @author Ariel Arnedo
 */
public  class RootEnable {
    
    private final VentanaEnable ventanaRoot;
    private boolean isEnable = false;
    
    public RootEnable(){
        this.ventanaRoot = new VentanaEnable();
    }

    public boolean isIsEnable() {
        return isEnable;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
        if(!isEnable)
            ventanaRoot.setVisible(false);
    }
    
    
    public void startEnable(){
        
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate( 
            new Runnable() {
                @Override
                public void run() {                   
                    if(isEnable){
                        toFront();
                    }
                }
              }, 500, 50 , TimeUnit.MILLISECONDS ); //comienza dentro de 1/2 segundo y luego se repite cada N segundos
    }
    
    /**
     * 
     */
    public void toFront(){
        if(!ventanaRoot.isVisible())
            ventanaRoot.setVisible(true);
        ventanaRoot.setExtendedState( JFrame.MAXIMIZED_BOTH );//maximizado
        ventanaRoot.toFront();
    }
}
