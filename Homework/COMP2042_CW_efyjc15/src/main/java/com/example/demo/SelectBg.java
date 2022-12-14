package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class SelectBg implements Initializable {
    @FXML
    private Button BackgroundBack;//

    @FXML
    private ImageView BackgroundImage;

    @FXML
    private Button ImageOne;//

    @FXML
    private Button ImageThree;//

    @FXML
    private Button ImageTwo;//

    @FXML
    private AnchorPane SelectBackgroundScene;//
    public static Image temp1;

    public Image getBgImg(){
        return temp1;
    }

    public void setBgImg(Image img){
        temp1 = img;
    }

    Image BackgroundImage1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("confuse nick young.jpg")));
    Image BackgroundImage2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("joji2.jpg")));
    Image BackgroundImage3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("backgroundMain.jpeg")));

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
        Controller controllerObj = new Controller();
        //controllerObj.mediaPlayer.stop();
        //controllerObj.musicStop = false;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image temp1 = getBgImg();
        if (temp1 != null){
            BackgroundImage.setImage(temp1);
            System.out.println("Not null" + temp1);
            System.out.println("null" + temp1);
        }
    }
}
