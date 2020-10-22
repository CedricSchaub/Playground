package com.playground.net;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class MathExpressionTokenizer {
	
	private final String expression;
	private List<String> tokens = new ArrayList<>();
	
	private List<Character> literalBuffer = new ArrayList<>();
	private List<Character> letterBuffer = new ArrayList<>();
	private Queue<String> output = new ArrayDeque<>();
	
	/**
	 * Syntax Rules
	 * 
	 * 1] Literals have the following Syntax: Number [ Decimal Point Number ] => 5, 5.0, 6.5 
	 * 2] Allowed Operators: + | - | / | * | ^
	 * 3] Functions have the following Syntax: FunctionName LP Argument1 [, Argument2] RP => sin(1.2)
	 * 
	 * */
	public MathExpressionTokenizer(String expression) {
		this.expression = expression.replace(" ", "");
		parseExpression();
	}
	
	// (?i) regex flag for case insensitive match
	// TODO: Better way to check char´s against a specific pattern ?
	public boolean isLiteral(char value) { return value >= 48 && value <= 57; }
	public boolean isLetter(char value) { return ( value >= 65 && value <= 90 ) || ( value >= 97 && value <= 122 ); } 
	public boolean isDecimalPoint(char value) { return value == 46; }
	public boolean isComma(char value) { return  value == 44; } 
	public boolean isOperator(char value) { return value == 42 || value == 43 || value == 45 || value == 47;  }
	public boolean isLeftParenthesis(char value) { return value == 40; }
	public boolean isRightParenthesis(char value) { return value == 41; }
	
	private String listToString(List<Character> list) { return list.stream().map( c -> c.toString()).collect(Collectors.joining()); }
	
	private void parseExpression() {
		// TODO: Parse Expression and add it to the tokens array
		for(int i = 0; i < expression.length(); i++) {
			char token = expression.charAt(i);
			
			if(isLiteral(token) || isDecimalPoint(token)) {
				literalBuffer.add(token);
			} else if(isOperator(token)) {
				if(!literalBuffer.isEmpty()) {
					output.add(listToString(literalBuffer));
					literalBuffer.clear();
				} else if(!letterBuffer.isEmpty()) {
					literalBuffer.stream().forEach( c -> output.add(c.toString()));
					literalBuffer.clear();
				}
				
				output.add(Character.toString(token));
			}
			
		}
		
		if(!literalBuffer.isEmpty()) {
			output.add(listToString(literalBuffer));
		}
	}
	
	public boolean isEmpty() {
		return output.isEmpty();
	}
	
	public String getNextToken() {
		return output.poll();
	}
	
}
