package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Account Class that store users username and score
 */
public class Account  {
    private String username;
    private int score;

    /**
     * @param username setter method for username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * @return getter method for username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param score setter method for score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return getter method for score
     */
    public int getScore() {
        return score;
    }
}
