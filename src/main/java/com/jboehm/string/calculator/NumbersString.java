package com.jboehm.string.calculator;

import java.util.List;
import java.util.stream.Collectors;

public class NumbersString {

    String clean(String numbersString, String delimiter) {
        if (!numbersString.contains("\n")) {
            return numbersString;
        }
        return numbersString.replace("\n", delimiter);
    }

    void validate(String numbersString, String delimiter) throws NotValidNumberString, NegativeNotAllowed {
        containsNewLines(numbersString, delimiter);
        containsNegative(numbersString, delimiter);

    }

    private void containsNewLines(String numbersString, String delimiter) throws NotValidNumberString {
        if (numbersString.contains("\n" + delimiter) || numbersString.contains(delimiter + "\n")) {
            throw new NotValidNumberString();
        }
    }

    private void containsNegative(String numbersString, String delimiter) throws NegativeNotAllowed {
        if (numbersString.contains("-")) {
            throw new NegativeNotAllowed("Negatives not allowed : " + getNegatives(numbersString, delimiter));
        }
    }

    private String getNegatives(String numbersString, String delimiter) {
        List<Integer> numbersList = IntArrays.toList(numbersString, delimiter);
        List<Integer> negatives = numbersList.stream().filter(number -> number < 0).collect(Collectors.toList());
        return negatives.stream().map(number -> number + " ").collect(Collectors.joining());
    }
}
