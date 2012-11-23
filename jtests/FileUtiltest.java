/**
 * This is the Junit test file for the FileUtil.java file. All tests
 * on the FileUtil class code should be placed in here.
 *
 * Author: Tyler Vodak
 * Date: 11/13/2011
 */
 
 import java.io.*;
 import org.junit.*;
 
 public class FileUtiltest {
 
 	/* These are just some static class variables */
 	private static final String TEST_DATA_DIR = "./tdata/";
 
 	//This is a wrapper function that can help
 	//determine which test case actually failed
 	private static void assertWrap(int expected, int result) {
 		try {
 			Assert.assertTrue(expected == result);
 		}
 		catch (AssertionError err) {
 			System.out.println("Error - FileUtil test failed!");
 			System.out.println("Expected: " + expected + ", Result: " + result);
 			err.printStackTrace();
 			System.exit(201);
 		}
 	}
 	
 	//Check the doesSourceExist method
 	@Test
 	public void test_doesSourceExist() {
 		String e_file = TEST_DATA_DIR + "index.html";
 		String ne_file = "blarg";
 		
 		FileUtil fu = new FileUtil(e_file);
 		Assert.assertTrue("doesSourceExist failed for existing file: " 
 			+ e_file, fu.doesSourceExist());
 		
 		fu = new FileUtil(ne_file);
 		Assert.assertTrue("test_doesSourceExist passed for non-existing file: "
 			+ ne_file, !fu.doesSourceExist());
 	}
 	
 	//Check if the openSource() method can handle files and directories
 	@Test
 	public void test_openSource() {
 		//First, check if an ordinary file can be opened
 		FileUtil fUtil = new FileUtil(TEST_DATA_DIR + "index.html");
 		assertWrap(0,fUtil.openSource());
 		
 		//Check if a non-existent file returns error code
 		fUtil = new FileUtil(TEST_DATA_DIR + "blarg");
 		assertWrap(1,fUtil.openSource());
 		
 		//Check what happens if file is unreadable
 		File unreadable = new File(TEST_DATA_DIR + "unreadable");
 		try {
 			Assert.assertTrue(unreadable.createNewFile());
 			Runtime.getRuntime().exec("chmod 000 " + TEST_DATA_DIR + "unreadable");
 			fUtil = new FileUtil(TEST_DATA_DIR + "unreadable");
 			//assertWrap(2,fUtil.openSource());
 			Runtime.getRuntime().exec("chmod 777 " + TEST_DATA_DIR + "unreadable");
 			unreadable.delete();
 		}
 		catch (IOException e) {
 			System.out.println("Cannot create unreadable file in test_openSource");
 			e.printStackTrace();
 			System.exit(1);
 		}
 		
 		//Check what happens if given directory is empty
 		File empty = new File(TEST_DATA_DIR + "empty");
 		empty.mkdir();
 		fUtil = new FileUtil(TEST_DATA_DIR + "empty");
 		assertWrap(3,fUtil.openSource());
 		empty.delete();
 		
 		//Check what happens if given directory is unreadable
 		File unreadabledir = new File(TEST_DATA_DIR + "unreadabledir/");
 		unreadabledir.mkdir();
 		File data = new File (TEST_DATA_DIR + "unreadabledir/data");
 		fUtil = new FileUtil(TEST_DATA_DIR + "unreadabledir");
 		try {
 			Assert.assertTrue(data.createNewFile());
 			Runtime.getRuntime().exec("chmod 000 " + TEST_DATA_DIR + "unreadabledir/");
 			assertWrap(3,fUtil.openSource());
 			Runtime.getRuntime().exec("chmod 777 " + TEST_DATA_DIR +"unreadabledir/");
 			Runtime.getRuntime().exec("rm -rf " + TEST_DATA_DIR +"unreadabledir/");
 		}
 		catch (IOException e) {
 			System.out.println("Cannot create unreadable directory in test_openSource");
 			e.printStackTrace();
 			System.exit(2);
 		}
 		
 		//Check what happens if directory is readable, but all contents are not
 		File dir = new File(TEST_DATA_DIR + "dir/");
 		dir.mkdir();
 		fUtil = new FileUtil(TEST_DATA_DIR + "dir/");
 		File data1 = new File (TEST_DATA_DIR + "dir/data1");
 		File data2 = new File (TEST_DATA_DIR + "dir/data2");
 		try {
 			Assert.assertTrue(data1.createNewFile());
 			Runtime.getRuntime().exec("chmod 000 " + TEST_DATA_DIR +"dir/data1");
 			Assert.assertTrue(data2.createNewFile());
 			Runtime.getRuntime().exec("chmod 000 " + TEST_DATA_DIR +"dir/data2");
 			assertWrap(4,fUtil.openSource());
 			Runtime.getRuntime().exec("chmod 777 " + TEST_DATA_DIR +"dir/data1");
 			Runtime.getRuntime().exec("chmod 777 " + TEST_DATA_DIR +"dir/data2");
 			Runtime.getRuntime().exec("rm -rf " + TEST_DATA_DIR +"dir/");
 		}
 		catch (IOException e) {
 			System.out.println("Cannot create unreadable data in directory in test_openSource");
 			e.printStackTrace();
 			System.exit(3);
 		}
 	}
 	
 	//Check if the getNextFile method returns the next file
 	@Test
 	public void test_getNextFile() {
 	
 		//First, let's try with just a single file
 		File tfile = new File(TEST_DATA_DIR + "index.html");
 		FileUtil fUtil = new FileUtil(TEST_DATA_DIR + "index.html");
 		fUtil.openSource();
 		String fName = fUtil.getNextFile();
 		assertWrap(1,fName.equals(tfile.getAbsolutePath()) ? 1 : 0);
 		
 		//Try with a directory that has just one file
 		File dir = new File(TEST_DATA_DIR + "NFdir");
 		File data3 = new File(TEST_DATA_DIR + "NFdir/data3");
 		fUtil = new FileUtil(TEST_DATA_DIR + "NFdir/////");  fName = "";
 		try {
 			dir.mkdir();
 			Assert.assertTrue(data3.createNewFile());
 			fUtil.openSource();
 			fName = fUtil.getNextFile();
 			assertWrap(1,fName.equals(data3.getAbsolutePath()) ? 1 : 0);
 			Runtime.getRuntime().exec("rm -rf " + TEST_DATA_DIR + "NFdir/");
 		}
 		catch (IOException e) {
 			System.out.println("Cannot create test dir in test_getNextFile");
 			e.printStackTrace();
 			System.exit(4);
 		}
 	}
 	
 	//Check if the getNextLine method returns the next line
 	@Test
 	public void test_getNextLine() {
 	
 		//Alright, first a very simple test of just reading a single line
 		FileUtil fUtil = new FileUtil(TEST_DATA_DIR  +"red_paragraph.html");
 		fUtil.openSource();	//Open the directory
 		String fname = fUtil.getNextFile();	//get the next file
 		String line = fUtil.getNextLine(fname);
 		assertWrap(1,line.equals("<html>") ? 1 : 0);
 		
 		//Next, let's test if we can read multiple lines
 		fUtil = new FileUtil(TEST_DATA_DIR  +"red_paragraph.html");
 		fUtil.openSource();
 		fname = fUtil.getNextFile();
 		line = fUtil.getNextLine(fname);
 		assertWrap(1,line.equals("<html>") ? 1 : 0);
 		line = fUtil.getNextLine(fname);
 		assertWrap(1,line.equals("<head>") ? 1 : 0);
 		line = fUtil.getNextLine(fname);
 		assertWrap(1,line.equals("<title>Red Paragraph</title>") ? 1 : 0);
 		line = fUtil.getNextLine(fname);
 		assertWrap(1,line.equals("<body>") ? 1 : 0);
 		
 		//Now, lets test what happens when we use a directory with +1 files
 		//We just need to make sure this test block finishes
 		fUtil =  new FileUtil(TEST_DATA_DIR);
 		fUtil.openSource();
 		fname = fUtil.getNextFile();	//First file
 		while(!fUtil.getNextLine(fname).isEmpty());
 		fname = fUtil.getNextFile();	//Second file
 		while(!fUtil.getNextLine(fname).isEmpty());
 	}
 }
