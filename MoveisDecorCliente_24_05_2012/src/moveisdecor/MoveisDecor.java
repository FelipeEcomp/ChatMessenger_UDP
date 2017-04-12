/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecor;

import Igráfica.IPServer;
import Igráfica.grafLogin;

/**
 *
 * @author Felipe
 */
public class MoveisDecor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        //Deixa a interface gráfica de login visível
        IPServer grafIP = new IPServer();
        grafIP.setVisible(true);



        
    }
}
