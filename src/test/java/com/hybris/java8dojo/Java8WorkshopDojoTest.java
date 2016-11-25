package com.hybris.java8dojo;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

/**
 * Material for Java8 Dojo, Workshop @ https://github.com/KenLomax/java8dojo20161125
 * 
 * Goal:  Get us working with Streams
 * Agenda: 
 * Work as Dojo through this file 
 * Looks at example at https://github.com/xebia/java8-hands-on-jfall-2014/blob/master/JFall_2014_Java8_Workshop.pdf
 * Work on labs from Urs Peter
 * 
 * Cheat Sheet: https://wiki.hybris.com/display/prodandtech/Java+8+Streams+Cheatsheet
 * Labs: https://wiki.hybris.com/display/prodandtech/Java8+Workout+-+Dojo
 * More on our wiki: https://wiki.hybris.com/display/prodandtech/Java+8+Dojo+-+A+major+first+step+to+getting+fluent+and+certified+in+Java8
 * Java8's docu: https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
 */

public class Java8WorkshopDojoTest {
	Consumer<String> print = s -> System.out.println(s);
	
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
		
		Stream.of( 1,2,3,4,5,6,7,8,9,10 ).
		filter( n->n%2 ==0 ).	
			forEach( s -> System.out.println(s) );
		
		System.out.println(Stream.of( "Bob", "The", "Builder")
				.filter( ( String s3) -> s3.startsWith("B")).
				collect( Collectors.joining( ", ")));
		System.out.println(Stream.of( "Bob", "The", "Builder")
				.filter( 
						new Predicate<String>(){
							@Override
							public boolean test(String t) {
								return t.startsWith("B");
							}}				
						).
				collect( Collectors.joining( ", ")));
			
		
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
		Consumer<String> c3 = new Consumer<String>(){
			@Override
			public void accept(String t) {
				System.out.println("Hi");			
			}};
		Consumer<String> c4 = (t) -> System.out.println("Hi");

		Supplier<String> s = new Supplier<String>(){
			@Override
			public String get() {
				return "Hello";
			}};
		Supplier<String> s1 = () 
				//-> "Hello";
				-> {return "Hello"; };
				
		Predicate<String> p = new Predicate<String>(){
			@Override
			public boolean test(String t) {
				return t.startsWith("B");
			}};
			
		Predicate<String> p2 = ( String s3) -> s3.startsWith("B");
	
		Function<String, String>f = new Function<String, String>(){
			@Override
			public String apply(String t) {
				// Business logic
				return "Hi";
			}};
			
		Function<String, String>f2 = ( String s5 ) -> { return s5.concat("Hi"); };
	}

	/**
	 * How to get Streams
	 * Arrays asList, stream	
	 * Stream of, generate, iterate
	 * Files lines
	 * IntStream range, iterate
	 * @throws IOException 
	 */
	@Test
	public void gettingAStream() throws IOException{	
		Stream.of("Bob","Builder").forEach( print );
		//Stream.iterate( 0, n -> n+1  ).forEach( n-> System.out.println(n));
		//Stream.generate( () -> "HelloThere").forEach( print );	
		//Stream.generate( () -> "HelloThere").forEach( System.out::println );		
		Files.lines( 
			Paths.get("/Users/d061192/Documents/20161125/java8dojo20161125/src/test/java/com/hybris/java8dojo/Java8WorkshopDojoTest.java")).
			filter(s -> s.contains("import")).
			forEach( System.out::println );	
	
		Arrays.asList(1,2,3,4,5).stream();
		IntStream.of(1,2,3,4,5).summaryStatistics();
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
		
		Stream.iterate( 1, f -> f+1 )
			.filter( n-> n%2==0)
			.limit(100).
			forEach(System.out::println) ;	
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
		
		Stream.of( p1, p2, p3 ).map( p-> p.getName()).forEach(print);		
		Stream.of( p1, p2, p3 ).map( Person::getName ).forEach(print);		
		Stream.of( p1, p2, p3 ).filter( p->!p.isMale() ).map( Person::getName ).forEach(print);	
		
		Stream.of( p1, p2, p3 ).map( p-> p.getHobbies()).forEach(s->System.out.println(s));		
		Stream.of( p1, p2, p3 ).flatMap( p-> p.getHobbies().stream()).distinct().forEach(s->System.out.println(s));			
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
		
		Predicate<String> startsWithB = s -> s.startsWith("B");
		Predicate<String> containsE = s -> s.contains("e");
		
		Predicate<String> complexPredicate = startsWithB.and( containsE );
		Stream.of("Bob", "the", "Builder", "Bakes" ,"Cakes").filter(complexPredicate).forEach(print);		
	}
	
	/**
	 * parallel
	 */
	@Test
	public void workingWithParallelStreams(){
		Person p1 = new Person("Bob", 21, true, "Sailing","Football");
		Person p2 = new Person("Fred", 22, true, "Sailing","Handball");
		Person p3 = new Person("mary", 23, false, "RockClimbing","Canoeing");
		Person p4 = new Person("Bob", 21, true, "Sailing","Football");
		Person p5 = new Person("Fred", 22, true, "Sailing","Handball");
		Person p6 = new Person("mary", 23, false, "RockClimbing","Canoeing");
		Person p7 = new Person("Bob", 21, true, "Sailing","Football");
		Person p8 = new Person("Fred", 22, true, "Sailing","Handball");
		Person p9 = new Person("mary", 23, false, "RockClimbing","Canoeing");
	
		Long l = System.currentTimeMillis();
		System.out.println(	
				Stream.of( p1, p2, p3, p4, p5, p6, p7, p8, p9 ).
				parallel().
				map( p -> p.findReactionTimeMS() ).
				reduce( (a,b) -> a+b));
		System.out.println("Time taken: " + (System.currentTimeMillis() - l));
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
		 long reactionTime = randomGenerator.nextInt(1000);
		 try {
			Thread.sleep(reactionTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		return reactionTime;
	}

}

