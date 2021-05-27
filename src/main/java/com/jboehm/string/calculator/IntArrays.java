package com.jboehm.string.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntArrays {
    static List<Integer> toList(String numbersString, String delimiter) {
        String[] split = numbersString.split(delimiter);
        return toIntList(split);
    }

    private static List<Integer> toIntList(String[] numbers) {
        return Arrays.stream(numbers).map(Integer::parseInt).collect(Collectors.toList());
    }
}
