package com.example.demo;

import java.io.*;
import java.util.ArrayList;

public class AccountList {
    ArrayList<Account> accountList = new ArrayList<>();
    File myFile = new File("leaderBoard.txt");
    File myFileMult = new File("multLeaderBoard.txt");

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

    public void writeFileMult(String username, int score){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(myFileMult, true));
            bw.write(username + " " + score + "\n");
            bw.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

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
            return accountList;
        }
        catch(IOException e){
            throw  new RuntimeException(e);
        }
    }

    public ArrayList<Account> readFileMult(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(myFileMult));
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
            return accountList;
        }
        catch(IOException e){
            throw  new RuntimeException(e);
        }
    }
}
