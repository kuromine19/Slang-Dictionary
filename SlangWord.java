package source;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SlangWord {
	
	public static HashMap<String,List<String>> m=new HashMap<String,List<String>>();
    public static List<String> historySlangWord=new ArrayList();
    public static Scanner word= new Scanner(System.in);
    
    public static void getSlangWord()
	{
		
		try
		{
			File f = new File(".Data/slang.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String data;
			while((data = br.readLine())!=null)
			{
				if(data.contains("`"))
				{
					String[] str= data.split("`");
					String[] temp= str[1].split("\\|");
					List<String> temp2=Arrays.asList(temp);
	                m.put(str[0],temp2);
				}
			}
		    fr.close();
	        br.close();
	    }
	    catch (Exception ex)
	    {
	        System.out.println("ERROR"+ex);
	    }
		
		
	}

}

