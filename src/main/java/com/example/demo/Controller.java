package com.example.demo;

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

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller extends Main implements Initializable{
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
    @FXML
    private Button SelectBackgroundButton;
    @FXML
    private Button BackgroundBack;
    @FXML
    private AnchorPane SelectBackgroundScene;
    @FXML
    public ImageView BackgroundImage;
    @FXML
    private Button ImageOne;
    @FXML
    private Button ImageThree;
    @FXML
    private Button ImageTwo;@FXML
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
    private ChoiceBox<String> modeChoiceBox;
    private String[] gameModeChoice = {"Normal", "Multiplier", "Drunk"};
    public static Image temp1;



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

    public Image getBgImg(){
        return temp1;
    }
    public void setBgImg(Image img){
        temp1 = img;
    }

    Image BackgroundImage1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("confuse nick young.jpg")));
    Image BackgroundImage2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("joji2.jpg")));
    Image BackgroundImage3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("backgroundMain.jpeg")));

    public static String username;

    @FXML
    public void Play(ActionEvent event) {
        username = TextInput.getText();
        if(!username.isEmpty() && !username.contains(" ")){

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
    void Replay(ActionEvent event) throws Exception {
//        Stage RetryStage = new Stage(); //create new stage
//        Parent StartRoot = FXMLLoader.load(getClass().getResource("sample.fxml")); //load new stage with sample.fxml
//        //so that player can retry the game
        Stage WinStage = (Stage) WinScene.getScene().getWindow(); //get the GUI of this win scene

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Retry");
        alert.setHeaderText("Retry the game?");
        alert.setContentText("Are you sure?????");

        if (alert.showAndWait().get() == ButtonType.OK){
            Main mainObj = new Main();
            Stage primaryStage = new Stage();
            mainObj.start(primaryStage);
//            Scene StartScene = new Scene(StartRoot);
//            RetryStage.setScene(StartScene);
//            RetryStage.show(); //show the beginning scene, i.e. the login scene
            WinStage.close(); //close the win scene
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
    }
    @FXML
    void backToMain(ActionEvent event) throws IOException {
        Parent StartRoot = FXMLLoader.load(getClass().getResource("sample.fxml")); //load new stage with sample.fxml
        //so that player can go back to the main page
        Stage MainStage = new Stage(); //create new stage
        Scene StartScene = new Scene(StartRoot);
        MainStage.setScene(StartScene);
        MainStage.show(); //show the beginning scene, i.e. the login scene

        //now close select background scene
        Stage SelectBackgroundStage = (Stage) SelectBackgroundScene.getScene().getWindow(); //get the GUI of this select background scene
        SelectBackgroundStage.close(); //close the select background scene
    }

    @FXML
    void SwitchImageToOne(ActionEvent event) {
        BackgroundImage.setImage(BackgroundImage1);
        setBgImg(BackgroundImage1);
    }

    @FXML
    void SwitchImageToTwo(ActionEvent event) {
        BackgroundImage.setImage(BackgroundImage2);
        setBgImg(BackgroundImage2);
    }

    @FXML
    void SwitchImageToThree(ActionEvent event) {
        BackgroundImage.setImage(BackgroundImage3);
        setBgImg(BackgroundImage3);
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
    }

    public String getUsername() {
        return username;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (modeChoiceBox!= null){
            modeChoiceBox.getItems().addAll(gameModeChoice);
        }

    }
}
