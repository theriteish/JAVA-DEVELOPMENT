package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
   private Circle coin;
   private  int currPosition;
   private String name;

   private static Board gameBoard = new Board();

   public Player(int tileSize , Color coinColor,String playerName){
       coin = new Circle(tileSize/2);
       coin.setFill(coinColor);
       currPosition = 0;
       movePlayer(1);
       name = playerName;
    }

    public void movePlayer(int diceValue){
       if(currPosition+diceValue<=100) {
           currPosition += diceValue;

           TranslateTransition secondMove = null, firstMove = translateAnimation(diceValue);

          // translateAnimation(diceValue);

           int newPosition = gameBoard.getNewPosition(currPosition);
           if(newPosition != currPosition || newPosition!=-1){
               //make a movement
               currPosition = newPosition;
              secondMove = translateAnimation(6);
           }
           if(secondMove == null){
               firstMove.play();
           }else{
               SequentialTransition sequentialTransition = new SequentialTransition(firstMove,
                       new PauseTransition(Duration.millis(1000)),secondMove);
               sequentialTransition.play();
           }
       }
//       int x = gameBoard.getXCoordinate(currPosition);
//       int y = gameBoard.getYCoordinate(currPosition);
//       coin.setTranslateX(x);
//       coin.setTranslateY(y);
    }
    private TranslateTransition translateAnimation(int diceValue){
        TranslateTransition animate = new TranslateTransition(Duration.millis(200*diceValue),coin);
        animate.setToX(gameBoard.getXCoordinate(currPosition));
        animate.setToY(gameBoard.getYCoordinate(currPosition));
        animate.setAutoReverse(false);
        return animate;
    }
    public void bringToStartingPosition(){
       currPosition = 1;
       movePlayer(0);
    }

    boolean isWinner(){
       if(currPosition == 100){
           return true;
       }
       return false;
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public String getName() {
        return name;
    }
}
