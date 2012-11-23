/**
 * This is the Junit test file for the StackPair.java file. All tests
 * on the StackPair class code should be placed in here.
 *
 * Author: Tyler Vodak
 * Date: 06/03/2012
 */
 
 import java.io.*;
 import org.junit.*;
 import java.util.*;
 
 public class StackPairtest {
 
 	/* These are just some static class variables */
 	private static final String TEST_DATA_DIR = "./tdata/";
 
 	//This is a wrapper function that can help
 	//determine which test case actually failed
 	private static void assertWrap(int expected, int result) {
 		try {
 			Assert.assertTrue(expected == result);
 		}
 		catch (AssertionError err) {
 			System.out.println("Error - Tags test failed!");
 			System.out.println("Expected: " + expected + ", Result: " + result);
 			err.printStackTrace();
 			System.exit(201);
 		}
 	}
 	
 	private static void assertWrap(boolean expected, boolean result) {
 		try {
 			Assert.assertTrue(expected == result);
 		}
 		catch (AssertionError err) {
 			System.out.println("Error - Tags test failed!");
 			System.out.println("Expected: " + expected + ", Result: " + result);
 			err.printStackTrace();
 			System.exit(201);
 		}
 	}
 	
 	//Check that a StackPair object can be created
 	@Test
 	public void test_StackPair() {
 		StackPair<String,Integer> n_pair = new StackPair<String,Integer>("arg1",new Integer(2));
 	}
 	
 	//Check all of the getter and setter methods
 	@Test
 	public void test_getFirstItem() {
 		StackPair<String,Integer> n_pair = new StackPair<String,Integer>("arg1",new Integer(2));
 		String res = n_pair.getFirstItem();
 		assertWrap(res.equals("arg1"),true);
 	}
 	
 	@Test
 	public void test_getSecondItem() {
 		StackPair<String,Integer> n_pair = new StackPair<String,Integer>("arg1",new Integer(2));
 		Integer res = n_pair.getSecondItem();
 		assertWrap(res.intValue() == 2,true);
 	}
 	
 	@Test
 	public void test_setFirstItem() {
 		StackPair<String,Integer> n_pair = new StackPair<String,Integer>("arg1",new Integer(2));
 		n_pair.setFirstItem("doggy");
 		String res = n_pair.getFirstItem();
 		assertWrap(res.equals("doggy"),true);
 	}
 	
 	@Test
 	public void test_setSecondItem() {
 		StackPair<String,Integer> n_pair = new StackPair<String,Integer>("arg1",new Integer(2));
 		n_pair.setSecondItem(new Integer(137));
 		Integer res = n_pair.getSecondItem();
 		assertWrap(res.intValue() == 137,true);
 	}
 	
 	//Test the toString method
 	@Test
 	public void test_toString() {
 		StackPair<String,Integer> n_pair = new StackPair<String,Integer>("arg1",new Integer(2));
 		boolean res = n_pair.toString().equals("First: arg1, Second: 2");
 		assertWrap(res,true);
 	}
 }