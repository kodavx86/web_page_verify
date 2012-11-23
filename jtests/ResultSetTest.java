/**
 * This is the Junit test file for the ResultSet.java file. All tests
 * on the ResultSet class code should be placed in here.
 *
 * Author: Tyler Vodak
 * Date: 02/26/2012
 */
 
 import java.io.*;
 import org.junit.*;
 
 public class ResultSetTest {
 
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
 	
 	//Check the ResultSet constructors
 	@Test
 	public void test_ResultSet_default() {
 		ResultSet rset = new ResultSet();
 	}
 	
 	@Test
 	public void test_ResultSet_params() {
 		ResultSet rset = new ResultSet("BLARG",13,"BLARG",ResultSet.Message.NONE,27);
 	}
 	
 	//Now, let's check all getter and setter methods
 	@Test
 	public void test_Getters() {
 		ResultSet rset = new ResultSet("BLARG",13,"DRUG",ResultSet.Message.NONE,27);
 		assertWrap(rset.getFileName().equals("BLARG") ? 0 : 1,0);
 		assertWrap(rset.getlineNum(),13);
 		assertWrap(rset.getLine().equals("DRUG") ? 0 : 1, 0);
 		assertWrap(rset.getMsg() == ResultSet.Message.NONE ? 0 : 1, 0);
 		assertWrap(rset.getCursor(),27);
 	}
 	
 	@Test
 	public void test_Setters() {
 		ResultSet rset = new ResultSet("BLARG",13,"DRUG",ResultSet.Message.NONE,27);
 		rset.setFileName("YAHOO");
 		assertWrap(rset.getFileName().equals("YAHOO") ? 0 : 1,0);
 		rset.setLineNum(17);
 		assertWrap(rset.getlineNum(),17);
 		rset.setLine("BUBBY");
 		assertWrap(rset.getLine().equals("BUBBY") ? 0 : 1, 0);
 		rset.setMsg(ResultSet.Message.MISSING_OPEN_TAG);
 		assertWrap(rset.getMsg() == ResultSet.Message.MISSING_OPEN_TAG ? 0 : 1, 0);
 		rset.setCursor(105);
 		assertWrap(rset.getCursor(),105);
 	}
 	
 	//And now, let's check the toString function
 	@Test
 	public void test_toString() {
 		ResultSet rset = new ResultSet("ARG1",13,"ARG2",ResultSet.Message.NONE,27);
 		System.out.print(rset);
 		rset = new ResultSet("ARG3",17,"ARG4",ResultSet.Message.MISSING_OPEN_TAG,37);
 		System.out.print(rset);
 		rset = new ResultSet("ARG5",18,"ARG5",ResultSet.Message.MISSING_CLOSE_TAG,37);
 		System.out.print(rset);
 		rset = new ResultSet("ARG6",19,"ARG6",ResultSet.Message.MISSING_OPEN_ARROW,38);
 		System.out.print(rset);
 		rset = new ResultSet("ARG7",20,"ARG7",ResultSet.Message.MISSING_CLOSE_ARROW,39);
 		System.out.print(rset);
 		rset = new ResultSet("ARG8",22,"ARG8",ResultSet.Message.NO_OP,40);
 		System.out.print(rset);
 		rset = new ResultSet("ARG9",23,"ARG9",ResultSet.Message.NO_OPEN_PAREN,41);
 		System.out.print(rset);
 		rset = new ResultSet("ARG10",24,"ARG10",ResultSet.Message.NO_CLOSE_PAREN,42);
 		System.out.print(rset);
 		rset = new ResultSet("ARG11",25,"ARG11",ResultSet.Message.INVALID_ATTR,43);
 		System.out.print(rset);
 		rset = new ResultSet("ARG12",26,"ARG12",ResultSet.Message.INVALID_OPEN_TAG,44);
 		System.out.print(rset);
 		rset = new ResultSet("ARG13",27,"ARG13",ResultSet.Message.DUPLICATE_ATTR,45);
 		System.out.print(rset);
 		rset = new ResultSet("ARG14",28,"ARG14",ResultSet.Message.MISSING_REQ_ATTR,46);
 		System.out.print(rset);
 	}
 	
 }