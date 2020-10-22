package com.codekata.kata21;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringListTest {

	private StringList list;
	private StringList emptyList;
	
	@BeforeEach
	void init() {
		list = new StringList();
		emptyList = new StringList();
		
		list.add("Skyr");
		list.add("Skyrim");
		list.add("League Of Legends");
		list.add("Assassins Creed");
		list.add("Tesla");
	}
	
	@Test
	void empty_values() {
		
		assertEquals(0, emptyList.values().length);
	}
	
	@Test
	void non_empty_values() {
			
		assertEquals(5, list.values().length);
	}
	
	@Test
	void find_existing_element() {
		
		StringList.ListNode result = list.find("Assassins Creed");
		assertEquals("Assassins Creed", result.value);
	}
	
	@Test
	void find_nonexisting_element() {

		StringList.ListNode result = list.find("Pokemon");
		assertTrue(result == null);
	}
	
	@Test
	void find_first() {

		StringList.ListNode result = list.find("Skyr");		
		assertEquals("Skyr", result.value);
	}
	
	@Test
	void find_last() {
		
		StringList.ListNode result = list.find("Tesla");
		assertEquals("Tesla", result.value);
	}
	
	@Test
	void delete_empty_node() {
		
		boolean result = emptyList.delete(null);
		
		assertAll("Test",
				() -> assertFalse(result),
				() -> assertTrue(emptyList.getStartNode() == null)
		);	
		
	}
	
	@Test
	void delete_first_node() {
		
		boolean result = list.delete(list.find("Skyr"));
		String[] data = list.values();
		String[] expected = new String[] { "Skyrim", "League Of Legends", "Assassins Creed", "Tesla" };
		
		assertAll("Delete-First-Node",
				() -> assertTrue(result),
				() -> assertEquals("Skyrim", list.getStartNode().value),
				() -> assertArrayEquals(expected, data)
		);
	}
	
	@Test
	void delete_last_node() {
		
		boolean result = list.delete(list.find("Tesla"));
		String[] data = list.values();
		String[] expected = new String[] { "Skyr", "Skyrim", "League Of Legends", "Assassins Creed" };
		
		assertAll("Delete-Last-Node",
				() -> assertTrue(result),
				() -> assertEquals("Skyr", list.getStartNode().value),
				() -> assertArrayEquals(expected, data)
		);
		
	}
	
	@Test
	void delete_middle_node() {
		boolean result = list.delete(list.find("League Of Legends"));
		String[] data = list.values();
		String[] expected = new String[] { "Skyr", "Skyrim", "Assassins Creed", "Tesla" };
		
		assertAll("Delete-Middle-Node",
				() -> assertTrue(result),
				() -> assertEquals("Skyr", list.getStartNode().value),
				() -> assertEquals("Assassins Creed", list.find("Skyrim").nextNode.value),
				() -> assertEquals("Tesla", list.find("Assassins Creed").nextNode.value),
				() -> assertArrayEquals(expected, data)
		);
	}
	
	@Test
	void delete_only_item() {
		StringList singleItem = new StringList();
		singleItem.add("Hello");
		
		boolean result = singleItem.delete(singleItem.find("Hello"));
		String[] data = singleItem.values();
		String[] expected = new String[0];
		
		assertAll("Delete-Single-Node",
				() -> assertTrue(result),
				() -> assertTrue(singleItem.getStartNode() == null),
				() -> assertArrayEquals(expected, data)
		);
	}
}
