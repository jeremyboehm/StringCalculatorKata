package com.jboehm.string.calculator;

import java.util.List;

public class StringCalculator {

    private final NumbersStringParser numbersStringParser;

    public StringCalculator() {
        this.numbersStringParser = new NumbersStringParser();
    }

    public int add(String numbersString) throws NotValidNumberString, NegativeNotAllowed {
        return numbersStringParser.isDelimiterSpecified(numbersString) ? addWithSpecifiedDelimiter(numbersString) : add(numbersString, ",");
    }

    private int add(String numbersString, String delimiter) throws NotValidNumberString, NegativeNotAllowed {
        NumbersString numbersStringHelper = new NumbersString();
        new NumbersString().validate(numbersString, delimiter);
        if (numbersString.isEmpty()) {
            return 0;
        }
        String cleanedNumbers = numbersStringHelper.clean(numbersString, delimiter);

        List<Integer> numbersIntArray = IntArrays.toList(cleanedNumbers, delimiter);

        return numbersIntArray.stream().mapToInt(integer -> integer).sum();
    }

    private int addWithSpecifiedDelimiter(String numbersString) throws NotValidNumberString, NegativeNotAllowed {
        String delimiter = numbersStringParser.getDelimiter(numbersString);
        String numbers = numbersStringParser.getNumbers(numbersString);

        return add(numbers, delimiter);
    }

}
