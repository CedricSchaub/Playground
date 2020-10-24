package com.codekata.kata21;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedStringList implements StringList {
	/**
	 * 
	 * Head of the list
	 * 
	 * */
	private ListNode headNode = null;
	
	/**
	 * 
	 * For debugging purposes
	 * 
	 * */
	public ListNode getStartNode() { return this.headNode; }
	
	
	public void add(String value) {
		
		if(headNode == null) {		
			headNode = new ListNode(value, null);
			
		} else {
			
			ListNode nextNode = headNode;
			
			while(nextNode.nextNode != null) {
				nextNode = nextNode.nextNode;
			}
			
			nextNode.nextNode = new ListNode(value, null);
			
		}
	}
	
	/**
	 * 
	 * There are four possible cases
	 * 
	 * 1) listNode == null -> return false
	 * 2) listNode is head of list -> set new head to the next element of previous head element, return true
	 * 3) listNode is between head and tail -> traverse through nodes and look for the element, return true if found
	 * 4) listNode not found -> return false
	 * 
	 * */
	public boolean delete(ListNode listNode) {
		
		
		ListNode currentNode = headNode;
		ListNode prevNode = null;
		
		
		if(currentNode != null && currentNode.value.equals(listNode.value)) {
			
			headNode = currentNode.nextNode;	
			return true;
		} 
		
		
		while(currentNode != null) {
			
			// Prev -> Current -> Tail
			if(currentNode.value.equals(listNode.value)) {
				
				prevNode.nextNode = currentNode.nextNode;
				currentNode = null;
				
				return true;
			}
			
			prevNode = currentNode;
			currentNode = currentNode.nextNode;
		}
		
		
		return false;
	}
	
	/**
	 * 
	 * Traverse through list until element is found. Otherwise return null
	 * 
	 * */
	public ListNode find(String value) { 
		
		ListNode node = headNode;
		
		while(node != null) {
			
			if(node.value.equals(value)) {
				return node;
			}
			
			node = node.nextNode;
		}
		
		return null;
	}
	
	public String[] values() {
		
		List<String> data = new ArrayList<>();
		ListNode node = headNode;
		
		while(node != null) {
			
			data.add(node.value);
			node = node.nextNode;
		}
			
		String[] dataInArray = new String[data.size()];
		
		data.toArray(dataInArray);
		
		return dataInArray;
	}
	

}
