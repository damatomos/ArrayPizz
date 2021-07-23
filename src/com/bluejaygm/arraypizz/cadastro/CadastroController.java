package com.bluejaygm.arraypizz.cadastro;

import com.bluejaygm.arraypizz.Alerta;
import com.bluejaygm.arraypizz.cadastro.arquivos.ArquivoBinario;
import com.bluejaygm.arraypizz.cadastro.arquivos.ArquivoBinarioLido;
import com.bluejaygm.arraypizz.cadastro.arquivos.ArquivoTexto;
import com.bluejaygm.arraypizz.cadastro.arquivos.ArquivoTextoLido;
import com.bluejaygm.arraypizz.login.LoginStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 *
 * classe usado para configuracao da tela de cadastro
 *
 * TODO: 10/06/2019
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class CadastroController implements Initializable {

    private ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();

    //Atributos da tela de cadastro
    @FXML private TextField tf_Name;
    @FXML private TextField tf_Email;
    @FXML private PasswordField pf_Password;
    @FXML private TextField tf_ConfirmEmail;
    @FXML private PasswordField pf_ConfirmPassword;
    @FXML private ImageView iv_background;

    //Atributos para manipulacao de dados
    private ArquivoTexto arquivo;
    private ArquivoBinario arquivoBinario;
    private File file;
    private ArquivoTextoLido arquivoLido;
    private ArquivoBinarioLido arquivoBinarioLido;
    private String conteudoLido;
    private ArrayList<Usuario> listaConteudoLido;

    private Stage stage;

    private CadastroStage cadastroStage = new CadastroStage();

    //Metodo que sera chamado quando a tela iniciar
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Pegando o estado da tela
        this.stage = new CadastroStage().getStage();

        System.out.println("\nEntrando na janela de Cadastro " + stage);

        //Configurando a imagem de fundo
        Image image = new Image(getClass().getResource("ativos/pizzas.png").toExternalForm());
        iv_background.setImage(image);
    }

    //Acao para voltar pra tela inicial
    public void actionVoltar(ActionEvent event){
        mudarTela();
    }

    //Acao para cadastrar um usuario
    public void actionCadastrar(ActionEvent event){

        //Pega as informacoes do usuario a ser cadastrado
        Usuario usuario = new Usuario(tf_Name.getText().toString(),
                                        tf_Email.getText().toString(),
                                        pf_Password.getText().toString());

        //inicializa o arquivo binario
        file = new File("dados.dat");

        //Verifica se o arquivo existe
        if (file.exists()){

            System.out.println("Arquivo Existe");

            //Verifica se os dados preenchidos sao validos
            if(verificaCadastro(usuario)){
                /*Com o arquivo existente, lemos os dados
                 *do arquivo e incrementamos o novo usuario
                 */

                try {
                    //Abre o arquivo
                    arquivoBinarioLido = new ArquivoBinarioLido(file);
                    //Pega o conteudo do arquivo
                    listaConteudoLido = arquivoBinarioLido.getConteudo();

                }catch (Exception e){
                    System.out.println("Impossivel abrir o arquivo!");
                }

                //adiciona o novo usuario ao arquivo
                listaConteudoLido.add(usuario);

                //reescreve os dados
                arquivoBinario = new ArquivoBinario(file.getPath(), listaConteudoLido);
                arquivoBinario.writeBinary();

                System.out.println("Usuario cadastrado: " + usuario);

                Alerta alerta = new Alerta(Alert.AlertType.CONFIRMATION, "CONFIRMADO",
                        "O usuario " + tf_Name.getText() + " foi cadastrado!",
                        "USUARIO CADASTRADO COM SUCESSO!", StageStyle.UTILITY, ButtonType.OK);

                limparDados();

            }

        }else{

            /*
            * Caso o arquivo nao exista
            * verifica se os dados preenchidos
            * sao validos e cria um arquivo
            * com o usuario informado
            * */

            if(verificaCadastro(usuario)){

                listaConteudoLido = new ArrayList<>();
                listaConteudoLido.add(usuario);
                //escreve o usuario e cria o arquivo
                arquivoBinario = new ArquivoBinario(file.getPath(), listaConteudoLido);
                arquivoBinario.writeBinary();

                System.out.println("Usuario cadastrado: " + usuario);

                new Alerta(Alert.AlertType.CONFIRMATION, "CONFIRMADO",
                        "O usuario " + tf_Name.getText() + " foi cadastrado!",
                        "USUARIO CADASTRADO COM SUCESSO!", StageStyle.UTILITY, ButtonType.OK);

                limparDados();

            }
        }

        //lista o usuario cadastrado
        usuariosCadastrados.add(usuario);
    }

    public boolean verificaCadastro(Usuario usuario){
        //adiciona restricoes no email e na senha
                                              //damato458     @     gmail    .com ou .com.br
        Pattern regexEmail = Pattern.compile("^([a-z(.)0-9]+){3,}(@)([a-z]{3,6})(([.])([a-z]{2,3}))+");
        Matcher verificaEmail = regexEmail.matcher(usuario.getEmail());
        //System.out.println(verificaEmail.group(1));

        Pattern regexSenha = Pattern.compile("^[a-z0-9]{6,15}");
        Matcher verificaSenha = regexSenha.matcher(usuario.getSenha());

        Pattern regexNome = Pattern.compile("^([ a-zA-Z0-9].{2,50})");
        Matcher verificaNome = regexNome.matcher(usuario.getNome());

        Boolean verificacaoAprovada = true;


        //Verifica se o arquivo existe
        if(file.exists()){

            ArrayList<Usuario> conteudo = null;
            //Verifica se e nulo
            if(isNull()){
                verificacaoAprovada = false;

                new Alerta(Alert.AlertType.WARNING, "ATENCAO",
                        "Verifique se ha alguma informacao nao adicionada!",
                        "CAMPOS NAO PREENCHIDOS", StageStyle.UTILITY);

            }//verifica se o email e senha e valido
            else if(verificaNome.matches() && verificaEmail.matches() && verificaSenha.matches()
                    && tf_Email.getText().equals(tf_ConfirmEmail.getText())
                    && pf_Password.getText().equalsIgnoreCase(pf_ConfirmPassword.getText())){

                //Tenta abrir o arquivo pra pegar o conteudo
                try {
                    //Abre o arquivo
                    arquivoBinarioLido = new ArquivoBinarioLido(file);
                    //Pega o conteudo do arquivo
                    conteudo = arquivoBinarioLido.getConteudo();
                } catch (Exception e) {
                    System.out.println("Impossivel pegar conteudo");
                }

                //Verifica se o email digitado ja foi cadastrado
                for (int i = 0; i < conteudo.size(); i++) {
                    if (usuario.getEmail().equalsIgnoreCase(conteudo.get(i).getEmail())) {

                        verificacaoAprovada = false;

                        new Alerta(Alert.AlertType.WARNING, "ATENCAO", "O email " + tf_Email.getText() +
                                         " ja foi cadastrado, adicione um novo email!",
                                "EMAIL JA CADASTRADO!", StageStyle.UTILITY, ButtonType.OK);
                    }

                }
                //Se ele foi cadastrado informa que houve cadastro
                if(verificacaoAprovada){
                    System.out.println("Cadastro efetuado com sucesso!");
                }

            }else{
                verificacaoAprovada = false;

                new Alerta(Alert.AlertType.WARNING, "ATENCAO",
                        "Verifique se seu nome " + tf_Name.getText().toUpperCase() +
                                " possui 3 ou mais caracteres e " +
                                "Verifique se seu email " + tf_Email.getText().toUpperCase() +
                                " e valido e igual a sua confirmacao " + tf_ConfirmEmail.getText().toUpperCase() +
                                " ou se sua senha e valida(Possui entre 6 a 15 caracteres)" +
                                " e possui confirmacao correta!" ,
                        "DADOS INCOMPATIVEIS PARA CADASTRO!", StageStyle.UTILITY, ButtonType.OK);
            }

        }else {

            if(isNull()){

                verificacaoAprovada = false;

                new Alerta(Alert.AlertType.WARNING, "ATENCAO",
                        "Verifique se ha alguma informacao nao adicionada!",
                        "CAMPOS NAO PREENCHIDOS", StageStyle.UTILITY, ButtonType.CLOSE);

            }//verifica se o email e valido
            else if(verificaNome.matches() && verificaEmail.matches() && verificaSenha.matches()
                    && tf_Email.getText().equals(tf_ConfirmEmail.getText())
                    && pf_Password.getText().equalsIgnoreCase(pf_ConfirmPassword.getText())){

                verificacaoAprovada = true;

                System.out.println("Usuario cadastro com sucesso!");

            }else {
                verificacaoAprovada = false;

                new Alerta(Alert.AlertType.WARNING, "ATENCAO",
                        "Verifique se seu nome " + tf_Name.getText().toUpperCase() +
                                " possui 3 ou mais caracteres e " +
                        "Verifique se seu email " + tf_Email.getText().toUpperCase() +
                                " e valido e igual a sua confirmacao " + tf_ConfirmEmail.getText().toUpperCase() +
                                " ou se sua senha e valida(Possui entre 6 a 15 caracteres)" +
                                " e possui confirmacao correta!" ,
                        "DADOS INCOMPATIVEIS PARA CADASTRO!", StageStyle.UTILITY, ButtonType.OK);
            }
        }

        return verificacaoAprovada;
    }

    //atualiza os dados depois do cadastro
    public void limparDados(){
        this.tf_Name.setText("");
        this.tf_Email.setText("");
        this.tf_ConfirmEmail.setText("");
        this.pf_Password.setText("");
        this.pf_ConfirmPassword.setText("");

        System.out.println("Dados de registro limpados com sucesso!");
    }

    public void mudarTela(){

        try{
            stage.close();

            LoginStage login = new LoginStage();
            //adiciona a posicao da janela para a nova janela
            login.setPositionX(cadastroStage.getStage().getX());
            login.setPositionY(cadastroStage.getStage().getY());
            login.start(new Stage());

        }catch (Exception e){
            System.out.println("Erro ao entrar na tela de login");
        }

    }

    //Verifica se os dados informados sao nulos
    public boolean isNull(){

        if((tf_Name.getText().equalsIgnoreCase("") || tf_Name.getText().isEmpty()) ||
                (tf_Email.getText().equalsIgnoreCase("") || tf_Email.getText().isEmpty()) ||
                (tf_ConfirmEmail.getText().equalsIgnoreCase("") || tf_ConfirmEmail.getText().isEmpty()) ||
                (pf_Password.getText().equalsIgnoreCase("") || pf_ConfirmPassword.getText().isEmpty()) ||
                (pf_ConfirmPassword.getText().equalsIgnoreCase("") || pf_ConfirmPassword.getText().isEmpty())
        ){
            return true;
        }else{
            return false;
        }

    }

    //Retorna os usuarios cadastrados
    public ArrayList<Usuario> getUsuariosCadastrados() {
        return usuariosCadastrados;
    }

}
