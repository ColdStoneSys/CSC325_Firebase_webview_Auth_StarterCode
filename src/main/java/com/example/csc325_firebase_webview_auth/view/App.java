package com.example.csc325_firebase_webview_auth.view;


import com.example.csc325_firebase_webview_auth.model.FirestoreContext;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;

import java.awt.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.util.Timer;


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
        AnchorPane anchorPane = new AnchorPane();
        scene = new Scene(anchorPane);
        scene.getStylesheets().add(getClass().getResource("/files/main.css").toExternalForm());
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setTitle("CSC325 - Module 6 Application");

        Label loadingTXT = new Label("Loading...");
        loadingTXT.getStyleClass().add("loadTXT");
        ProgressBar loadBar = new ProgressBar();
        loadBar.getStyleClass().add("loadBar");

        loadingTXT.setContentDisplay(ContentDisplay.CENTER);
        loadingTXT.setPrefWidth(800);
        loadingTXT.setPrefHeight(600);
        loadingTXT.setAlignment(Pos.BASELINE_CENTER);

        loadBar.setPrefWidth(785);
        loadBar.setPrefHeight(15);


        anchorPane.getChildren().add(loadBar);
        anchorPane.getChildren().add(loadingTXT);



        primaryStage.setScene(scene);
        primaryStage.show();

        // Go to the main screen after 3.5s
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run(){
                        Platform.runLater(() ->{
                            primaryStage.setScene(mainScene);
                            primaryStage.setHeight(571.0);
                            primaryStage.setWidth(894.0);
                        });

                    }
                }, 3500
        );


    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml ));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

