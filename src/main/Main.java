package main;

import java.io.File;

import Algorithms.LZ78;
import Structures.NodeHandler;
import Structures.Trie;

public class Main {

	/*
	 * A library to test and implement some algorithms from the lecture 
	 * "Algorithmen zur String-Verarbeitung und Techniken zur Datenkompression"
	 * at the RWTH Aachen
	 * Author: Thomas Schemmer
	 * Example usage: 
	 * - algorithms: 
	 * 		- LZ77.decompress(LZ77.compress(LZ77.STRINGA, 2, 2));
	 * 		- CountingSort: RadixSort.countingSort(String, 26)
	 * 		- it. Radix sort: RadixSort.iterativeSort(new String[]{"which", "witch", "winds", "white", "wiser","water"})		bugged
	 * - Tries: 
	 * 		- static: Trie.createExample();
	 * 		- dynamic: Trie t = new Trie(); t.createTrieFromStrings(String[] s);
	 * 		- print: Trie.printTrie(Trie t);
	 * 		- paint: t.drawTrie(getNH());
	 */
	
	private NodeHandler nh;
	public Main(){
		LZ78.decompress(LZ78.compress(new File("C:/Users/Thomas/workspace/LZ77/src/testfiles/lorem.txt")));
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
		
		frame.repaint();
	}
	
	public NodeHandler getNH(){
		return nh;
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
