package com.himanzano.rpncalculator.operator;

public enum Operator {
    ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION, NEGATIVE;

    public static Operator mapOperator(char c) {
        return switch (c) {
            case '+' -> ADDITION;
            case '*' -> MULTIPLICATION;
            case '/' -> DIVISION;
            case '-' -> SUBTRACTION;
            case '~' -> NEGATIVE;
            default -> throw new IllegalArgumentException("Unknown operator: " + c);
        };
    }
}
