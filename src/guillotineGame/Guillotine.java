package guillotineGame;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * javafx application for guillotineGame.Guillotine game
 * @author rtp32
 */
public class Guillotine extends Application {

    // button that shows whose turn it is
    Button txt = new Button("Player 1's turn");

    // button that shows user 1's score
    Button user1score = new Button("0");

    // button that shows user 2's score
    Button user2score = new Button("0");


    /**
     * assigns color to each button based on guillotineGame.Group
     * @param b the button
     * @param g the card's group
     */
    public void chooseColor(Button b, Group g) {
        switch (g) {
            case ROY:
                b.setStyle("-fx-background-color: thistle");
                break;
            case CHU:
                b.setStyle("-fx-background-color: lightblue");
                break;
            case CIV:
                b.setStyle("-fx-background-color: lightgreen");
                break;
            case COM:
                b.setStyle("-fx-background-color: gainsboro");
                break;
            case MIL:
                b.setStyle("-fx-background-color: salmon");
                break;
        }
    }


    /**
     * updates each user's list of collected buttons
     * @param pane user's TilePane
     */
    public void updateCollectedButtons(TilePane pane) {
        Button b;  // new first button of each user's collected buttons list
        if (GuillotineFramework.getIsUser1Turn()) {
            b = new Button(GuillotineFramework.getUser1List()[GuillotineFramework.getUser1Index()].compactString());
            chooseColor(b, GuillotineFramework.getUser1List()[GuillotineFramework.getUser1Index()].getID().getGroup());
        }
        else {
            b = new Button(GuillotineFramework.getUser2List()[GuillotineFramework.getUser2Index()].compactString());
            chooseColor(b, GuillotineFramework.getUser2List()[GuillotineFramework.getUser2Index()].getID().getGroup());
        }
        pane.getChildren().add(b);
    }


    /**
     * creates each player's action buttons and their setOnAction() overridden methods from interface EventHandler<T>
     * @param pane user pane
     * @param unclaimed unclaimed TilePane
     * @param turn whose turn it is
     */
    public void makeButtonLists(TilePane pane, TilePane unclaimed, boolean turn) {

        Button b1 = new Button("Move first card back 4");  // moveBack(4) button
        b1.setOnAction(event -> {
            if (turn == GuillotineFramework.getIsUser1Turn()) {
                GuillotineFramework.getUnclaimedList().moveBack(4);
                b1.setDisable(true);
                updateUnclaimedList(unclaimed);
            }
        });

        Button b2 = new Button("Move first card back 3");  // moveBack(3) button
        b2.setOnAction(event -> {
            if (turn == GuillotineFramework.getIsUser1Turn()) {
                GuillotineFramework.getUnclaimedList().moveBack(3);
                b2.setDisable(true);
                updateUnclaimedList(unclaimed);
            }
        });

        Button b3 = new Button("Move first card back 2");  // moveBack(2) button
        b3.setOnAction(event -> {
            if (turn == GuillotineFramework.getIsUser1Turn()) {
                GuillotineFramework.getUnclaimedList().moveBack(2);
                b3.setDisable(true);
                updateUnclaimedList(unclaimed);
            }
        });

        Button b4 = new Button("Move first card back 1");  // moveBack(1) button
        b4.setOnAction(event -> {
            if (turn == GuillotineFramework.getIsUser1Turn()) {
                GuillotineFramework.getUnclaimedList().moveBack(1);
                b4.setDisable(true);
                updateUnclaimedList(unclaimed);
            }
        });

        Button b5 = new Button("Move first card to back");  //moveFrontToBack() button
        b5.setOnAction(event -> {
            if (turn == GuillotineFramework.getIsUser1Turn()) {
                GuillotineFramework.getUnclaimedList().moveFirstToLast();
                b5.setDisable(true);
                updateUnclaimedList(unclaimed);
            }
        });

        Button b6 = new Button("Move last card to front");  // moveBackToFront() button
        b6.setOnAction(event -> {
            if (turn == GuillotineFramework.getIsUser1Turn()) {
                GuillotineFramework.getUnclaimedList().moveLastToFirst();
                b6.setDisable(true);
                updateUnclaimedList(unclaimed);
            }
        });

        Button b7 = new Button("Reverse entire list");  // reverseList() button
        b7.setOnAction(event -> {
            if (turn == GuillotineFramework.getIsUser1Turn()) {
                GuillotineFramework.getUnclaimedList().reverseList();
                b7.setDisable(true);
                updateUnclaimedList(unclaimed);
            }
        });

        Button b8 = new Button("Reverse first 5 cards");  // reverseFirstK(5) button
        b8.setOnAction(event -> {
            if (turn == GuillotineFramework.getIsUser1Turn()) {
                GuillotineFramework.getUnclaimedList().reverseFirstK(5);
                b8.setDisable(true);
                updateUnclaimedList(unclaimed);
            }
        });

        Button b9 = new Button("Skip turn");  // skips user turn, does not update list
        b9.setOnAction(event -> {
            if (turn == GuillotineFramework.getIsUser1Turn()) {
                b9.setDisable(true);
                GuillotineFramework.toggleUserTurn();
                txt.setText(GuillotineFramework.getIsUser1Turn() ? "Player 1's turn" : "Player 2's turn");
            }
        });

        Button b10 = new Button("Take first card");  // takes first card, updates list
        b10.setOnAction(event -> {
            if (GuillotineFramework.getIsUser1Turn() && turn) {
                GuillotineFramework.addToUserList(GuillotineFramework.getUser1List());
                updateCollectedButtons(pane);
                GuillotineFramework.toggleUserTurn();
                txt.setText("Player 2's turn");
                user1score.setText("" + GuillotineFramework.calculateScore(GuillotineFramework.getUser1List()));
            }
            else if (! GuillotineFramework.getIsUser1Turn() && ! turn) {
                GuillotineFramework.addToUserList(GuillotineFramework.getUser2List());
                updateCollectedButtons(pane);
                GuillotineFramework.toggleUserTurn();
                txt.setText("Player 1's Turn");
                user2score.setText("" + GuillotineFramework.calculateScore(GuillotineFramework.getUser2List()));
            }
            updateUnclaimedList(unclaimed);
        });

        pane.getChildren().add(b1);
        pane.getChildren().add(b2);
        pane.getChildren().add(b3);
        pane.getChildren().add(b4);
        pane.getChildren().add(b5);
        pane.getChildren().add(b6);
        pane.getChildren().add(b7);
        pane.getChildren().add(b8);
        pane.getChildren().add(b9);
        pane.getChildren().add(b10);

    }


