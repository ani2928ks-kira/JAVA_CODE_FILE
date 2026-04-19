package lab2;

import java.util.LinkedHashMap;
import java.util.Map;

public class p12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] keys = {"C", "A", "B", "E", "D"};
		Integer[] values = {3, 1, 2, 5, 4};
		Map<String,Integer> lMap = new LinkedHashMap<>();
		for(int i=0;i<keys.length;i++) {
			lMap.put(keys[i], values[i]);
		}
		System.out.println(lMap);

	}

}