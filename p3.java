package lab2;

import java.util.HashMap;
import java.util.Map;

public class p3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map< Integer,String> map1 = new HashMap<>();
		map1.put( 1, "Red");
		map1.put(2, "Green");
		map1.put(3, "Black");
		// Merging maps
		Map<Integer,String> map2 = new HashMap<>();
		map2.put(4, "White");
		map2.put(5, "Blue");
		map2.put(6, "Orange");
		map2.forEach((key, value) ->
		map1.merge(key, value, (v1, v2) -> v1 + v2));
		System.out.println("After merging maps: " + map1);
	}

}