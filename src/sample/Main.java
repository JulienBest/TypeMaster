package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    private static final int LIST_LENGTH = 4552;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    private HBox word;
    private BorderPane root;
    private int wordCount;
    private int letterCount;

    private String currentWord;
    private int currentLetter;
    private Text letter;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();
        primaryStage.setTitle("Type Master");

        currentWord = genWord();
        currentLetter = 0;
        letterCount = 0;
        Button start = new Button("Start");
        BorderPane.setAlignment(start, Pos.BOTTOM_CENTER);
        root.setBottom(start);

        word = buildBox();
        letter = new Text("Letter count: " + wordCount);

        setPosition(letter, 20, 20);
        setPosition(word, WIDTH / 2 - 50, HEIGHT / 2 - 20);

        //root.getChildren().addAll(word, letter);
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
                    currentWord = genWord();
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
        word = buildBox();
        setPosition(word, WIDTH / 2 - 50, HEIGHT / 2 - 20);
        root.setCenter(word);
    }

    private String genWord() {

        String word = "File not found";

        try {
            FileReader fr = new FileReader("nouns.txt");
            BufferedReader br = new BufferedReader(fr);

            for (int i = 0; i < ((int) (Math.random() * LIST_LENGTH)); i++) {
                word = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return word;
    }

    private void setPosition(Node node, int x, int y) {
        node.setTranslateY(y);
        node.setTranslateX(x);
    }

    private HBox buildBox() {
        HBox box = new HBox();
        StringBuilder before = new StringBuilder();
        StringBuilder after = new StringBuilder();
        String current;

        if (currentLetter == currentWord.length()) {
            current = String.valueOf(currentWord.charAt(currentWord.length() - 1));
        } else {
            current = String.valueOf(currentWord.charAt(currentLetter));

        }
        if (currentLetter != 0) {
            for (int i = 0; i < currentLetter; i++) {
                before.append(currentWord.charAt(i));
            }
        }
        if (currentLetter != currentWord.length()) {
            for (int i = currentLetter + 1; i < currentWord.length(); i++) {
                after.append(currentWord.charAt(i));
            }
        }


        Text beforeText = new Text(before.toString());
        Text afterText = new Text(after.toString());
        Text currentText = new Text(current);


        currentText.setStyle("-fx-font-size: 30px");
        afterText.setStyle("-fx-font-size: 30px");
        beforeText.setStyle("-fx-font-size: 30px");
        beforeText.setFill(Color.BLACK);
        currentText.setFill(Color.RED);

        box.getChildren().addAll(beforeText, currentText, afterText);

        return box;
    }
}
