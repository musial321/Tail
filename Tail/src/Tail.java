import java.io.*;

public class Tail 
{
	static BufferedReader input=null;
	static BufferedReader input2=null;
	static BufferedReader input3=null;
	static File inputFile;
	static boolean follow = false;
	static int n = 10;
	
	public static boolean parseArgs(String [] args) {
		int i = 0;
		boolean validArgs = true;
		
		if(args.length==0) { //If there are no arguments, the arguments are invalid
			validArgs=false;
		}
		
		while(i<args.length) { //While there are arguments that haven't been checked
			if(args[i].equals("-n")) {
				try {
					n = Integer.parseInt(args[i+1]); //Makes sure the argument after -n is an integer
					
					if(n<1) { //Makes sure the number is greater than or equal to 1
						validArgs=false;
					}
				}
				catch(Exception e) {
					validArgs=false;
					break;
				}
				i+=2; //Advance 2 in arguments since an integer comes after -n
			}
				
			else if(args[i].equals("-f")) { //Checks if user wants follow on
				i++;
				follow=true;
			}
			
			else { //If argument is neither -n or -f, it is assumed to be the file
				try {
				inputFile = new File(args[i]);
					if(!inputFile.exists())
					{
						validArgs=false;
					}
				i++;
				}
				catch(Exception e) {
					validArgs=false;
					break;
				}
			}	
		}
		return validArgs;
	}
	
	public static String getNewText(File a) {
		
		String inputTxt="";
		int j=0;
		
		try {
			input3 = new BufferedReader(new FileReader(a));
		} 
		catch (Exception e) {
		}
			
		while(j<inputFile.length()-1) { //Converts file into a string
			try {
				inputTxt+=(char)(input3.read());
			} 
			catch (Exception e) {		
			}
				j++;
		}
			
		return inputTxt;
	}

	public static void main(String [] args) throws IOException
	{
		if(!parseArgs(args)) { //Invalid arguments
			System.out.println("Argument Error.");
		}
		else {	//Valid arguments
			try {
				input = new BufferedReader(new FileReader(inputFile));
				input2 = new BufferedReader(new FileReader(args[args.length-1]));
				
			
				String inputTxt=getNewText(inputFile); //Stores file as a string
				String temp="";
				String[]lines=new String[n]; //An array that stores the file lines
				
				int totalLineCounter=1;
				int arrayPosition = 1;
				
				temp=input.readLine();
				lines[0]=temp;
				
				
				while(temp!=null) { //Gets line count as well as creates array of the last n lines in file
				
					temp=input.readLine();
					
					if(temp!=null) {
						if(arrayPosition==(n)) { //Resets arrayPosition at n since array is the length of n
							arrayPosition=0;
						}
					
					totalLineCounter++;
					lines[arrayPosition]=temp;
					arrayPosition++;
					}
				}

				int arrayStart=totalLineCounter%n; //Array position to start printing lines
				
				if(follow) {
					int num=0;
					long oldSize = inputFile.length();
					long newSize;
					int arrayStart2 = arrayStart;
					
					while(arrayStart<lines.length) { //Prints from lines%n to end
						if(arrayStart2==0 && n-arrayStart==1) {
							System.out.print(lines[arrayStart]);
						}
						else {
							System.out.println(lines[arrayStart]);
						}
						
						arrayStart++;
					}
					while(num<arrayStart2) //Prints from the start of the array to lines%n
					{	
						if(arrayStart2-num==1) {
							System.out.print(lines[num]);
						}
						else {
							System.out.println(lines[num]);			
						}
						num++;
					}

					
					
					while(true) //Continuously updates file
					{
						newSize=inputFile.length();
						
						if(newSize<oldSize) {
							input2 = new BufferedReader(new FileReader(inputFile));
							int count = 0;
							char readChar=0;
							int readChar2;
							
							try {
								while((readChar=(char)(input2.read()))==inputTxt.charAt(count)) {
									count++;
								}
							}
							catch(Exception e) {	
							}
							
							System.out.print(readChar);
							
							while((readChar2=input2.read())!=-1) {
								System.out.print((char)(readChar2));
							}
							
							if(input2!=null) {
								input2.close();
							}
							
							oldSize=inputFile.length();
							inputTxt=getNewText(inputFile);	
						}
						if(newSize>oldSize)
						{
							input2 = new BufferedReader(new FileReader(inputFile));
							int count = 0;
							int readChar2=0;
							
							try {
								while((readChar2 =(input2.read()))==inputTxt.charAt(count)) {
									count++;
								}
							}
							catch(Exception e) {		
							}
							
							do {
								System.out.print((char)(readChar2));
							}
							while((readChar2=input2.read())!=-1);
								
							if(input2!=null) {
								input2.close();
							}
							
							oldSize=inputFile.length();
							inputTxt=getNewText(inputFile);
						}
					}	
				}
				if(!follow) {
					int arrayStart2=arrayStart;
					int num=0;
					
					while(arrayStart<lines.length) { //Prints from lines%n to end
						if(arrayStart2==0 && n-arrayStart==1) {
							System.out.print(lines[arrayStart]);
						}
						else {
							System.out.println(lines[arrayStart]);
						}
						
						arrayStart++;
					}
					
					while(num<arrayStart2) { //Prints from the start of the array to lines%n
						if(arrayStart2-num==1) {
							System.out.print(lines[num]);
						}
						else {
							System.out.println(lines[num]);			
						}
						num++;
					}
				}
			}
			finally
			{
				if(input!=null) {
					input.close();
				}
				if(input2!=null) {
					input2.close();
				}
				if(input3!=null) {
					input3.close();
				}
			}
		}
	}
}