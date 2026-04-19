package lab2;

import java.util.HashMap;
import java.util.*;

public class p7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map< Integer,String> Map = new TreeMap<>();
		// 1. Basic Operations
		// Adding elements
		Map.put(1,"Apple");
		Map.put(2,"Strawberry");
		Map.put(3,"Pear");
		Map.put(4,"Cucumber");
		Map.put(5,"Grapes");
		Map.clear();
		System.out.println("After removing All elements size : "+Map.size());
		Map.put(1,"Apple");
		Map.put(2,"Strawberry");
		Map.put(3,"Pear");
		Map.put(4,"Cucumber");
		Map.put(5,"Grapes");
		System.out.println("After Adding All elements size : "+Map.size());

	}

}