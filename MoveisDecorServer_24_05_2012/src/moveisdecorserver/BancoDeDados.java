package moveisdecorserver;

import java.io.*;

public class BancoDeDados {

    public static void gravaArquivo(String dado) throws IOException {


        //Gera o arquivo para armazenar o objeto
        String local = System.getProperty("user.dir"); //Retorna o diretorio atual da aplicação

        //==================================================
        //Cria a pasta chamada Usuários
        File dir = new File(local + "\\Usuarios");
        if (dir.mkdir()) {
            System.out.println("Diretorio criado com sucesso!");
        } else {
            System.out.println("Diretório já existe!");
        }

        //===============================================
        File arquivoCliente = new File(local + "\\Usuarios\\" + dado);

//grava o objeto
        FileOutputStream fos = new FileOutputStream(arquivoCliente);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(dado);

        oos.flush();
        oos.close();
        fos.close();

    }

    public static String lerArquivo(File arquivoselecionado) throws IOException,
            ClassNotFoundException {
        String Nome;

        if (arquivoselecionado.exists()) { //se o login existe

            FileInputStream fis = new FileInputStream(arquivoselecionado);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Nome = (String) ois.readObject(); //dados do login

            fis.close();
            ois.close();
        } else {
            Nome = null;
        }


        return Nome;
    }
    /*  
    public static Cliente[] LerTodos() throws IOException, ClassNotFoundException { //Lê todos os arquivos do diretorio CHECAR PQ ESTA DANDO ESSE ERRO
    String local = System.getProperty("user.dir"); //Retorna o diretorio atual da aplicação
    File dir = new File(local + "\\Usuarios");// Diretório onde será lido os arquivos  
    
    
    File[] files = dir.listFiles(); //Lista de arquivos no diretorio
    Cliente[] clientes = new Cliente[files.length];
    for (int i = 0; i < files.length; i++) {
    clientes[i] = (Cliente) lerObjeto(files[i]);
    }
    return clientes;
    }
    
    public static int quantidadeArquivos(){//Quantidade de Arquivos numa pasta
    String local = System.getProperty("user.dir"); //Retorna o diretorio atual da aplicação
    File dir = new File(local+"\\Clientes");// Diretório onde será lido os arquivos  
    
    int quantidade = dir.listFiles().length;
    return quantidade;
    }
    
    
    
     */
}
