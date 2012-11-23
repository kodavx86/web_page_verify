/*
 * This is a simple generics class that impletments a pair container
 * object. The primary purpose of this class is to allow the tag stack
 * to keep records of open tags and their line numbers, but as a generics
 * class this class can be used to pair any two objects in one container.
 *
 * Author: Tyler Vodak
 * Date: 6/3/2012
 */
 
 public class StackPair<T,V> {
 
 	//These are the object attributes
 	private T firstItem;
 	private V secondItem;
 	
 	//The class constructor
 	public StackPair(T first, V second) {
 		this.firstItem = first;
 		this.secondItem = second;
 	}
 	
 	//These are your getter and setter methods
 	public T getFirstItem() {
 		return firstItem;
 	}
 	
 	public V getSecondItem() {
 		return secondItem;
 	}
 	
 	public void setFirstItem(T n_first) {
 		this.firstItem = n_first;
 	}
 	
 	public void setSecondItem(V n_second) {
 		this.secondItem = n_second;
 	}
 	
 	//This is just a toString method
 	public String toString() {
 		return "First: " + this.firstItem + ", Second: " + this.secondItem;
 	}
 }