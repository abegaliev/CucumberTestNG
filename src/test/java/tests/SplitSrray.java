package tests;

import java.util.Arrays;
import java.util.HashMap;

public class SplitSrray {

	public static void main(String[] args) {
		HashMap [] map = parseString("I am doing all right. Hi adf dsf");
		for(int i=0; i<map.length; i++)
		System.out.println(map[i]);
	}
	
	public static HashMap[] parseString(String str){ 
		
		String[] arrSent = str.trim().split("[.]");
		HashMap[] arrMap = new HashMap[arrSent.length];

		for(int i=0; i<arrSent.length; i++) {
			
			String[] arr = arrSent[i].trim().split(" ");
			HashMap map = new HashMap();
			for(int n=0; n<arr.length; n++) {
				map.put(n+1, arr[n]);
			}
			arrMap[i] = map;
		}
	
		return arrMap;
	}
	
	
	
	
}
