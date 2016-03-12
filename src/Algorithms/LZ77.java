package Algorithms;

import java.util.ArrayList;

public class LZ77 {
	private static char[] chars;
	private static int maxD, maxL;
	private static ArrayList<Triplet> triplets = new ArrayList<Triplet>();

	public static final String STRINGA = "ANNASNASSEANANAS";			// test strings
	public static final String STRINGB = "BADADADABAAB";

	/*
	 * compresses the given string to an array list of triplets
	 * iterates over each character and checks if a substring has already occurred before
	 * if so, encode it with a triplet
	 * Explanation at https://en.wikipedia.org/wiki/LZ77_and_LZ78
	 */
	public static ArrayList<Triplet> compress(String s, int maxDistance, int maxLength){
		maxD = maxDistance;
		maxL = maxLength;

		chars = new char[s.length()];
		for(int i = 0; i < chars.length; i++){
			chars[i] = s.charAt(i);
		}

		triplets.add(new Triplet(0, 0, chars[0]));

		int i = 1;
		while(i < chars.length){
			ArrayList<Integer> distances = getDistances(i);
			ArrayList<Integer> lengths;
			if(distances.isEmpty()){
				triplets.add(new Triplet(0, 0, chars[i]));
			}else{
				lengths = getLengths(i, distances);
				int maxL = 0;
				for(int l = 0; l < lengths.size(); l++){
					if(lengths.get(l) >= lengths.get(maxL)){
						maxL = l;
					}
				}
				triplets.add(new Triplet(distances.get(maxL), lengths.get(maxL), chars[i+lengths.get(maxL)]));
				i += lengths.get(maxL);
			}
			i++;
		}
		return triplets;
	}
	
	/*
	 * Helper function to get each possible distances
	 * (pre-occurrences of the character at position pos)
	 */
	private static ArrayList<Integer> getDistances(int pos){
		int distance = 0;
		ArrayList<Integer> distances = new ArrayList<Integer>();
		while(distance < maxD && (pos - distance > 0 && (distance + pos) < chars.length)){
			distance++;
			if(chars[pos - distance] == chars[pos]){
				distances.add(distance);
			}
		}
		return distances;
	}

	/* 
	 * Helper function to get all lengths for each given distance
	 */
	private static ArrayList<Integer> getLengths(int pos, ArrayList<Integer> distances){
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		for(int i = 0; i < distances.size() ; i++){
			lengths.add(substringEqualAmount(pos, distances.get(i)));
		}
		return lengths;
	}

	/*
	 * Helper function to get the amount of equal characters,
	 * starting from (position-back)
	 */
	private static int substringEqualAmount(int pos, int back){
		int amount = 0;
		while(true){
			boolean check = chars[pos - back + amount] == chars[pos + amount];
			if(!check || amount > maxL){
				break;
			}
			amount++;
		}
		return amount;
	}
	
	/*
	 * return the given compressed string
	 * iterates over all triplets and creates characters according to 
	 * compression information
	 */
	public static String decompress(ArrayList<Triplet> triplets){
		char[] chars = new char[getCharLength(triplets)];
		int charIndex = 0;
		for(int i = 0; i < triplets.size(); i++){
			Triplet t = triplets.get(i);
			if(t.d == 0){
				chars[charIndex] = t.c;
				charIndex++;
			}else{
				for(int l = 0; l < t.l; l++){
					chars[charIndex+l] = chars[charIndex - t.d + l];
				}
				charIndex += t.l;
				chars[charIndex] = t.c;
				charIndex++;
			}
		}
		System.out.println(new String(chars));
		return new String(chars);
	}
	
	/*
	 * Gets an ArrayList of Triplets and returns the total sum 
	 * of all characters in it (the length of the final string)
	 */
	private static int getCharLength(ArrayList<Triplet> triplets){
		int ret = 0;
		for(int i = 0; i < triplets.size(); i++){
			Triplet t = triplets.get(i);
			ret += (t.l+1);
		}
		return ret;
	}
	
	/*
	 * Nested class: Basically an information packet for each compression step
	 * Consists of distance (d), length (l) and character(c) information
	 */
	private static class Triplet {
		public int d, l;
		public char c;
		
		public Triplet(int d, int l, char c){
			this.d = d;
			this.l = l;
			this.c = c;
		}
		
		@Override
		public String toString(){
			return "("+d+", "+l+", "+c+")";
		}
	}

}
