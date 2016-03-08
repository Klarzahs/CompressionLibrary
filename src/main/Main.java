package main;

public class Main {

	/*
	 * A library to test and implement some algorithms from the lecture 
	 * "Algorithmen zur String-Verarbeitung und Techniken zur Datenkompression"
	 * at the RWTH Aachen
	 * Author: Thomas Schemmer
	 */
	public static void main(String[] args) {
		LZ77.decompress(LZ77.compress(LZ77.STRINGA, 2, 2));
	}
}
