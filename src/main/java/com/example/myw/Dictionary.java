package com.example.myw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Dictionary {
    private static Dictionary instance;
    private static int size = 69306;
    private Word[] array;

    public static Dictionary getInstance() {
        if (instance == null) {
            instance = new Dictionary();
        }

        return instance;
    }

    private Dictionary() {
        array = new Word [size];

        int currentIndex = 0;

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("dictionary"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String line;

        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] splitResult = line.split(" ", 2);

            array [currentIndex] = new Word (splitResult [0], splitResult [1]);

            currentIndex++;
        }

        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Word findWord(Pattern p) {
        for (Word w : array) {
            if (p.matcher(w.word).matches()) {
                return w;
            }
        }

        return null;
    }

}
