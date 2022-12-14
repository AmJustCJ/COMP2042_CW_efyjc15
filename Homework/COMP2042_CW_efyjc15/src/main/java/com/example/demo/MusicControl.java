package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class MusicControl {

    public void playMusic(){
        String song = "music/game song.mp3";
        Media media = new Media(Paths.get(song).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
