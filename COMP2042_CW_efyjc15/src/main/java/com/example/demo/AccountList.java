package com.example.demo;

import java.io.*;
import java.util.ArrayList;

/**
 * AccountList class contain write and read File method, Once user finish the game, write file method will store username
 * and password inside text file. When user click leaderBoard button in main page and select a game mode they wanted to
 * see, read file method will run and store data from text file into arraylist and sort them.
 */
public class AccountList {
    ArrayList<Account> accountList = new ArrayList<>();
    File myFile = new File("leaderBoard.txt");
    File myFileTwoThree = new File("TwoThreeLeaderBoard.txt");
    File myFileDrunk = new File("DrunkLeaderBoard.txt");

    /**
     * @param username
     * @param score
     * store username and score into text file for normal game mode
     */
    public void writeFile(String username, int score){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(myFile, true));
            bw.write(username + " " + score + "\n");
            bw.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * @param username
     * @param score
     * store username and score into text file for TwoThree game mode
     */
    public void writeFileTwoThree(String username, int score){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(myFileTwoThree, true));
            bw.write(username + " " + score + "\n");
            bw.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * @param username
     * @param score
     * store username and score into text file for Drunk game mode
     */
    public void writeFileDrunk(String username, int score){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(myFileDrunk, true));
            bw.write(username + " " + score + "\n");
            bw.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * read the normal game mode file, store their data into an arraylist
     * sort the arraylist
     * @return a sorted arraylist
     */
    public ArrayList<Account> readFile(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(myFile));
            String line = br.readLine();
            while (line != null){
                Account accountObj = new Account();
                String[] splitted = line.split(" ");
                accountObj.setUsername(splitted[0]);
                accountObj.setScore(Integer.parseInt(splitted[1]));
                accountList.add(accountObj);
                line = br.readLine();
            }
            br.close();
            bubbleSort(accountList);
            return accountList;
        }
        catch(IOException e){
            throw  new RuntimeException(e);
        }
    }

    /**
     * read the TwoThree game mode file, store their data into an arraylist
     * sort the arraylist
     * @return a sorted arraylist
     */
    public ArrayList<Account> readFileTwoThree(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(myFileTwoThree));
            String line = br.readLine();
            while (line != null){
                Account accountObj = new Account();
                String[] splitted = line.split(" ");
                accountObj.setUsername(splitted[0]);
                accountObj.setScore(Integer.parseInt(splitted[1]));
                accountList.add(accountObj);
                line = br.readLine();
            }
            br.close();
            bubbleSort(accountList);
            return accountList;
        }
        catch(IOException e){
            throw  new RuntimeException(e);
        }
    }

    /**
     * read the Drunk game mode file, store their data into an arraylist
     * sort the arraylist
     * @return a sorted arraylist
     */
    public ArrayList<Account> readFileDrunk(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(myFileDrunk));
            String line = br.readLine();
            while (line != null){
                Account accountObj = new Account();
                String[] splitted = line.split(" ");
                accountObj.setUsername(splitted[0]);
                accountObj.setScore(Integer.parseInt(splitted[1]));
                accountList.add(accountObj);
                line = br.readLine();
            }
            br.close();
            bubbleSort(accountList);
            return accountList;
        }
        catch(IOException e){
            throw  new RuntimeException(e);
        }
    }

    /**
     * @param accountList sort the arraylists
     */
    public void bubbleSort(ArrayList<Account> accountList){
        for(int i = 0; i<accountList.size() - 1; i++){ //bubble sort to sort the text file data
            for(int j = 0; j<accountList.size() - i - 1; j++){
                if(accountList.get(j).getScore() < accountList.get(j+1).getScore() ){
                    Account temp = accountList.get(j); //create an account object call "temp" to temporary store an account object from
                    //the arraylist
                    accountList.set(j, accountList.get(j+1));
                    accountList.set(j+1, temp);
                }
            }
        }
    }
}
