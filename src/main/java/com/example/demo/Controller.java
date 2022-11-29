package com.example.demo;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Controller extends Main {
    @FXML
    private Button PlayButton;
    @FXML
    private TextField TextInput;
    @FXML
    private BorderPane startScene;
    @FXML
    private Button PlayAgain;
    @FXML
    private Button QuitGame;
    @FXML
    private BorderPane WinScene;



    static final int WIDTH = 900;
    static final int HEIGHT = 800;
    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
    private static Scanner input= new Scanner(System.in);

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    String username;
    @FXML
    public void Play(ActionEvent event) {
        String username = TextInput.getText();
        if(!username.isEmpty()){
            System.out.println(username);
            Stage startStage = (Stage) startScene.getScene().getWindow();
            startStage.close();

            Stage primaryStage = new Stage();
            Stage mainStage = new Stage();
            Group menuRoot = new Group();
            Scene menuScene = new Scene(menuRoot, WIDTH, HEIGHT);
            Group accountRoot = new Group();
            Scene accountScene = new Scene(accountRoot, WIDTH, HEIGHT, Color.rgb(150, 20, 100, 0.2));
            Group getAccountRoot = new Group();
            Scene getAccountScene = new Scene(getAccountRoot, WIDTH, HEIGHT, Color.rgb(200, 20, 100, 0.2));
            Group endgameRoot = new Group();
            Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));
            Group rankRoot = new Group();
            Scene rankScene = new Scene(rankRoot, WIDTH, HEIGHT, Color.rgb(250, 50, 120, 0.3));
            BackgroundFill background_fill = new BackgroundFill(Color.rgb(120, 100, 100), CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(background_fill);

            Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(120, 120, 120, 0.2));
            backgroundOfMenu.setX(WIDTH / 2 - 120);
            backgroundOfMenu.setY(180);
            menuRoot.getChildren().add(backgroundOfMenu);

            Rectangle backgroundOfMenuForPlay = new Rectangle(240, 140, Color.rgb(120, 20, 100, 0.2));
            backgroundOfMenuForPlay.setX(WIDTH / 2 - 120);
            backgroundOfMenuForPlay.setY(180);
            accountRoot.getChildren().add(backgroundOfMenuForPlay);

            Group gameRoot = new Group();
            setGameRoot(gameRoot);
            Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
            setGameScene(gameScene);
            primaryStage.setScene(gameScene);
            GameScene game = new GameScene();
            game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);

            primaryStage.show();
        }
        else{
            System.out.println("please enter username");
        }
    }

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
    void Replay(ActionEvent event) throws IOException {
        Stage RetryStage = new Stage(); //create new stage
        Parent StartRoot = FXMLLoader.load(getClass().getResource("sample.fxml")); //load new stage with sample.fxml
        //so that player can retry the game
        Stage WinStage = (Stage) WinScene.getScene().getWindow(); //get the GUI of this win scene

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Retry");
        alert.setHeaderText("Retry the game?");
        alert.setContentText("Are you sure?????");

        if (alert.showAndWait().get() == ButtonType.OK){
            Scene StartScene = new Scene(StartRoot);
            RetryStage.setScene(StartScene);
            RetryStage.show(); //show the beginning scene, i.e. the login scene
            WinStage.close(); //close the win scene
        }
    }
}
