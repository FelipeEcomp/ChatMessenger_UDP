/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveisdecor;

import Igráfica.Arquivo;
import Igráfica.ChatPrivate;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransfArquivo implements Runnable {

    public static void Envia(String arquivo, String Nome, String IP) throws IOException {
        // cria o nosso socket  

        DatagramSocket servsock = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(IP); //endereço



        System.out.println(arquivo);
        System.out.println(Nome);

        // envia o arquivo (transforma em byte array)  
        File myFile = new File(arquivo);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
     

        //concatena o arquivo com sua extensão
        // byte[] FileByte = new byte[bos.toByteArray().length+Nome.length()];
        byte[] FileByte = bos.toByteArray();



        DatagramPacket sendPacket = new DatagramPacket(FileByte, FileByte.length, IPAddress, 9877);

        servsock.send(sendPacket);

        servsock.close();



    }

    public void Recebe() throws IOException, ClassNotFoundException {
        //Ouvinte na porta 3020 (Arquivos)
        DatagramSocket FserverSocket = new DatagramSocket(9877);

        //cria o datagrama pra receber a msg 
        DatagramPacket FilePacket = new DatagramPacket(new byte[10000], 10000);

        System.out.println("to na Areaaa");
        //Aguarda ate o recebimento da lista
        FserverSocket.receive(FilePacket);
        System.out.println("to na Areaaa22222");
        System.out.println(ChatPrivate.getConfirmacao());

        //if(ChatPrivate.getConfirmacao() == true){ //se o cara confirmar q quer o arquivo

        String nome = new String(FilePacket.getData(), "UTF-8");
        System.out.println("Nomeee " + nome);


        byte[] Fconvert = FilePacket.getData();


        //    File arquivo =(File) in.readObject();

        //Gera o arquivo para armazenar o objeto
        String local = System.getProperty("user.dir"); //Retorna o diretorio atual da aplicação

        //==================================================
        //Cria a pasta chamada Clientes
        File dir = new File(local + "\\Arquivos Recebidos");
        if (dir.mkdir()) {
            System.out.println("Diretorio criado com sucesso!");
        } else {
            System.out.println("Diretório já existe!");
        }


        File arquivoCliente = new File(local + "\\Arquivos Recebidos\\" + Fconvert);


        FileOutputStream fos = new FileOutputStream(arquivoCliente);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(Fconvert);






        FserverSocket.close();
        fos.close();
        oos.close();
        //}
        Recebe();
    }

    @Override
    public void run() {
        try {
            Recebe();
        } catch (IOException ex) {
            Logger.getLogger(TransfArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TransfArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
