package Collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapInterfaceDemo {
	public static void main(String[] args) {
		// HashMap Demonstration
		System.out.println("=== HashMap Demonstration ===");
		Map<String, Integer> hashMap = new HashMap<>();
		// 1. Basic Operations
		// Adding elements
		hashMap.put("Apple", 1);
		hashMap.put("Banana", 2);
		hashMap.put("Orange", 3);
		System.out.println("Initial HashMap: " + hashMap);
		// Updating value
		hashMap.put("Apple", 5); // Updates existing value
		System.out.println("After updating Apple's value: " + hashMap);
		// putIfAbsent
		hashMap.putIfAbsent("Apple", 10); // Won't update as key exists
		hashMap.putIfAbsent("Grape", 4); // Will add new entry
		System.out.println("After putIfAbsent operations: " + hashMap);
		// 2. Accessing Elements
		System.out.println("\nAccessing Elements:");
		System.out.println("Value for Apple: " + hashMap.get("Apple"));
		System.out.println("Value for missing key: " + hashMap.get("Mango"));
		System.out.println("Value for missing key with default: " + hashMap.getOrDefault("Mango", 0));

		// 3. Removing Elements
		hashMap.remove("Banana");
		System.out.println("After removing Banana: " + hashMap);
		// Conditional remove
		hashMap.remove("Apple", 5); // Removes only if value matches
		System.out.println("After conditional remove: " + hashMap);
		// 4. TreeMap Demonstration (Sorted Map)
		System.out.println("\n=== TreeMap Demonstration ===");

		TreeMap<String, Integer> scores = new TreeMap<>();
		// 5 Adding elements (put operation)
		scores.put("Alice", 95);
		scores.put("Bob", 82);
		scores.put("Charlie", 90);
		scores.put("David", 78);
		scores.put("Eva", 88);
		// Display the TreeMap (naturally sorted by keys)
		System.out.println("TreeMap contents: " + scores);
		// 6 Accessing elements (get operation)
		System.out.println("Charlie's score: " + scores.get("Charlie"));
		// 7. removing elements
		scores.remove("David");
		System.out.println("After removing David: " + scores);
		// 8 Navigation operations (TreeMap-specific)
		// First (lowest) and last (highest) entries
		System.out.println("First entry: " + scores.firstEntry());
		System.out.println("Last entry: " + scores.lastEntry());
		// 8 naturally ordered by keys
		Map<String, Integer> treeMap = new TreeMap<>();
		treeMap.put("Zebra", 1);
		treeMap.put("Lion", 2);
		treeMap.put("Elephant", 3);
		System.out.println("TreeMap (naturally ordered by keys): " + treeMap);
		// 9. eldest entry if size exceeds 3
		System.out.println("\n=== LinkedHashMap Demonstration ===");
		Map<String, Integer> linkedHashMap = new LinkedHashMap<>() {
		@Override

		protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
		return size() > 3; // Remove eldest entry if size exceeds 3
		}
		};
		linkedHashMap.put("A", 1);
		linkedHashMap.put("B", 2);
		linkedHashMap.put("C", 3);
		System.out.println("Initial LinkedHashMap: " + linkedHashMap);
		linkedHashMap.put("D", 4); // Will remove eldest entry
		System.out.println("After adding D (notice removal of eldest): " + linkedHashMap);
		// 10. Iterating Over Map
		System.out.println("\n=== Map Iteration ===");
		// Iterating over entries
		System.out.println("Iterating over entries:");
		for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
		System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
		// Iterating over keys
		System.out.println("\nIterating over keys:");
		for (String key : hashMap.keySet()) {
		System.out.println("Key: " + key);
		}
		// Iterating over values
		System.out.println("\nIterating over values:");
		for (Integer value : hashMap.values()) {
		System.out.println("Value: " + value);
		}
		// 11. Map Operations
		System.out.println("\n=== Map Operations ===");
		Map<String, Integer> map1 = new HashMap<>();
		map1.put("A", 1);
		map1.put("B", 2);
		// Merging maps
		Map<String, Integer> map2 = new HashMap<>();
		map2.put("B", 3);
		map2.put("C", 4);
		// Merge with custom remapping function
		map2.forEach((key, value) ->
		map1.merge(key, value, (v1, v2) -> v1 + v2));
		System.out.println("After merging maps: " + map1);

		// 12. Compute Operations
		System.out.println("\n=== Compute Operations ===");
		map1.compute("A", (k, v) -> (v == null) ? 1 : v * 2);
		System.out.println("After computing A: " + map1);
		map1.computeIfPresent("B", (k, v) -> v * 3);
		System.out.println("After computeIfPresent B: " + map1);
		map1.computeIfAbsent("D", k -> 10);
		System.out.println("After computeIfAbsent D: " + map1);
		// 13. Bulk Operations
		System.out.println("\n=== Bulk Operations ===");
		Map<String, Integer> newMap = new HashMap<>();
		newMap.putAll(map1);
		System.out.println("After putAll: " + newMap);
		// Clear the map
		newMap.clear();
		System.out.println("After clearing: " + newMap);
		// 14. Checking Operations
		System.out.println("\n=== Checking Operations ===");
		System.out.println("Is map empty? " + newMap.isEmpty());
		System.out.println("Map size: " + map1.size());
		System.out.println("Contains key 'A'? " + map1.containsKey("A"));
		System.out.println("Contains value 1? " + map1.containsValue(1));
		}

}
