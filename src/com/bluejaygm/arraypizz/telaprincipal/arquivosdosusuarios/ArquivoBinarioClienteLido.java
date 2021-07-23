package com.bluejaygm.arraypizz.telaprincipal.arquivosdosusuarios;

import com.bluejaygm.arraypizz.telaprincipal.Cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/***
 *
 * classe utilizada para ler objetos de clientes de arquivos binarios
 *
 * @throws FileNotFoundException
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class ArquivoBinarioClienteLido {

    private ArquivoBinarioCliente arquivoBinarioCliente;
    private File file;

    private ArrayList<Cliente> arquivoLido;

    public ArquivoBinarioClienteLido(File file) throws FileNotFoundException{

        this.file = file;

        if(file.exists()){
            this.arquivoBinarioCliente = new ArquivoBinarioCliente(file.getPath());
        }else{
            throw new FileNotFoundException("O arquivo não existe ou não foi encontrado!");
        }

    }

    public ArrayList<Cliente> getConteudo(){

        arquivoLido = new ArrayList<>();

        arquivoLido = (ArrayList<Cliente>) arquivoBinarioCliente.readBinary();

        return arquivoLido;

    }

}
