package coding;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Permutation {
	
	private List<String> results = new ArrayList<>();
	public void permutaion(String string, String result) {
		if(string.length() == 0) {
			return;
		}
		if(string.length() == 1) {
			result += string;
			results.add(result);
			return;
		}
		int length = string.length();
		for(int i=0; i<length; i++) {
			result += string.substring(i, i+1);
			String rest = new String();
			if(i == 0) {
				rest = string.substring(1);
			}
			else {
				if(i == length -1) {
					rest = string.substring(0, length-1);
				}
				else {
					rest = string.substring(0, i) + string.substring(i+1);
				}
			}
			permutaion(rest , result);
			result = result.substring(0, result.length()-1);
		}
	}
	public void combination(String string) {
		char[] cs = string.toCharArray();
		int length = cs.length;
		int m=1;
//		boolean[] marked = new boolean[length];
		while(m <= length) {
			combination(cs, m, new StringBuffer(),0);
			m++;
		}
	}
	public void combination(char[] cs, int rest, StringBuffer stringBuffer, int begin) {
		if(rest == 0) {
			results.add(stringBuffer.toString());
			return;
		}
		for(int i=begin; i<cs.length; i++) {
//			if(marked[i]) {
//				continue;
//			}
//			marked[i] = true;
			stringBuffer.append(cs[i]);
			combination(cs, rest-1, stringBuffer, i+1);
			stringBuffer.deleteCharAt(stringBuffer.length()-1);
//			marked[i] = false;
		}
		
	}

	public static void main(String[] arg0) {
		Permutation permutation = new Permutation();
//		permutation.permutaion("abcd", new String());
		permutation.combination("abcd");
		for(String string : permutation.results) {
			System.out.println(string);
		}
	}
}
