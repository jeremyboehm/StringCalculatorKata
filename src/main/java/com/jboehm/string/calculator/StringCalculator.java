package com.jboehm.string.calculator;

public class StringCalculator {

    public StringCalculator() {
    }

    public int add(String input) throws NotValidNumberString, NegativeNotAllowed {
        if (input.isEmpty()) {
            return 0;
        }
        NumbersString numbersString = new NumbersString(input);

        return numbersString.getNumbersAsList().stream().mapToInt(number -> number).sum();
    }
}
