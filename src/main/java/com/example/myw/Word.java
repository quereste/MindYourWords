package com.example.myw;

public class Word {
    String word;
    String definition;

    Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    @Override
    public String toString() {
        return word + " " + definition + "\n";
    }
}
