package com.bluejaygm.arraypizz.telaprincipal;

import com.bluejaygm.arraypizz.Alerta;
import com.bluejaygm.arraypizz.login.LoginController;
import com.bluejaygm.arraypizz.login.LoginStage;
import com.bluejaygm.arraypizz.telaprincipal.arquivosdosusuarios.ArquivoBinarioCliente;
import com.bluejaygm.arraypizz.telaprincipal.arquivosdosusuarios.ArquivoBinarioClienteLido;
import com.bluejaygm.arraypizz.telaprincipal.cadastrarclientes.CadastroClienteStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/***
 *
 * classe utilizada para listar clientes
 *
 * @throws Exception
 * @throws FileNotFoundException
 * @serial
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class ListagemClientesController implements Initializable {

    @FXML private TableView tableview_Clientes;

    @FXML private TableColumn<Cliente, String> tablecolumn_Nome;
    @FXML private TableColumn<Cliente, String> tablecolumn_CPF;

    @FXML private Label lblCodigo;
    @FXML private Label lblNome;
    @FXML private Label lblCPF;
    @FXML private Label lblTelefone;
    @FXML private TextArea ta_Endereco;


    private ObservableList<Cliente> obsClientes;
    private List<Cliente> clientes = new ArrayList<>();

    private Stage stage;

    private LoginController telaLogin = new LoginController();
    private String usuario;

    private ListagemClientesStage listagemClientesStage = new ListagemClientesStage();

    private File file;
    private ArquivoBinarioCliente arquivo;
    private ArquivoBinarioClienteLido arquivoLido;

    private static Cliente clienteAtual;

    private static boolean isCadastrando;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //PEGAR O ESTADO DA TELA
        this.stage = new ListagemClientesStage().getStage();

        System.out.println("\nEntrando na janela principal " + stage);
        //PEGAR O EMAIL DO USUARIO LOGADO ATUALMENTE
        usuario = telaLogin.getNomeUser();
        //ESCREVER UM ARQUIVO COM O EMAIL DO USUARIO
        file = new File(usuario + ".dat");

        System.out.println("arquivo= " + file);
        stage.setTitle("CLIENTES DO USUARIO: " + usuario.toUpperCase());

        //PEGAR O USUARIO SELECIONADO
        tableview_Clientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItensClientes((Cliente) newValue)
        );

        carregarClientes();
    }

    public void actionSair(ActionEvent event){
        try {
            stage.close();
            LoginStage login = new LoginStage();

            login.setPositionX(listagemClientesStage.getStage().getX());
            login.setPositionY(listagemClientesStage.getStage().getY());
            login.start(new Stage());
        }catch (Exception e){
            System.out.println("Erro ao voltar para a tela de telaLogin");
        }
    }

    public void actionNovo(ActionEvent event){
        CadastroClienteStage cadastroCliente = new CadastroClienteStage();
        isCadastrando = true;
        System.out.println("Cadastrando? " + isCadastrando);

        try {
            stage.close();
            cadastroCliente.start(new Stage());
        }catch (Exception e){
            System.out.println("Erro ao entrar na tela de cadastro de cliente!");
        }
    }

    public void actionEditar(ActionEvent event){

        if(file.exists()) {

            if (clienteAtual != null) {
                CadastroClienteStage cadastroCliente = new CadastroClienteStage();
                isCadastrando = false;
                System.out.println("Editando? " + !isCadastrando);

                try {
                    stage.close();
                    cadastroCliente.start(new Stage());
                } catch (Exception e) {
                    System.out.println("Erro ao entrar na tela de cadastro de cliente!");
                }
            } else {
                new Alerta(Alert.AlertType.WARNING, "ATENCAO",
                        "Verifique se algum cliente foi clicado para edicao",
                        "IMPOSSIVEL EDITAR", StageStyle.UTILITY, ButtonType.OK);
            }
        }else{
            new Alerta(Alert.AlertType.WARNING, "ATENCAO", "Adicione clientes clicando em NOVO",
                    "NAO HA CLIENTE PARA EDITAR", StageStyle.UTILITY);
        }

        //adiciona um valor nulo quando um cliente é removido(evita outros serem apagados sequencialmente)

        selecionarItensClientes(null);
        setClienteAtual(null);

    }

    public void actionDeletar(ActionEvent event){
        ArrayList<Cliente> clientesAtuais = new ArrayList<>();

        if(file.exists()){

            if(getClienteAtual() != null){

                try {
                    arquivoLido = new ArquivoBinarioClienteLido(file);
                }catch (FileNotFoundException e){
                    System.out.println("Erro ao abrir o arquivo!");
                }

                //arquivoLido = new ArquivoBinarioClienteLido(file);
                clientesAtuais = arquivoLido.getConteudo();

                for(int i = 0; i < clientesAtuais.size(); i++){

                    if(clientesAtuais.get(i).getCodigo() == getClienteAtual().getCodigo()){
                        String cAtual = clientesAtuais.get(i).getNome();
                        clientesAtuais.remove(i);
                        new Alerta(Alert.AlertType.INFORMATION, "INFORMACAO",
                                "Usuario " + cAtual + " removido com sucesso!",
                                "CLIENTE REMOVIDO COM SUCESSO!", StageStyle.UTILITY);
                    }else{
                        continue;
                    }
                }

                for(int i = 0; i < clientesAtuais.size(); i++){
                    clientesAtuais.get(i).setCodigo(i + 1);
                }

                arquivo = new ArquivoBinarioCliente(file.getPath(), clientesAtuais);
                arquivo.writeBinary();

            }else{
                new Alerta(Alert.AlertType.WARNING, "ATENCAO", "Clique em algum cliente para remover",
                        "NAO HA CLIENTE PARA REMOVER", StageStyle.UTILITY);
            }

        }else {
            new Alerta(Alert.AlertType.WARNING, "ATENCAO", "Adicione clientes clicando em NOVO",
                    "NAO HA CLIENTE PARA REMOVER", StageStyle.UTILITY);
        }

        //adiciona um valor nulo quando um cliente é removido(evita outros serem apagados sequencialmente)
        if(clientesAtuais.size() > 0){
            selecionarItensClientes(null);
            setClienteAtual(null);
        }else {
            setClienteAtual(null);
        }

        carregarClientes();

    }


    public void carregarClientes(){

        if(file.exists()){
            try {
                arquivoLido = new ArquivoBinarioClienteLido(file);
            }catch (FileNotFoundException e){
                System.out.println("Erro ao abrir o arquivo!");
            }

            clientes = arquivoLido.getConteudo();

            tablecolumn_Nome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
            tablecolumn_CPF.setCellValueFactory(new PropertyValueFactory<Cliente, String >("cpf"));

            System.out.println("\n\n~ CLIENTES EXISTENTES ~\n");
            for(Cliente e : clientes){
                System.out.println(e);
            }


            obsClientes = FXCollections.observableArrayList(clientes);

            tableview_Clientes.setItems(obsClientes);
        }else {
            System.out.println("Arquivo inexistente, impossível carregar dados!");
        }

    }

    public void selecionarItensClientes(Cliente cliente){

        if(cliente != null){
            lblCodigo.setText(Integer.toString(cliente.getCodigo()));
            lblNome.setText(cliente.getNome());
            lblCPF.setText(cliente.getCpf());
            lblTelefone.setText(cliente.getTelefone());
            ta_Endereco.setText(cliente.getEndereco());
            setClienteAtual(cliente);

        }



        System.out.println("Cliente atual pego= " + getClienteAtual());
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public boolean isIsCadastrando(){return isCadastrando;}

}
