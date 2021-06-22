package com.chengchw.SHEA.Model;

import java.util.Arrays;
import java.util.List;




public class Attribute {
	
	public List<String> States = Arrays.asList("Alaska", "Alabama", "Arkansas", "American Samoa", "Arizona", "California", "Colorado", "Connecticut", 
			"District of Columbia", "Delaware", "Florida", "Georgia", "Guam", "Hawaii", "Iowa", "Idaho", "Illinois", "Indiana", "Kansas", "Kentucky", 
			"Louisiana", "Massachusetts", "Maryland", "Maine", "Michigan", "Minnesota", "Missouri", "Mississippi", "Montana", "North Carolina", 
			"North Dakota", "Nebraska", "New Hampshire", "New Jersey", "New Mexico", "Nevada", "New York", "Ohio", "Oklahoma", 
			"Oregon", "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", 
			"Utah", "Virginia", "Virgin Islands", "Vermont", "Washington", "Wisconsin", "West Virginia", "Wyoming");
	
	public List<String> Brands = Arrays.asList("Samsung","Dell","HP","Apple","LG","Canon","Nikon","Sony","SanDisk","Beats","GoPro","Nintendo",
			"Microsoft","Other");
	
	public List<String> DamageRate = Arrays.asList("0%","10%","20%","30%","40%","50%");
	
	public List<String> Year = Arrays.asList("2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010");
	
	public Attribute() {}
	
	
//	public Attribute(int thisYear) {
//		
//		for (int i = 0; i <= 20; i++) {
//			
//			String inputYear = Integer.toString(thisYear - i);
//			this.Year.add(inputYear);
//		}
//		
//		
//	}
}
