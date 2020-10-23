package com.codekata.kata06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppEntry {

	final static String wordList = "C:\\Entwicklung\\Java\\Data\\wordlist.txt";
	
	private static String reverse(String value) {
		
		String reversed = "";
		
		for(int i = value.length() - 1; i >= 0; i--) {
			reversed += value.charAt(i);
		}
	
		return reversed;
	}
	
	private static String sort(String value) {
		
		char tempArray[] = value.toLowerCase().toCharArray();
		Arrays.sort(tempArray);
		
		return new String(tempArray);
	}
	
	
	public static int getMaxAnagramList(Map<String, List<String>> dictionary) {
		int max = 0;
		
		for(var value : dictionary.values()) {
			var currentMax = value.size();
			if( currentMax > max) {
				max = currentMax;
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Map<String, List<String>> dictionary = new HashMap<>();
		
		BufferedReader reader = new BufferedReader(new FileReader(wordList));		

		String line = reader.readLine();	
		
		
		while(line != null) {
			
			String sorted = sort(line);
			if(dictionary.containsKey(sorted)) {
				dictionary.get(sorted).add(line);
			} else {
				dictionary.put(sorted, new ArrayList<>());
			}
			
			line = reader.readLine();
		}
		
		reader.close();
		
		int max = getMaxAnagramList(dictionary);		
		System.out.println(max);
	}

}
