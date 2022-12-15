package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class LeaderBoard implements Initializable {

    @FXML
    private TableView<Account> table;
    @FXML
    private TableColumn<Account, String> usernameColumn;
    @FXML
    private TableColumn<Account, Integer> scoreColumn;
    @FXML
    private Button leaderBoardBack;
    @FXML
    private AnchorPane leaderBoardScene;
    @FXML
    private ChoiceBox<String> ScoreChoiceBox;
    private String[] gameModeChoice = {"Normal", "TwoThree", "Drunk"};
    public static String GameModeChoiceString = "Normal";
    String song = "music/Monster inc song.mp3";
    Media media = new Media(Paths.get(song).toUri().toString());
    public MediaPlayer mediaPlayer = new MediaPlayer(media);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ScoreChoiceBox.getItems().addAll(gameModeChoice);
        ScoreChoiceBox.setOnAction(this::gameMode);
        mediaPlayer.play();
    }
    @FXML
    void backToMain2(ActionEvent event) throws IOException {
        Parent StartRoot = FXMLLoader.load(getClass().getResource("sample.fxml")); //load new stage with sample.fxml
        //so that player can go back to the main page
        Stage MainStage = new Stage(); //create new stage
        Scene StartScene = new Scene(StartRoot);
        MainStage.setScene(StartScene);
        MainStage.show(); //show the beginning scene, i.e. the login scene

        Stage leaderBoardStage = (Stage) leaderBoardScene.getScene().getWindow(); //get the GUI of this leaderboard scene
        leaderBoardStage.close(); //close the win scene
        mediaPlayer.stop();
    }

    public void gameMode(ActionEvent event){
        GameModeChoiceString = ScoreChoiceBox.getValue();
        AccountList accountListObj = new AccountList();
        if(Objects.equals(GameModeChoiceString, "Normal")){
            ArrayList<Account> accountList = accountListObj.readFile();
            ObservableList<Account> obList = FXCollections.observableArrayList(accountList);
            usernameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
            scoreColumn.setCellValueFactory(new PropertyValueFactory<Account, Integer>("score"));
            table.setItems(obList);
        }
        else if (Objects.equals(GameModeChoiceString, "TwoThree")){
            ArrayList<Account> accountList = accountListObj.readFileTwoThree();
            ObservableList<Account> obList = FXCollections.observableArrayList(accountList);
            usernameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
            scoreColumn.setCellValueFactory(new PropertyValueFactory<Account, Integer>("score"));
            table.setItems(obList);
        }
        else{
            ArrayList<Account> accountList = accountListObj.readFileDrunk();
            ObservableList<Account> obList = FXCollections.observableArrayList(accountList);
            usernameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
            scoreColumn.setCellValueFactory(new PropertyValueFactory<Account, Integer>("score"));
            table.setItems(obList);
        }
    }

}
