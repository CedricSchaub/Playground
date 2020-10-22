package com.playground.net;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PostfixNotation implements Notation {

	private Deque<String> stack = new ArrayDeque<>() ;
	private Deque<String> output = new ArrayDeque<>();
	
	private final static Map<String, Integer> OPERATOR_MAP = new HashMap<>();
	
	public PostfixNotation() {
		
		// Operator - Precedence
		OPERATOR_MAP.put("+", 1);
		OPERATOR_MAP.put("-", 1);
		OPERATOR_MAP.put("*", 10);
		OPERATOR_MAP.put("/", 10);
	}
	
	@Override
	public String getRepresentation(MathExpressionTokenizer tokenizer) {
		// TODO Auto-generated method stub
		
		
		while(!tokenizer.isEmpty()) {
			
			String token = tokenizer.getNextToken();
			
			// Operator
			if(OPERATOR_MAP.containsKey(token)) {
				// No operator on top of the stack
				if(!OPERATOR_MAP.containsKey(stack.peek()) || stack.isEmpty()) {
					stack.push(token);
				} else {
					
					int precedence = OPERATOR_MAP.get(token);
					
					while(!stack.isEmpty() && OPERATOR_MAP.containsKey(stack.peek()) && OPERATOR_MAP.get(stack.peek()) >= precedence) {
						String stackToken = stack.pop();
						output.add(stackToken);
					}
					
					stack.push(token);
				}
			} 
			// Parenthesis
			else if(token.equals("(") || token.equals(")")) {
				if(token.equals("(")) {
					
					stack.push(token);
					
				} else {
					
					String currentToken = stack.pop();
					
					while(!currentToken.equals("(")) {
						output.add(currentToken);
						currentToken = stack.pop();
					}
				}
			}
			// Operand
			else {
				output.add(token);
			}
		}
		
		while(!stack.isEmpty()) {
			output.add(stack.pop());
		}
		
		return output.stream().collect(Collectors.joining(""));
	}

}
