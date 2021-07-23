package com.bluejaygm.arraypizz;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

/***
 *
 * Classe usada para adicionar avisos
 *
 * TODO: 15/06/2019
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class Alerta {

    private Alert alert;

    public Alerta(Alert.AlertType alertType,
                  String title, String contentText,
                  String headerText, StageStyle stageStyle){
         alert = new Alert(alertType, null, ButtonType.CLOSE);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.initStyle(stageStyle);
        alert.show();
    }

    public Alerta(Alert.AlertType alertType,
                  String title,
                  String contentText,
                  String headerText,
                  StageStyle stageStyle, ButtonType buttonType){
        alert = new Alert(alertType, null, buttonType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.initStyle(stageStyle);
        alert.show();
    }

    public Alert getAlert(){
        return alert;
    }



}
