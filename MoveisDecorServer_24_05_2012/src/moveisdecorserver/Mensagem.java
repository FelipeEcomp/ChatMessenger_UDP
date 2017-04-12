/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecorserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bianca
 */
public class Mensagem implements Runnable {

    public static void Mensagem() throws SocketException, IOException, Exception {
        //Ouvinte na porta 4000 (Mensagem)
        DatagramSocket MserverSocket = new DatagramSocket(4000);

        //cria o datagrama pra receber a msg 
        DatagramPacket mensagem = new DatagramPacket(new byte[512], 512);

        //Aguarda ate o recebimento da msg
        MserverSocket.receive(mensagem);

        //Lendo os dados do cadastro
        String Mconvert = new String(mensagem.getData(), "UTF-8"); //transforma o pacote em binario
        System.out.println("Mensagem = " + Mconvert);

        send(mensagem); //Manda a mensagem para o usuáio desejado

    }

    public static void send(DatagramPacket question) throws Exception {
        //Criando o socket
        DatagramSocket serverSocket = new DatagramSocket();

        DatagramPacket answer = question;
        answer.setPort(4001); //seta uma nova porta para o pacote (4545)
        //answer.setAddress(null); //seta novo endereço IP

        serverSocket.send(answer);

        serverSocket.close();

        System.out.println(answer);

    }

    @Override
    public void run() {
        try {
            Mensagem();
        } catch (SocketException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
