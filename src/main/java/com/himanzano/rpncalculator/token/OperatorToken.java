package com.himanzano.rpncalculator.token;

import com.himanzano.rpncalculator.operator.Operator;

public record OperatorToken(Operator operator) implements Token {
}
