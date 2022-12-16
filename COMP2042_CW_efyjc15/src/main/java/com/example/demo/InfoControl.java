package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class InfoControl {
    @FXML
    private Button backButton;
    @FXML
    private AnchorPane infoPane;


    @FXML
    void backToMainInfo(ActionEvent event) throws IOException {
        Parent StartRoot = FXMLLoader.load(getClass().getResource("mainPage.fxml")); //load new stage with mainPage.fxml
        //so that player can go back to the main page
        Stage MainStage = new Stage(); //create new stage
        Scene StartScene = new Scene(StartRoot);
        MainStage.setScene(StartScene);
        MainStage.show(); //show the beginning scene, i.e. the login scene

        Stage leaderBoardStage = (Stage) infoPane.getScene().getWindow(); //get the GUI of this leaderboard scene
        leaderBoardStage.close(); //close the win scene

    }
}
