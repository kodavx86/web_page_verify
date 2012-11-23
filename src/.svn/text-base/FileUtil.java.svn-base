/**
 * This is the file class of the wpv program that is responsible
 * for checking, validating, opening, and reading input file(s). Any kind
 * of input file operation in wpv should be defined here.
 *
 * Author: Tyler Vodak
 * Date: 11/13/2011
 */
 
 import java.io.*;
 import java.util.*;
 
 public class FileUtil {
 
 	private String source; 			//source location of html files
 	private Vector<String> files; 	//list of files to read out
 	private int iterator; 			//keeps track of current file "pointer" in source
 	private BufferedReader reader;	//BufferedReader file pointer
 	
 	/* Class variables */
 	private static final short TRUE_EXCEPTION = 255;	//This error makes the program exit
  
 	/* This is the primary class constructor
 	 * of the FileUtil class.
 	 * source => file(s)/Directory(ies) to process
 	 */
 	public FileUtil(String source) {
 		this.source = source;
 		files = new Vector<String>();
 		iterator = 0;
 		reader = null;
 	}
 	
 	/* This method returns a yes or no answer
 	 * as to whether the source exists or not
 	 * returns: true if source exists, false if not
 	 */
 	public boolean doesSourceExist() {
 		File nFile = new File(source);
 		if(nFile.exists()) {
 			return true;
 		}
 		return false;
 	}
 	
 	/* This method attempts to open the current source
 	 * returns => 	a status code indicating the result of
 	 *				attempting to open source.
 	 *				0 => everything is OK (source was opened)
 	 *				1 => the source does not exist
 	 *				2 => the file is not readable or cannot be accessed
 	 *				3 => the source directory is empty or not readable
 	 *				4 => the contents of source directory are inaccessible
 	 */
 	public int openSource() {
 		File fObj = new File(source);
 		//check if source exists
 		if(!fObj.exists()) {
 			System.out.println("Error - " + source + " does not exist");
 			return 1;
 		}
 		if(fObj.isFile()) { //is source just a regular file
 			//need to save file string to vector here
 			try {
 				if(fObj.canRead()) {
 					files.add(fObj.getAbsolutePath());
 				}
 				else {
 					System.out.println(fObj.getAbsolutePath() + " is not readable");
 					return 2;
 				}
 			}
 			catch (SecurityException e) {
 				System.out.println("Error - " + source + " cannot be accessed");
 				return 2;
			}
 		}
 		else if(fObj.isDirectory()) { //is source a directory
 			try {
 				String[] f_list = fObj.list();
 				if(null == f_list || 0 == f_list.length) {
 					System.out.println("Warning - Source directory is empty or " + 
 										"not accesible: " + source);
 					return 3;
 				}
 				for(int i = 0; i < f_list.length; i++) {
 					File tmp = new File(source + "/" + f_list[i]);
 					if(tmp.canRead()) {
 						if(!tmp.isDirectory()) {
 							files.add(tmp.getAbsolutePath());
 						}
 					}
 					else {
 						System.out.println(fObj.getAbsolutePath() + " is not readable");
 						return 4;
 					}
 				}
 			}
 			catch (SecurityException e) {
 				System.out.println("Error - Not authorized to open the directory: " + source);
 				return 4;
 			}
 		}
 		return 0;
 	}
 	
 	/* This method will return the next file to process. If
 	 * no more files exist, the method returns null;
 	 * 
 	 * returns: A string of the next file in the files array
 	 *			null if no more files to read in files array
 	 */
 	 public String getNextFile() {
 	 	if(iterator < files.size()) {
 	 		return files.get(iterator++);
 	 	}
 	 	return null;
 	 }
 	 
 	 /* This method will return the next "line" in the current
 	  * file. Given that a "line" in html could potentially span
 	  * a ton of characters, this method could trigger a buffer
 	  * overflow since it tries to read the whole line.
 	  *
 	  * f_name => the name of the file to read [taken from getNextFile()]
 	  * returns: A string that's supposed to be the next "line"
 	  *			null if no more data can be read
 	  */
 	  public String getNextLine(String f_name) {
 	  	String line = "";
 	  		
 	  	//First, we need to open the current file
 	  	try {
 	  		if(null == reader) {
 	  			reader = new BufferedReader(new FileReader(f_name));
 	  		}
 			line = reader.readLine();
 			if(null == line) {
 				reader = null;
 			}
 			else {
 				//make sure the line is a "complete" html line
 				String tmp = line.trim();
 				while((0 != tmp.length()) && ('>' != tmp.charAt(tmp.length() - 1))) {
 					tmp = reader.readLine();
 					if(null == tmp) {
 						reader.close();
 						break;
 					}
 					line = line + tmp;
 					tmp = line.trim();
 				}
 			}
 	  	}
 	  	catch (IOException e) {
 	  		System.out.println("ERROR - Cannot read from file: " + f_name);
 	  		System.exit(TRUE_EXCEPTION);
 	  	}
 	  	
 	  	return line;
 	  }
 }