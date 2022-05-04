/**
 *
 */
package unsw.collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of Set that uses an ArrayList to store the elements.
 *
 * @invariant All e in elements occur only once
 *
 * @author Robert Clifton-Everest
 *
 */
public class ArrayListSet<E> implements Set<E> {

    private ArrayList<E> elements;

    public ArrayListSet() {
        elements = new ArrayList<>();
    }

    @Override
    public void add(E e) {
        this.elements.add(e);
    }

    @Override
    public void remove(E e) {
        elements.remove(e);
    }

    @Override
    public boolean contains(Object e) {
        return elements.contains(e);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean subsetOf(Set<?> other) {
        
    	for(E curr : this.elements)
    		if (!other.contains(curr)) return false;
    	return true;
    }

    @Override
    public Iterator<E> iterator() {
        return this.elements.iterator();
    }

    @Override
    public Set<E> union(Set<? extends E> other) {
        Set<E> tmp = (Set<E>) other;
        for (E curr : this.elements) 
        	if (!tmp.contains(curr)) tmp.add(curr);
        return tmp;
    }

    @Override
    public Set<E> intersection(Set<? extends E> other) {
    	Set<E> tmp = (Set<E>) other;
        for (E cur : tmp)
        	if (!elements.contains(cur)) tmp.remove(cur);
        return tmp;

    }

    /**
     * For this method, it should be possible to compare all other possible sets
     * for equality with this set. For example, if an ArrayListSet<Fruit> and a
     * LinkedListSet<Fruit> both contain the same elements they are equal.
     * Similarly, if a Set<Apple> contains the same elements as a Set<Fruit>
     * they are also equal.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Set<?>)) return false;
        if (((Set<E>) other).size() != this.elements.size()) return false;
        for (E curr : this.elements) 
        	if (!((Set<E>) other).contains(curr)) return false;
        return true;
    }

}
