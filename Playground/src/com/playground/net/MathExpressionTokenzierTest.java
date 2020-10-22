package com.playground.net;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MathExpressionTokenzierTest {

	static MathExpressionTokenizer tokenizer;
	
	@BeforeAll
	static void init() {
		tokenizer = new MathExpressionTokenizer("");
	}
	
	@Test
	void match_all_symbols() {
		assertAll("All Operators",
					() -> assertEquals(true, tokenizer.isOperator('+')),
					() -> assertEquals(true, tokenizer.isOperator('-')),
					() -> assertEquals(true, tokenizer.isOperator('/')),
					() -> assertEquals(true, tokenizer.isOperator('*')),
					() -> assertEquals(true, tokenizer.isComma(',')),
					() -> assertEquals(true, tokenizer.isDecimalPoint('.')),
					() -> assertEquals(true, tokenizer.isLeftParenthesis('(')),
					() -> assertEquals(true, tokenizer.isRightParenthesis(')'))
				);
	}
	
	@Test
	void match_all_literals() {
		assertAll("All literals",
					() -> assertEquals(true, tokenizer.isLiteral('0')),
					() -> assertEquals(true, tokenizer.isLiteral('1')),
					() -> assertEquals(true, tokenizer.isLiteral('2')),
					() -> assertEquals(true, tokenizer.isLiteral('3')),
					() -> assertEquals(true, tokenizer.isLiteral('4')),
					() -> assertEquals(true, tokenizer.isLiteral('5')),
					() -> assertEquals(true, tokenizer.isLiteral('6')),
					() -> assertEquals(true, tokenizer.isLiteral('7')),
					() -> assertEquals(true, tokenizer.isLiteral('8')),
					() -> assertEquals(true, tokenizer.isLiteral('9'))
				);
	}
	
	@Test
	void match_all_letters_case_insensitive() {
		assertAll("All literals",
				() -> assertEquals(true, tokenizer.isLetter('a')),
				() -> assertEquals(true, tokenizer.isLetter('B')),
				() -> assertEquals(true, tokenizer.isLetter('c')),
				() -> assertEquals(true, tokenizer.isLetter('D')),
				() -> assertEquals(true, tokenizer.isLetter('e')),
				() -> assertEquals(true, tokenizer.isLetter('F')),
				() -> assertEquals(true, tokenizer.isLetter('g')),
				() -> assertEquals(true, tokenizer.isLetter('H')),
				() -> assertEquals(true, tokenizer.isLetter('i')),
				() -> assertEquals(true, tokenizer.isLetter('J')),
				() -> assertEquals(true, tokenizer.isLetter('k')),
				() -> assertEquals(true, tokenizer.isLetter('L')),
				() -> assertEquals(true, tokenizer.isLetter('m')),
				() -> assertEquals(true, tokenizer.isLetter('N')),
				() -> assertEquals(true, tokenizer.isLetter('o')),
				() -> assertEquals(true, tokenizer.isLetter('P')),
				() -> assertEquals(true, tokenizer.isLetter('q')),
				() -> assertEquals(true, tokenizer.isLetter('R')),
				() -> assertEquals(true, tokenizer.isLetter('s')),
				() -> assertEquals(true, tokenizer.isLetter('T')),
				() -> assertEquals(true, tokenizer.isLetter('u')),
				() -> assertEquals(true, tokenizer.isLetter('V')),
				() -> assertEquals(true, tokenizer.isLetter('w')),
				() -> assertEquals(true, tokenizer.isLetter('X')),
				() -> assertEquals(true, tokenizer.isLetter('y')),
				() -> assertEquals(true, tokenizer.isLetter('Z'))				
			);
	}
}
