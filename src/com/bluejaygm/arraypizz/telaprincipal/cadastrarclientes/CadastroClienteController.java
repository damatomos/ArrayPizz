package com.bluejaygm.arraypizz.telaprincipal.cadastrarclientes;

import com.bluejaygm.arraypizz.login.LoginController;
import com.bluejaygm.arraypizz.telaprincipal.Cliente;
import com.bluejaygm.arraypizz.telaprincipal.ListagemClientesController;
import com.bluejaygm.arraypizz.telaprincipal.ListagemClientesStage;
import com.bluejaygm.arraypizz.telaprincipal.arquivosdosusuarios.ArquivoBinarioCliente;
import com.bluejaygm.arraypizz.telaprincipal.arquivosdosusuarios.ArquivoBinarioClienteLido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/***
 *
 * classe utilizada para cadastrar clientes
 *
 * @throws Exception
 * @throws FileNotFoundException
 * @serial
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class CadastroClienteController implements Initializable {

    @FXML private TextField tf_Nome;
    @FXML private TextField tf_CPF;
    @FXML private TextField tf_Telefone;
    @FXML private TextField tf_Endereco;

    private static Stage stage;


    private File file;
    private ArquivoBinarioCliente arquivo;
    private ArquivoBinarioClienteLido arquivoLido;

    private LoginController telaLogin = new LoginController();

    private String arquivoUsuario;

    private ArrayList<Cliente> listaClientes = new ArrayList<>();

    private ListagemClientesStage listagemCliente = new ListagemClientesStage();;
    private ListagemClientesController listagemClientesController = new ListagemClientesController();
    private Cliente clienteAtual;
    private boolean isCadastrando;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stage = new CadastroClienteStage().getStage();

        clienteAtual = listagemClientesController.getClienteAtual();
        isCadastrando = listagemClientesController.isIsCadastrando();
        System.out.println("Cliente atual= " + clienteAtual);
        arquivoUsuario = telaLogin.getNomeUser();

        file = new File(arquivoUsuario + ".dat");


        try {
            arquivoLido = new ArquivoBinarioClienteLido(file);

            System.out.println(arquivoLido.getConteudo());

        }catch (FileNotFoundException e){

        }

        if(isCadastrando){

        }else {
            valoresEditaveis();
        }


    }

    public void actionConfirmar(ActionEvent event) {

        if(isCadastrando){
            System.out.println("tela de cadastro");
            cadastrarCliente();
        }else {
            System.out.println("tela de edicao");
            editarClientes();
        }


    }

    public void actionCancelar(ActionEvent event){
        try {
            stage.close();
            listagemCliente.start(new Stage());
        }catch (Exception e){
            System.out.println("Erro ao ir para a tela de listagem");
        }
    }

    public void editarClientes(){
        ListagemClientesController listagemClientesController = new ListagemClientesController();
        ArrayList<Cliente> editClientes = new ArrayList<>();
        Cliente cliente = listagemClientesController.getClienteAtual();
        System.out.println("\ntela editar cliente atual= " + cliente);

        try {
            arquivoLido = new ArquivoBinarioClienteLido(file);
            editClientes = arquivoLido.getConteudo();
        }catch (FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
        }

        cliente.setNome(tf_Nome.getText());
        cliente.setCpf(tf_CPF.getText());
        cliente.setTelefone(tf_Telefone.getText());
        cliente.setEndereco(tf_Endereco.getText());

        for(int i = 0; i < editClientes.size(); i++){
            if(editClientes.get(i).getCodigo() == cliente.getCodigo()){
                System.out.println(editClientes.get(i) + " e o mesmo que " + cliente);
                editClientes.get(i).setNome(cliente.getNome());
                editClientes.get(i).setCpf(cliente.getCpf());
                editClientes.get(i).setTelefone(cliente.getTelefone());
                editClientes.get(i).setEndereco(cliente.getEndereco());
            }
        }

        arquivo = new ArquivoBinarioCliente(file.getPath(), editClientes);
        arquivo.writeBinary();
        System.out.println("ARQUIVO EDITADO COM SUCESSO!");

        try {
            stage.close();
            listagemCliente.start(new Stage());
        }catch (Exception e){
            System.out.println("Erro ao ir para a tela de listagem");
        }



    }

    public void cadastrarCliente(){
        Cliente cliente = new Cliente(tf_Nome.getText(), tf_CPF.getText(),
                tf_Telefone.getText(), tf_Endereco.getText());


        if (file.exists()) {

            try {
                arquivoLido = new ArquivoBinarioClienteLido(file);

                listaClientes = arquivoLido.getConteudo();

            } catch (FileNotFoundException e) {
                System.out.println("Impossível pegar o conteudo do arquivo!");
            }

            listaClientes.add(cliente);

            for(int i = 0; i < listaClientes.size(); i++){
                listaClientes.get(i).setCodigo(i + 1);
            }

            arquivo = new ArquivoBinarioCliente(file.getPath(), listaClientes);
            arquivo.writeBinary();
            System.out.println("Arquivo escrito com sucesso");


        } else {
            cliente.setCodigo(1);
            listaClientes.add(cliente);

            arquivo = new ArquivoBinarioCliente(file.getPath(), listaClientes);
            arquivo.writeBinary();
            System.out.println("Arquivo escrito com sucesso");
        }
        try {
            stage.close();
            listagemCliente.start(new Stage());
        }catch (Exception e){
            System.out.println("Erro ao ir para a tela de listagem");
        }
    }

    public void valoresEditaveis(){
        tf_Nome.setText(clienteAtual.getNome());
        tf_CPF.setText(clienteAtual.getCpf());
        tf_Telefone.setText(clienteAtual.getTelefone());
        tf_Endereco.setText(clienteAtual.getEndereco());

    }

}
