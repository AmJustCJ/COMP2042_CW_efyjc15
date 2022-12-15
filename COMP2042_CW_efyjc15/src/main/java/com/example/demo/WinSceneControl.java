package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;


public class WinSceneControl {
    @FXML
    private BorderPane WinScene;
    @FXML
    private Button PlayAgain;
    @FXML
    private Button backToMainWin;
    @FXML
    private Button QuitGame;


    @FXML
    void Quit(ActionEvent event) throws IOException {
        Stage WinStage = (Stage) WinScene.getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Dialog");
        alert.setHeaderText("Quit from this page");
        alert.setContentText("Are you sure?????");

        if (alert.showAndWait().get() == ButtonType.OK){
            WinStage.close();
        }
    }

    @FXML
    void Replay(ActionEvent event) throws Exception {
////        Stage RetryStage = new Stage(); //create new stage
////        Parent StartRoot = FXMLLoader.load(getClass().getResource("sample.fxml")); //load new stage with sample.fxml
////        //so that player can retry the game
//        Stage WinStage = (Stage) WinScene.getScene().getWindow(); //get the GUI of this win scene
//
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Retry");
//        alert.setHeaderText("Retry the game?");
//        alert.setContentText("Are you sure?????");
//
//        if (alert.showAndWait().get() == ButtonType.OK){
//            Main mainObj = new Main();
//            Stage primaryStage = new Stage();
//            mainObj.start(primaryStage);
////            Scene StartScene = new Scene(StartRoot);
////            RetryStage.setScene(StartScene);
////            RetryStage.show(); //show the beginning scene, i.e. the login scene
//            WinStage.close(); //close the win scene
//        }

    }

    @FXML
    void backToMainPage(ActionEvent event) throws IOException {
        Stage WinStage = (Stage) WinScene.getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Back to main page");
        alert.setHeaderText("Go back to Main page, where you enter username");
        alert.setContentText("Are you sure?????");

        if (alert.showAndWait().get() == ButtonType.OK){
            Parent StartRoot = FXMLLoader.load(getClass().getResource("sample.fxml")); //load new stage with sample.fxml
            //so that player can go back to the main page
            Stage MainStage = new Stage(); //create new stage
            Scene StartScene = new Scene(StartRoot);
            MainStage.setScene(StartScene);
            MainStage.show(); //show the beginning scene, i.e. the login scene

            WinStage.close();
        }
    }

}
