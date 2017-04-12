/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecor;
import Igráfica.ChatGrupo2;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Multicast2 implements Runnable{
 private String msg;
    private ChatGrupo2 janelaAberta;

    //O broadcast foi implementado através do multicast, todo mundo ao logar será inscrito nesse grupo assim o chat global enviará as mensagens para o IP deste grupo 
    public void setJanela(ChatGrupo2 janela) {
        this.janelaAberta = janela;

    }

    public String getMsg() {

        return msg;
    }

    public void entrarChatMulticast() throws IOException {
        InetAddress group = InetAddress.getByName("226.5.6.7");
        MulticastSocket s = new MulticastSocket(5802);

        //Entra no grupo, com isso as mensagens para este IP
        //serão recebidas
        s.joinGroup(group);

    }

    public void MulticastEnvia(String Mensagem) throws IOException {


        InetAddress group = InetAddress.getByName("226.5.6.7");
        MulticastSocket s = new MulticastSocket();

        //Entra no grupo, com isso as mensagens para este IP
        //serão recebidas
        //  s.joinGroup(group); se deixar isso aki, toda vez q digitar algo entra no mesmo grupo

        byte[] buffer = Mensagem.getBytes();



//Envia datagrama para destinatario na porta 5800
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, group, 5802);

//Envia o datagrama
        s.send(sendPacket);


    }

    public void MulticastReceive() throws IOException {
        //ainda por definir
        InetAddress group = InetAddress.getByName("226.5.6.7");
        MulticastSocket s = new MulticastSocket(5802);




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

        MulticastReceive();

    }

    @Override
    public void run() {
        try {
            MulticastReceive();
        } catch (IOException ex) {
            Logger.getLogger(Multicast.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
