package com.bluejaygm.arraypizz.login;

import com.bluejaygm.arraypizz.cadastro.CadastroStage;
import com.bluejaygm.arraypizz.cadastro.Usuario;
import com.bluejaygm.arraypizz.cadastro.arquivos.ArquivoBinarioLido;
import com.bluejaygm.arraypizz.teladoadm.ADMStage;
import com.bluejaygm.arraypizz.telaprincipal.ListagemClientesStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/***
 *
 * classe utilizada para a configuracao da tela de login
 *
 * TODO: 10/06/2019
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class LoginController implements Initializable {

    @FXML
    private TextField tf_Email;

    @FXML
    private PasswordField pf_Senha;

    @FXML private ImageView iv_logo;

    private File file;
    private ArquivoBinarioLido arquivoBinarioLido;

    private Alert alert;

    private Stage stage;

    private static String nomeUsuario;

    private LoginStage loginStage = new LoginStage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.stage = loginStage.getStage();
        System.out.println("\nEntrando na janela de Login " + stage);
        //adicionar a imagem
        Image image = new Image(getClass().getResource("loginativos/logo_arraypizz.png").toExternalForm());
        iv_logo.setImage(image);
    }

    public void clickEntrar(KeyEvent event){

        if(event.getCode() == KeyCode.ENTER){
            actionEntrar(new ActionEvent());
        }
    }

    //Ação para entrar
    public void actionEntrar(ActionEvent event){
        nomeUsuario = tf_Email.getText();

        if(tf_Email.getText().equalsIgnoreCase("ADMIN") &&
           pf_Senha.getText().equalsIgnoreCase("ROOT")){

            mudarTela(1);

        }
        //Verifica se os dados corresponde ao banco de dados
        else if(verificaLogin(tf_Email.getText(), pf_Senha.getText())){

            mudarTela(3);

        }else{

            alert = new Alert(Alert.AlertType.WARNING, null, ButtonType.OK);
            alert.setTitle("ATENCAO");
            alert.setContentText("Verifique se informou seus dados corretamente!");
            alert.setHeaderText("EMAIL/SENHA NAO COMPATIVEIS");
            alert.initStyle(StageStyle.UTILITY);
            alert.show();

        }


    }
    //Ação para fechar o programa
    public void actionSair(ActionEvent event){

        System.exit(0);

    }
    //Ação para ir para a tela de cadastro
    public void actionCadastrar(ActionEvent event){
        mudarTela(2);
    }

    public boolean verificaLogin(String email, String senha){

        file = new File("dados.dat");

        Boolean cond = true;
        //Verifica se o arquivo existe
        if(file.exists()){

            ArrayList<Usuario> conteudo = null;
            //Verifica se é diferente de nulo
            if((email.equalsIgnoreCase("") || email.isEmpty()) ||
                    (senha.equalsIgnoreCase("") || senha.isEmpty())){

                cond = false;

            }else {

                //Tenta abrir o arquivo pra pegar o conteudo
                try {
                    //Abre o arquivo
                    arquivoBinarioLido = new ArquivoBinarioLido(file);
                    //Pega o conteudo do arquivo
                    conteudo = arquivoBinarioLido.getConteudo();
                } catch (Exception e) {
                    System.out.println("Impossivel pegar conteudo");
                }

                if(conteudo.size() > 0)
                {
                    //Verifica se o email do usuário já foi cadastrado
                    for (int i = 0; i < conteudo.size(); i++) {

                        System.out.println(conteudo.get(i).getEmail());
                        System.out.println(conteudo.get(i).getSenha());

                        if (email.equalsIgnoreCase(conteudo.get(i).getEmail()) &&
                                senha.equalsIgnoreCase(conteudo.get(i).getSenha())) {

                            System.out.println(email);
                            System.out.println(senha);
                            cond = true;
                            break;
                        }else {
                            System.out.println("nao entrou");
                            cond = false;
                            //break;
                        }

                    }
                } else {
                    cond = false;
                }

            }

        }else{
            cond = false;
        }

        System.out.println(cond);
        return cond;
    }

    //mudança de tela
    public void mudarTela(int id){

        switch (id){
            case 1: try {
                    stage.close();
                    ADMStage adm = new ADMStage();
                    adm.setPositionX(loginStage.getStage().getX());
                    adm.setPositionY(loginStage.getStage().getY());
                    adm.start(new Stage());

                }catch (Exception e){
                    System.out.println("Erro ao entrar na tela de administrador!");
                }
                break;
            case 2: try {
                    stage.close();
                    CadastroStage cadastro = new CadastroStage();
                    cadastro.setPositionX(loginStage.getStage().getX());
                    cadastro.setPositionY(loginStage.getStage().getY());
                    cadastro.start(new Stage());

                }catch (Exception e){
                    System.out.println("Erro ao entrar na tela de cadastro! = " + e);
                }break;
            case 3: try {
                    stage.close();

                ListagemClientesStage listagemClientesStage = new ListagemClientesStage();

                listagemClientesStage.setPositionX(loginStage.getStage().getX());
                listagemClientesStage.setPositionY(loginStage.getStage().getY());
                listagemClientesStage.start(new Stage());

                }catch (Exception e){
                    System.out.println("Erro ao entrar na tela principal!" + e);
                }
        }

    }

    public String getNomeUser(){
        return nomeUsuario;
    }

}
