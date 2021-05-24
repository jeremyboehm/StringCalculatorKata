package com.jboehm.string.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String numbers) throws NotValidNumberString {
        return numbers.startsWith("/") ? addWithSpecifiedDelimiter(numbers) : add(numbers, ",");
    }

    private int add(String numbers, String delimiter) throws NotValidNumberString {
        validateString(numbers, delimiter);
        if (numbers.isEmpty()) {
            return 0;
        }
        String cleanedNumbers = cleanNumbersString(numbers ,delimiter);

        String[] numbersSplit = cleanedNumbers.split(delimiter);
        List<Integer> numbersIntArray = toIntList(numbersSplit);

        return numbersIntArray.stream().mapToInt(integer -> integer).sum();
    }

    private int addWithSpecifiedDelimiter(String string) throws NotValidNumberString {
        String[] split = string.split("\n", 2);
        String delimiter = split[0].replaceAll("/", "");
        String numbers = Arrays.stream(split, 1, split.length).collect(Collectors.joining());

        return add(numbers, delimiter);
    }

    private void validateString(String numbers, String delimiter) throws NotValidNumberString {
        if (numbers.contains("\n" + delimiter) || numbers.contains(delimiter + "\n")) {
            throw new NotValidNumberString();
        }
    }

    private String cleanNumbersString(String numbers, String delimiter) {
        if (!numbers.contains("\n")) {
            return numbers;
        }
        return numbers.replace("\n", delimiter);
    }

    private List<Integer> toIntList(String[] numbers) {
        List<Integer> numbersList = new ArrayList<>();
        for (String number : numbers) {
            numbersList.add(Integer.parseInt(number));
        }
        return numbersList;
    }
}
