package com.jboehm.string.calculator;

import java.util.ArrayList;
import java.util.List;

public class IntArrays {
    static List<Integer> toList(String numbersString, String delimiter) {
        String[] split = numbersString.split(delimiter);
        return toIntList(split);
    }

    private static List<Integer> toIntList(String[] numbers) {
        List<Integer> numbersList = new ArrayList<>();
        for (String number : numbers) {
            numbersList.add(Integer.parseInt(number));
        }
        return numbersList;
    }
}
