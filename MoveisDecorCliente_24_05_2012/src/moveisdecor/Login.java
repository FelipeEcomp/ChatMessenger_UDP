/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecor;

import Igráfica.grafLogin;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Igráfica.IPServer;
/**
 *
 * @author Felipe
 */
public class Login implements Runnable {

    private grafLogin janela;
    private boolean respLogin;
    private String IP = IPServer.IPServ;

    public void setJanela(grafLogin janela) {
        this.janela = janela;

    }

    public void FLogin(String login, String senha) throws Exception {

        //Cria Socket UDP
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(IP); //endereço IPServer do servidor tem que trocar isso posteriormente
        System.out.println(IPAddress);

        
        String entrar = login + "&" + senha + "&" + InetAddress.getLocalHost().getHostAddress(); //envia login, senha e o IPServer da maquina onde o usuario está tentando logar no mesmo datagrama
        System.out.println("Login e senha digitados " + entrar);
        byte[] buffer = entrar.getBytes();



//Envia datagrama para destinatario na porta 3000
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, 3000);//aqui o endereço do servidor



//Envia o datagrama
        clientSocket.send(sendPacket);
clientSocket.close();

    }

    public void RespostaLogin() throws SocketException, IOException { //ISSO NAO TA FUNFANDO PQ ELE SO EH EXECUTADO DEPOIS QUE A MSG EH ENVIADA
        //QD COLOCARMOS A THREAD ISSO PROVAVELMENTE FUNFARÁ

        System.out.println("Entrou aqui");
        //Ouvinte na porta 3001
        DatagramSocket msgRecebidaSocket = new DatagramSocket(3001);
        //cria o datagrama pra receber a msg
        DatagramPacket answer = new DatagramPacket(new byte[4], 4);

        System.out.println("To aki?");
        //Recebe Datagrama
        msgRecebidaSocket.receive(answer);

        System.out.println("Não to aki né?");
        //Lendo a mensagem
        String convert = new String(answer.getData(), "UTF-8");

        System.out.println("checagem de login " + convert);

        byte[] testador = new byte[1024];
        testador = "nulo".getBytes();
        String test = new String(testador, "UTF-8");

        System.out.println("checagem de login " + test);


        if (convert.equals("nulo")) { //se for null, entao o login nao está cadastrado
            respLogin = false;
            System.out.println("ta falso");
            janela.logar(respLogin);
        } else {
            System.out.println("ta verdadeiro");
            respLogin = true;
            janela.logar(respLogin);
        }
        //isso deve ser trocado assim que for pensado um metodo para a identificação dos Ips dos usuários online e quando
        //for definido o tipo de transmissão unicast, multicast e blablabla pra definir o JFrame que irá receber
        System.out.println("Login&Senha = " + convert);

        //Fecha Socket
        msgRecebidaSocket.close();



    }

    public boolean getRespLogin() {
        return respLogin;
    }

    public void enviarIP(String login) throws Exception {

        //Cria Socket UDP
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(IP); //endereço de Ip do servidor        
        String entrar = login + "&" + IP; //o endereço IPServer da máquina 
        System.out.println("enviar meu ip lalalalala");
        byte[] buffer = entrar.getBytes();

        //Envia datagrama para destinatario na porta 7008
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, 7008);
        //Envia o datagrama
        clientSocket.send(sendPacket);

        clientSocket.close();
    }

    @Override
    public void run() {
        try {
            RespostaLogin();
        } catch (SocketException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
