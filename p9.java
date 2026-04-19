package lab2;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.*;

public class p9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("\n=== LinkedHashMap Demonstration ===");
        Map<Integer,String> Map = new LinkedHashMap<>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer,String> eldest) {
                return size() > 4; // Remove eldest entry if size exceeds 4
            }
        };
		Map.put(1,"Apple");
		Map.put(2,"Strawberry");
		Map.put(3,"Pear");
		Map.put(4,"Cucumber");
		Map.put(5,"Grapes");
		System.out.println("Iterating over entries:");
		for (Entry<Integer, String> entry : Map.entrySet()) {
		System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
		System.out.println("Elderly entries(To display only 4 elements):");
		System.out.println("After adding 5 (notice removal of eldest): " + Map);	
		}
	}

