package com.codekata.kata21;

public class DoubleyLinkedList implements StringList {

	public ListNode headNode;
	
	@Override
	public ListNode getStartNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(String value) {
		// TODO Auto-generated method stub
		if(headNode == null) {
			headNode = new ListNode(value, null, null);
		} else {
			ListNode node = headNode;
			
			while(node.nextNode != null) {
				node = node.nextNode;
			}
			
			node.nextNode = new ListNode(value, node, null);
		}
		
	}

	@Override
	public boolean delete(ListNode listNode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ListNode find(String value) {
		// TODO Auto-generated method stub
		return new ListNode("", null, null);
	}

	@Override
	public String[] values() {
		// TODO Auto-generated method stub
		return null;
	}

}
