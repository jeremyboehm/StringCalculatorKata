package com.jboehm.string.calculator;

import java.util.List;
import java.util.stream.Collectors;

public class NumbersStringParser {

    private boolean isDelimiterSpecified(String numbersString) {
        return numbersString.startsWith("/");
    }

    private void containsNegative(String numbersString, String delimiter) throws NegativeNotAllowed {
        if (numbersString.contains("-")) {
            throw new NegativeNotAllowed("Negatives not allowed : " + getNegatives(numbersString, delimiter));
        }
    }

    private void containsNewLines(String numbersString, String delimiter) throws NotValidNumberString {
        if (numbersString.contains("\n" + delimiter) || numbersString.contains(delimiter + "\n")) {
            throw new NotValidNumberString();
        }
    }

    String getNumbers(String numbersString) {
        return isDelimiterSpecified(numbersString) ? splitDelimiterAndNumbers(numbersString)[1] : numbersString;
    }

    String getDelimiter(String numbersString) {
        if (isDelimiterSpecified(numbersString)) {
            String[] split = splitDelimiterAndNumbers(numbersString);
            return split[0].replaceAll("/", "");
        }
        return ",";
    }

    private String getNegatives(String numbersString, String delimiter) {
        List<Integer> numbersList = IntArrays.toList(numbersString, delimiter);
        List<Integer> negatives = numbersList.stream().filter(number -> number < 0).collect(Collectors.toList());
        return negatives.stream().map(number -> number + " ").collect(Collectors.joining());
    }

    private String[] splitDelimiterAndNumbers(String numbersString) {
        return numbersString.split("\n", 2);
    }

    String clean(String numbersString, String delimiter) {
        if (!numbersString.contains("\n")) {
            return numbersString;
        }
        return isDelimiterSpecified(numbersString) ? numbersString.substring(numbersString.indexOf("\n") + 1).replace("\n", delimiter) : numbersString.replace("\n", delimiter);
    }

    void validate(String numbersString) throws NotValidNumberString, NegativeNotAllowed {
        containsNewLines(getNumbers(numbersString), getDelimiter(numbersString));
        containsNegative(getNumbers(numbersString), getDelimiter(numbersString));
    }
}
