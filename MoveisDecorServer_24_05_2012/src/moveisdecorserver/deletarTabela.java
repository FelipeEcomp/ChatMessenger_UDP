/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecorserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class deletarTabela implements Runnable{
 
    
    
    
    public void deletaTabela() throws Exception {
        
        //Ouvinte na porta 3006 (Lista de IP e nomes)
        DatagramSocket LserverSocket = new DatagramSocket(3006);

        //cria o datagrama pra receber a msg 
        DatagramPacket PosPacket = new DatagramPacket(new byte[1], 1);

       
        //Aguarda ate o recebimento da lista
        LserverSocket.receive(PosPacket);
         System.out.println("TENTANDO DELETAR DA TABELAAAA");
        String Pconvert = new String(PosPacket.getData(), "UTF-8");
       
        System.out.println("TENTANDO DELETAR DA TABELAAAA 2222");
        
        int posicao = Integer.parseInt(Pconvert); 
        
         
        ServerLogin.enviarTabela(posicao);
        
        LserverSocket.close();
        
        deletaTabela();
        
    }

    @Override
    public void run() {
        try {
            deletaTabela();
        } catch (Exception ex) {
            Logger.getLogger(deletarTabela.class.getName()).log(Level.SEVERE, null, ex);
      
    }
    }
}

