package com.jboehm.string.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String numbersString) throws NotValidNumberString, NegativeNotAllowed {
        return numbersString.startsWith("/") ? addWithSpecifiedDelimiter(numbersString) : add(numbersString, ",");
    }

    private int add(String numbersString, String delimiter) throws NotValidNumberString, NegativeNotAllowed {
        NumbersString numbersStringHelper = new NumbersString();
        numbersStringHelper.validate(numbersString, delimiter);
        if (numbersString.isEmpty()) {
            return 0;
        }
        String cleanedNumbers = numbersStringHelper.clean(numbersString, delimiter);

        List<Integer> numbersIntArray = IntArrays.toList(cleanedNumbers, delimiter);

        return numbersIntArray.stream().mapToInt(integer -> integer).sum();
    }

    private int addWithSpecifiedDelimiter(String numbersString) throws NotValidNumberString, NegativeNotAllowed {
        String[] split = numbersString.split("\n", 2);
        String delimiter = split[0].replaceAll("/", "");
        String numbers = Arrays.stream(split, 1, split.length).collect(Collectors.joining());

        return add(numbers, delimiter);
    }

}
