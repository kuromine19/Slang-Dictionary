package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class SlangDictionary{
	
	public static String fileslang="D:/University/JAVA/SlangDictionary/SlangDictionary/Data/slang.txt";
	public static String filehistory="D:/University/JAVA/SlangDictionary/SlangDictionary/Data/history.txt";
	public static ArrayList<String>historySearch=new ArrayList<String>();
	public static ArrayList<String>tempHistorySearch=new ArrayList<String>(); 
	public static Scanner sc = new Scanner(System.in);
	
	
	
	public static class Word{
		public String slangword;
		public String definition;
		public List<String>definitions;
		Word(String slangword, String definition){
			this.slangword=slangword;
			this.definition=definition;
		}
		Word(String slangword, List<String> definitions){
			this.slangword=slangword;
			this.definitions=definitions;
		}
	}
	
	public static HashMap<String,List<String>> map=new HashMap<String,List<String>>();
	
	/*public SlangDictionary(){
		   this.Menu();
	       this.getSlangWord();
	       this.printDefinition();
	       this.viewHistory();
	    }*/
	
	public static void clearConsoleScreen() {
		try
		{	
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}catch(Exception E)
			{
				System.out.println(E);
			}
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
						def[i]=def[i].toLowerCase();
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
    
   
    
    public static void getHistory() {
    	try {
    		
    		File file=new File(filehistory);
    		FileReader fr=new FileReader(file);
    		BufferedReader br=new BufferedReader(fr);
    		String data;
    		while((data = br.readLine())!=null)
			{
				historySearch.add(data);	
			}
		    fr.close();
	        br.close();
	    }
	    catch (Exception ex)
	    {
	        System.out.println("ERROR"+ex);
	    }
    }
    
 
    
    public static void updateHistoryFile(String word) {
    	try {
    		File file=new File(filehistory);
    		FileWriter fw=new FileWriter(file,true);
    		BufferedWriter bw = new BufferedWriter(fw);
    		fw.write(word+"\n");
    		fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    }
    
    public static void viewHistory() {
    	getHistory();
    	if(historySearch.isEmpty()) {
    		System.out.println("Empty history!");
    		return;
    	}
    	System.out.println("\t\tHistory - Slang Words you have searched: ");
    	for(String slangWord: historySearch) {
    		System.out.println("\t"+slangWord);
    	}
    	historySearch.clear();
    	
    }
    
    public static List<String> searchSlangWord() {
    	System.out.println("Type the slang word you wanna search: ");
    	
    	String word=sc.nextLine();
    	word=word.toUpperCase();
    	updateHistoryFile(word);
    	for(String i: map.keySet()) {
    		if(i.equals(word)) {
    			List<String>def=map.get(i);
    			return def;
    		}
    	}
    	return null;
    }
    
    public static void printDefinition() {
    	List<String> res=searchSlangWord();
    	if(res==null)return;
    	System.out.println("Definitions:");
    	for(String i:res) {
    		System.out.println("\t"+i);
    	}
    }
    
    public static ArrayList<Word> searchDefinition() {
    	System.out.println("Type any definitions you wanna search: ");
    	
    	String word=sc.nextLine();
    	ArrayList<Word>list=new ArrayList<Word>();
    	for(String i: map.keySet()) {
    		for(String j: map.get(i)) {
    			if(j.contains(word)) {
    			Word w=new Word(i,j);
    			list.add(w);
    			}
    		}
    	}
    	return list;
    }
    
    public static void printSlangWord() {
    	ArrayList<Word> res=searchDefinition();
    	if(res==null)return;
    	for(Word i:res) {
    		System.out.print(i.slangword+"\t");
    		System.out.println(i.definition);
    	}
    	
    }
    
    
    
    
    public static void updateFileSlangWord() {
    	
    }
    
    
    
	public static void Menu() {
		String repeat=null; 
		do
		{
			clearConsoleScreen();
			repeat=null; 
			getSlangWord();
			System.out.println("\t\tMENU");
			System.out.println("1. Find definitions by slang word.");
			System.out.println("2. Find slang word by definiton.");
			System.out.println("3. History.");
			System.out.println("4. Add slang word.");
			System.out.println("5. Edit slang word.");
			System.out.println("6. Reset dictionary.");
			System.out.println("7. Random a slang word.");
			System.out.println("8. Slang Quiz.");
			System.out.print("Your choice: ");
			String option = sc.nextLine();
			switch(option) {
    	   case "1":
    		   printDefinition();
    		   break;
    	   case "2":  
    		   printSlangWord();
    		   break;
    	   case "3":
    		   viewHistory();
    		   break;
    	   case "4": 
    		 
    		   break;
    	   case "5": 
    		  
    		   break;
    	   case "6":
    		   
    		   break;
    	   case "7":
    		  
    		   break;
    	   case "8":
    		  
    		   break;
    	   case "9":
    		   
    		   break;
    	   default: 
    		   System.out.println("\n\tLua chon cua ban khong hop le.");
    		   break;
    	}
    	
		   System.out.println("\n\tBan co muon tiep tuc khong?(yes/no)");
    	   System.out.print("\t\t\t");
    	   
    	   repeat = sc.nextLine();
    	   System.out.println("\n");
	}while(repeat.equals("yes")==true);
}
	
	public static void main(String[] args) {
	    Menu();
	    sc.close();
	}
}
		
