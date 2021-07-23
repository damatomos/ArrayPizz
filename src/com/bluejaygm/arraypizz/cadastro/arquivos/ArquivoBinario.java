package com.bluejaygm.arraypizz.cadastro.arquivos;

import com.bluejaygm.arraypizz.cadastro.Usuario;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/***
 *
 * classe para a leitura e escrita de arquivos binarios de objetos do tipo usuario
 *
 *
 * TODO: 10/06/2019
 * @throws Exception
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class ArquivoBinario {

    private ObjectOutputStream writeBinary;
    private String file;

    private ObjectInputStream read;

    private ArrayList<Usuario> usuarios;


    //pega o arquivo e uma lista de usuarios
    public ArquivoBinario(String file, ArrayList<Usuario> usuarios){
        this.file = file;
        this.usuarios = usuarios;
    }
    //pega apenas um arquivo
    public ArquivoBinario(String file){

        this.file = file;
    }

    //Escreve no arquivo binário
    public void writeBinary(){
        //adiciona o usuário a lista e escreve no arquivo
        try {

            ArrayList<Usuario> listaUsuarios = new ArrayList<>();

            listaUsuarios = usuarios;

            writeBinary = new ObjectOutputStream(new FileOutputStream(file));

            writeBinary.writeObject(listaUsuarios);

            writeBinary.close();

        }catch (Exception e){

            System.out.println("Erro ao criar o arquivo" + e);
            //new JOptionPane().showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }

    }

    public ArrayList<Usuario> readBinary(){

        ArrayList<Usuario> ListaUsuarios = new ArrayList<>();
        try {
            read = new ObjectInputStream(new FileInputStream(file));

            ListaUsuarios = (ArrayList<Usuario>) read.readObject();

            read.close();

            return  ListaUsuarios;

        }catch (Exception e){
            System.out.println("Erro ao ler o arquivo! " + e);

            return null;
        }



    }



}
