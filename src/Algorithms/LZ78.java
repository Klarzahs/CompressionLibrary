package Algorithms;

import java.util.ArrayList;

public class LZ78 {
	private static char[] chars;
	private static ArrayList<DEntry> entries = new ArrayList<DEntry>();

	public static final String STRINGA = "ANNASNASSEANANAS";			// test strings
	public static final String STRINGB = "BADADADABAAB";
	
	public static ArrayList<DEntry> compress(String s){
		chars = new char[s.length()];
		for(int i = 0; i < chars.length; i++){
			chars[i] = s.charAt(i);
		}
		
		entries.add(new DEntry(0, '/'));
		entries.add(new DEntry(0, chars[0]));
		int i = 1;
		while(i < chars.length){
			DEntry e = getLongestEntry(i);
			if(e == null){
				entries.add(new DEntry(0, chars[i]));
				i++;
			}else{
				entries.add(new DEntry(entries.indexOf(e), chars[i + getChars(e).length]));
				i += getChars(e).length + 1;
			}
		}
		
		return entries;
	}
	
	public static String decompress(ArrayList<DEntry> entries){
		String s;
		
		ArrayList<Character> chars = new ArrayList<Character>();
		chars.add(entries.get(1).c);
		
		for(int i = 2; i < entries.size(); i++){
			DEntry e = entries.get(i);
			if(e.r == 0){
				chars.add(e.c);
			}else{
				addRecursively(chars, entries.get(i));
			}
		}
		s = toString(chars);
		
		System.out.println(s);
		return s;
	}
	
	private static void addRecursively(ArrayList<Character> chars, DEntry e){
		if(e.r == 0 ){
			if(e.c != '/')
				chars.add(e.c);
		}else{
			addRecursively(chars, entries.get(e.r));
			chars.add(e.c);
		}
	}
	
	private static String toString(ArrayList<Character> chars){
		char[] c = new char[chars.size()];
		for(int i = 0; i < chars.size(); i++){
			c[i] = chars.get(i);
		}
		return (new String(c));
	}
	
	private static DEntry getLongestEntry(int charI){
		if(charI >= chars.length - 1)
			return null;
		
		DEntry ret = null;
		int maxLength = 0;
		for(int j = 0; j < entries.size(); j++){
			char[] sub = getChars(entries.get(j));
			String entrystr = new String(sub);
			String substr;
			if(entrystr.length() + charI >= chars.length)
				substr = new String(chars).substring(charI, chars.length);
			else
				substr = new String(chars).substring(charI, entrystr.length() + charI);
			if(entrystr.equals(substr)){
				int length = entrystr.length();
				if(length > maxLength){
					maxLength = length;
					ret = entries.get(j);
				}
			}
		}
		
		return ret;
	}
	
	private static char[] getChars(DEntry entry){
		if(entry.r == 0)
			return new char[] {entry.c};
		// get referred array and concatenate the char to it
		char[] old = getChars(entries.get(entry.r));
		char[] ret = new char[old.length + 1];
		System.arraycopy(old, 0, ret, 0, old.length);
		ret[old.length] = entry.c;
		return ret;
	}
	
	private static class DEntry{
		int r;
		char c;
		
		public DEntry(int r, char c){
			this.r  = r;
			this.c = c;
			System.out.println(toString());
		}
		
		@Override
		public String toString(){
			return "("+r+", "+c+")";
		}
	}
}
