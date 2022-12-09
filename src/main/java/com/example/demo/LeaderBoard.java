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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AccountList accountListObj = new AccountList();
        ArrayList<Account> accountList = accountListObj.readFile();

        ObservableList<Account> obList = FXCollections.observableArrayList(accountList);

//        for(Account accountObj : accountList) {
//            obList.add(accountObj);
//        }

        usernameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Account, Integer>("score"));
        table.setItems(obList);
//        for(Account accountObj : accountList){
//            table.setItems((ObservableList<Account>) accountObj);
//        }
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
    }
}
