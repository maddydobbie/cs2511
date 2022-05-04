package unsw.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayTest {

	@Test
	void testUnion() {
		Set<String> set1 = new ArrayListSet<>();
		set1.add("Hello");
	    set1.add("World");
	     
	    Set<String> set2 = new ArrayListSet<>();
		set2.add("Goodbye");
	    set2.add("World");
	     
	    Set<String> union = set1.union(set2);
	    assertTrue(union.contains("Hello"));
	    assertTrue(union.contains("World"));
	    assertTrue(union.contains("Goodbye"));
	}
	
	@Test
	void testIntersection() {
		Set<String> set1 = new ArrayListSet<>();
		set1.add("Hello");
	    set1.add("World");
	     
	    Set<String> set2 = new ArrayListSet<>();
		set2.add("Goodbye");
	    set2.add("World");
	     
	    Set<String> inter = set1.intersection(set2);
	    assertFalse(inter.contains("Hello"));
	    assertTrue(inter.contains("World"));
	    assertFalse(inter.contains("Goodbye"));
	}
	
	@Test
	void testEquals() {
		Set<String> set1 = new ArrayListSet<>();
		set1.add("Hello");
	    set1.add("World");
	     
	    Set<String> set2 = new ArrayListSet<>();
		set2.add("Hello");
	    set2.add("World");
	    
	    Set<String> set3 = new ArrayListSet<>();
		set3.add("Hello");
	    set3.add("World");
	    set3.add("Unless");	
	    
	    Set<String> set4 = new ArrayListSet<>();
		set4.add("Goodbye");
	    set4.add("World");
	    
	    assertTrue(set1.equals(set2));
	    assertFalse(set1.equals(set3));
	    assertFalse(set1.equals(set4));
	    
	    
	}

}
