package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    private HBox word;
    private BorderPane root;
    private int wordCount;
    private int letterCount;

    private String currentWord;
    private int currentLetter;
    private Text letter;
    private WordGenerator wordGenerator;
    private BoxBuilder boxBuilder;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();
        primaryStage.setTitle("Type Master");
        wordGenerator = new WordGenerator();
        boxBuilder = new BoxBuilder();

        currentWord = wordGenerator.genWord();
        currentLetter = 0;
        letterCount = 0;
        //Button start = new Button("Start");
        //BorderPane.setAlignment(start, Pos.BOTTOM_CENTER);
        //root.setBottom(start);

        word = boxBuilder.buildBox(currentLetter, currentWord);
        letter = new Text("Letter count: " + wordCount);

        setPosition(letter, 20, 20);
        setPosition(word, WIDTH / 2 - 50, HEIGHT / 2 - 20);

        root.setCenter(word);
        root.setTop(letter);
        BorderPane.setAlignment(letter, Pos.TOP_CENTER);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        scene.setOnKeyPressed(event -> {
            if (event.getText().equals(String.valueOf(currentWord.charAt(currentLetter)))) {
                currentLetter++;
                letterCount++;
                letter.setText("Letter count: " + letterCount);
                synchronize();

                if (currentLetter == currentWord.length()) {
                    currentLetter = 0;
                    currentWord = wordGenerator.genWord();
                    synchronize();
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void synchronize() {
        root.getChildren().removeAll(word);
        word = boxBuilder.buildBox(currentLetter, currentWord);
        setPosition(word, WIDTH / 2 - 50, HEIGHT / 2 - 20);
        root.setCenter(word);
    }

    private void setPosition(Node node, int x, int y) {
        node.setTranslateY(y);
        node.setTranslateX(x);
    }
}
