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
    	System.out.print("\nType the slang word you wanna search: ");
    	String word=sc.nextLine();
    	word=word.toUpperCase();
    	updateHistoryFile(word);
    	List<String>def=null;
    	for(String i: map.keySet()) {
    		if(i.toUpperCase().compareTo(word)==0) {
    			def=map.get(i);
    		}
    	}
    	return def;
    }
    
    public static void printDefinition() {
    	List<String> res=searchSlangWord();
    	if(res==null){
    		System.out.println("\nNot Found.");
    		return;
    	}
    	System.out.println("\nDefinitions:");
    	for(String i:res) {
    		System.out.println("\t"+i);
    	}
    }
    
    public static ArrayList<Word> searchDefinition() {
    	System.out.print("\nType any definitions you wanna search: ");
    	
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
    	if(res.isEmpty()){
    		System.out.println("\nNot Found.");
    		return;
    	}
    	System.out.println("Slang Word relating to the definition you have searched: ");
    	System.out.print("\nSlang Word");
    	System.out.println("\tDefinitions");
    	for(Word i:res) {
    		System.out.print(i.slangword);
    		System.out.println("\t\t"+i.definition);
    	}
    	
    }
    public static void updateSlangFile() {
    	try {
    		File file=new File(fileslang);
    		FileWriter fw=new FileWriter(file);
    		BufferedWriter bw = new BufferedWriter(fw);
    		for(String i:map.keySet()) {
    			fw.write(i+"`");
    			int j=0;
        		for(String k:map.get(i))
        		{
        			if(j==0)fw.write(k);
        			else fw.write("|"+k);
        			j++;
        		}
        		fw.write("\n");
    		}
    		
    		fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    }
    public static void updateSlangFile(Word newSlangWord) {
    	try {
    		File file=new File(fileslang);
    		FileWriter fw=new FileWriter(file,true);
    		BufferedWriter bw = new BufferedWriter(fw);
    		fw.write("\n"+newSlangWord.slangword+"`");
    		int j=0;
    		for(String i:newSlangWord.definitions)
    		{
    			if(j==0)fw.write(i);
    			else fw.write("|"+i);
    			j++;
    		}
    		fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    }
    
    public static void addSlangWord() {
    	System.out.print("Type the slang word you wanna add: ");
    	String word=sc.nextLine();
    	word=word.toUpperCase();
    	System.out.print("Type the definitions of the slang word: ");
    	ArrayList<String>definitions=new ArrayList<String>();
    	String repeat=null;
    	do {
    	String def=null;
    	def=sc.nextLine();
    	definitions.add(def);
    	System.out.println("You wanna add more definition?(yes/no)");
    	String option=sc.nextLine();
    	switch(option) {
    	case "yes":
    		repeat="1";
    		System.out.print("Type another definition for the slang word: ");
    		break;
    	default:
    		repeat="0";
    		break;		
    	}
    	}while(repeat=="1");
    	
    	Word newSlangWord=new Word(word,definitions);
    	updateSlangFile(newSlangWord);
    	
    }
    
    public static void deleteSlangWord() {
    	System.out.print("Type the word you wanna delete: ");
    	String word=sc.nextLine();
    	word=word.toUpperCase();
    	int k=0;
    	for(String i:map.keySet()) {
    		if(i.toUpperCase().equals(word)) {
    			k=1;
    		}
    	}
    	if(k==0)
    		System.out.println("Not Found.");
    	else map.remove(word);
    	updateSlangFile();
    	
    }

    public static void editSlangWord() {
    	System.out.print("Type the slang word you wanna edit: ");
    	String word=sc.nextLine();
    	word=word.toUpperCase();
    	
    }
    
	public static void Menu() {
		String repeat=null; 
		do
		{
			getSlangWord();
			clearConsoleScreen();
			repeat=null;
			System.out.println("\t\tMENU");
			System.out.println("1. Find definitions by slang word.");
			System.out.println("2. Find slang word by definiton.");
			System.out.println("3. History.");
			System.out.println("4. Add slang word.");
			System.out.println("5. Edit slang word.");
			System.out.println("6. Delete slang word.");
			System.out.println("7. Reset dictionary.");
			System.out.println("8. Random a slang word.");
			System.out.println("9. Slang Quiz.");
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
    		   addSlangWord();
    		   break;
    	   case "5": 
    		   editSlangWord();
    		   break;
    	   case "6":
    		   deleteSlangWord();
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
		

		
