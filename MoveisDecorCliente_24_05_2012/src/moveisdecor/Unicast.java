/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecor;

import Igráfica.ChatPrivate;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Unicast implements Runnable{
 private String msg;
  ChatPrivate priv;
 
 public Unicast(ChatPrivate chat){
     this.priv= chat; 
 }
  
    
    public void UnicastEnvia(String texto, String IP) throws Exception {
        //Cria Socket UDP
        
        System.err.println("IPPP"+ IP);
        //envia para o ip correspondente ao outro usuário selecionado
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(IP); //endereço
        System.out.println(IPAddress);  
         
     
        byte[] buffer = texto.getBytes();
              

//Envia datagrama para destinatario na porta 3000
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, 9876);
                        
//Envia o datagrama
        clientSocket.send(sendPacket);
        
   }
    
    
    
    public void UnicastReceive() throws Exception{
       
      
        DatagramSocket msgRecebidaSocket = new DatagramSocket(9876);
        //cria o datagrama pra receber a msg
        DatagramPacket answer = new DatagramPacket(new byte[1024], 1024);
        
        //Recebe Datagrama
        msgRecebidaSocket.receive(answer);
        System.out.println("To esperando abrir ");
        //Lendo a mensagem
        String convert = new String (answer.getData(), "UTF-8"); 
        msg = convert;
        
        
        System.out.println("Como ta a janela?? "+priv.isVisible());
      
        priv.setMsg(msg);
        
        
        
        
        
              
        //Fecha Socket
        msgRecebidaSocket.close();
        UnicastReceive();
      
    }

    @Override
    public void run() {
        try {
            UnicastReceive();
        } catch (Exception ex) {
            Logger.getLogger(Unicast.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    }
    

