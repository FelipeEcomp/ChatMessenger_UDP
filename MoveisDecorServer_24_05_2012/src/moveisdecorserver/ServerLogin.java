/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecorserver;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerLogin implements Runnable {

    private static List listaIPs = new LinkedList(); //lista para saber quem está online  
    // private static String IP = "localhost";//rever isso depois pode dar merda se deixar assim por mto tempo
    private static int a;
    private static ArrayList ListaNomes = new ArrayList();
    private static ArrayList ListaIPsDefinitivo = new ArrayList();

    public void Login() throws SocketException, IOException, ClassNotFoundException {



        //Ouvinte na porta 3000 (login)
        DatagramSocket LserverSocket = new DatagramSocket(3000);

        //cria o datagrama pra receber a msg 
        DatagramPacket login = new DatagramPacket(new byte[512], 512);

        //Aguarda ate o recebimento da msg
        LserverSocket.receive(login);

        //Lendo os dados do cadastro
        String Lconvert = new String(login.getData(), "UTF-8"); //transforma o pacote em binario


        //para pegar o ip e depois juntar o login e a senha novamente
        String[] tudoSeparado = new String[3];
        tudoSeparado = Lconvert.split("&");
        System.out.println(tudoSeparado[0] + tudoSeparado[1] + tudoSeparado[2]);
        String[] IP = new String[2];
        IP[0] = tudoSeparado[0];
        IP[1] = tudoSeparado[2];
        String loginSenha = tudoSeparado[0] + "&" + tudoSeparado[1];
        System.out.println(loginSenha);
        System.out.println(IP[0] + "&" + IP[1]);
        //aqui acaba a tentativa de juntar e separar       

        String local = System.getProperty("user.dir"); //Retorna o diretorio atual da aplicação


        File arquivo = new File(local + "\\Usuarios\\" + loginSenha); //Busca o arquivo pelo nome do cliente

        if (arquivo.exists()) {
            ListaIPsDefinitivo.add(IP);
            confLogin(Lconvert, IP);

            System.out.println("tamanho da lista de ips: " + ListaIPsDefinitivo.size());

        } else {
            confLogin(null, IP);
        }
        LserverSocket.close();

        Login();

        //String Nome = BancoDeDados.lerArquivo(arquivo);   //Passa a string do arquivo para a variável
    }

    private static void confLogin(String Nome, String[] IP) throws SocketException, UnknownHostException, IOException {

        
        if (Nome == null) {
            Nome = "nulo";
        }

        byte[] buffer = Nome.getBytes();
        System.out.println("CONFLOGIN Login = " + Nome);

        //Criando o socket
        DatagramSocket serverSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName(IP[1]); //endereço isso tem que mudar, deve pegar do vetor IP criado no metodo Login


        //Envia datagrama para destinatario na porta 3001
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, 3001);

        /* 
        String[] array= new String [2];
        array[0]= Nome;
        array[1]= IP;
        boolean add = listaIPs.add(array);
         */

        //Envia o datagrama
        serverSocket.send(sendPacket);


        //     Nome = null;
        serverSocket.close();



        enviarTabela(Nome, IP); //metodo que envia os clientes pra jtable
    }

    public static void enviarTabela(String Nome, String[] IP) throws IOException {
// System.out.println("olha a lista ae genteee antess " + ListaNomes.get(0));


        System.out.println("olha a lista ae genteee" + ListaIPsDefinitivo.size());
        // System.out.println("olha a lista ae genteee " + ListaNomes.get(1));
        String[] IP1 = new String[2];

        for (int i = 0; i < ListaIPsDefinitivo.size(); i++) {//mandar a ista de IPs pra todo mundo
            //transformando a lista de usuarios online em array de bytes
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(ListaIPsDefinitivo);
            byte[] ListaByte = bos.toByteArray();


            DatagramSocket tabelSocket = new DatagramSocket();


            IP1 = (String[]) ListaIPsDefinitivo.get(i);
            InetAddress IPAddress = InetAddress.getByName(IP1[1]); //endereço

            //Envia datagrama para destinatario na porta 3004
            System.err.println("TAMANHO" + ListaByte.length);
            DatagramPacket sendPacket = new DatagramPacket(ListaByte, ListaByte.length, IPAddress, 3004);

            //Envia o datagrama
            tabelSocket.send(sendPacket);
            //     Nome = null;
            tabelSocket.close();

            out.close();
            bos.close();

        }

    }
    
 public static void enviarTabela(int posicao) throws IOException{
//esse metodo atualiza a tabela quando alguem sai do sistema           
   System.out.println("olha a lista ae genteee " + ListaIPsDefinitivo.size());
     
    ListaIPsDefinitivo.remove(posicao);

     
        // System.out.println("olha a lista ae genteee " + ListaNomes.get(1));
        String[] IP1 = new String[2];

        for (int i = 0; i < ListaIPsDefinitivo.size(); i++) {//mandar a ista de IPs pra todo mundo
            //transformando a lista de usuarios online em array de bytes
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(ListaIPsDefinitivo);
            byte[] ListaByte = bos.toByteArray();


            DatagramSocket tabelSocket = new DatagramSocket();


            IP1 = (String[]) ListaIPsDefinitivo.get(i);
            InetAddress IPAddress = InetAddress.getByName(IP1[1]); //endereço

            //Envia datagrama para destinatario na porta 3004
            System.err.println("TAMANHO" + ListaByte.length);
            DatagramPacket sendPacket = new DatagramPacket(ListaByte, ListaByte.length, IPAddress, 3004);

            //Envia o datagrama
            tabelSocket.send(sendPacket);
            //     Nome = null;
            tabelSocket.close();

            out.close();
            bos.close();
     
        }
 }
    
    
    
    

    @Override
    public void run() {
        try {
            Login();
        } catch (SocketException ex) {
            Logger.getLogger(ServerLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
