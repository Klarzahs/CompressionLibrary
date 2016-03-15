package Structures;

public class Alphabet {
	public static int R = 26; //size
	public static int offset = 'a';
	
	public static int getIndex(char c){
		return ((int) c) - offset;
	}
	
	public static char getChar(int i){
		return (char) (i + offset);
	}
}


