package sample;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BoxBuilder {
    public HBox buildBox(int currentLetter, String currentWord) {
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
