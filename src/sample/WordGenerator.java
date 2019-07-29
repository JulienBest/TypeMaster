package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordGenerator {

    private static final int LIST_LENGTH = 4552;

    public String genWord() {
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
}
