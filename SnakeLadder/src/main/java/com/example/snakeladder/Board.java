package com.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer,Integer>>  positionCoordinates;

    ArrayList<Integer> snakeLadderPosition;

    private void populateSnakeLadder(){
        snakeLadderPosition = new ArrayList<>();
        for(int i=0; i<101; i++){
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(8,26);
        snakeLadderPosition.set(21,82);
        snakeLadderPosition.set(43,77);
        snakeLadderPosition.set(44,22);
        snakeLadderPosition.set(46,5);
        snakeLadderPosition.set(48,9);
        snakeLadderPosition.set(50,91);
        snakeLadderPosition.set(52,11);
        snakeLadderPosition.set(54,93);
        snakeLadderPosition.set(55,7);
        snakeLadderPosition.set(59,17);
        snakeLadderPosition.set(62,96);
        snakeLadderPosition.set(64,36);
        snakeLadderPosition.set(66,87);
        snakeLadderPosition.set(69,33);
        snakeLadderPosition.set(73,1);
        snakeLadderPosition.set(80,100);
        snakeLadderPosition.set(83,19);
        snakeLadderPosition.set(92,51);
        snakeLadderPosition.set(95,24);
        snakeLadderPosition.set(98,28);
    }
    public int getNewPosition(int currentPosition){
        if(currentPosition>0 && currentPosition<=100){
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }

    public Board(){
        positionCoordinates = new ArrayList<>();
        populatePositionCoordinates();
        populateSnakeLadder();
    }

    private void populatePositionCoordinates(){
        positionCoordinates.add(new Pair<>(0,0));
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                //x coordinates
                 int xCord = 0;
                 if(i%2==0){
                     xCord = j*SnakeLadder.tileSize + SnakeLadder.tileSize/2;
                 }else{
                     xCord = SnakeLadder.tileSize*SnakeLadder.height - (j*SnakeLadder.tileSize)- SnakeLadder.tileSize/2;
                 }
                //y coordinates
                int yCord = SnakeLadder.tileSize*SnakeLadder.height - (i*SnakeLadder.tileSize)- SnakeLadder.tileSize/2;
                 //adding it to our positionCoordinates:
                positionCoordinates.add(new Pair<>(xCord,yCord));
            }
        }
    }

    int getXCoordinate(int position){
        if(position >= 1 && position <= 100){
            return positionCoordinates.get(position).getKey();
        }
        return -1;
    }

    int getYCoordinate(int position){
        if(position >= 1 && position <= 100){
            return positionCoordinates.get(position).getValue();
        }
        return -1;
    }

//    public static void main(String[] args) {
//        Board board = new Board();
//        for (int i = 0; i <board.positionCoordinates.size() ; i++) {
//            System.out.println(i+" $  x : "+ board.positionCoordinates.get(i).getKey()+"  y : "+board.positionCoordinates.get(i).getValue());
//        }
//    }
}
