package com.example.rps;


import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.effect.*;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class RockPaperScissors extends Application {

    //Declare global variables to be used in functions
    ImageView userRock;
    ImageView userPaper;
    ImageView userScissors;
    ImageView computerImage;
    Image rockImage = new Image("file:Rock.jpg");
    Image paperImage = new Image("file:Paper.jpg");
    Image scissorsImage = new Image("file:Scissors.jpg");
    ColorAdjust colorGrey;
    ColorAdjust contrastNormal;
    TextField uWins;
    TextField tfTies;
    TextField cWins;

    Button confirmChoice;
    Alert alert;

    Random randNum = new Random();//Creates a random object
    //Declare counting variables
    int userChoice, computersChoice, userWins = 0, userLosses = 0, tiesInGame = 0;

    @Override
    public void start(Stage primaryStage) {
        //Set size of the scene
        final int WIDTH = 1000;
        final int HEIGHT = 700;
        //Create the ColorAdjust to grey out unselected images
        colorGrey = new ColorAdjust();
        colorGrey.setContrast(-0.5);
        contrastNormal = new ColorAdjust();
        contrastNormal.setContrast(0.0);
        userChoice = 0;


        //Create AudioClips for the different actions the user can take
//        URL rockResource = getClass().getResource("RockAudio.mp3");
//        AudioClip rockAudio = new AudioClip(rockResource.toString());

//        URL paperResource = getClass().getResource("PaperAudio.mp3");
//        AudioClip paperAudio = new AudioClip(paperResource.toString());

//        URL scissorsResource = getClass().getResource("ScissorsAudio.mp3");
//        AudioClip scissorsAudio = new AudioClip(scissorsResource.toString());

//        URL win = getClass().getResource("YouWon.mp3");
//        youWon = new AudioClip(win.toString());

//        URL lose = getClass().getResource("YouLost.mp3");
//        computerWon = new AudioClip(lose.toString());

//        URL tie = getClass().getResource("TieGame.mp3");
//        tieGame = new AudioClip(tie.toString());

//        URL gameIsOver = getClass().getResource("GameOver.mp3");
//        AudioClip gameOver = new AudioClip(gameIsOver.toString());

//        URL startGame = getClass().getResource("StartRound.mp3");
//        startRound = new AudioClip(startGame.toString());

        //Create VBox for place choice/Images
        userRock = new ImageView(rockImage);
        userRock.setFitHeight(200);
        userRock.setFitWidth(215);
        //Play audio, grey out the other choices, and set userChoice to 1
        userRock.setOnMouseClicked(event
                -> {
//            rockAudio.play();
            makeChoice(1);
            userChoice = 1;
        });

        userPaper = new ImageView(paperImage);
        userPaper.setFitHeight(180);
        userPaper.setFitWidth(215);
        //Play audio, grey out the other choices, and set userChoice to 2
        userPaper.setOnMouseClicked(event
                -> {
//            paperAudio.play();
            makeChoice(2);
            userChoice = 2;
        });

        userScissors = new ImageView(scissorsImage);
        userScissors.setFitHeight(180);
        userScissors.setFitWidth(215);
        //Play audio, grey out the other choices, and set userChoice to 3
        userScissors.setOnMouseClicked(event
                -> {
//            scissorsAudio.play();
            makeChoice(3);
            userChoice = 3;
        });
        VBox playerChoice = new VBox(10, userRock, userPaper, userScissors);


        //Set score for player and computer
        Label userWinsTracker = new Label("User wins");
        Label computerWinsTracker = new Label("Computer wins");
        Label labelTiesTracker = new Label("Ties");

        uWins = new TextField("0");
        uWins.setMaxWidth(40);
        uWins.setAlignment(Pos.CENTER);
        uWins.setEditable(false);

        tfTies = new TextField("0");
        tfTies.setMaxWidth(40);
        tfTies.setAlignment(Pos.CENTER);
        tfTies.setEditable(false);

        cWins = new TextField("0");
        cWins.setMaxWidth(40);
        cWins.setAlignment(Pos.CENTER);
        cWins.setEditable(false);

        //Align the VBoxes with the user, center, and computer areas
        VBox forUser = new VBox(userWinsTracker, uWins);
        forUser.setAlignment(Pos.CENTER);
        VBox forComputer = new VBox(computerWinsTracker, cWins);
        forComputer.setAlignment(Pos.CENTER);
        VBox forTies = new VBox(labelTiesTracker, tfTies);
        forTies.setAlignment(Pos.CENTER);
        forTies.setTranslateX(30.0);

        //Set image for Computer on right side
        computerImage = new ImageView(rockImage);
        computerImage.setFitHeight(350);
        computerImage.setFitWidth(350);
        computerImage.setVisible(false);


        //Set image and confirm choice in middle
        Image versus = new Image("file:vs.png");
        ImageView versusImage = new ImageView(versus);
        versusImage.setTranslateX(30.0);
        confirmChoice = new Button("Confirm your selection");
        confirmChoice.setTranslateY(90.0);
        confirmChoice.setTranslateX(30.0);
        confirmChoice.setPrefSize(200, 60);
        //Call the confirmSelection function to check the users choice to the pc's
        confirmChoice.setOnAction(event
                -> confirmSelection());
        VBox vs = new VBox(versusImage, confirmChoice);
        vs.setAlignment(Pos.CENTER);

        //Reset, exit, info buttons at bottom
        Button reset = new Button("Reset");
        //Resets the game
        reset.setOnAction((ActionEvent)
                -> reset());
        reset.setPrefSize(100, 30);

        Button exit = new Button("Exit");
        //Play audio and exit the game after the audio is done playing
        exit.setOnAction(event
                -> System.exit(0));
        exit.setPrefSize(100, 30);

        Button info = new Button("How to play");
        //Opens a JOptionPane that explains how to play the game

        info.setPrefSize(100, 30);
        HBox buttonGroup = new HBox(50, reset, info, exit);
        buttonGroup.setTranslateX(30.0);

        buttonGroup.setAlignment(Pos.CENTER);

        //Create the GridPane
        GridPane root = new GridPane();
        //Add the different layouts to the GridPane
        root.add(playerChoice, 0, 1, 1, 1);
        root.add(forUser, 0, 0);
        root.add(forTies, 1, 0);
        root.add(forComputer, 2, 0);
        root.add(vs, 1, 1);
        root.add(computerImage, 2, 1);
        root.add(buttonGroup, 1, 3);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        info.setOnAction((ActionEvent)
                -> getInfo());
        primaryStage.setTitle("Rock Paper Scissors!");
        primaryStage.setScene(scene);
//        scene.getStylesheets().add("RPS.css");
        primaryStage.show();
    }

    /**
     * Greys out the images that the user did not select
     * @param choice Passes the choice 1, 2, or 3 depending on the option selected
     * the player selected
     */
    public void makeChoice(int choice) {
        switch (choice) {
            case 1 -> {
                //ContrastNormal sets the image to its normal state
                //ColorGrey colors out the other options not selected
                userRock.setEffect(contrastNormal);
                userPaper.setEffect(colorGrey);
                userScissors.setEffect(colorGrey);
            }
            case 2 -> {
                userPaper.setEffect(contrastNormal);
                userRock.setEffect(colorGrey);
                userScissors.setEffect(colorGrey);
            }
            case 3 -> {
                userScissors.setEffect(contrastNormal);
                userPaper.setEffect(colorGrey);
                userRock.setEffect(colorGrey);
            }
        }
    }

    /**
     * Once the user has selected their option, this function runs and determines
     * a winner
     */
    public void confirmSelection() {
        //Randomly select a number between 1-3, and change computer image based
        //on that number

        if (userChoice == 0) {
            JOptionPane.showMessageDialog(null, "Please make a selection before"
                    + "clicking the \"Confirm your selection\" button.");
            return;
        }
        //Determine the computers choice
        computersChoice = randNum.nextInt(3) + 1;
//        startRound.play();
//        do {
//        } while (startRound.isPlaying());

        //Used to change the computers image based on the choice
        switch (computersChoice) {
            case 1 -> {
                computerImage.setImage(rockImage);
                computerImage.setVisible(true);
            }
            case 2 -> {
                computerImage.setImage(paperImage);
                computerImage.setVisible(true);
            }
            case 3 -> {
                computerImage.setImage(scissorsImage);
                computerImage.setVisible(true);
            }
        }

        //Calls the ChooseWinner function to check the users choice to the
        //computers choice
        if (ChooseWinner(userChoice, computersChoice).equals("You won!")) {
            userWins += 1;
//            youWon.play();
            //Adds a win for the user to the text box in the GUI
            uWins.setText(String.format("%d", userWins));
        }
        if (ChooseWinner(userChoice, computersChoice).equals("You lost!")) {
            userLosses += 1;
//            computerWon.play();
            //Adds a win for the computer to the text box in the GUI
            cWins.setText(String.format("%d", userLosses));
        }
        if (ChooseWinner(userChoice, computersChoice).equals("It is a tie!")) {
            tiesInGame += 1;
//            tieGame.play();
            //Adds a tie to the text box in the GUI
            tfTies.setText(String.format("%d", tiesInGame));
        }
    }

    public static String ChooseWinner(int user, int computer) {
        //If ints are the same, return a tie
        if (user == computer) {
            return "It is a tie!";
        }

        //If user has rock vs computer scissors, then user won
        if (user == 1 && computer == 3) {
            return "You won!";
        }
        //If computer has rock vs user scissors, then user lost
        if (computer == 1 && user == 3) {
            return "You lost!";
        }

        //If none of the above if statements are true,
        //whoever has the higher int value wins
        if (user > computer) {
            return "You won!";
        }

        return "You lost!";

    }

    /**
     * Resets the game
     */
    public void reset() {
        userWins = 0;
        userLosses = 0;
        tiesInGame = 0;
        userChoice = 0;
        uWins.setText(String.format("%d", userWins));
        cWins.setText(String.format("%d", userLosses));
        tfTies.setText(String.format("%d", tiesInGame));
        userRock.setEffect(contrastNormal);
        userPaper.setEffect(contrastNormal);
        userScissors.setEffect(contrastNormal);
        computerImage.setVisible(false);
    }

    /**
     * JOptionPane to explain how the game is played.
     */
    public void getInfo() {

        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("How to play.");
        alert.setHeaderText("How to play the game.");
        alert.setContentText("""
                On the left side, select the choice you want.
                Once you know that is the option you want,
                click the "Confirm your selection" button in the middle.
                The computer will then make it selection.
                Once the game is over, choose to either play again or exit.

                Have fun!""");

        alert.showAndWait();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
