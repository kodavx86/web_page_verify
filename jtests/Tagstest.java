/**
 * This is the Junit test file for the Tags.java file. All tests
 * on the Tags class code should be placed in here.
 *
 * Author: Tyler Vodak
 * Date: 01/29/2012
 */
 
 import java.io.*;
 import org.junit.*;
 import java.util.*;
 
 public class Tagstest {
 
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
 	
 	//Check the validate_tags method
 	@Test
 	public void test_validate_tags() {
 		//First, we need a FileUtil object
 		FileUtil f_util = new FileUtil(TEST_DATA_DIR);
 		Tags n_tags = new Tags(f_util);
 		
 		//Now, let's check what validate_tags() returns
 		//Note: we could try passing a whole bunch of junk
 		//test cases, but given validate_tags just calls
 		//openSource on a FileUtil object and FileUtil
 		//has been tested extensively, no need for that
 		//kind of testing again
 		int res = n_tags.validate_tags();
 		assertWrap(0,res);
 	}
 	
 	//Test if we can get the result set list
 	@Test
 	public void test_getResultSet() {
 		//First, we need a FileUtil object
 		FileUtil f_util = new FileUtil(TEST_DATA_DIR);
 		Tags n_tags = new Tags(f_util);
 		n_tags.validate_tags();
 		ArrayList<ResultSet> r_set = n_tags.getResultSet();
 		assertWrap((r_set != null),true);
 	}
 }