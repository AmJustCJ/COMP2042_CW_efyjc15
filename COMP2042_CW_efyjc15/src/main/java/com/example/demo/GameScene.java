package com.example.demo;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

/**
 * GameScene class for starting the game
 */
class GameScene extends Movement{
    /**
     * play sound effect when user win the game, reach 2048 or 1536
     */
    public void playWinMusic(){
        String WinSong = "music/groove song.mp3";
        Media media = new Media(Paths.get(WinSong).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    /**
     * @param gameScene get game scene
     * @param root get root
     * @param primaryStage get stage
     * @param endGameScene get end game scene
     * @param endGameRoot get end game root
     *                    this method starts the game
     */
    void game(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, Group endGameRoot) {
        this.root = root;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells + 50,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells + 50, LENGTH, root);
            }
        }

        Text text = new Text();
        root.getChildren().add(text);
        text.setText("SCORE :");
        text.setFont(Font.font(30));
        text.relocate(750, 100);
        Text scoreText = new Text();
        root.getChildren().add(scoreText);
        scoreText.relocate(750, 150);
        scoreText.setFont(Font.font(20));
        scoreText.setText("0");

        randomFillNumber(1);
        randomFillNumber(1);

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
                Platform.runLater(() -> {
                    int haveEmptyCell;
                    if(Objects.equals(GameModeChoiceString, "Drunk")){
                        switch(key.getCode()){
                            case UP -> GameScene.this.moveDown();
                            case DOWN -> GameScene.this.moveUp();
                            case LEFT -> GameScene.this.moveRight();
                            case RIGHT -> GameScene.this.moveLeft();
                            default -> {
                                Alert Error = new Alert(Alert.AlertType.ERROR);
                                Error.setTitle("Error");
                                Error.setHeaderText("You can only input arrow key: Left, right, up and down");

                                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                                delay.setOnFinished(e -> Error.hide());
                                Error.show();
                                delay.play();

                                return;
                            }
                        } //end of switch
                    }//end of if, this code only run when game mode is "drunk", meaning the control are reverse
                    else{
                        switch(key.getCode()){
                            case UP -> GameScene.this.moveUp();
                            case DOWN -> GameScene.this.moveDown();
                            case LEFT -> GameScene.this.moveLeft();
                            case RIGHT -> GameScene.this.moveRight();
                            default -> {
                                Alert Error = new Alert(Alert.AlertType.ERROR);
                                Error.setTitle("Error");
                                Error.setHeaderText("You can only input arrow key: Left, right, up and down");

                                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                                delay.setOnFinished(e -> Error.hide());
                                Error.show();
                                delay.play();

                                return;
                            }
                        } //end of switch
                    }//end of else, this code only when game mode is not "Drunk"

                    scoreText.setText(score + "");

                    haveEmptyCell = GameScene.this.haveEmptyCell();
                    if(winCondition == false){ //if winCondition = false
                        if(find2048() == true){
                            FXMLLoader loadWin = new FXMLLoader();
                            loadWin.setLocation(getClass().getResource("EndgameWin.fxml"));
                            try {
                                DialogPane dialogPane = loadWin.load();
                                Dialog<ButtonType> dialog = new Dialog<>();
                                dialog.setDialogPane(dialogPane);
                                dialog.setTitle("YOU WIN");

                                playWinMusic();

                                Optional<ButtonType> result = dialog.showAndWait();
                                if(result.get() == ButtonType.OK){
                                    System.out.println("ok");
                                    if(Objects.equals(GameModeChoiceString, "Normal")){
                                        accListObj.writeFile(controllerObj.getUsername(), (int)score);
                                    }
                                    else if(Objects.equals(GameModeChoiceString, "TwoThree")){
                                        accListObj.writeFileTwoThree(controllerObj.getUsername(), (int)score);
                                    }
                                    else{
                                        accListObj.writeFileDrunk(controllerObj.getUsername(), (int)score);
                                    }
                                    Main mainObj = new Main();
                                    Stage backToMainStage = new Stage();
                                    try {
                                        mainObj.start(backToMainStage);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    primaryStage.close();
                                }
                            }
                            catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            winCondition = true;
                        }
                    }


                    if (haveEmptyCell == -1) {
                        if (GameScene.this.canNotMove()) {
                            if(Objects.equals(GameModeChoiceString, "TwoThree")){
                                accListObj.writeFileTwoThree(controllerObj.getUsername(), (int)score);
                                System.out.println("twothree");
                            }
                            else if (Objects.equals(GameModeChoiceString, "Normal")){
                                accListObj.writeFile(controllerObj.getUsername(), (int)score);
                                System.out.println("norm");
                            }
                            else if(Objects.equals(GameModeChoiceString, "Drunk")){
                                accListObj.writeFileDrunk(controllerObj.getUsername(), (int)score);
                                System.out.printf("drunk");
                            }
                            EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, score);
                            primaryStage.setScene(endGameScene);
                            root.getChildren().clear();
                            score = 0;
                        }
                    } else if(haveEmptyCell == 1) {
                        GameScene.this.randomFillNumber(2);
                    }
                });
            });
    }
}
