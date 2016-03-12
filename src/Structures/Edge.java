package Structures;

public class Edge {
	private char c = '/';
	private Trie a;
	private Trie b;
	
	public Edge(Trie a, Trie b, char c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public Trie getA(){
		return a;
	}
	
	public Trie getB(){
		return b;
	}
	
	public char getC(){
		return c;
	}
	
	public void setChar(char c){
		this.c = c;
	}
}
