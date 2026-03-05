package com.example.csc325_firebase_webview_auth.view;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.util.Duration;

public class SplashScreenController {

    @FXML
    public void init(){
        PauseTransition pause =  new PauseTransition(Duration.seconds(3));

        pause.setOnFinished(event -> {
            try{
                App.setRoot("/files/AccessFBView.fxml");
            } catch (Exception err) {
                err.printStackTrace();
            }
        });

        pause.play();
    }

}
