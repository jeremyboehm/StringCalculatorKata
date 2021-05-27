package com.jboehm.string.calculator;

public class StringCalculator {

    public StringCalculator() {
    }

    public int add(String numbersString) throws NotValidNumberString, NegativeNotAllowed {
        if (numbersString.isEmpty()) {
            return 0;
        }
        NumbersString numbersString1 = new NumbersString(numbersString);

        return numbersString1.getNumbersAsList().stream().mapToInt(number -> number).sum();
    }
}
