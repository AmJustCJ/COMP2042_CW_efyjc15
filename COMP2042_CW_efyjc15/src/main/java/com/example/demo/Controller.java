package com.example.demo;

import javafx.animation.PauseTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller extends Main implements Initializable{
    @FXML
    private Button PlayButton; //at mainPage
    @FXML
    private TextField TextInput; //at mainPage
    @FXML
    private BorderPane startScene; //at mainPage
    @FXML
    public ImageView BackgroundImage; //at mainPage
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
    private ChoiceBox<String> modeChoiceBox; //at mainPage
    @FXML
    private Button InfoButton;
    private String[] gameModeChoice = {"Normal", "TwoThree", "Drunk"};

    String song = "music/game song.mp3";
    Media media = new Media(Paths.get(song).toUri().toString());
    public MediaPlayer mediaPlayer = new MediaPlayer(media);


    static final int WIDTH = 900;
    static final int HEIGHT = 800;
    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));

    public static String username;

    public static String GameModeChoiceString;

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    public String getUsername() {
        return username;
    }

    public void gameMode(ActionEvent event){
        GameModeChoiceString = modeChoiceBox.getValue();
    }


    @FXML
    public void Play(ActionEvent event) {
        username = TextInput.getText();
        if(!username.isEmpty() && !username.contains(" ") && GameModeChoiceString != null){
            System.out.println(GameModeChoiceString);
            System.out.println(username);
            Stage startStage = (Stage) startScene.getScene().getWindow();
            startStage.close();
            mediaPlayer.stop();

            Stage primaryStage = new Stage();

            Group endgameRoot = new Group();
            Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));

            Group gameRoot = new Group();
            setGameRoot(gameRoot);
            Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
            setGameScene(gameScene);
            primaryStage.setScene(gameScene);
            GameScene game = new GameScene();
            game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        else{
            Alert Error = new Alert(Alert.AlertType.ERROR);
            Error.setTitle("Error");
            Error.setHeaderText("Please input username without spacebar and/or choose a game mode!");

            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> Error.hide());
            Error.show();
            delay.play();
        }
    }

    @FXML
    void SelectBackground(ActionEvent event) throws IOException {
        Stage selectBackgroundStage = new Stage();
        Parent StartRoot = FXMLLoader.load(getClass().getResource("SelectBackground.fxml"));
        Scene StartScene = new Scene(StartRoot);
        selectBackgroundStage.setScene(StartScene);
        selectBackgroundStage.show();

        Stage StartStage = (Stage) startScene.getScene().getWindow(); //get the GUI of main starting scene
        StartStage.close(); //close the main starting scene
        mediaPlayer.stop();
    }

    @FXML
    void showLeaderBoard(ActionEvent event) throws IOException {
        Stage showLeaderBoardStage = new Stage();
        Parent leaderBoardRoot = FXMLLoader.load(getClass().getResource("LeaderBoard.fxml"));
        Scene showLeaderBoardScene = new Scene(leaderBoardRoot);
        showLeaderBoardStage.setScene(showLeaderBoardScene);
        showLeaderBoardStage.show();

        Stage StartStage = (Stage) startScene.getScene().getWindow(); //get the GUI of main starting scene
        StartStage.close(); //close the main starting scene
        mediaPlayer.stop();
    }

    @FXML
    void showInfo(ActionEvent event) throws IOException {
        Stage showInfoStage = new Stage();
        Parent InfoRoot = FXMLLoader.load(getClass().getResource("Info.fxml"));
        Scene showLeaderBoardScene = new Scene(InfoRoot);
        showInfoStage.setScene(showLeaderBoardScene);
        showInfoStage.show();

        Stage StartStage = (Stage) startScene.getScene().getWindow(); //get the GUI of main starting scene
        StartStage.close(); //close the main starting scene
        mediaPlayer.stop();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(modeChoiceBox != null){
            modeChoiceBox.getItems().addAll(gameModeChoice);
            modeChoiceBox.setOnAction(this::gameMode);
        }
        mediaPlayer.play();
        SelectBg selectBgObj = new SelectBg();
        Image temp1 = selectBgObj.getBgImg();
        if (temp1 != null){
            BackgroundImage.setImage(temp1);
        }
    }
}
