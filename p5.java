package lab2;

import java.util.HashMap;
import java.util.*;
import java.util.Map.Entry;

public class p5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map< Integer,String> treeMap = new TreeMap<>();
		treeMap.put(1,"Apple");
		treeMap.put(2,"Strawberry");
		treeMap.put(3,"Pear");
		treeMap.put(4,"Cucumber");
		treeMap.put(5,"Grapes");
	
		System.out.println("Iterating over entries:");
		for (Entry<Integer, String> entry : treeMap.entrySet()) {
		System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
	}

}