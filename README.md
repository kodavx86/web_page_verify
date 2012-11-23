This is the wpv or Web Page Verify program that can verify if the
HTML code in a given file or directory is correct. To compile the
program, you first need to build it with the following command:

	make build
	
To test the program (make sure everything is working), you can run
the junit test cases with this command:

	make test
	
To clean up any binaries, use this command:

	make clean
	
The Web Page Validator doesn't attempt to render any given code (i.e.
it won't try to execute javascript or render any XML code, but tries
to determine if any tag names are mispelled, any attributes are invalid,
and whether or not any tags are missing. If the program does find an error,
it will print out the file name, the line number, the reason for the error,
and a line showing the offending code with a carret pointing to it. For
instance, the following command shows a sample run the program:

	make run
	
	Users/kom/code/java_code/wpv/./tdata/red_paragraph.html,line 2 :  cannot find close tag
	head
	^