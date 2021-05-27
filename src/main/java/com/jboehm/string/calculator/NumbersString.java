package com.jboehm.string.calculator;

public class NumbersString {

    private final NumbersStringParser numbersStringParser;

    public NumbersString() {
        this.numbersStringParser = new NumbersStringParser();
    }

    void validate(String numbersString, String delimiter) throws NotValidNumberString, NegativeNotAllowed {
        numbersStringParser.containsNewLines(numbersString, delimiter);
        numbersStringParser.containsNegative(numbersString, delimiter);
    }

    String clean(String numbersString, String delimiter) {
        return numbersStringParser.clean(numbersString, delimiter);
    }

}
