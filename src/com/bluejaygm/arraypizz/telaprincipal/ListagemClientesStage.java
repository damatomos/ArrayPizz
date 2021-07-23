package com.bluejaygm.arraypizz.telaprincipal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/***
 *
 * classe utilizada para adicionar a janela de listagem de clientes
 *
 * @throws Exception
 * @serial
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class ListagemClientesStage extends Application {

    private static Stage stage;

    private static double positionX;
    private static double positionY;

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        stage.setResizable(false);
        stage.setX(getPositionX());
        stage.setY(getPositionY());

        //A imagem precisa ser 32x32 ou 16x16
        Image image = new Image(getClass().getResource("/com/bluejaygm/arraypizz/resource/iconpizz.png").toExternalForm());
        Image imagew = new Image(getClass().getResource("/com/bluejaygm/arraypizz/resource/iconpizz.ico").toExternalForm());
        stage.getIcons().addAll(image, imagew);

        Parent root = FXMLLoader.load(getClass().getResource("listagemClientesScene.fxml"));
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
        return stage;
    }

    public static void main(String[] args){launch(args);}
}
