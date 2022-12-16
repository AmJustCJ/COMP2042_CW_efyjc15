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
    private static int HEIGHT = 700;
    private static int n = 4;
    public final static int distanceBetweenCells = 10;
    public static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    private TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells = new Cell[n][n];
    private Group root;
    private long score = 0;
    boolean winCondition = false;

    String GameModeChoiceString = Controller.GameModeChoiceString;
    Controller controllerObj = new Controller();
    AccountList accListObj = new AccountList();

    static void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    /**
     * @return get length of cell
     */
    static double getLENGTH() {
        return LENGTH;
    }

    /**
     * @param turn This method generate number 2 or 4 on the cells,
     *             If its TwoThree mode, it will generate 2 or 3
     */
    private void randomFillNumber(int turn) {

        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < n-1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==n)
                            break outer;
                    }
                }
            }
        }



        Text text;
        Random random = new Random();
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)
            putTwo = false;
        int xCell, yCell;
            xCell = random.nextInt(aForBound+1);
            yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            if(Objects.equals(GameModeChoiceString, "TwoThree")){
                text = textMaker.madeText("3", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
                emptyCells[xCell][yCell].setTextClass(text);
                root.getChildren().add(text);
                emptyCells[xCell][yCell].setColorByNumber(3);
            }
            else{
                text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
                emptyCells[xCell][yCell].setTextClass(text);
                root.getChildren().add(text);
                emptyCells[xCell][yCell].setColorByNumber(4);
            }

        }
    }

    /**
     * @return check whether a cell is empty
     */
    private int  haveEmptyCell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
            }
        }
        return -1;
    }

    /**
     * @param i get location i
     * @param j get location j
     * @param direct get direction of the cell
     * @return this method decide where to pass the cell to in terms of direction
     */
    private int passDestination(int i, int j, char direct) {
        int coordinate = j;
        if (direct == 'l') {
            for (int k = j - 1; k >= 0; k--) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        coordinate = j;
        if (direct == 'r') {
            for (int k = j + 1; k <= n - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'd') {
            for (int k = i + 1; k <= n - 1; k++) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k - 1;
                    break;

                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'u') {
            for (int k = i - 1; k >= 0; k--) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        return -1;
    }

    /**
     * Move every cells to the left and play a sound effect for move
     */
    private void moveLeft() {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
        playMoveMusic();
    }

    /**
     * Move every cells to the right and play a sound effect for move
     */
    private void moveRight() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
        playMoveMusic();
    }

    /**
     * Move every cells upward and play a sound effect for move
     */
    private void moveUp() {
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                moveVertically(i, j, passDestination(i, j, 'u'), -1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }
        playMoveMusic();
    }

    /**
     * Move every cells downward and play a sound effect for move
     */
    private void moveDown() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                moveVertically(i, j, passDestination(i, j, 'd'), 1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }
        playMoveMusic();
    }

    /**
     * @param i get location i
     * @param j get location j
     * @param des get destination/position of cell
     * @param sign get sign of cell
     * @return check if it is valid to move horizontally
     */
    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param i get i
     * @param j get j
     * @param des get des
     * @param sign get sign
     *             move cells horizontally and add any cells with same number
     *             if there's 2 cells added together, a sound effect will play
     */
    private void moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign)) {
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des + sign].setModify(true);
            score += cells[i][des + sign].getNumber(); //added this
            playAddMusic();
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
        }
    }

    /**
     * @param i get location i
     * @param j get location j
     * @param des get des
     * @param sign get sign
     * @return check if it is valid to move vertically
     */
    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

    /**
     * @param i get i
     * @param j get j
     * @param des get des
     * @param sign get sign
     *             move cells vertically and add any cells with same number
     *             if there's 2 cells added together, a sound effect will play
     */
    private void moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            cells[i][j].adder(cells[des + sign][j]);
            cells[des + sign][j].setModify(true);
            score += cells[des + sign][j].getNumber(); //added this
            playAddMusic();
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
        }
    }

    /**
     * @param i get i
     * @param j get j
     * @return check if the cell next to each other have same value or not
     */
    private boolean haveSameNumberNearly(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    /**
     * @return Check if there's any valid move
     */
    private boolean canNotMove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (haveSameNumberNearly(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return check if 2048 or 1536 is reach
     */
    public boolean find2048(){
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++){
                if(cells[i][j].getNumber() == 2048 || cells[i][j].getNumber() == 1536){
                   return true;
                }
            }
        }
        return false;
    }

    /**
     * play sound effect for every cell move
     */
    public void playMoveMusic(){
        String MoveSong = "music/moveCell.mp3";
        Media mediaMove = new Media(Paths.get(MoveSong).toUri().toString());
        MediaPlayer mediaPlayerMove = new MediaPlayer(mediaMove);
        mediaPlayerMove.play();
    }

    /**
     * play sound effect for every cell added
     */
    public void playAddMusic(){
        String AddSong = "music/addCell.mp3";
        Media mediaAdd = new Media(Paths.get(AddSong).toUri().toString());
        MediaPlayer mediaPlayerAdd = new MediaPlayer(mediaAdd);
        mediaPlayerAdd.play();
    }

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
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
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
//                                    mediaPlayer.stop();
                                }
                            }
                            catch (IOException e) {
                                throw new RuntimeException(e);
                            }
//                            AccountList accListObj = new AccountList();

//                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                            alert.setTitle("Continue?");
//                            alert.setHeaderText("Do you want to quit? Yes will quit to main page while cancel will continue");
//                            alert.setContentText("Are you sure?????");
//
//                            Optional<ButtonType> result = alert.showAndWait();
//                            if (result.get() == ButtonType.OK){ //to quit
//                                if(Objects.equals(GameModeChoiceString, "Normal")){
//                                    accListObj.writeFile(controllerObj.getUsername(), (int)score);
//                                }
//                                else if(Objects.equals(GameModeChoiceString, "TwoThree")){
//                                    accListObj.writeFileTwoThree(controllerObj.getUsername(), (int)score);
//                                }
//                                else{
//                                    accListObj.writeFileDrunk(controllerObj.getUsername(), (int)score);
//                                }
//                                Main mainObj = new Main();
//                                Stage backToMainStage = new Stage();
//                                try {
//                                    mainObj.start(backToMainStage);
//                                } catch (Exception e) {
//                                    throw new RuntimeException(e);
//                                }
//                                primaryStage.close();
//                            }
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
