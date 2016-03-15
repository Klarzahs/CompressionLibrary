package main;

import Algorithms.RadixSort;
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
	 * - Tries: 
	 * 		- static: Trie.createExample();
	 * 		- dynamic: Trie t = new Trie(); t.createTrieFromStrings(String[] s);
	 * 		- print: Trie.printTrie(Trie t);
	 * 		- paint: t.drawTrie(getNH());
	 */
	
	private NodeHandler nh;
	public Main(){
		//System.out.println(RadixSort.countingSort("uehfiuajsfjhbeoujbfiuzvaibouwjbraizfbksjfiazwkr", 26));
		RadixSort.print(RadixSort.iterativeSort(new String[]{"which", "witch", "winds", "white", "wiser","water"}));
	}
	
	public void testTrie(){
		MyFrame frame = new MyFrame(this);
		nh = new NodeHandler(this);
		
		Trie root = new Trie();
		String[] strings = {"barfoo", "foobar", "foo", "bar", "barfuss", "fool", "hallo"};

		root.createTrieFromStrings(strings);
		root.drawTrie(nh);
		
		root.deleteTrie("fool", root);
		
		root.drawTrie(nh);
	}
	
	public NodeHandler getNH(){
		return nh;
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
