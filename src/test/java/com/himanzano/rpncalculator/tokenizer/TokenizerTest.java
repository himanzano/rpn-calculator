package com.himanzano.rpncalculator.tokenizer;

import com.himanzano.rpncalculator.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class TokenizerTest {
    private Tokenizer tk;

    @BeforeEach
    void setUp() {
        tk = new Tokenizer();
    }

    @ParameterizedTest
    @CsvSource({
            "3+5, 3",
            "3-5, 3",
            "3*5, 3",
            "3/5, 3",
            "3 + 5, 3",
            "3+5-8, 5",
            "(3+5)-8, 7",
            "3.25+5.75, 3",
            "-3+5, 4",
            "-(3+5), 6",
            "3+-5, 4",
            "(-3+5)-8, 8",
            "-(-3+5)-8, 9",
    })
    @DisplayName("Should return tokens")
    void shouldReturnTokens(String input, int expected) {
        List<Token> tokens = tk.tokenize(input);
        Assertions.assertEquals(expected, tokens.size());
    }

    @ParameterizedTest
    @CsvSource({
            "3:5",
            "x+y",
    })
    @DisplayName("Should throw exception with illegal parameter")
    void shouldThrowExceptionWithIllegalParameter(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> tk.tokenize(input));
    }
}
