package com.jboehm.string.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumbersStringParser {

    private String getNegatives(String numbersString, String delimiter) {
        List<Integer> numbersList = IntArrays.toList(numbersString, delimiter);
        List<Integer> negatives = numbersList.stream().filter(number -> number < 0).collect(Collectors.toList());
        return negatives.stream().map(number -> number + " ").collect(Collectors.joining());
    }

    void containsNegative(String numbersString, String delimiter) throws NegativeNotAllowed {
        if (numbersString.contains("-")) {
            throw new NegativeNotAllowed("Negatives not allowed : " + getNegatives(numbersString, delimiter));
        }
    }

    void containsNewLines(String numbersString, String delimiter) throws NotValidNumberString {
        if (numbersString.contains("\n" + delimiter) || numbersString.contains(delimiter + "\n")) {
            throw new NotValidNumberString();
        }
    }

    boolean isDelimiterSpecified(String numbersString) {
        return numbersString.startsWith("/");
    }

    private String[] splitDelimiterAndNumbers(String numbersString) {
        return numbersString.split("\n", 2);
    }

    String getNumbers(String numbersString) {
        String[] split = splitDelimiterAndNumbers(numbersString);
        return Arrays.stream(split, 1, split.length).collect(Collectors.joining());
    }

    String getDelimiter(String numbersString) {
        String[] split = splitDelimiterAndNumbers(numbersString);
        return split[0].replaceAll("/", "");
    }

    String clean(String numbersString, String delimiter) {
        if (!numbersString.contains("\n")) {
            return numbersString;
        }
        return numbersString.replace("\n", delimiter);
    }
}
