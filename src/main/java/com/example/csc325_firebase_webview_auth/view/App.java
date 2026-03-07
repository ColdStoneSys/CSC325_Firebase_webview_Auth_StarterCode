package com.example.csc325_firebase_webview_auth.view;


import com.example.csc325_firebase_webview_auth.model.FirestoreContext;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;

import java.awt.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {

    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();
    public static Scene scene;
    public static Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();
        mainScene = new Scene(loadFXML("/files/AccessFBView.fxml"));
        scene = new Scene(loadFXML("/files/SplashScreen.fxml"));
        scene.getStylesheets().add(getClass().getResource("/files/main.css").toExternalForm());
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setTitle("CSC325 - Module 6 Application");

        primaryStage.setScene(scene);
        primaryStage.show();

        // Go to the main screen after 3.5s
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run(){
                        Platform.runLater(() ->{
                            primaryStage.setScene(mainScene);
                            primaryStage.setHeight(600.0);
                            primaryStage.setWidth(900.0);
                        });

                    }
                }, 3500
        );


    }

    public static void setRoot(String fxml) throws IOException {
        mainScene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml ));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