    /**
     * removes cards from unclaimed list
     * @param tile unclaimed TilePane so that the buttons know where to go
     */
    public void updateUnclaimedList(TilePane tile) {
        tile.getChildren().clear();
        for (Card c : GuillotineFramework.getUnclaimedList()) {
            Button b = new Button(c.toString());  // button representing each card
            chooseColor(b, c.getID().getGroup());
            b.setTextAlignment(TextAlignment.CENTER);
            b.setPrefHeight(50.0);
            b.setPrefWidth(175.0);
            tile.getChildren().add(b);
        }

        if (GuillotineFramework.getUnclaimedList().isEmpty()) {
            Button winner = new Button();  // button that shows the winner of the game
            winner.setText(GuillotineFramework.getWinner());
            tile.getChildren().add(winner);
            winner.setOnAction(event -> {
                Stage stage = (Stage) winner.getScene().getWindow();
                stage.close();  // if you click this button, it closes the window
            });
        }
    }


    /**
     * overridden start method from javafx Application
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();                 // layout for window

        TilePane turn = new TilePane();                           // says score and whose turn it is

        TilePane unclaimed = new TilePane(Orientation.VERTICAL);  // list of unclaimed cards
        TilePane user1 = new TilePane(Orientation.VERTICAL);      // list of user 1 buttons/actions
        TilePane user2 = new TilePane(Orientation.VERTICAL);      // list of user 2 buttons/actions

        // adds scoreboard to top
        turn.getChildren().add(user1score);
        turn.getChildren().add(txt);
        turn.getChildren().add(user2score);

        // makes sure window doesn't get super messed up
        user1.setPrefRows(30);
        user2.setPrefRows(30);
        unclaimed.setPrefRows(10);

        // sets alignment of each list/tile pane
        turn.setAlignment(Pos.CENTER);
        unclaimed.setAlignment(Pos.CENTER);
        user1.setAlignment(Pos.CENTER_LEFT);
        user2.setAlignment(Pos.CENTER_RIGHT);

        // makes each user's interface
        makeButtonLists(user1, unclaimed, true);
        makeButtonLists(user2, unclaimed, false);

        // creates list of unclaimed cards
        updateUnclaimedList(unclaimed);

        // lays out window
        borderPane.setTop(turn);
        borderPane.setCenter(unclaimed);
        borderPane.setLeft(user1);
        borderPane.setRight(user2);

        // starts the game
        Scene scene = new Scene(borderPane, 750, 750);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CSDS 132 Guillotine!");
        primaryStage.show();

        }


    /**
     * main method which starts the program
     * @param args number of cards in deck
     */
    public static void main(String... args) {
        GuillotineFramework.main(args);
        Application.launch(args);
    }

}
