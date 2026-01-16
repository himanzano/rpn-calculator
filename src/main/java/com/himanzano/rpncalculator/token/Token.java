package com.himanzano.rpncalculator.token;

public sealed interface Token
        permits ClosingParenthesisToken, NumberToken, OperatorToken, OpeningParenthesisToken {
}
