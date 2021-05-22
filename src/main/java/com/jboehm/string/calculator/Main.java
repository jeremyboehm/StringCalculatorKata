package com.jboehm.string.calculator;

import java.text.StringCharacterIterator;

public class Main {
    public static void main(String[] args) {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("1,2");
        System.out.println(result);
    }
}
