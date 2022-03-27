package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class SlangDictionary{
public HashMap<String,String> map=new HashMap<String,String>();
    
    public static void getSlangWord()
	{
		
		try
		{
			File file = new File(".Data/slang.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String data;
			while((data = br.readLine())!=null)
			{
				if(data.contains("`"))
				{
					String[] info= data.split("`");
					String[] def = info[1].split("\\|");
					for(int i=0;i<def.length;i++)
					{
						def[i]=def[i].trim();
						
					}
					
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
	public static void Menu(){
		System.out.println("\t\tMENU");
		System.out.println("1. Find definition by slang word.");
		System.out.println("2. Find sland words by definiton.");
		System.out.println("3. History.");
		System.out.println("4. Add slang word.");
		System.out.println("5. Edit slang word.");
		System.out.println("6. Reset dictionary.");
		System.out.println("7. Random a slang word.");
		System.out.println("8. Slang Quiz.");
	}
	public static void main(String[] args) {
		Menu();
	}
}
