import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/*
 * Given an equation "x-y", for example, "111-12".
 *  you need to add pluses inside x to make the equation
 *   correct. In our example "111-12". we can add one plus 
 *   "11+1-12" and the equation becomes correct. 
 *   You need to find the minimum number of pluses to add to x to make the equation correct. 
 *   If there is no answer print -1.
 */

public class Sample {
	
	public static int checkNoOfPluses(String expression) {
		
		String arr[] = expression.split("=");
		
		int xLen = arr[0].length();
		int yLen = arr[1].length();
		
	
		
		if(xLen == 1 && yLen == 1) {
			if(toInt(arr[0]) == toInt(arr[1]))
				return 0;
			else 
				return -1;
		}
		
		String binString = "";
		for(int i=0; i<xLen-1; i++)
			binString += '1';
		
		String newExpression = "";
		
		int minPlus = temp(arr[0],Integer.parseInt(arr[1]), 3);
		System.out.println("count"+minPlus);
		return -1;
	}
	
	public static int temp(String expression,int y,int plusSequence) {
		
		int n = Integer.toBinaryString(plusSequence).length();
	    
		Set<Integer> setOfPlusCount = new HashSet<Integer>();
		
		for(int i=plusSequence; i>=1; i--) {
			
			String temp = ""+expression.charAt(0);
			String binSeq = Integer.toBinaryString(i);
			System.out.println(binSeq);
			int x = 1;
			int plusCount = 0;
			
			for(int j=-1; j<binSeq.length()-1; j++) {
				
				if(binSeq.length() < n) {
					binSeq = '0'+binSeq;
					plusCount ++;
				}
				
				if(binSeq.charAt(j+1) == '1') {
					temp += "+";
					temp += expression.charAt(x);
					x++;
				}
				else if(binSeq.charAt(j+1) == '0') {
					temp += expression.charAt(x);
					x++;
				}			
					
			}
			
			int sum = 0;
			String[] arr = temp.split("\\+");
			System.out.print("length "+arr.length);
			for(int k=0; k<arr.length; k++) {
				sum += Integer.parseInt(arr[k]);
			}
		
			
			if(sum == y) 
				setOfPlusCount.add(plusCount);
			
			
		}
		if(setOfPlusCount.size() == 0)
			return -1;
		return Collections.min(setOfPlusCount);		
	}
	
	public static String addPluses(String expression,int plusSequence) {
		
		for(int i=plusSequence; i>=1; i--) {
			
			String binSeq = Integer.toBinaryString(i);
			
			String temp = ""+expression.charAt(0);
			int x = 1;
			for(int j=0; j<binSeq.length(); j++) {
				
				if(binSeq.charAt(j) == '1')
					temp += '+';
				else if(binSeq.charAt(j) == '0') {
					temp += expression.charAt(x);
					x++;
				}
				else {
					temp += expression.charAt(x);
					x++;
				}	
			}	
			System.out.println(temp);
		}
		return "";		
	}
	
	
	public static int toInt(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String Args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		String expression = sc.next();
		
		Sample s = new Sample();
		System.out.print(s.checkNoOfPluses(expression));
		
		
	}

}

