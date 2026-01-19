package com.himanzano.rpncalculator.tokenizer;

import com.himanzano.rpncalculator.operator.Operator;
import com.himanzano.rpncalculator.token.*;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Token lastToken = null;

        char[] chars = input.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c) && c != '.' && !sb.isEmpty()) {
                String str = sb.toString();
                double number = Double.parseDouble(str);
                NumberToken token = new NumberToken(number);
                tokens.add(token);
                lastToken = token;
                sb.setLength(0);
            }

            if (c == '(') {
                OpeningParenthesisToken token = new OpeningParenthesisToken();
                tokens.add(token);
                lastToken = token;
                continue;
            }

            if (c == ')') {
                ClosingParenthesisToken token = new ClosingParenthesisToken();
                tokens.add(token);
                lastToken = token;
                continue;
            }

            if (c == '-') {
                OperatorToken token;
                if (lastToken == null
                        || lastToken instanceof OperatorToken
                        || lastToken instanceof OpeningParenthesisToken) {
                    token = new OperatorToken(Operator.NEGATIVE);
                } else {
                    token = new OperatorToken(Operator.SUBTRACTION);
                }

                tokens.add(token);
                lastToken = token;
                continue;
            }

            if (c == '+' || c == '*' || c == '/') {
                Operator op = Operator.mapOperator(c);
                OperatorToken token = new OperatorToken(op);
                tokens.add(token);
                lastToken = token;
                continue;
            }

            if (Character.isDigit(c) || c == '.') {
                sb.append(c);
                continue;
            }

            if (Character.isWhitespace(c)) {
                continue;
            }

            throw new IllegalArgumentException("Illegal character " + c);
        }

        if (!sb.isEmpty()) {
            String str = sb.toString();
            double number = Double.parseDouble(str);
            NumberToken token = new NumberToken(number);
            tokens.add(token);
        }

        return tokens;
    }
}
