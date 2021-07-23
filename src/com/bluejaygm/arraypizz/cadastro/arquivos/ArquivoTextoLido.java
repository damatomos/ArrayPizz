package com.bluejaygm.arraypizz.cadastro.arquivos;

import java.io.File;
import java.io.FileNotFoundException;

public class ArquivoTextoLido {

    private ArquivoTexto arquivo;
    //Pegar o arquivo e verificar se ele existe
    public ArquivoTextoLido(File file) throws FileNotFoundException {

         if(file.exists()){
             this.arquivo = new ArquivoTexto(file.getPath());
         }else{
             throw new FileNotFoundException("O arquivo não existe ou não foi encontrado!");
         }

    }
    //Pegar o conteúdo do arquivo
    public String getConteudo(){

        String conteudoArquivo = arquivo.read();
        System.out.println("ArquivoTexto anteiror");
        System.out.println("------------------");
        System.out.println(conteudoArquivo);
        System.out.println("------------------");

        return conteudoArquivo;

    }

}
