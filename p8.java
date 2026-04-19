package lab2;

import java.util.*;
import java.util.TreeMap;

public class p8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeMap< String,String> map1 = new TreeMap<>();
		map1.put( "c2", "Red");
		map1.put("c1", "Green");
		map1.put("c4", "Black");
		map1.put("c3", "White");
		System.out.println(map1);
		System.out.println("The first key is: "+map1.firstKey());
		System.out.println("The Last key is: "+map1.lastKey());

	}

}