package com.example.snakeladder;

import com.example.snakeladder.Tile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize=40, width=10, height=10;

    //Adding button to pane & display (label) line
    public static final int buttonLine = height*tileSize + 50 , infoLine = buttonLine-30;

    private static Dice dice = new Dice();
    private Player playerOne , playerTwo;
    private boolean gameStarted = false , playerOneTurn = false, playerTwoTurn = false;

    private Pane createContent() throws FileNotFoundException {
        Pane root = new Pane();
        //setting the size of pane(frame)
        root.setPrefSize(width*tileSize, height*tileSize + 100);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }
        //we use file input stream because sometimes the image file wont get associated directly, so use file input stream
        //to get path of file
        //to add image

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Acer\\Desktop\\Java Development\\SnakeLadder\\src\\main\\SnakeLadder2.png");
        Image img = new Image(fileInputStream);
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        //To create buttons
        Button playerOneButton = new Button("BLUE");
        Button playerTwoButton = new Button("RED");
        Button startButton = new Button("Start");

        //Translate all the buttons to buttonLine: Adding controls
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(160);

        //create labels and add it
        //Info /display
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start the Game");

        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(20);
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(300);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);



        playerOne = new Player(tileSize-8, Color.BLUE,"BLUE");
        playerTwo = new Player(tileSize-10, Color.BROWN, "RED" );

        //player action:
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Value : "+ diceValue);
                        playerOne.movePlayer(diceValue);
                        //winning condition:
                        if(playerOne.isWinner()){
                            //announce the winner
                            diceLabel.setText(playerOne.getName()+" WON THE GAME!!!");
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted = false;
                        }else{
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn ");
                        }
                    }
                }
            }
        });
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Value : "+ diceValue);
                        playerTwo.movePlayer(diceValue);
                        //winning condition:
                        if(playerTwo.isWinner()){
                            //announce the winner
                            diceLabel.setText(playerTwo.getName()+" WON THE GAME!!!");
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted = false;
                        }else{
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn ");

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                        }
                    }
                }
            }
        });
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                playerOneTurn = true;

                playerOneLabel.setText("Your Turn ");
                playerOneButton.setDisable(false);
                playerOne.bringToStartingPosition();

                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.bringToStartingPosition();

            }
        });

        root.getChildren().addAll(board,playerOneButton,playerTwoButton,startButton,playerOneLabel,playerTwoLabel,diceLabel,
                playerOne.getCoin(),playerTwo.getCoin());


        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}