package com.spendsense.splitx.util;

public class GroupCodeGenerator {
	
	private static String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public  String generateGroupCode() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < 5; i++) {
			int x = (int) (Math.random() * 10000)%36;
			str.append(LETTERS.charAt(x ));
		}
		return str.toString();
	}
	
	
}
