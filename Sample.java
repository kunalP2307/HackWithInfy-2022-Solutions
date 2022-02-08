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
		
		int minPlus = temp(arr[0],Integer.parseInt(arr[1]), Integer.parseInt(binString,2));
		
		System.out.println("Min No of Pluses Required : "+minPlus);
		
		return minPlus;
	}
	
	public static int temp(String expression,int y,int plusSequence) {
		
		int n = Integer.toBinaryString(plusSequence).length();
	    
		Set<Integer> setOfPlusCount = new HashSet<Integer>();
		
		for(int i=plusSequence; i>=1; i--) {
			
			String temp = ""+expression.charAt(0);
			String binSeq = Integer.toBinaryString(i);
			//System.out.println(binSeq);
			int x = 1;
			int plusCount = 0;
			
			for(int j=-1; j<binSeq.length()-1; j++) {
				
				if(binSeq.length() < n) {
					binSeq = '0'+binSeq;
				}
				
				if(binSeq.charAt(j+1) == '1') {
					temp += "+";
					temp += expression.charAt(x);
					plusCount ++;
					x++;
				}
				else if(binSeq.charAt(j+1) == '0') {
					temp += expression.charAt(x);
					x++;
				}					
			}
			
			if(checkSum(temp, y)) 
				setOfPlusCount.add(plusCount);
						
		}
		if(setOfPlusCount.size() == 0)
			return -1;
		return Collections.min(setOfPlusCount);		
	}
	
	public static boolean checkSum(String expression,int y) {
		
		int sum = 0;
		System.out.println("Expression : "+expression);
		String nums[] = expression.split("\\+");
		for(int i=0; i<nums.length; i++)
			sum += toInt(nums[i]);
		
		System.out.println("Sum : "+sum);
		if(sum == y)
			return true;
		
		return false;
			
	}
	
	public static int toInt(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String Args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		
		Sample s = new Sample();
		String expression;
		
		while(true) {
			expression = sc.next();
			s.checkNoOfPluses(expression);	
			
			System.out.print("Do You want to continue [y/n] : [1/0] : ");
			if(sc.nextInt() != 1)
				break;
			
		}
	}

}

