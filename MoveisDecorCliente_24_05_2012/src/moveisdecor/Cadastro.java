/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecor;

import Igráfica.IPServer;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Felipe
 */
public class Cadastro {

    private String IP = IPServer.IPServ;

    public void Cadastrar(String login, String senha) throws Exception {
   //     Usuario usuarioob = new Usuario();

       // usuarioob.setLogin(login);
      //  usuarioob.setSenha(senha);

        //Cria Socket UDP
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(IP); //endereço
        System.out.println(IPAddress);

        String cadastro = login + "&" + senha; //envia login e senha no mesmo datagrama
        System.out.println(cadastro);
        byte[] buffer = cadastro.getBytes();



//Envia datagrama para destinatario na porta 2000
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, 2000);


//Envia o datagrama
        clientSocket.send(sendPacket);

    }
}
