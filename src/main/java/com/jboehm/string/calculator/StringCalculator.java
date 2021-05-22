package com.jboehm.string.calculator;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String cleanedNumbers = cleanNumbersString(numbers);

        String[] numbersSplit = cleanedNumbers.split(",");
        List<Integer> numbersIntArray = toIntList(numbersSplit);

        return numbersIntArray.stream().mapToInt(integer -> integer).sum();
    }

    private String cleanNumbersString(String numbers) {
        if (!numbers.contains("\n")) {
            return numbers;
        }
        return numbers.replace("\n", ",");
    }

    private List<Integer> toIntList(String[] numbers) {
        List<Integer> numbersList = new ArrayList<>();
        for (String number : numbers) {
            numbersList.add(Integer.parseInt(number));
        }
        return numbersList;
    }
}
