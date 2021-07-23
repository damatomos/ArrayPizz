package com.bluejaygm.arraypizz.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.Toolkit;

/***
 *
 * classe utilizada para criar a tela de login
 *
 * TODO: 10/06/2019
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 */

public class LoginStage extends Application {


    private static Stage estado;

    private static Dimension dimension2D = Toolkit.getDefaultToolkit().getScreenSize();

    private static final double WIDTH = 600;
    private static final double HEIGHT = 600;

    private static double positionX = (dimension2D.getWidth() - WIDTH)/2;
    private static double positionY = (dimension2D.getHeight() - HEIGHT)/2;

    @Override
    public void start(Stage stage) throws Exception {

        this.estado = stage;
        stage.setResizable(false);
        stage.setX(positionX);
        stage.setY(positionY);
        stage.setTitle("ArrayPizz ~ LOGIN");

        System.out.println( "Path: " + getClass().getResource("/com/bluejaygm/arraypizz/resource/iconpizz.ico").toExternalForm());
        System.out.println( "Path: " + getClass().getResource("iconpizza.png").toExternalForm());

        //A imagem precisa ser 32x32 ou 16x16
        Image image = new Image(getClass().getResource("/com/bluejaygm/arraypizz/resource/iconpizz.png").toExternalForm());
        Image imagew = new Image(getClass().getResource("/com/bluejaygm/arraypizz/resource/iconpizz.ico").toExternalForm());
        stage.getIcons().addAll(image, imagew);

        Parent root = FXMLLoader.load(getClass().getResource("loginScene.fxml"));

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

    public static void main(String[] args){launch(args);}
}
