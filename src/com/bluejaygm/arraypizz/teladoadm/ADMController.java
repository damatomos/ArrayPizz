package com.bluejaygm.arraypizz.teladoadm;

import com.bluejaygm.arraypizz.Alerta;
import com.bluejaygm.arraypizz.cadastro.Usuario;
import com.bluejaygm.arraypizz.cadastro.arquivos.ArquivoBinario;
import com.bluejaygm.arraypizz.cadastro.arquivos.ArquivoBinarioLido;
import com.bluejaygm.arraypizz.login.LoginStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
 * classe utilizada para configurar a tela de administrador
 *
 * @throws FileNotFoundException
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class ADMController implements Initializable {


    private Stage stage;

    @FXML private ListView listview_Usuarios;
    @FXML private Label lbl_quantUsuarios;

    private List<Usuario> listaUsuarios = new ArrayList<>();
    private ObservableList<Usuario> obsUsuarios;

    private File file;
    private ArquivoBinarioLido arquivoBinarioLido;
    private ArquivoBinario arquivoBinario;

    private Usuario user;

    private ADMStage admStage = new ADMStage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        file = new File("dados.dat");

        this.stage = new ADMStage().getStage();
        System.out.println("\nMODO ADMINISTRADOR ATIVADO! - " + stage);

        try {
            carregarUsuarios();
        }catch (FileNotFoundException e){
            System.out.println("Erro ao carregar usuarios na listview");
        }

        listview_Usuarios.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewUsuarios((Usuario) newValue)
        );

    }

    public void actionVoltar(){

        stage.close();

        try {
            LoginStage login = new LoginStage();

            login.setPositionX(admStage.getStage().getX());
            login.setPositionY(admStage.getStage().getY());

            login.start(new Stage());
        }catch (Exception e){
            System.out.println("Erro ao voltar para tela de login!");
        }

    }

    public void selecionarItemTableViewUsuarios(Usuario usuario){
        Usuario user = usuario;
        System.out.println("usuario selecionado: " + user);
        setUser(usuario);

    }

    public void setUser(Usuario user){
        this.user = user;
    }

    public Usuario getUser(){
        return this.user;
    }

    public void remover(){
        ArrayList<Usuario> lista = new ArrayList<>();

        if(file.exists()){
            lista = arquivoBinarioLido.getConteudo();

            if(lista.size() > 0){
                System.out.println("user= " + getUser());

                if(getUser() != null){

                    for(int i = 0; i < lista.size(); i++){
                        if(getUser().getEmail().equals(lista.get(i).getEmail())){
                            System.out.println("Usuario encontrado para ser removido!");
                            lista.remove(i);
                            System.out.println("Usuario removido com sucesso!");
                        }
                    }

                    arquivoBinario = new ArquivoBinario(file.getPath(), lista);
                    arquivoBinario.writeBinary();

                    try {
                        carregarUsuarios();
                    }catch (Exception e){
                        System.out.println("impossivel carregar usuarios");
                    }

                    System.out.println(lista);
                }else{
                    new Alerta(Alert.AlertType.WARNING, "ATENCAO", "Selecione um usuario para remover",
                            "NENHUM USUARIO SELECIONADO!", StageStyle.UTILITY, ButtonType.CLOSE);
                }

            }else {
                System.out.println("Nao ha elementos na lista para remover");
            }

        }else {
            System.out.println("arquivo nao existe! Impossivel buscar arquivos para remover");
        }

    }

    public void carregarUsuarios() throws FileNotFoundException {

        if(file.exists()){
            arquivoBinarioLido = new ArquivoBinarioLido(file);

            listaUsuarios = arquivoBinarioLido.getConteudo();

            lbl_quantUsuarios.setText(Integer.toString(listaUsuarios.size()));

            obsUsuarios = FXCollections.observableArrayList(listaUsuarios);

            listview_Usuarios.setItems(obsUsuarios);
        }else {
            System.out.println("Arquivo inexistente");
        }

    }
}
