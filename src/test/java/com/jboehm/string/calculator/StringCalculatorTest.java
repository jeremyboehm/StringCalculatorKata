package com.jboehm.string.calculator;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class StringCalculatorTest {

    @Test
    public void should_add_numbers() throws NotValidNumberString, NegativeNotAllowed {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("1,2,3,4");
        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    public void should_return_0_for_an_empty_string() throws NotValidNumberString, NegativeNotAllowed {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("");
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void should_add_numbers_with_new_lines() throws NotValidNumberString, NegativeNotAllowed {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("1\n2,3");
        Assertions.assertThat(result).isEqualTo(6);
    }

    @Test(expected = NotValidNumberString.class)
    public void should_not_validate_string() throws NotValidNumberString, NegativeNotAllowed {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("1,\n");
    }

    @Test()
    public void should_add_numbers_with_specified_delimiter() throws NotValidNumberString, NegativeNotAllowed {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("//;\n1;2;3");
        Assertions.assertThat(result).isEqualTo(6);
    }

    @Test(expected = NotValidNumberString.class)
    public void should_not_validate_string_with_specified_limiter() throws NotValidNumberString, NegativeNotAllowed {
        StringCalculator stringCalculator = new StringCalculator();
        stringCalculator.add("//;\n1\n;2;3");
    }

    @Test(expected = NegativeNotAllowed.class)
    public void should_not_validate_string_with_negative_number() throws NegativeNotAllowed, NotValidNumberString {
        StringCalculator stringCalculator = new StringCalculator();
        stringCalculator.add("//;\n1;-2;-3");
    }

    @Test(expected = NotValidNumberString.class)
    public void should_not_validate_string_because_of_integrity_instead_of_negative() throws NotValidNumberString, NegativeNotAllowed {
        StringCalculator stringCalculator = new StringCalculator();
        stringCalculator.add("//;\n-1\n;2;3");
    }
}