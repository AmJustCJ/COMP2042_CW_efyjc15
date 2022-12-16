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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * Controller class for Select Background scene
 */
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

    /**
     * @return getter method to get image
     */
    public Image getBgImg(){
        return temp1;
    }

    /**
     * @param img set the background image
     */
    public void setBgImg(Image img){
        temp1 = img;
    }


    Image BackgroundImage1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("board.jpg")));
    Image BackgroundImage2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("chess board.jpg")));
    Image BackgroundImage3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("blackBoard.jpg")));

    /**
     * @param event After clicking this button, it bring user back to main page
     *              and closes select background scene
     * @throws IOException
     */
    @FXML
    void backToMain(ActionEvent event) throws IOException {
        Parent StartRoot = FXMLLoader.load(getClass().getResource("mainPage.fxml")); //load new stage with mainPage.fxml
        //so that player can go back to the main page
        Stage MainStage = new Stage(); //create new stage
        Scene StartScene = new Scene(StartRoot);
        MainStage.setScene(StartScene);
        MainStage.show(); //show the beginning scene, i.e. the login scene

        //now close select background scene
        Stage SelectBackgroundStage = (Stage) SelectBackgroundScene.getScene().getWindow(); //get the GUI of this select background scene
        SelectBackgroundStage.close(); //close the select background scene
    }

    /**
     * @param event Switch background image to BackgroundImage1
     */
    @FXML
    void SwitchImageToOne(ActionEvent event) {
        BackgroundImage.setImage(BackgroundImage1);
        setBgImg(BackgroundImage1);
    }

    /**
     * @param event Switch background image to BackgroundImage2
     */
    @FXML
    void SwitchImageToTwo(ActionEvent event) {
        BackgroundImage.setImage(BackgroundImage2);
        setBgImg(BackgroundImage2);
    }

    /**
     * @param event Switch background image to BackgroundImage3
     */
    @FXML
    void SwitchImageToThree(ActionEvent event) {
        BackgroundImage.setImage(BackgroundImage3);
        setBgImg(BackgroundImage3);
    }

    /**
     * @param url initialize method, set the background image everytime this scene is shown
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image temp1 = getBgImg();
        if (temp1 != null){
            BackgroundImage.setImage(temp1);
        }
    }
}
