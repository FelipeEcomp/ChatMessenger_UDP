/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecorserver;

import Igrafica.Server;

/**
 *
 * @author Felipe
 */
public class MoveisDecorServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        /* 
        Server srv = new Server();
        Thread thrserv = new Thread(srv);
        thrserv.start();
        
         */


        Server srv = new Server();
        srv.setVisible(true);

        /*  Cadastro cad = new Cadastro();
        Thread thr = new Thread(cad);
        thr.start();
        
        Mensagem chat=new Mensagem();
        Thread thrMsg=new Thread(chat);
        thrMsg.start();
        
        
        ServerLogin login=new ServerLogin();
        Thread thrLogin = new Thread(login);
        thrLogin.start();   //aki eh so pra testar o login
        // server.receive();        //   isso roda as threads
        
         */


    }
}
