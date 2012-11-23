/**
 * This is the tag config class responsible for reading and
 * processing any and all config files with tag data. This data
 * includes tag names, attributes, values, and closing tags if
 * applicable.
 *
 * Author: Tyler Vodak
 * Date: 4/30/2012
 */
 
 import java.util.*;
 import java.io.*;
 
 public class TagConfig {
 
 	//These are the class objects of TagConfig
 	private static final String defaultConf = "./config/tags.conf";
 	private static final short TRUE_EXCEPTION = 255;
 	private static final char comment_line = '#';
 	
 	//These are the object attributes
 	HashMap<String,String>OpenCloseTag;					//map of open tags to close tags
 	HashMap<String, HashMap<String,String>>Attributes;	//map of open tag attributes
 	
 	/*
 	 * This is the class constructor for the TagConfig class. It
 	 * takes a vector of files to read config data, giving the caller
 	 * a chance to provide additional files along with the default
 	 * tags config file.
 	 */
 	public TagConfig (Vector<String> configs) {
 	
 		OpenCloseTag = new HashMap<String,String>();
 		Attributes = new HashMap<String, HashMap<String,String>>();
 		//First, add the default config file
 		configs.add(defaultConf);
 		//Now, read the data from all the config files
 		readConfigData(configs);
 	}
 	
 	/*
 	 * This function will read the data from each of the files
 	 * the vector parameter and add the data to some containers
 	 * that can be queried.
 	 *
 	 * Returns: nothing, but sets up the containers
 	 */
 	private void readConfigData(Vector<String> configs) {
 		//This will be our file object
 		File cFile = null;
 		String line = "";
 		
 		//Now, go through each file, read data, save to containers
 		for (String nfile : configs) {
 			cFile = new File(nfile);
 			if(!cFile.exists()) {
 				continue;
 			}
 			try {
 				BufferedReader reader = new BufferedReader (new FileReader(nfile));
 				line = reader.readLine();
 				while(null != line) {
 					parseLine(line);
 					line = reader.readLine();
 				}
 			}
 			catch (IOException e) {
 				System.out.println("ERROR - Cannot read from config file: " + nfile);
 	  			System.exit(TRUE_EXCEPTION);
 			}
 		}
 	}
 	
 	/*
 	 * This function will take a given line and parse the different
 	 * parts such as the open tag, closing tag, and any attributes
 	 *
 	 * Returns: nothing, will silently ignore errors
 	 */
 	private void parseLine(String line) {
 		//First, check if the given line is just a comment line
 		line = line.replaceAll("\\s","").toLowerCase();
 		int res_size = 3;
 		if(line.isEmpty()) {
 			return;
 		}
 		else if (line.charAt(0) == comment_line) {
 			return;
 		}
 		//At this point, this line is a possible config line
 		String[] properties = line.split(":");
 		//First, check the size of the array
 		if(res_size != properties.length) {
 			System.out.println("The following config line is not valid => " + line);
 			return;
 		}
 		//Alright, the first element is the open tag, the second is the close tag
 		OpenCloseTag.put(properties[0],properties[1]);
 		//Now, map the attributes to the open tag
 		String[] attr_split = properties[2].split(",");
 		HashMap<String,String> attrs = new HashMap<String,String>();
 		for (int j = 0; j < attr_split.length; j++) {
 			String[] key_value = attr_split[j].split("=");
 			if(key_value.length > 1) {
 				attrs.put(key_value[0],key_value[1]);
 			}
 		}
 		Attributes.put(properties[0],attrs);
 	}
 	
 	/*
 	 * This function returns a true false value based on whether
 	 * or not the supplied tag is a valid open tag name.
 	 *
 	 * Returns => true if the given name is a valid open tag name, false if not
 	 */
 	public boolean isOpenTag(String tag_name) {
 		//First, trim the whitespace and set to lower case
 		tag_name = tag_name.trim().toLowerCase();
 		
 		//Now, check if this open tag exists
 		if(OpenCloseTag.containsKey(tag_name)) {
 			return true;
 		}
 		//else
 		return false;
 	}
 	
 	/*
 	 * This function returns the map of attr/value pairs for a given
 	 * open tag.
 	 *
 	 * Returns: a map of attr/val pairs, null if open tag is invalid
 	 */
 	public HashMap<String,String> getAttrVals(String tag_name) {
 		tag_name = tag_name.trim().toLowerCase();
 		return Attributes.get(tag_name);
 	}
 	
 	/*
 	 * This function returns a map of required attr/value pairs for
 	 * a given tag.
 	 *
 	 * Returns: a vector of attrs, null if open tag is invalid
 	 */
 	public Vector<String> getReqAttrs(String tag_name) {
 		tag_name = tag_name.trim().toLowerCase();
 		if(null == Attributes.get(tag_name)) {
 			return null;
 		}
 		
 		Vector<String> res = new Vector<String>();
 		Iterator iter = Attributes.get(tag_name).keySet().iterator();
 		while(iter.hasNext()) {
 			String n_key = iter.next().toString().trim().toLowerCase();
 			String req_flag = Attributes.get(tag_name).get(n_key).trim().toLowerCase();
 			if(req_flag.equalsIgnoreCase("r")) {
 				res.add(n_key);
 			}
 		}
 		return res;
 	}
 	
 	/*
 	 * This function returns the corresponding close tag for the
 	 * given open tag
 	 *
 	 * Returns => name of the close tag, or null if one doesn't exist
 	 */
 	public String getCloseTag(String openTag) {
 		openTag = openTag.trim().toLowerCase();
 		String close_tag = OpenCloseTag.get(openTag);
 		if((null == close_tag) || close_tag.equalsIgnoreCase("none")) {
 			return null;
 		}
 		return close_tag;
 	}
 }