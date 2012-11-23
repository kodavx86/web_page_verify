/**
 * This is the class for containing result information while
 * processing HTML text data. Any errors or warnings found while
 * processing the HTML data will create and populate an object
 * from this class and be stored in an array for final output.
 *
 * Author: Tyler Vodak
 * Date: 02/20/2012
 */
 
 public class ResultSet {
 
 	//These are some extra attributes made for special purposes
 	public enum Message { 
 		NONE, 
 		MISSING_OPEN_TAG,
 		MISSING_CLOSE_TAG,
 		MISSING_OPEN_ARROW,
 		MISSING_CLOSE_ARROW,
 		MISSING_REQ_ATTR,
 		NO_OP,
 		NO_OPEN_PAREN,
 		NO_CLOSE_PAREN,
 		INVALID_ATTR,
 		INVALID_OPEN_TAG,
 		DUPLICATE_ATTR
 	}
 
 	//Let's start with some class attributes
 	private String fileName;	//Name of the file
 	private int lineNum;		//Line number of line in the file
 	private String line;		//String in file
 	private Message msg;		//What message to display about the result
 	private int cursor;			//cursor position on where to place the carot mark
 	
 	//The default constructor
 	public ResultSet() {
 		fileName = null;
 		lineNum = 0;
 		line = null;
 		msg = Message.NONE;
 		cursor = -1;
 	}
 	
 	//The "everything" constructor
 	public ResultSet(String fileName, int lineNum, String line,
 						Message msg, int cursor) {
 		this.fileName = fileName;
 		this.lineNum = lineNum;
 		this.line = line;
 		this.msg = msg;
 		this.cursor = cursor;
 	}
 	
 	//Now, we need getters and setters (joyness)
 	public String getFileName() {return this.fileName;}
 	
 	public int getlineNum() {return this.lineNum;}
 	
 	public String getLine() {return this.line;}
 	
 	public Message getMsg() {return this.msg;}
 	
 	public int getCursor() {return this.cursor;}
 	
 	//..and the setter methods
 	public void setFileName(String fileName) {this.fileName = fileName;}
 	
 	public void setLineNum(int lineNum) {this.lineNum = lineNum;}
 	
 	public void setLine(String line) {this.line = line;}
 	
 	public void setMsg(Message msg) {this.msg = msg;}
 	
 	public void setCursor(int cursor) {this.cursor = cursor;}
 	
 	
 	//The first and most necessary function of this class is toString
 	//Defining a toString method keeps the message formatting in one
 	//place and makes it easier to print all ResultSet objects
 	public String toString() {
 		String f_result = "";
 		String s_msg = "";
 		
 		if(msg == Message.MISSING_OPEN_TAG) {
 			s_msg = "cannot find open tag";
 		}
 		else if(msg == Message.MISSING_CLOSE_TAG) {
 			s_msg = "cannot find close tag";
 		}
 		else if(msg == Message.MISSING_OPEN_ARROW) {
 			s_msg = "cannot find open < ";
 		}
 		else if(msg == Message.MISSING_CLOSE_ARROW) {
 			s_msg = "cannot find close >";
 		}
 		else if(msg == Message.NO_OP) {
 			s_msg = "tag has no effect";
 		}
 		else if(msg == Message.NO_OPEN_PAREN) {
 			s_msg = "missing open parentheses";
 		}
 		else if(msg == Message.NO_CLOSE_PAREN) {
 			s_msg = "missing close parentheses";
 		}
 		else if(msg == Message.INVALID_ATTR) {
 			s_msg = "invalid attribute";
 		}
 		else if(msg == Message.INVALID_OPEN_TAG) {
 			s_msg = "invalid open tag name";
 		}
 		else if(msg == Message.DUPLICATE_ATTR) {
 			s_msg = "duplicate attribute";
 		}
 		else if(msg == Message.MISSING_REQ_ATTR) {
 			s_msg = "missing required attribute";
 		}
 		
 		f_result = fileName + ",line " + lineNum + " : " + " " + s_msg;
 		f_result = f_result + System.getProperty("line.separator");
 		f_result = f_result + line + System.getProperty("line.separator");
 		
 		int offset = cursor;
 		while (offset > 1) {
 			f_result = f_result + " ";
 			offset -= 1;
 		}
 		f_result = f_result + "^" + System.getProperty("line.separator");
 		return f_result;
 	}
 }