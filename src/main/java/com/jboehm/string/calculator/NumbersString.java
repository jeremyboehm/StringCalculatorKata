package com.jboehm.string.calculator;

import java.util.List;

public class NumbersString {

    private final NumbersStringParser numbersStringParser;
    private final String numbers;
    private final String delimiter;

    public NumbersString(String numbersString) throws NotValidNumberString, NegativeNotAllowed {
        this.numbersStringParser = new NumbersStringParser();
        validate(numbersString);
        this.delimiter = numbersStringParser.getDelimiter(numbersString);
        this.numbers = clean(numbersString);
    }

    void validate(String numbersString) throws NotValidNumberString, NegativeNotAllowed {
        numbersStringParser.validate(numbersString);
    }

    private String clean(String numbersString) {
        return numbersStringParser.clean(numbersString, delimiter);
    }

    public List<Integer> getNumbersAsList() {
        return IntArrays.toList(numbers, delimiter);
    }

}
