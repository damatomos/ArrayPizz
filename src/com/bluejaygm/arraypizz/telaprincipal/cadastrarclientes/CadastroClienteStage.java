package com.bluejaygm.arraypizz.telaprincipal.cadastrarclientes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/***
 *
 * classe utilizada para adicionar a janela de cadastro de clientes
 *
 * @throws Exception
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class CadastroClienteStage extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;

        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        //A imagem precisa ser 32x32 ou 16x16
        Image image = new Image(getClass().getResource("/com/bluejaygm/arraypizz/resource/iconpizz.png").toExternalForm());
        Image imagew = new Image(getClass().getResource("/com/bluejaygm/arraypizz/resource/iconpizz.ico").toExternalForm());
        stage.getIcons().addAll(image, imagew);

        Parent root = FXMLLoader.load(getClass().getResource("cadastroClienteScene.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.showAndWait();
    }

    public Stage getStage(){
        return stage;
    }
}
