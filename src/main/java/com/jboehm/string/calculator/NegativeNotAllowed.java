package com.jboehm.string.calculator;

public class NegativeNotAllowed extends Throwable {
    public NegativeNotAllowed(String message) {
        super(message);
    }
}
