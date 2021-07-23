package com.bluejaygm.arraypizz.cadastro.arquivos;

import com.bluejaygm.arraypizz.cadastro.Usuario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/***
 *
 * classe utilizada para a leitura de arquivos com objetos do tipo usuario
 *
 * TODO: 10/06/2019
 * @throws FileNotFoundException
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class ArquivoBinarioLido {

    private ArrayList<Usuario> arquivoLido;

    private ArquivoBinario arquivo;
    //Pegar o arquivo e verificar se ele existe
    public ArquivoBinarioLido(File file) throws FileNotFoundException {

        if(file.exists()){
            this.arquivo = new ArquivoBinario(file.getPath());
        }else{
            throw new FileNotFoundException("O arquivo não existe ou não foi encontrado!");
        }

    }
    //Pegar o conteúdo do arquivo
    public ArrayList<Usuario> getConteudo(){

        arquivoLido = new ArrayList<>();

        arquivoLido = (ArrayList<Usuario>) arquivo.readBinary();
//        System.out.println("ArquivoTexto anteiror");
//        System.out.println("------------------");
//        System.out.println(arquivoLido.toString());
//        System.out.println("------------------");

        return arquivoLido;

    }

}
