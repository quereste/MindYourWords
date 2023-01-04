package com.example.myw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

public class EasyCompositor implements BoardCompositor {

    Dictionary dictionary;
    String basicRegex = "[a-z]";
    int bound = 9;
    int offset = 5;

    int wordBoundLeft = 3;
    int wordBoundRight = 4;
    int wordOffset = 1;
    int height = 16;
    int width = 11;
    int solutionColumn = 5;

    EasyCompositor() {
        dictionary = Dictionary.getInstance();
    }
    @Override
    public BoardModel compose() {
        BoardModelBuilder builder = new BoardModelBuilder();

        Random r = new Random();

        int passwordLength = r.nextInt(bound) + offset;

        String pattern = basicRegex.repeat(passwordLength);

        Word password = dictionary.findWord(Pattern.compile(pattern));

        System.out.println(password.word);

        int left = height - passwordLength;

        int fromUp = left / 2;

        int fromDown = passwordLength + fromUp;

        builder.buildModel(height, width);

        System.out.println(fromUp + " " + fromDown);
        for (int i = 0; i < fromUp; i++) {
            for (int j = 0; j < width; j++) {
                builder.addContent(new TileContent(TileType.EMPTY, ""), i, j);
            }
        }

        for (int i = fromUp; i < fromDown; i++) {
            String passwordLetter = password.word.substring(i - fromUp, i - fromUp + 1);

            int lengthFromLeft = r.nextInt(wordBoundLeft) + wordOffset;
            int lengthFromRight = r.nextInt(wordBoundRight) + wordOffset;

            Pattern wordPattern = Pattern.compile(basicRegex.repeat(lengthFromLeft) + "[" + passwordLetter +  "]" + basicRegex.repeat(lengthFromRight));

            Word word = dictionary.findWord(wordPattern);

            System.out.println(word.word);
            builder.addContent(new TileContent(TileType.SOLUTION, passwordLetter), i, solutionColumn);

            int firstBreak = solutionColumn - lengthFromLeft - 1;
            int lastBreak = solutionColumn + lengthFromRight + 1;

            int j = 0;

            for (; j < firstBreak; j++) {
                builder.addContent(new TileContent(TileType.EMPTY, ""), i, j);
            }

            builder.addContent(new TileContent(TileType.DEFINITION, word.definition), i, firstBreak);

            int wordIndex = 0;

            for (j = firstBreak + 1; j < solutionColumn; j++) {
                builder.addContent(new TileContent(TileType.BASIC, word.word.substring(wordIndex, wordIndex + 1)), i, j);
                wordIndex++;
            }

            wordIndex++;

            for (j = solutionColumn + 1; j < lastBreak; j++) {
                builder.addContent(new TileContent(TileType.BASIC, word.word.substring(wordIndex, wordIndex + 1)), i, j);
                wordIndex++;
            }

            for (j = lastBreak; j < width; j++) {
                builder.addContent(new TileContent(TileType.EMPTY, ""), i, j);
            }
        }


        for (int i = fromDown; i < height; i++) {
            for (int j = 0; j < width; j++) {
                builder.addContent(new TileContent(TileType.EMPTY, ""), i, j);
            }
        }



        return builder.getModel();
    }

    private void read() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("dictionary"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
// delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(stringBuilder);

        String content = stringBuilder.toString();
    }
}
