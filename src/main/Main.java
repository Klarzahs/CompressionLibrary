package main;

import Structures.NodeHandler;
import Structures.Trie;

public class Main {

	/*
	 * A library to test and implement some algorithms from the lecture 
	 * "Algorithmen zur String-Verarbeitung und Techniken zur Datenkompression"
	 * at the RWTH Aachen
	 * Author: Thomas Schemmer
	 * Example usage: 
	 * - algorithms: LZ77.decompress(LZ77.compress(LZ77.STRINGA, 2, 2));
	 * - Tries: Trie.createExample();
	 */
	public static void main(String[] args) {
		new Main();
	}
	
	private NodeHandler nh;
	public Main(){
		MyFrame frame = new MyFrame(this);
		nh = new NodeHandler(this);
		
		Trie root = new Trie();
		String[] strings = {"barfoo", "foobar", "foo", "bar", "barfuss", "fool"};

		for(int i = 0; i < strings.length; i++){
			Trie temp = root;
			for(int j = 0; j < strings[i].length(); j++){
				temp = temp.addChar(strings[i].charAt(j));
			}
			temp.setMarked();
		}
		
		root.drawTrie(nh);
	}
	
	public NodeHandler getNH(){
		return nh;
	}
}
