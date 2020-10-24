package com.codekata.kata18;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TransitiveTest {

	private Transitive t;
	
	@BeforeEach
	void init() {
		t = new Transitive();
	}
	
	private static boolean equals(Set<?> set1, Set<?> set2) {
		
		return set1.size() == set2.size() && set1.containsAll(set2);
	}
	
	@Test
	void test_cycle() {
		
		try {
			
			t.add_direct('A', 'B');
			t.add_direct('B', 'C');
			t.add_direct('C', 'A');
			
			var result1 = t.dependencies_for('A');
			var result2 = t.dependencies_for('B');
			var result3 = t.dependencies_for('C');
			
			Set<Character> expected1 = new HashSet<>();
			expected1.add('A');
			expected1.add('B');
			expected1.add('C');
			
			Set<Character> expected2 = new HashSet<>();
			expected2.add('A');
			expected2.add('B');
			expected2.add('C');
			
			Set<Character> expected3 = new HashSet<>();
			expected3.add('A');
			expected3.add('B');
			expected3.add('C');
			
			assertAll(
					() -> assertTrue(equals(expected1, result1)),
					() -> assertTrue(equals(expected2, result2)),
					() -> assertTrue(equals(expected3, result3))
			);
			
	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void code_kata_18() {
		try {
			
			t.add_direct('A', 'B', 'C');
			t.add_direct('B', 'C', 'E');
			t.add_direct('C', 'G');
			t.add_direct('D', 'A', 'F');
			t.add_direct('E', 'F');
			t.add_direct('F', 'H');
			
			var result1 = t.dependencies_for('A');
			var result2 = t.dependencies_for('B');
			var result3 = t.dependencies_for('C');
			var result4 = t.dependencies_for('D');
			var result5 = t.dependencies_for('E');
			var result6 = t.dependencies_for('F');
			
			Set<Character> expected1 = new HashSet<>();
			expected1.add('B');
			expected1.add('C');
			expected1.add('E');
			expected1.add('F');
			expected1.add('G');
			expected1.add('H');
			
			Set<Character> expected2 = new HashSet<>();
			expected2.add('C');
			expected2.add('E');
			expected2.add('F');
			expected2.add('G');
			expected2.add('H');
			
			Set<Character> expected3 = new HashSet<>();
			expected3.add('G');
			
			Set<Character> expected4 = new HashSet<>();
			expected3.add('A');
			expected3.add('B');
			expected3.add('C');
			expected3.add('E');
			expected3.add('F');
			expected3.add('G');
			expected3.add('H');
			
			Set<Character> expected5 = new HashSet<>();
			expected3.add('F');
			expected3.add('H');
			
			Set<Character> expected6 = new HashSet<>();
			expected3.add('H');
			
			assertAll(
					() -> assertTrue(equals(expected1, result1)),
					() -> assertTrue(equals(expected2, result2)),
					() -> assertTrue(equals(expected3, result3)),
					() -> assertTrue(equals(expected4, result4)),
					() -> assertTrue(equals(expected5, result5)),
					() -> assertTrue(equals(expected6, result6))
			);
			
	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
