package com.example.demo;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;


public class EndGame {

    private static EndGame singleInstance = null;
    String GameModeChoiceString = Controller.GameModeChoiceString;
    private EndGame(){

    }
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score){
        Text text = new Text("GAME OVER");
        text.relocate(250,250);
        text.setFont(Font.font(80));
        root.getChildren().add(text);


        Text scoreText = new Text(score+"");
        scoreText.setFill(Color.BLACK);
        scoreText.relocate(250,600);
        scoreText.setFont(Font.font(80));
        root.getChildren().add(scoreText);

        Button quitButton = new Button("QUIT");
        quitButton.setPrefSize(100,30);
        quitButton.setTextFill(Color.BLACK);
        root.getChildren().add(quitButton);
        quitButton.relocate(100,700);

        Button homeButton = new Button("Back to main page");
        homeButton.setPrefSize(120,30);
        homeButton.setTextFill(Color.BLACK);
        root.getChildren().add(homeButton);
        homeButton.relocate(700,700);

        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Quit Dialog");
                alert.setHeaderText("Quit from this page");
                alert.setContentText("Are you sure?????");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    root.getChildren().clear();
                    primaryStage.close(); //added this
                }
            }
        });

        homeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Back to main page");
                alert.setHeaderText("Go back to Main page, where you enter username");
                alert.setContentText("Are you sure?????");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    root.getChildren().clear();
                    primaryStage.close(); //added this

                    Stage mainStage = new Stage();
                    Parent StartRoot;
                    try {
                        StartRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene StartScene = new Scene(StartRoot);
                    mainStage.setScene(StartScene);
                    mainStage.show();
                }
//                Parent StartRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));
//                Scene StartScene = new Scene(StartRoot);
//                primaryStage.setScene(StartScene);
//                primaryStage.show();
            }
        });

    }
}
