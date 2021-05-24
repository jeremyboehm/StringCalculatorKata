package com.jboehm.string.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String numbers) throws NotValidNumberString, NegativeNotAllowed {
        return numbers.startsWith("/") ? addWithSpecifiedDelimiter(numbers) : add(numbers, ",");
    }

    private int add(String numbers, String delimiter) throws NotValidNumberString, NegativeNotAllowed {
        validateString(numbers, delimiter);
        if (numbers.isEmpty()) {
            return 0;
        }
        String cleanedNumbers = cleanNumbersString(numbers ,delimiter);

        List<Integer> numbersIntArray = toList(cleanedNumbers, delimiter);

        return numbersIntArray.stream().mapToInt(integer -> integer).sum();
    }

    private int addWithSpecifiedDelimiter(String string) throws NotValidNumberString, NegativeNotAllowed {
        String[] split = string.split("\n", 2);
        String delimiter = split[0].replaceAll("/", "");
        String numbers = Arrays.stream(split, 1, split.length).collect(Collectors.joining());

        return add(numbers, delimiter);
    }

    private void validateString(String numbers, String delimiter) throws NotValidNumberString, NegativeNotAllowed {
        containsNewLines(numbers, delimiter);
        containsNegative(numbers, delimiter);

    }

    private void containsNewLines(String numbers, String delimiter) throws NotValidNumberString {
        if (numbers.contains("\n" + delimiter) || numbers.contains(delimiter + "\n")) {
            throw new NotValidNumberString();
        }
    }

    private void containsNegative(String numbers, String delimiter) throws NegativeNotAllowed {
        if (numbers.contains("-")) {
            throw new NegativeNotAllowed("Negatives not allowed : " + getNegatives(numbers, delimiter));
        }
    }

    private String getNegatives(String numbers, String delimiter) {
        List<Integer> numbersList = toList(numbers, delimiter);
        List<Integer> negatives = numbersList.stream().filter(number -> number < 0).collect(Collectors.toList());
        return negatives.stream().map(number -> number + " ").collect(Collectors.joining());
    }

    private String cleanNumbersString(String numbers, String delimiter) {
        if (!numbers.contains("\n")) {
            return numbers;
        }
        return numbers.replace("\n", delimiter);
    }

    private List<Integer> toList(String numbers, String delimiter) {
        String[] split = numbers.split(delimiter);
        return toIntList(split);
    }

    private List<Integer> toIntList(String[] numbers) {
        List<Integer> numbersList = new ArrayList<>();
        for (String number : numbers) {
            numbersList.add(Integer.parseInt(number));
        }
        return numbersList;
    }
}
