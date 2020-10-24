package com.codekata.kata18;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Transitive {

	private Map<Character, Set<Character>> dependencyTable = new HashMap<>();
	
	private Set<Character> makeSet(char ...elements){
		var set = new HashSet<Character>();
		for(char element : elements) {
			set.add(element);
		}
		
		return set;
	} 
	
	public void print_map() {
		
		for(var entry : dependencyTable.entrySet()) {
			System.out.println("Key: " + entry.getKey() + ", Values: " + entry.getValue().toString());
		}
	}
	
	public void add_direct(char root, char ...elements) throws Exception {
	
		if(dependencyTable.containsKey(root)) {
			throw new Exception("Element " + root + " already there.");
		}
		
		var set = makeSet(elements);
		dependencyTable.put(root, set);
		
	}
	
	
	public Set<Character> dependencies_for(char root) {
		
		Deque<Character> visit = new ArrayDeque<>();
		Deque<Character> visited = new ArrayDeque<>();
		Set<Character> result = new HashSet<>();
		
		var set = dependencyTable.get(root);
		
		visit.addAll(set);
		result.addAll(set);
		
		var nextNode = visit.poll();
		
		while(nextNode != null) {
			
			var deps = dependencyTable.get(nextNode);	
			
			if(deps != null && !visited.contains(nextNode)) {		
		
		
				result.addAll(deps);
				visit.addAll(deps);
				visited.add(nextNode);
			}
			
			nextNode = visit.poll();
		}
		
		return result;
	}
	
	
}
