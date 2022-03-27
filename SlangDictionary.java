package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class SlangDictionary{
	
	public static String fileslang="Data/slang.txt";
	public static String filehistory="Data/history.txt";
	public static Scanner sc = new Scanner(System.in);
	
	public class Word{
		public String slangword;
		
		public List<String>definition;
		
		Word(String slangword, List<String> definition){
			this.slangword=slangword;
			this.definition=definition;
		}
	}
	
	public static HashMap<String,List<String>> map=new HashMap<String,List<String>>();
	
	public SlangDictionary(){
	       this.getSlangWord();
	       this.printDefinition();
	       this.viewHistory();
	    }
	
	public static void clearConsoleScreen() {
		System.out.print("\033[H\033[2J");
        System.out.flush();
	}
	
    
    public static void getSlangWord()
	{
		try
		{
			File file = new File(fileslang);
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
					List<String>words=Arrays.asList(def);
					map.put(info[0].trim(),words);
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
  
    
    public static List<String> searchDefinition() {
    	clearConsoleScreen();
    	System.out.print("Type the slang word you wanna search: ");
    	String word=sc.nextLine();
    	for(String i: map.keySet()) {
    		if(i.equals(word)) {
    			List<String>def=map.get(i);
    			return def;
    		}
    	}
    	return null;
    }
    
    public static void printDefinition() {
    	List<String> res=searchDefinition();
    	System.out.println(res);
    }
    
    public String searchSlangWord(String word) {
    	clearConsoleScreen();
    	for(String i: map.keySet()) {
    		if(i.equals(word)) {
    			//List<String>=map.get(i);
    			//return newslangword.slangword;
    		}
    	}
    	return null;
    }
    
    public void viewHistory() {
    	
    }
    
    
    
	public static void Menu(){
		System.out.println("\t\tMENU");
		System.out.println("1. Find definitions by slang word.");
		System.out.println("2. Find slang word by definiton.");
		System.out.println("3. History.");
		System.out.println("4. Add slang word.");
		System.out.println("5. Edit slang word.");
		System.out.println("6. Reset dictionary.");
		System.out.println("7. Random a slang word.");
		System.out.println("8. Slang Quiz.");
	}
	
	public static void main(String[] args) {
		SlangDictionary sl=new SlangDictionary();
		String repeat=null;
	    int option;
	    do {
	    	Menu();
	    	option = sc.nextInt();
	    	System.out.print("Your choice: ");
	    	switch(option) {
	    	   case 1:
	    		   sl.printDefinition();
	    		   break;
	    	   case 2:  
	    		 
	    		   break;
	    	   case 3:
	    		   
	    		   break;
	    	   case 4: 
	    		 
	    		   break;
	    	   case 5: 
	    		  
	    		   break;
	    	   case 6:
	    		   
	    		   break;
	    	   case 7:
	    		  
	    		   break;
	    	   case 8:
	    		  
	    		   break;
	    	   case 9:
	    		   
	    		   break;
	    	   default: 
	    		   System.out.println("\n\tLua chon cua ban khong hop le.");
	    		   break;
	    	   }
	    		   
	    	   System.out.println("\n\tBan co muon tiep tuc khong?(yes/no)");
	    	   sc.nextLine();
	    	   System.out.print("\t\t\t");
	    	   repeat = sc.nextLine();
	    	   System.out.println("\n");
	       }while(repeat.equals("yes")==true);
	       sc.close();
		
	}
}
