package Algorithms;

import Structures.Alphabet;

public class RadixSort {
	public static String[] iterativeSort(String[] s){
		int[][] arr = arrToInt(s);
		
		int N = arr.length;
		for(int d = arr[0].length - 1; d >= 0; d--){
			int[] keys = new int[N];
			for(int i = 0; i < N; i++){
				keys[i] = arr[i][d];
			}
			System.out.println("It "+d+", keys:");
			printArr(keys);
			int[] swap = countingSortSwap(keys, Alphabet.R);
			printArr(swap);
			int[][] tempArr = new int[arr.length][arr[0].length];
			for(int i = 0; i < swap.length; i++){
				tempArr[i] = arr[swap[i]];
			}
			
			arr = tempArr;
		}
		
		return arrToString(arr);
	}
	
	public static void print(String[] s){
		for(int i = 0; i < s.length; i++){
			System.out.println(s[i]);
		}
	}
	
	private static int[][] swap(int[][] arr, int a, int b){
		int[] temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
		return arr;
	}
	
	private static String[] arrToString(int[][] arr){
		String[] ret = new String[arr.length];
		for(int i = 0; i < arr.length; i++){
			String s = "";
			for(int j = 0; j < arr[i].length; j++){
				s += Alphabet.getChar(arr[i][j]);
			}
			ret[i] = s;
		}
		return ret;
	}
	
	private static int[][] arrToInt(String[] s){
		int[][] ret = new int[s.length][s[0].length()];
		for(int i = 0; i < s.length; i++){
			for(int j = 0; j < s[i].length(); j++){
				ret[i][j] = Alphabet.getIndex(s[i].charAt(j));
			}
		}
		return ret;
	}
	
	private static int[] countingSortSwap(int[] a, int R){
		int[] cnt = new int[R + 1];
		int[] swap = new int[a.length];
		
		for (int i = 0; i < a.length ; i++){
			cnt[a[i] + 1]++;
		}

		for (int r = 0; r < R - 1; r++){ 
			cnt[r + 1] = cnt[r + 1] + cnt[r];
		}

		for (int i = 0; i < a.length - 1; i++){	//-1
			swap[cnt[a[i]]] = i;
			cnt[a[i]]++;
		}
		//swap[swap.length - 1] = swap.length - 1;
		return swap;
	}
	
	public static int[] countingSort(int[] a, int R){
		int[] cnt = new int[R + 1];

		for (int i = 0; i < a.length - 1; i++){
			cnt[a[i] + 1]++;
		}

		for (int r = 0; r < R - 1; r++){ 
			cnt[r + 1] = cnt[r + 1] + cnt[r];
		}

		int temp[] = new int [a.length];
		for (int i = 0; i < a.length - 1; i++){
			temp[cnt[a[i]]] = a[i]; 
			cnt[a[i]]++;
		}
		for (int i = 0; i < a.length - 1; i++){
			a[i] = temp[i];
		}
		
		return a;
	}

	public static String countingSort(String s, int R){
		int[] ar = new int[s.length()];
		for(int i = 0; i < ar.length; i++){
			ar[i] = Alphabet.getIndex(s.charAt(i));
		}
		
		int[] a = countingSort(ar, R);
		String ret = "";
		for(int i = 0; i < a.length; i++){
			ret += Alphabet.getChar(a[i]);
		}
		return ret;
	}
	
	public static void printArr(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]+", ");
		}
		System.out.println("");
	}
}
