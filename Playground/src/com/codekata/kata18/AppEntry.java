package com.codekata.kata18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AppEntry {

	private static Map<Character, Set<Character>> dependencyTable = new HashMap<>();
	
	private static void printMap(Map<Character, Set<Character>> map) {
		
		for(var entry : dependencyTable.entrySet()) {
			
			String template = "Key: %s, Value: %s";
			System.out.println(String.format(template, entry.getKey(), entry.getValue()));
		}
	}
	
	private static  Set<Character> getDependencies(Set<Character> dependencies, Set<Character> result) {
		
		
		
		for(char c : dependencies) {
			var set = dependencyTable.get(c);
			
			
			if(set == null || result.containsAll(set)) {
				return result;
			}

			result.addAll(set);
			getDependencies(set, result);
		}
		
		
		
		
	
		return result;
	}
	
	
	private static void printSet(Set<Character> set) {
		
		String message = "";
		
		for(var item :  set) {
			
			message += item + ",";
		}
		
		message = message.substring(0, message.lastIndexOf(","));
		System.out.print(message + "\n");
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Transitive t = new Transitive();
		
		t.add_direct('A', 'B');
		t.add_direct('B', 'C', 'H');
		t.add_direct('H', 'E');
		
		var result1 = t.dependencies_for('A');
		var result2 = t.dependencies_for('B');
		var result3 = t.dependencies_for('H');
		
		printSet(result1);
		printSet(result2);
		printSet(result3);
		
	}

}
