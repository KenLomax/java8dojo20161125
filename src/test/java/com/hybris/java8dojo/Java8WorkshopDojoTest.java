package com.hybris.java8dojo;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Material for Java8 Dojo, Workshop @ https://github.com/KenLomax/java8dojo20161125
 * 
 * Goal:  Get us working with Streams
 * Agenda: Work as Dojo through this file and then the labs from Urs Peter
 * 
 * Cheat Sheet: https://wiki.hybris.com/display/prodandtech/Java+8+Streams+Cheatsheet
 * Labs: https://wiki.hybris.com/display/prodandtech/Java8+Workout+-+Dojo
 * More on our wiki: https://wiki.hybris.com/display/prodandtech/Java+8+Dojo+-+A+major+first+step+to+getting+fluent+and+certified+in+Java8
 * Java8's docu: https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
 */

public class Java8WorkshopDojoTest {

	/**
	 * Example
	 * >=0 Lazy non terminal operations
	 * 1 Terminal operation
	 * No moving parts
	 * No mutable parts
	 * Works with predicates, consumers, functions, suppliers: functional interfaces
	 * Works with lambda notation
	 */
	@Test
	public void streamsAreCool(){	
		assertTrue(false);
	}
	
	/**
	 * Four types of functional interfaces: Consumers, Suppliers, Predicates, Functions
	 * You need to know 
	 *   what they are, 
	 *   how to write them as Lambdas
	 * Dojo fashion repeat this example
	 */
	@Test
	public void typesOfFunctionalInterfacesUsed(){
	}

	
	/**
	 * How to get Streams
	 * Arrays asList, stream	
	 * Stream of, generate
	 * Files lines
	 * IntStream range, iterate
	 */
	@Test
	public void gettingAStream(){
	}
	
	/**
	 * toArray()
	 * Collectors toList, groupingBy, join
	 * logic allMatch, anyMatch, noneMatch, count
	 * reduce
	 * optional: findAny, findFirst
	 * intstream: summaryStatistics, min, max,..
	 */
	@Test
	public void closingAStream(){
	}
	
	/**
	 * filter
	 * map
	 * flatmap
	 */
	@Test
	public void workingWithStreamsLazyIntermediateOperations(){
		Person p1 = new Person("Bob", 21, true, "Sailing","Football");
		Person p2 = new Person("Fred", 22, true, "Sailing","Handball");
		Person p3 = new Person("mary", 23, false, "RockClimbing","Canoeing");
	}
	
	/**
	 * Separating out the business logic - stand-alond predicates..
	 */
	@Test
	public void methodReference(){
		
	}

	/**
	 * Separating out the business logic - stand-alond predicates..
	 */
	@Test
	public void separatingOutBusinessLogicFurther(){
		
	}
	/**
	 * parallel
	 */
	@Test
	public void workingWithParallelStreams(){
		
	}


}



class Person {

	private final String name;
	private final int age;
	private final boolean male;
	private final List<String> hobbies;
	private Random randomGenerator = new Random();

	public Person(String name, int age, boolean isMale, String... hobby) {
		this.age = age;
		this.name = name;
		this.male = isMale;
		this.hobbies = Arrays.asList(hobby);
	}

	public int getAge() {
		return age;
	}

	public boolean isMale() {
		return male;
	}

	public String getName() {
		return name;
	}

	public boolean isAdult() {
		return age >= 18;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public String toString() {
		return String.format("Person[name=%s, age=%s, hobbies=%s]", name, age, hobbies);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((hobbies == null) ? 0 : hobbies.hashCode());
		result = prime * result + (male ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (hobbies == null) {
			if (other.hobbies != null)
				return false;
		} else if (!hobbies.equals(other.hobbies))
			return false;
		if (male != other.male)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public long findReactionTimeMS(){
		 long reactionTime = randomGenerator.nextInt(5000);
		 try {
			Thread.sleep(reactionTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		return reactionTime;
	}

}

