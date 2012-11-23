/**
 * This is the Junit test file for the TagConfig.java file. All tests
 * on the TagConfig class code should be placed in here.
 *
 * Author: Tyler Vodak
 * Date: 04/30/2012
 */
 
 import java.util.*;
 import org.junit.*;
 
 public class TagConfigtest {
 
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
 	
 	//First, check to make sure we can make an object
 	@Test
 	public void test_TagConfigtest() {
 		TagConfig n_config = new TagConfig(new Vector<String>());
 	}
 	
 	//Let's verify that a valid tag is known to exist
 	@Test
 	public void test_isOpenTag() {
 		TagConfig n_config = new TagConfig(new Vector<String>());
 		
 		boolean res = n_config.isOpenTag("head");
 		assertWrap(res,true);
 		res = n_config.isOpenTag("HEAD");
 		assertWrap(res,true);
 		res = n_config.isOpenTag("HeAd");
 		assertWrap(res,true);
 		res = n_config.isOpenTag("blarg");
 		assertWrap(res,false);
 	}
 	
 	//Verify that a valid tag will return a container of attribute/values
 	@Test
 	public void test_getAttrVals() {
 		TagConfig n_config = new TagConfig(new Vector<String>());
 		HashMap<String,String> res = n_config.getAttrVals("head");
 		assertWrap((res != null),true);
 		res = n_config.getAttrVals("HEAD");
 		assertWrap((res != null),true);
 		res = n_config.getAttrVals("HeAd");
 		assertWrap((res != null),true);
 		res = n_config.getAttrVals("blarg");
 		assertWrap((res != null),false);
 	}
 	
 	//Verify that a valid tag will return a container of required attribute/values
 	@Test
 	public void test_getReqAttrs() {
 		TagConfig n_config = new TagConfig(new Vector<String>());
 		Vector<String> res = n_config.getReqAttrs("head");
 		assertWrap((res != null),true);
 		res = n_config.getReqAttrs("HEAD");
 		assertWrap((res != null),true);
 		res = n_config.getReqAttrs("HeAd");
 		assertWrap((res != null),true);
 		res = n_config.getReqAttrs("blarg");
 		assertWrap((res != null),false);
 		res = n_config.getReqAttrs("meta");
 		assertWrap((res != null),true);
 	}
 	
 	//Verify that a valid open tag will return a valid close tag
 	@Test
 	public void test_getCloseTag() {
 		TagConfig n_config = new TagConfig(new Vector<String>());
 		String res = n_config.getCloseTag("head");
 		assertWrap((res != null),true);
 		res = n_config.getCloseTag("HEAD");
 		assertWrap((res != null),true);
 		res = n_config.getCloseTag("HeAd");
 		assertWrap((res != null),true);
 		res = n_config.getCloseTag("blarg");
 		assertWrap((res != null),false);
 		res = n_config.getCloseTag("meta");
 		assertWrap((res != null),false);
 	}
 }