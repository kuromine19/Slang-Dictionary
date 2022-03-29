package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class SlangDictionary{
	
	public static String fileslang="D:/University/JAVA/SlangDictionary/SlangDictionary/Data/slang.txt";
	public static String filehistory="D:/University/JAVA/SlangDictionary/SlangDictionary/Data/history.txt";
	public static String filebackup="D:/University/JAVA/SlangDictionary/SlangDictionary/Data/backup.txt";
	public static ArrayList<String>historySearch=new ArrayList<String>();
	public static ArrayList<Word>tempSlangQuiz=new ArrayList<Word>();
	
	public static Scanner sc = new Scanner(System.in);
	
	public static class Word{
		public String slangword;
		public String definition;
		public List<String>definitions;
		
		Word(){
			
		}
		
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
    
    public static void resetHistory() {
    	try {
    		File file=new File(filehistory);
    		FileWriter fw=new FileWriter(file);
    		BufferedWriter bw = new BufferedWriter(fw);
    		fw.write("");
    		fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    	System.out.println("\n\t\tReset History successfully!");
    }
    
    public static void viewHistory() {
    	getHistory();
    	if(historySearch.isEmpty()) {
    		System.out.println("\n\t\t\tEmpty history!");
    		return;
    	}
    	System.out.println("\n\t\tHistory - Slang Words you have searched: \n");
    	for(String slangWord: historySearch) {
    		System.out.println("\t\t\t\t"+slangWord);
    	}
    	historySearch.clear();
    }
    
    public static List<String> searchSlangWord() {
    	System.out.print("\n\t\tType the slang word you wanna search: ");
    	String word=sc.nextLine();
    	word=word.toUpperCase();
    	updateHistoryFile(word);
    	List<String>def=null;
    	for(String i: map.keySet()) {
    		if(i.toUpperCase().equals(word)) {
    			def=map.get(i);
    		}
    	}
    	return def;
    }
    
    public static void findDefinition() {
    	List<String> res=searchSlangWord();
    	if(res==null){
    		System.out.println("\n\t\t\tNot Found.");
    		return;
    	}
    	System.out.println("\n\t\tDefinitions:");
    	for(String i:res) {
    		System.out.println("\t\t\t"+i);
    	}
    }
    
    public static ArrayList<Word> searchDefinition() {
    	System.out.print("\n\t\tType any definitions you wanna search: ");

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
    
    public static void findSlangWord() {
    	ArrayList<Word> res=searchDefinition();
    	if(res.isEmpty()){
    		System.out.println("\n\t\t\tNot Found.");
    		return;
    	}
    	System.out.println("\t\tSlang Word relating to the definition you have searched: ");
    	System.out.print("\n\t\tSlang Word");
    	System.out.println("\t\tDefinitions\n");
    	for(Word i:res) {
    		System.out.print("\t\t"+i.slangword);
    		System.out.println("\t\t\t"+i.definition);
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
    
    public static boolean checkSameSlangWord(Word word,ArrayList<Word>list) {
    	for(Word i:list) {
    		if(i.slangword==word.slangword) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public static void addSlangWord() {
    	System.out.print("\n\t\tType the slang word you wanna add: ");
    	String word=sc.nextLine();
    	word=word.toUpperCase();
    	boolean check=false;
    	for(String i:map.keySet()) {
    		if(i.toUpperCase().equals(word))check=true;
    	}
    	if(!check)
    	{
	    	System.out.print("\n\t\tType the definitions of the slang word: ");
	    	ArrayList<String>definitions=new ArrayList<String>();
	    	String def=null;
	    	def=sc.nextLine();
	    	definitions.add(def);
	    	String option="";
	    	do {
	    	def=null;
	    	if(option.equals("YES")) {
	    	def=sc.nextLine();
	    	definitions.add(def);
	    	}
	    	System.out.println("\n\t\tYou wanna add more definition?(yes/no)");
	    	System.out.print("\t\t\t\t");
	    	option=sc.nextLine();
	    	option=option.toUpperCase();
	    	switch(option) {
	    	case "YES":
	    		System.out.print("\n\t\tType another definition for the slang word: ");
	    		break;
	    	case "NO":
	    		break;
	    	default:
	    		System.out.println("\n\t\t\tInvalid option.\n");
	    	}
	    	}while(option.equals("YES")||(!option.equals("YES")&&!option.equals("NO")));
	    	Word newSlangWord=new Word(word,definitions);
	    	updateSlangFile(newSlangWord);
    	}
    	else System.out.println("\n\t\tThe slang word you wanna add is existed!");
    }
    
    public static void deleteSlangWord() {
    	System.out.print("\n\t\tType the word you wanna delete: ");
    	String word=sc.nextLine();
    	word=word.toUpperCase();
    	String option=null;
    	boolean check=false;
    	for(String i:map.keySet()) {
    		if(i.toUpperCase().equals(word))check=true;
    	}
    	if(check) {
	    	do {
	    	System.out.println("\n\t\tAre you sure you wanna delete this slang word?(yes/no)");
	    	System.out.print("\t\t\t\t");
	    	option=sc.nextLine();
	    	option=option.toUpperCase();
	    	switch(option) {
	    	case "YES":
		    	map.remove(word);
		    	updateSlangFile();
		    	System.out.println("\n\t\tThis slang word has been deleted successfuly.");
		    	break;
	    	case "NO":
	    		break;
	    	default:
	    		System.out.println("\n\t\t\tInvalid option.\n");
	    		option=null;
	    	}
	    	}while(option==null);
	    	
	    	}
	    	else System.out.println("\n\t\t\tNot Found.");
    	}

    public static void editSlangWord() {
    	System.out.print("\n\t\tType the slang word you wanna edit: ");
    	String word=sc.nextLine();
    	ArrayList<String>definitions=new ArrayList<String>();
    	word=word.toUpperCase();
    	String option=null;
    	boolean check=false;
    	for(String i:map.keySet()) {
    		if(i.toUpperCase().equals(word))check=true;
    	}
    	if(check) {
	    	do {
	    	System.out.println("\n\t\tAre you sure you wanna edit this slang word?(yes/no)");
	    	System.out.print("\t\t\t\t");
	    	option=sc.nextLine();	 
	    	option=option.toUpperCase();
	    	switch(option) {
	    	case "YES":   	
		    	map.remove(word);
		    	System.out.print("\t\tType new definition for the slang word: ");
		    	String op="";
		    	String def=null;
		    	def=sc.nextLine();
		    	definitions.add(def);
		    	do {
		    	def=null;
		    	if(op.equals("YES")) {
		    	def=sc.nextLine();
		    	definitions.add(def);
		    	}
		        System.out.println("\n\t\tYou wanna add more definitions?(yes/no)");
		        System.out.print("\t\t\t\t");
		        op=sc.nextLine();
		        op=op.toUpperCase();
		        switch(op) {
		        case "YES":
		        	System.out.print("\t\tType another definition for the slang word: ");
		        	break;
		        case "NO":
		        	break;
		        default:
		        	System.out.println("\n\tInvalid option.\n");
		        	break;
		        }
		        }while(op.toUpperCase().equals("YES")||(!op.toUpperCase().equals("YES")&&!op.toUpperCase().equals("NO")));
		        map.put(word,definitions);
		        updateSlangFile();
		        System.out.println("\n\t\tThis slang word has been edited successfuly.");
		        break;
	    	case "NO":
	    		break;
	    	default:
	    		System.out.println("\n\t\t\tInvalid option.\n");
	    		option=null;
	    		break;
	    	}
	    	}while(option==null);
	    	}
    	else System.out.println("\n\t\t\tNot Found.");
    }
    
    public static void resetSlangDictionary() {
    	try
		{
    		map.clear();
			File file = new File(filebackup);
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
	        System.out.println("\n\t\tReset dictionary successfuly.");
	    }
	    catch (Exception ex)
	    {
	        System.out.println("ERROR"+ex);
	    }
    	
    	updateSlangFile();
    }
    
    public static Word getRandomSlangWord() {
    	Word word=null;
    	Random random=new Random();
    	int index=random.nextInt(map.size());
    	int count=0;
    	for(String i: map.keySet()) {
    		if(count==index) {
    			word=new Word(i,map.get(i));
    		}
    		count++;
    	}
    	return word;
    }
    
    public static void printrandomSlangWord() {
    	Word randomSlangWord=getRandomSlangWord();
    	if(randomSlangWord==null) {
    		System.out.println("\t\t\t\tERROR");
    	}
    	else {
    		System.out.println("\n\t\tRandom Slang Word:");
    		System.out.println("\n\t\t\t"+randomSlangWord.slangword);
    		System.out.println("\n\t\tDefinitions:");
    		for(String i:randomSlangWord.definitions){
    			System.out.print("\n\t\t\t"+i);
    		}
    	}
    	System.out.print("\n");
    }
    
    public static void quizSlangWord() {
    	Word randomSlangWord=getRandomSlangWord();
    	tempSlangQuiz.add(randomSlangWord);
    	Word temp=new Word();
    	int k=0;
    	do {
    		temp=getRandomSlangWord();
    		if(!checkSameSlangWord(temp,tempSlangQuiz)) {
    			k++;
    			tempSlangQuiz.add(temp);
    		}
    	}while(k<3);
    	Collections.shuffle(tempSlangQuiz);
    	char a='A';
    	System.out.println("\n\t\t\t\tQUIZ");
    	System.out.println("\n\t\t\t\t"+randomSlangWord.slangword);
    	for(Word i:tempSlangQuiz) {
    		System.out.println("\n\t\t"+a+". "+i.definitions);
    		a=(char)(((int)a)+1);
    	}
    	String choice=null;
    	boolean check=false;
    	do {
    	System.out.print("\n\t\tYour Answer: ");
    	choice=sc.nextLine();
    	choice=choice.toUpperCase();
    	switch(choice) {
    	case "A":
    		if(tempSlangQuiz.get(0).definitions==randomSlangWord.definitions)check=true;
    		break;
    	case "B":
    		if(tempSlangQuiz.get(1).definitions==randomSlangWord.definitions)check=true;
    		break;
    	case "C":
    		if(tempSlangQuiz.get(2).definitions==randomSlangWord.definitions)check=true;
    		break;
    	case "D":
    		if(tempSlangQuiz.get(3).definitions==randomSlangWord.definitions)check=true;
    		break;
    	default:
    		System.out.println("\n\t\tYour answer is invalid. Please try again.");
    		break;
    	}
    	}while(!choice.equals("A")&&!choice.equals("B")&&!choice.equals("C")&&!choice.equals("D"));
    	if(check)System.out.println("\n\t\t\tHurray.. Correct! You are so intelligent!");
    	else System.out.println("\n\t\t\tBetter next time..");
    	tempSlangQuiz.clear();
    }
    
    public static void quizDefinition() {
    	Word randomSlangWord=getRandomSlangWord();
    	tempSlangQuiz.add(randomSlangWord);
    	Word temp=new Word();
    	int k=0;
    	do {
    		temp=getRandomSlangWord();
    		if(!checkSameSlangWord(temp,tempSlangQuiz)) {
    			k++;
    			tempSlangQuiz.add(temp);
    		}
    	}while(k<3);
    	
    	Collections.shuffle(tempSlangQuiz);
    	char a='A';
    	System.out.println("\n\t\t\t\tQUIZ");
    	System.out.println("\n\t\t\t"+randomSlangWord.definitions);
    	for(Word i:tempSlangQuiz) {
    		System.out.println("\n\t\t"+a+". "+i.slangword);
    		a=(char)(((int)a)+1);
    	}
    	String choice=null;
    	boolean check=false;
    	do {
    	System.out.print("\n\t\tYour Answer: ");
    	choice=sc.nextLine();
    	choice=choice.toUpperCase();
    	switch(choice) {
    	case "A":
    		if(tempSlangQuiz.get(0).slangword==randomSlangWord.slangword)check=true;
    		break;
    	case "B":
    		if(tempSlangQuiz.get(1).slangword==randomSlangWord.slangword)check=true;
    		break;
    	case "C":
    		if(tempSlangQuiz.get(2).slangword==randomSlangWord.slangword)check=true;
    		break;
    	case "D":
    		if(tempSlangQuiz.get(3).slangword==randomSlangWord.slangword)check=true;
    		break;
    	default:
    		System.out.println("\n\t\t\tYour answer is invalid. Please try again.");
    		break;
    	}
    	}while(!choice.equals("A")&&!choice.equals("B")&&!choice.equals("C")&&!choice.equals("D"));
    	if(check)System.out.println("\n\t\tHurray.. Correct! You are so intelligent.");
    	else System.out.println("\n\t\tBetter next time..");
    	tempSlangQuiz.clear();
    }
    
	public static void Menu() {
		String repeat=null; 
		do
		{
			//map.clear();
			getSlangWord();
			clearConsoleScreen();
			repeat=null;
			System.out.println("\t\t\t\t\tMENU\n");
			System.out.println("\t\t\t1. Find definitions by slang word.");
			System.out.println("\t\t\t2. Find slang word by definiton.");
			System.out.println("\t\t\t3. History.");
			System.out.println("\t\t\t4. Reset History.");
			System.out.println("\t\t\t5. Add slang word.");
			System.out.println("\t\t\t6. Edit slang word.");
			System.out.println("\t\t\t7. Delete slang word.");
			System.out.println("\t\t\t8. Reset dictionary.");
			System.out.println("\t\t\t9. Random a slang word.");
			System.out.println("\t\t\t10. Slang Quiz.");
			System.out.println("\t\t\t11. Definition Quiz.");
			System.out.print("\n\t\t\tYour choice: ");
			String option = sc.nextLine();
			switch(option) {
    	   case "1":
    		   findDefinition();
    		   break;
    	   case "2":  
    		   findSlangWord();
    		   break;
    	   case "3":
    		   viewHistory();
    		   break;
    	   case "4":
    		   resetHistory();
    		   break;
    	   case "5": 
    		   addSlangWord();
    		   break;	
    	   case "6": 
    		   editSlangWord();
    		   break;
    	   case "7":
    		   deleteSlangWord();
    		   break;
    	   case "8":
    		   resetSlangDictionary();
    		   break;
    	   case "9":
    		   printrandomSlangWord();
    		   break;
    	   case "10":
    		   quizSlangWord();
    		   break;
    	   case "11":
    		   quizDefinition();
    		   break;
    	   default: 
    		   System.out.println("\n\t\t\tYour choice is invalid.");
    		   break;
    	}
		do {
		   System.out.println("\n\t\tDo you wanna continue?(yes/no)");
    	   System.out.print("\t\t\t");
    	  
    	   repeat = sc.nextLine();
    	   repeat=repeat.toUpperCase();
    	   System.out.print("\n");
    	   if(!repeat.equals("YES")&&!repeat.equals("NO"))
    		   System.out.println("\t\t\tInvalid option.\n");
		}while(!repeat.equals("YES")&&!repeat.equals("NO"));   
	}while(repeat.equals("YES"));
	System.out.print("\n\t\tSEE YOU LATER..");
}
	
	public static void main(String[] args) {
	    Menu();
	    historySearch=null;
	    map=null;
	    tempSlangQuiz=null;
	    sc.close();
	}
}
