package com.bluejaygm.arraypizz.telaprincipal.arquivosdosusuarios;

import com.bluejaygm.arraypizz.telaprincipal.Cliente;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/***
 *
 * classe utilizada para leitura e escrita em arquivos binarios de objetos de clientes
 *
 * @throws Exception
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class ArquivoBinarioCliente {

    private ObjectOutputStream writeBinary;
    private String file;

    private ObjectInputStream read;

    private ArrayList<Cliente> clientes;


    //pega o arquivo e uma lista de usuarios
    public ArquivoBinarioCliente(String file, ArrayList<Cliente> clientes){
        this.file = file;
        this.clientes = clientes;
    }
    //pega apenas um arquivo
    public ArquivoBinarioCliente(String file){

        this.file = file;
    }

    //Escreve no arquivo binário
    public void writeBinary(){
        //adiciona o usuário a lista e escreve no arquivo
        try {

            ArrayList<Cliente> listaClientes = new ArrayList<>();

            listaClientes = clientes;

            writeBinary = new ObjectOutputStream(new FileOutputStream(file));

            writeBinary.writeObject(listaClientes);

            writeBinary.close();

        }catch (Exception e){

            System.out.println("Erro ao criar o arquivo" + e);
        }

    }
    //ler o arquivo binario
    public ArrayList<Cliente> readBinary(){

        ArrayList<Cliente> ListaClientes = new ArrayList<>();
        try {
            //abre o arquivo
            read = new ObjectInputStream(new FileInputStream(file));
            //pega os clientes do arquivo
            ListaClientes = (ArrayList<Cliente>) read.readObject();

            //fecha o arquivo
            read.close();
            //retorna uma lista de clientes
            return  ListaClientes;

        }catch (Exception e){
            System.out.println("Erro ao ler o arquivo! " + e);

            return null;
        }



    }

}
