package org.shirk.pal;

public class PalindromeTester {
	
	public static boolean isPalindrome(String input) {
		int length = input.length();
		
		if (length == 0 || length == 1) { 
			return true;
		}
		
		if (input.charAt(0) == input.charAt(length - 1)) {
			return isPalindrome(input.substring(1, length - 1));
		} else {
			return false;
		}
		
	}

}
