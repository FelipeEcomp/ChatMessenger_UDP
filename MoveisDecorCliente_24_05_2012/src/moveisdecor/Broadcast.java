/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecor;

import Igráfica.ChatGlobal;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bianca
 */
public class Broadcast implements Runnable {

    private String msg;
    private ChatGlobal janelaAberta;

    //O broadcast foi implementado através do multicast, todo mundo ao logar será inscrito nesse grupo assim o chat global enviará as mensagens para o IP deste grupo 
    public void setJanela(ChatGlobal janela) {
        this.janelaAberta = janela;

    }

    public String getMsg() {

        return msg;
    }

    public void entrarChatGlobal() throws IOException {
        InetAddress group = InetAddress.getByName("228.5.6.7");
        MulticastSocket s = new MulticastSocket(5800);

        //Entra no grupo, com isso as mensagens para este IP
        //serão recebidas
        s.joinGroup(group);

    }

    public void BroadcastEnvia(String Mensagem) throws IOException {


        InetAddress group = InetAddress.getByName("228.5.6.7");
        MulticastSocket s = new MulticastSocket();

        //Entra no grupo, com isso as mensagens para este IP
        //serão recebidas
        //  s.joinGroup(group); se deixar isso aki, toda vez q digitar algo entra no mesmo grupo

        byte[] buffer = Mensagem.getBytes();



//Envia datagrama para destinatario na porta 5800
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, group, 5800);

//Envia o datagrama
        s.send(sendPacket);


    }

    public void BroadcastReceive() throws IOException {
        //IP e porta do grupo 
        InetAddress group = InetAddress.getByName("228.5.6.7");
        MulticastSocket s = new MulticastSocket(5800);




        //Entra no grupo, com isso as mensagens para este IP
        //serão recebidas
        s.joinGroup(group);

        //cria o datagrama pra receber a msg
        DatagramPacket answer = new DatagramPacket(new byte[1024], 1024);

        //Recebe Datagrama
        s.receive(answer);


        //Lendo a mensagem
        //Enviar a mensagem pra interface grafica FALTA FAZER
        String convert = new String(answer.getData(), "UTF-8");
        msg = convert;
        janelaAberta.setMsg(msg);


        System.out.println("Mensagem: " + msg);
        s.close();

        BroadcastReceive();

    }

    @Override
    public void run() {
        try {
            BroadcastReceive();
        } catch (IOException ex) {
            Logger.getLogger(Multicast.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
