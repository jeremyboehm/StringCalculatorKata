package com.jboehm.string.calculator;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public int add(String numbers) {

        if (numbers.isEmpty()) {
            return 0;
        }

        String[] numbersSplit = numbers.split(",");
        List<Integer> numbersIntArray = toIntArray(numbersSplit);

        return numbersIntArray.stream().mapToInt(integer -> integer).sum();
    }

    private List<Integer> toIntArray(String[] numbers) {
        List<Integer> numbersList = new ArrayList<>();
        for (String number : numbers) {
            numbersList.add(Integer.parseInt(number));
        }
        return numbersList;
    }
}
