/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecor;

import Igráfica.ChatGlobal;
import Igráfica.IPServer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class Tabela implements Runnable {
static String IP = IPServer.IPServ;
    static int jaTemTabela = 0;
    static int i = 0;
    static String[][] ListaNomes = new String[100][1];
    static String[][] ListaIPs = new String[100][2];
    static String[][] Lista = new String[100][2];
    static int size;
    static String[][] NomesLista = new String[100][2];//é esse o array que tem o login e o IP do usuário =]
    static ChatGlobal chat;
    static private Broadcast conversa;

    public void setChatGlobal(ChatGlobal chat) {

        Tabela.chat = chat;
    }

    public static void enviarTabela() throws Exception {



        //Ouvinte na porta 3004 (Lista de IP e nomes)
        DatagramSocket LserverSocket = new DatagramSocket(3004);

        //cria o datagrama pra receber a msg 
        DatagramPacket ListaPacket = new DatagramPacket(new byte[10000], 10000);

        System.out.println("akiiiii");
        //Aguarda ate o recebimento da lista
        LserverSocket.receive(ListaPacket);
        System.out.println("Olha a lista ai gennteee " + ListaPacket);


        ByteArrayInputStream bis = new ByteArrayInputStream(ListaPacket.getData());
        ObjectInput in = new ObjectInputStream(bis);


        ArrayList NomesIPs = (ArrayList) in.readObject();
        size = NomesIPs.size();


        for (i = 0; i < size; i++) {

            System.out.println("estou dentro do for");
            NomesLista[i] = (String[]) NomesIPs.get(i);
            System.out.println("Aqui a lista NomesLista:" + NomesLista[i]);
            //Lista[i]= NomesLista[i][0]; //divide a string

            ListaNomes[i] = NomesLista[i];
            //ListaNomes[i] = Lista[i];

            ListaIPs[i] = NomesLista[i];
            System.out.println("akiiiiiaaa " + ListaNomes[0]);

        }



        LserverSocket.close();
        bis.close();
        in.close();

        if (jaTemTabela != 0) {
            System.out.println("ulá ulá ulá ulá ulá ulá llalalalalallala");
            chat.setVisible(false);
            ChatGlobal newChat = new ChatGlobal();
            conversa.setJanela(newChat);
            newChat.setName();
            newChat.setVisible(true);
        }
        if (jaTemTabela == 0) {
            jaTemTabela = 1;
        }

        enviarTabela();



    }

    
  public static void  deletarTabela(int posicao) throws SocketException, UnknownHostException, IOException{
        //Cria Socket UDP
        DatagramSocket nomeSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(IP); //endereço IP do servidor tem que trocar isso posteriormente
        System.out.println(IPAddress);
        //tem que trocar isso aqui
        
        String posic = Integer.toString(posicao); //typecast de int pra string
        byte[] buffer = posic.getBytes();
        
        System.out.println("Posição denovo "+posic);


//Envia datagrama para destinatario na porta 3006
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, 3006);//aqui o endereço do servidor



//Envia o datagrama
        nomeSocket.send(sendPacket);
        
    }
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static String[][] getListaNomes() {
        //System.out.println("Lista de Nomes "+ListaNomes[0]);
        return ListaNomes;
    }

    public static String[][] getNomesLista() {

        return NomesLista;
    }

    public static String[][] getListaIPs() {

        return ListaIPs;
    }

    public static int getSize() {
        return size;
    }

    @Override
    public void run() {
        try {
            enviarTabela();
        } catch (Exception ex) {
            Logger.getLogger(Tabela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setConversa(Broadcast conversa) {
        Tabela.conversa = conversa;
    }
}
