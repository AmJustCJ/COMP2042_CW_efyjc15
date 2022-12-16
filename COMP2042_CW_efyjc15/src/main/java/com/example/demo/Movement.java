package com.example.demo;

import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

public class Movement {
//    private static int HEIGHT = 700;
//    private static int n = 4;
//    public final static int distanceBetweenCells = 10;
//    public static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
//    private TextMaker textMaker = TextMaker.getSingleInstance();
//    private Cell[][] cells = new Cell[n][n];
//    private Group root;
//    private long score = 0;
//    boolean winCondition = false;
//
//    static void setN(int number) {
//        n = number;
//        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
//    }
//
//    private void randomFillNumber(int turn) {
//
//        Cell[][] emptyCells = new Cell[n][n];
//        int a = 0;
//        int b = 0;
//        int aForBound=0,bForBound=0;
//        outer:
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (cells[i][j].getNumber() == 0) {
//                    emptyCells[a][b] = cells[i][j];
//                    if (b < n-1) {
//                        bForBound=b;
//                        b++;
//
//                    } else {
//                        aForBound=a;
//                        a++;
//                        b = 0;
//                        if(a==n)
//                            break outer;
//                    }
//                }
//            }
//        }
//
//
//
//        Text text;
//        Random random = new Random();
//        boolean putTwo = true;
//        if (random.nextInt() % 2 == 0)
//            putTwo = false;
//        int xCell, yCell;
//        xCell = random.nextInt(aForBound+1);
//        yCell = random.nextInt(bForBound+1);
//        if (putTwo) {
//            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
//            emptyCells[xCell][yCell].setTextClass(text);
//            root.getChildren().add(text);
//            emptyCells[xCell][yCell].setColorByNumber(2);
//        } else {
//            if(Objects.equals(GameModeChoiceString, "TwoThree")){
//                text = textMaker.madeText("3", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
//                emptyCells[xCell][yCell].setTextClass(text);
//                root.getChildren().add(text);
//                emptyCells[xCell][yCell].setColorByNumber(3);
//            }
//            else{
//                text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
//                emptyCells[xCell][yCell].setTextClass(text);
//                root.getChildren().add(text);
//                emptyCells[xCell][yCell].setColorByNumber(4);
//            }
//
//        }
//    }
//
//    private int  haveEmptyCell() {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (cells[i][j].getNumber() == 0)
//                    return 1;
//            }
//        }
//        return -1;
//    }
//
//    private int passDestination(int i, int j, char direct) {
//        int coordinate = j;
//        if (direct == 'l') {
//            for (int k = j - 1; k >= 0; k--) {
//                if (cells[i][k].getNumber() != 0) {
//                    coordinate = k + 1;
//                    break;
//                } else if (k == 0) {
//                    coordinate = 0;
//                }
//            }
//            return coordinate;
//        }
//        coordinate = j;
//        if (direct == 'r') {
//            for (int k = j + 1; k <= n - 1; k++) {
//                if (cells[i][k].getNumber() != 0) {
//                    coordinate = k - 1;
//                    break;
//                } else if (k == n - 1) {
//                    coordinate = n - 1;
//                }
//            }
//            return coordinate;
//        }
//        coordinate = i;
//        if (direct == 'd') {
//            for (int k = i + 1; k <= n - 1; k++) {
//                if (cells[k][j].getNumber() != 0) {
//                    coordinate = k - 1;
//                    break;
//
//                } else if (k == n - 1) {
//                    coordinate = n - 1;
//                }
//            }
//            return coordinate;
//        }
//        coordinate = i;
//        if (direct == 'u') {
//            for (int k = i - 1; k >= 0; k--) {
//                if (cells[k][j].getNumber() != 0) {
//                    coordinate = k + 1;
//                    break;
//                } else if (k == 0) {
//                    coordinate = 0;
//                }
//            }
//            return coordinate;
//        }
//        return -1;
//    }
//
//    private void moveLeft() {
//        if(Objects.equals(GameModeChoiceString, "Drunk")){
//            for (int i = 0; i < n; i++) {
//                for (int j = n - 1; j >= 0; j--) {
//                    moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
//                }
//                for (int j = 0; j < n; j++) {
//                    cells[i][j].setModify(false);
//                }
//            }
//        } //move right
//        else{
//            for (int i = 0; i < n; i++) {
//                for (int j = 1; j < n; j++) {
//                    moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
//                }
//                for (int j = 0; j < n; j++) {
//                    cells[i][j].setModify(false);
//                }
//            }
//        } // move left
//        playMoveMusic();
//    }
//
//    private void moveRight() {
//        if(Objects.equals(GameModeChoiceString, "Drunk")){
//            for (int i = 0; i < n; i++) {
//                for (int j = 1; j < n; j++) {
//                    moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
//                }
//                for (int j = 0; j < n; j++) {
//                    cells[i][j].setModify(false);
//                }
//            }
//        }// move left
//        else{
//            for (int i = 0; i < n; i++) {
//                for (int j = n - 1; j >= 0; j--) {
//                    moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
//                }
//                for (int j = 0; j < n; j++) {
//                    cells[i][j].setModify(false);
//                }
//            }
//        } // move right
//        playMoveMusic();
//    }
//
//    private void moveUp() {
//        if(Objects.equals(GameModeChoiceString, "Drunk")){
//            for (int j = 0; j < n; j++) {
//                for (int i = n - 1; i >= 0; i--) {
//                    moveVertically(i, j, passDestination(i, j, 'd'), 1);
//                }
//                for (int i = 0; i < n; i++) {
//                    cells[i][j].setModify(false);
//                }
//            }
//        } //move down
//        else{
//            for (int j = 0; j < n; j++) {
//                for (int i = 1; i < n; i++) {
//                    moveVertically(i, j, passDestination(i, j, 'u'), -1);
//                }
//                for (int i = 0; i < n; i++) {
//                    cells[i][j].setModify(false);
//                }
//            }
//        } //move up
//        playMoveMusic();
//    }
//
//    private void moveDown() {
//        if(Objects.equals(GameModeChoiceString, "Drunk")){
//            for (int j = 0; j < n; j++) {
//                for (int i = 1; i < n; i++) {
//                    moveVertically(i, j, passDestination(i, j, 'u'), -1);
//                }
//                for (int i = 0; i < n; i++) {
//                    cells[i][j].setModify(false);
//                }
//            }
//        } //if game mode is "drunk", move down will become move up
//        else{
//            for (int j = 0; j < n; j++) {
//                for (int i = n - 1; i >= 0; i--) {
//                    moveVertically(i, j, passDestination(i, j, 'd'), 1);
//                }
//                for (int i = 0; i < n; i++) {
//                    cells[i][j].setModify(false);
//                }
//            }
//        }// if game mode is "normal" or "mult", move down still move down
//        playMoveMusic();
//    }
//
//    private boolean isValidDesH(int i, int j, int des, int sign) {
//        if (des + sign < n && des + sign >= 0) {
//            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
//                    && cells[i][des + sign].getNumber() != 0) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void moveHorizontally(int i, int j, int des, int sign) {
//        if (isValidDesH(i, j, des, sign)) {
//            cells[i][j].adder(cells[i][des + sign]);
//            cells[i][des + sign].setModify(true);
//            score += cells[i][des + sign].getNumber(); //added this
//            playAddMusic();
//        } else if (des != j) {
//            cells[i][j].changeCell(cells[i][des]);
//        }
//    }
//
//    private boolean isValidDesV(int i, int j, int des, int sign) {
//        if (des + sign < n && des + sign >= 0)
//            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
//                    && cells[des + sign][j].getNumber() != 0) {
//                return true;
//            }
//        return false;
//    }
//
//    private void moveVertically(int i, int j, int des, int sign) {
//        if (isValidDesV(i, j, des, sign)) {
//            cells[i][j].adder(cells[des + sign][j]);
//            cells[des + sign][j].setModify(true);
//            score += cells[des + sign][j].getNumber(); //added this
//            playAddMusic();
//        } else if (des != i) {
//            cells[i][j].changeCell(cells[des][j]);
//        }
//    }
//
//    private boolean haveSameNumberNearly(int i, int j) {
//        if (i < n - 1 && j < n - 1) {
//            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
//                return true;
//            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
//                return true;
//        }
//        return false;
//    }
//
//    private boolean canNotMove() {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (haveSameNumberNearly(i, j)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public boolean find2048(){
//        for(int i=0; i<n; i++) {
//            for(int j=0; j<n; j++){
//                if(cells[i][j].getNumber() == 2048 || cells[i][j].getNumber() == 1536){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public void playMoveMusic(){
//        String MoveSong = "music/moveCell.mp3";
//        Media mediaMove = new Media(Paths.get(MoveSong).toUri().toString());
//        MediaPlayer mediaPlayerMove = new MediaPlayer(mediaMove);
//        mediaPlayerMove.play();
//    }
//
//    public void playAddMusic(){
//        String AddSong = "music/addCell.mp3";
//        Media mediaAdd = new Media(Paths.get(AddSong).toUri().toString());
//        MediaPlayer mediaPlayerAdd = new MediaPlayer(mediaAdd);
//        mediaPlayerAdd.play();
//    }
//
//    public void playWinMusic(){
//        String WinSong = "music/groove song.mp3";
//        Media media = new Media(Paths.get(WinSong).toUri().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();
//    }
}
