/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecorserver;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cadastro implements Runnable {

    private static void Cadastrar() throws SocketException, IOException {
        //Ouvinte na porta 2000 (cadastro)
        DatagramSocket CserverSocket = new DatagramSocket(2000);

        //cria o datagrama pra receber a msg 
        DatagramPacket cadastro = new DatagramPacket(new byte[512], 512);

        //Aguarda ate o recebimento da msg
        CserverSocket.receive(cadastro);

        //Lendo os dados do cadastro
        String Cconvert = new String(cadastro.getData(), "UTF-8"); //transforma o pacote em binario
        System.out.println("Cadastro = " + Cconvert);

        //Chama o método que cadastra o usuario no servidor
        BancoDeDados.gravaArquivo(Cconvert);

        CserverSocket.close();

        Cadastrar();
    }

    //tentar 
    @Override
    public void run() {
        try {
            Cadastrar();
        } catch (SocketException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
}
