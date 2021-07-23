package com.bluejaygm.arraypizz.cadastro.arquivos;

import java.io.*;

public class ArquivoTexto {

    private File file;

    private FileWriter arquivoWrite;
    private PrintWriter writer;

    private FileReader arquivoRead;
    private BufferedReader reader;
    //conteudoInformado informado
    private String conteudoInformado;
    //conteudoInformado lido
    private String conteudoRead = "";
    //Adiciona o arquivo com um conteúdo inicial
    public ArquivoTexto(String arquivo, String conteudoInformado){
        this.file = new File(arquivo);
        this.conteudoInformado = conteudoInformado;
    }
    //adiciona o arquivo
    public ArquivoTexto(String arquivo){ this.file = new File(arquivo); }

    //leitura do arquivo
    public String read(){
        //verifica se há arquivo
        try {
            //Abre o arquivo
            arquivoRead = new FileReader(file);
            reader = new BufferedReader(arquivoRead);
        }catch (Exception e){
            System.out.println("ArquivoTexto não encontrado!");
        }
        //leitura do arquivo
        try {
            while (reader.ready()){

                String c = reader.readLine();

                conteudoRead += c + "\n";

            }
        }catch (Exception e){
            System.out.println("Erro ao ler o arquivo!");
        }
        //retorna o conteudoInformado lido
        return conteudoRead;
    }

    //Escreve o arquivo
    public Boolean write(){
        //verifica se é possível criar o arquivo
        try {
            //Verifica se o arquivo ainda não existe
            if(!file.exists()){
                System.out.println("Criando arquivo");
                arquivoWrite = new FileWriter(file);
                writer = new PrintWriter(arquivoWrite);
            }else{
                System.out.println("Escrevendo no arquivo!");
                writer = new PrintWriter(file);

                writer.write(conteudoInformado);
                writer.close();

            }

            return true;

        }catch (Exception e){
            System.out.println("Não foi possível criar o arquivo");
            return false;
        }

    }


}
