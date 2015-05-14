import java.io.*;

public class Tail 
{
	
	
	public static boolean parseArgs(String [] args)
	{
		int i = 0;
		boolean bool = true;
		
		if(args.length==0)
		{
			bool=false;
		}
		
		while(i<args.length)
		{
			if(args[i].equals("-n"))
			{
				try
				{
					int temp = Integer.parseInt(args[i+1]);
					if(temp<1)
					{
						bool=false;
					}
				}
				catch(Exception e)
				{
					bool=false;
					break;
				}
				
			}
				
			
			
			
			
			
			if(args[i].equals("-f"))
			{
				i++;
			}
			else if(args[i].equals("-n"))
			{
				i+=2;
			}
			else
			{
				File inputFile;
				try
				{
				inputFile = new File(args[i]);
					if(!inputFile.exists())
					{
						bool=false;
					}
				i++;
				}
				catch(Exception e)
				{
					bool=false;
					break;
				}
			}
			
				
				
		}
		
		
		
		return bool;
	}
	

	
	
	public static void main(String [] args) throws IOException
	{
		BufferedReader input=null;
		BufferedReader input2=null;
		//BufferedWriter output=null;
		
		if(!parseArgs(args))
		{
			System.out.println("Argument Error.");
		}
		else
		{
		try
			{
			input = new BufferedReader(new FileReader(args[args.length-1]));
			input2 = new BufferedReader(new FileReader(args[args.length-1]));
			
			int i = 0;
			int n = 10;
			boolean f = false;
			
			while(i<args.length)
			{
				if(args[i].equals("-n"))
				{
						n = Integer.parseInt(args[i+1]);
				}
	
				if(args[i].equals("-f"))
				{
					f=true;
					i++;
				}
				else if(args[i].equals("-n"))
				{
					i+=2;
				}
				else
				{
					i++;
				}
			}
			
			int totalLineCounter=0;
			String temp="";
			
				
				temp = input.readLine();
				while(temp!=null)
				{
					totalLineCounter++;
					temp=input.readLine();
				}
			
				
					
				int lineToStartReading=totalLineCounter-n;
				
			
				
				
				while(lineToStartReading>0)
				{
					input2.readLine();
					lineToStartReading--;
				}
				
				String c;
				
				if(f)
				{
					while(true)
					{
						c=input2.readLine();
						
						if(c!=null && !c.isEmpty())
						{
						System.out.println(c);
						}
						
					}
				}
				
				int num=0;
				
				if(!f)
				{
					while(num<n)
					{
						System.out.println(input2.readLine());
						num++;
					}
				}
				
				
				
			}
			
			
			
			
			finally
			{
				if(input!=null)
				{
					input.close();
				}
				if(input2!=null)
				{
					input.close();
				}
			}
		}
	}
	
}
