package com.bluejaygm.arraypizz.cadastro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/***
 *
 * classe da janela de cadastro
 *
 * TODO: 10/06/2019
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class CadastroStage extends Application {

    private static Stage estado;

    private static double positionX;
    private static double positionY;

    @Override
    public void start(Stage stage) throws Exception {

        estado = stage;
        stage.setResizable(false);
        stage.setX(getPositionX());
        stage.setY(getPositionY());
        stage.setTitle("ArrayPizz ~ CADASTRAR");

        //A imagem precisa ser 32x32 ou 16x16
        Image image = new Image(getClass().getResource("/com/bluejaygm/arraypizz/resource/iconpizz.png").toExternalForm());
        Image imagew = new Image(getClass().getResource("/com/bluejaygm/arraypizz/resource/iconpizz.ico").toExternalForm());
        stage.getIcons().addAll(image, imagew);

        Parent root = FXMLLoader.load(getClass().getResource("cadastroScene.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public Stage getStage() {
        return estado;
    }

    //public static void main(String[] args){launch(args);}
}
