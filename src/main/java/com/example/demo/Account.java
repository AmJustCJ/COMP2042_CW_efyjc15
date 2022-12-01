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

public class Account /*implements Comparable<Account>*/ {
    ArrayList<String> accountName = new ArrayList<>();
    ArrayList<Integer> accountScore = new ArrayList<>();
    File myNameFile = new File("username.txt"); //file methods
    File myScoreFile = new File("score.txt");
    public void writeFile(String username, long score){
        try {
            BufferedWriter bwName = new BufferedWriter(new FileWriter(myNameFile, true));
            BufferedWriter bwScore = new BufferedWriter(new FileWriter(myScoreFile, true));
            bwName.write(username + "\n");
            bwScore.write(score + "\n");
            bwName.close();
            bwScore.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void readFile(){
        try{
            BufferedReader brName = new BufferedReader(new FileReader(myNameFile));
            BufferedReader brScore = new BufferedReader(new FileReader(myScoreFile));

            String line = brName.readLine();
            while (line != null){
                accountName.add(line);
                line = brName.readLine();
            }
            String line1 = brScore.readLine();
            while (line1 != null){
                accountScore.add(Integer.parseInt(line1));
                line1 = brScore.readLine();
            }
            brName.close();
            brScore.close();
            System.out.println(accountName);
            System.out.println(accountScore);

            for(int i = 0; i<accountScore.size() - 1; i++){ //bubble sort
                for(int j = 0; j<accountScore.size() - i - 1; j++){
                    if(accountScore.get(j) < accountScore.get(j+1) ){
                        int temp = accountScore.get(j); //sort score
                        accountScore.set(j, accountScore.get(j+1));
                        accountScore.set(j+1, temp);

                        String temp1 = accountName.get(j); //sort username
                        accountName.set(j, accountName.get(j+1));
                        accountName.set(j+1, temp1);
                    }
                }
            }
            System.out.println(accountName);
            System.out.println(accountScore);
        }
        catch(IOException e){
            throw  new RuntimeException(e);
        }
    }


//    private long score = 0;
//    private String userName ;
//    private static ArrayList<Account> accounts = new ArrayList<>();
//
//    public Account(String userName){
//        this.userName=userName;
//    }
//
//    @Override
//    public int compareTo(Account o) {
//        return Long.compare(o.getScore(), score);
//    }
//
//    public void addToScore(long score) {
//        this.score += score;
//    }
//
//    private long getScore() {
//        return score;
//    }
//
//    private String getUserName() {
//        return userName;
//    }
//
//    static Account accountHaveBeenExist(String userName){
//        for(Account account : accounts){
//            if(account.getUserName().equals(userName)){
//                return account;
//            }
//        }
//        return null;
//
//    }
//
//    static Account makeNewAccount(String userName){
//        Account account = new Account(userName);
//        accounts.add(account);
//        return account;
//    }

}
