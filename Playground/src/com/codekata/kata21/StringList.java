package com.codekata.kata21;

public interface StringList {
	public static class ListNode {
		
		public String value;
		public ListNode nextNode;
		public ListNode prevNode;
		
		public ListNode(String value, ListNode nextNode) {
			this.value = value;
			this.nextNode = nextNode;
		}
		
		public ListNode(String value, ListNode prevNode, ListNode nextNode) {
			this.value = value;
			this.nextNode = nextNode;
			this.prevNode = prevNode;
		}
	}
	
	/**
	 * @return the head of the list. Only for debugging
	 * */
	public ListNode getStartNode();
	
	/**
	 * Add a new ListNode to the end of the list
	 * @param value Value to add
	 * */
	public void add(String value);
	
	/**
	 *  @param listNode First occurens of this node should be deleted
	 *  @return true if element was found, false otherwise
	 * */
	public boolean delete(ListNode listNode);
	
	/**
	 * @param value Value to search for
	 * @return ListNode with the same value
	 * */
	public ListNode find(String value);
	
	/**
	 * @return List in array representation
	 * */
	public String[] values();
	
}
