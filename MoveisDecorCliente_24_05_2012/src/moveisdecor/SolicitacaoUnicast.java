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

/**
 *
 * @author Bianca
 */
public class SolicitacaoUnicast implements Runnable {

    private static String login;
    private static Tabela tabela;

    public SolicitacaoUnicast(String login, Tabela tabela) {
        SolicitacaoUnicast.login = login;
       
        SolicitacaoUnicast.tabela = tabela;

    }

    public void setTabela(Tabela tabela) {

        SolicitacaoUnicast.tabela = tabela;

    }

    
    public void SolicitacaoUnicastEnvia( int selected) throws Exception {
        //Cria Socket UDP
        String[] IP1 = (String[]) Tabela.getListaIPs()[selected];
        String IP = IP1[1];
        
        System.err.println("IPPP"+ IP);
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(IP); //endereço
        System.out.println(IPAddress);  
         
        
        byte[] buffer = InetAddress.getLocalHost().getHostAddress().getBytes();
              

//Envia datagrama para destinatario na porta 3000
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, 9878);
                        
//Envia o datagrama
        clientSocket.send(sendPacket);
        
   }
    
    
    
    
    public void SolicitacaoUnicastReceive() throws Exception {
        DatagramSocket msgRecebidaSocket = new DatagramSocket(9878);
        DatagramPacket answer = new DatagramPacket(new byte[1024], 1024);
        msgRecebidaSocket.receive(answer);
        String ip = new String(answer.getData(), "UTF-8"); //ele recebe o ip do cara que quer abrir o unicast
  
        
        System.out.println("aeee recebi a solicitação   " + ip);


        //aqui eu faço tudo que tem associado ao botão Particular da janela chatGlobal
        ChatPrivate priv = new ChatPrivate(ip);
        Unicast uni = new Unicast(priv);
        Thread uniThread = new Thread(uni);
        uniThread.start();

        TransfArquivo trans = new TransfArquivo();
        Thread transThread = new Thread(trans);
        transThread.start();



        //  priv.setIP(IP);  DEPOIS DE IMPLEMENTAR A TABELA DE IPS, TIRAR ESSE COMENTARIO E DEIXAR O METODO    
        priv.setVisible(true);
        priv.setLogin(login);




        //___________________________-----
        //Fecha Socket
        msgRecebidaSocket.close();
        SolicitacaoUnicastReceive();

    }

    @Override
    public void run() {
        try {
            SolicitacaoUnicastReceive();
        } catch (Exception ex) {
            Logger.getLogger(Unicast.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setLogin(String login) {
        this.login= login;
    }
}
