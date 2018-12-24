package com.nn.java11;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java11 {

	 private static final List<String> myList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

	public static void main(String[] args) {
		
	    hashMapComputeIfAbsent();
	    collectorsJoining();
	    reduce();
	    printList();

	    Predicate<Integer> divisibleBy5 = div -> (div % 5 == 0);
	    printNumbersBasedOnGivenAlg(divisibleBy5);
	}

	  private static void hashMapComputeIfAbsent() {
	    
	    Map<String, Integer> nameMap = new HashMap<>();
	    nameMap.put("1", 1);
	    nameMap.put("2", 2);
	    nameMap.put("3", 3);
	    nameMap.put("4", 4);
	    nameMap.put("5", 5);

	    Integer value = nameMap.computeIfAbsent("asdfsdfsdfsdfsdfs", s -> s.length());
	    System.out.println(value);
	    
	    intStream();
	  }

	  private static void collectorsJoining() {

	    // Comma separated string
	    String result1 = myList.stream().collect(joining(", "));
	    System.out.println("collectorsJoining( ) - comma separated : " + result1);

	    // Delimiter with Pre and Post appenders
	    String result2 = myList.stream().collect(joining(" ", "PRE-", "-POST"));
	    System.out.println("collectorsJoining( ) - with PRE- and -POST : " + result2);
	  }

	  private static void reduce() {
	     Integer sum1 = myList.stream().mapToInt(Integer::valueOf).sum();
	     System.out.println("reduce() - sum1 : " + sum1);

	     Integer sum2 = myList.stream().mapToInt(Integer::valueOf).reduce(0, (partRslt, next) -> (partRslt + next));
	     System.out.println("reduce() - sum2 : " + sum2);
	  }

	  /**
	   * Print range of numbers
	   */
	  private static List<Integer> intStream() {

	    // IntStream.range(0, 100).forEach(i -> System.out.println(i));
	    // Getting error in Eclipse while executing the following code : Type mismatch: cannot convert from Collector<Object,capture#5-of ?,List<Object>> to Supplier<R>    
		// FIX: IntStream doesn't have a collect method that accepts a Collector. If you want a List<Integer>, you have to box the IntStream into a Stream<Integer>
		// To convert a stream of primitives, you must first box the elements in their wrapper class and then collect them. This type of stream in called boxed stream.
		  List<Integer> numberList = IntStream.range(0, 100).boxed().collect(Collectors.toList());
	    // List<Long> targetLongList = LongStream.of(1L, 10L).collect(Collectors.toList());
		 return numberList;
	  }

	  private static void printList() {
			var list = new ArrayList<String>();
			
			list.add("One");
			list.add("Two");
			list.add("Three");
			list.add("Four");
			list.add("Five");
			
			// list.stream().forEach(System.out::println);
			 list.stream().forEach(s -> System.out.println(s.toLowerCase()));
	  }

	  private static void printNumbersBasedOnGivenAlg(Predicate<Integer> alg) {

		  List<Integer> algAppliedList = intStream().stream().filter(alg).collect(Collectors.toList());
		  algAppliedList.forEach(i -> System.out.println(i));
	  }

}
