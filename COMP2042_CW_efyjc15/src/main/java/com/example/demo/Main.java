package com.example.demo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main extends Application implements Initializable {

    static final int WIDTH = 900;
    static final int HEIGHT = 800;
    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
//    private static Scanner input= new Scanner(System.in);

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }
    private static ImageView backgroundImage;

    @FXML
    private ImageView BackgroundImage;
    @FXML
    private BorderPane startScene;
    MusicControl musicControlObj = new MusicControl();
    String hi;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //for accessing first page
        Parent StartRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene StartScene = new Scene(StartRoot);
        primaryStage.setScene(StartScene);
        primaryStage.show();
        //musicControlObj.playMusic();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*if (backgroundImage == null){
            back
        }*/
//        SelectBg selectBgObj = new SelectBg();
//        Image temp1 = selectBgObj.getBgImg();
//        if (temp1 != null){
//            BackgroundImage.setImage(temp1);
//            System.out.println("Not null" + temp1);
//            System.out.println("null" + temp1);
//        }


    }
}
