package com.jcg.csv2excel;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvUtil {
	
	static Map<String,String> sapMap = null;
	public static void setSapId()
	{
		sapMap = new HashMap<String, String>();
		sapMap.put("Rakesh Yadav4", "51609621");
		sapMap.put("Durgaraju Gayathri", "40178192");
		sapMap.put("Abhishek K", "51728420");
		sapMap.put("Sharma, Abhilasha", "51746319");
		sapMap.put("Payasi, Vikas", "51746843");
		sapMap.put("Jyoti Sinha", "51669402");
		sapMap.put("Nair, Dilip Sasidharan", "51770409");
		sapMap.put("Jetty, Amith", "51769144");
		sapMap.put("Mamatha, Patthipati", "51564457");
		sapMap.put("Shiney K", "51770390");
		sapMap.put("Leena, Lakshmi", "51742009");
		sapMap.put("Singh, Rajesh Kumar", "51696943");
		sapMap.put("Shivangi Agrawal", "51634068");
		sapMap.put("Ritika Pandhi", "51681385");
		sapMap.put("Nathula, Sravani", "51775017");
		sapMap.put("Ashok, Eluri", "51730109");
		
		sapMap.put("Deepak C", "51682390");
		sapMap.put("Sudheer Kumar, Souri", "51681036");
		sapMap.put("Lokanadham, Modhugapalyam", "51329406");
		sapMap.put("Rajkumar ChokanadarPauldurai", "51611647");
		
		sapMap.put("Ainampudi, Aruna", "51739054");
		sapMap.put("Abhishek Mittal", "40135122");
		
		sapMap.put("Kapoor, Anu", "51334038");
		sapMap.put("Capoor, Nehaa", "51744432");
		
	
		
	}
	public static String getSapId(String name)
	{
		return sapMap.get(name);
	}

	
	public static void sortCsvData(List<String[]> csvDataList) {
		Collections.sort(csvDataList, new Comparator<String[]>(){
				
		    // Used for sorting in ascending order of story ID
		    public int compare(String[] a, String[] b) 
		    {
		    	int flagvalue= 0;
		    	try{
		    	String storyIdA = a[2].split(":")[0];
		    	int first = Integer.parseInt(storyIdA.substring(2, storyIdA.length()));
		    	String storyIdB = b[2].split(":")[0];
		    	int second = Integer.parseInt(storyIdB.substring(2, storyIdB.length()));

		    	flagvalue = first - second; 
		    }
		    catch(Exception ex)
		    {
		    	flagvalue = 0;
		    }
		    	
		    	return flagvalue;
		    } 
		} );
	}
	
}
