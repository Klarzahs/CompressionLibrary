package Structures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;

import main.MyScreen;

public class Trie {
	private boolean isMarked;
	private Edge[] edges = new Edge[26];
	private Trie parent;

	public Trie(){}

	public void setMarked(){
		isMarked = true;
	}
	
	private void setParent(Trie t){
		this.parent = t;
	}
	
	public Trie getRoot(){
		if(this.parent == null)
			return this;
		return parent.getRoot();
	}
	
	private boolean isLeaf(){
		for(int i = 0; i < edges.length; i++){
			if(edges[i] != null){
				return false;
			}
		}
		return true;
	}

	public Trie addChar(char c){
		if(edges[Alphabet.getIndex(c)] != null){
			//do nothing, already created
		}else{
			Trie b = new Trie();
			b.setParent(this);
			edges[Alphabet.getIndex(c)] = new Edge(this, b, c);
		}
		return edges[Alphabet.getIndex(c)].getB();
	}

	public boolean isMarked(){
		return isMarked;
	}

	public void drawTrie(NodeHandler nh){	
		int[] depthArray;
		ArrayList<DepthInfo> depth = this.getDepthList(1);

		//get max depth from depth information
		int maxDepth = 0;
		for(int i = 0; i < depth.size(); i++){
			if(depth.get(i).level > maxDepth)
				maxDepth = depth.get(i).level;
		}

		//create array with number of nodes per level
		depthArray = new int[maxDepth];
		for(int i = 0; i < depth.size(); i++){
			DepthInfo di = depth.get(i);
			depthArray[di.level - 1] += di.count;
		}

		nh.setDepthInfo(depthArray, this);
	}

	private ArrayList<DepthInfo> getDepthList(int level){
		ArrayList<DepthInfo> depth = new ArrayList<DepthInfo>();
		for(int i = 0; i <  this.getEdges().length; i++){
			if(getEdges()[i] != null){
				ArrayList<DepthInfo> rec = getEdges()[i].getB().getDepthList(level + 1);
				depth.addAll(rec);
			}
		}
		depth.add(new DepthInfo(level, 1));
		return depth;
	}

	private class DepthInfo{
		int level;
		int count;

		public DepthInfo(int l, int c){
			level = l;
			count = c;
			//System.out.println(this.toString());
		}

		public String toString(){
			return "("+level+", "+count+")";
		}
	}

	public static void createExample(){
		Trie root = new Trie();
		String[] strings = {"barfoo", "foobar", "foo", "bar", "barfuss", "fool"};

		for(int i = 0; i < strings.length; i++){
			Trie temp = root;
			for(int j = 0; j < strings[i].length(); j++){
				temp = temp.addChar(strings[i].charAt(j));
			}
			temp.setMarked();
		}
	}
	
	public void createTrieFromStrings(String[] strings){
		for(int i = 0; i < strings.length; i++){
			Trie temp = this;
			for(int j = 0; j < strings[i].length(); j++){
				temp = temp.addChar(strings[i].charAt(j));
			}
			temp.setMarked();
		}
	}

	public Edge[] getEdges(){
		return edges;
	}

	private static void printTrie(Trie t){
		Stack<TrieString> stack = new Stack<TrieString>();
		stack.push(new TrieString(t, ""));
		// stack−based depth−first search with output in preorder
		while (!stack.isEmpty()){
			TrieString ts = stack.pop();
			Trie n = ts.t;
			if(n != null){
				if(n.isMarked())
					System.out.println(ts.s);
				for(int i = 0; i < n.getEdges().length; i++){
					Edge e = n.getEdges()[i];
					if(e != null){
						stack.push(new TrieString(e.getB(), ts.s + e.getC()));
					}
				}
			}
		}
	}

	private static class TrieString{
		public String s;
		public Trie t;

		public TrieString(Trie t, String s){
			this.s = s;
			this.t = t;
		}
	}

	public void paintlvl(Graphics g, int oldx, int oldy, int rangex, int lvl, int[] depthInfo, int offsetY){
		//draw node at old position
		g.setColor(Color.gray);
		if(this.isMarked)
			g.setColor(Color.green);
		g.drawRect(oldx, oldy, 16, 16);
		g.setColor(Color.gray);

		int nr = 0;
		for(int i = 0; i < edges.length; i++){
			Edge e = edges[i];
			if(e != null){
				int offsetX = rangex / (depthInfo[lvl] / 2 + 1);
				int newx;
				int newy = oldy + offsetY;
				if(nr < depthInfo[lvl] / 2){
					newx = oldx-rangex + offsetX * (nr + 1);
				}else{
					newx = oldx+ offsetX * (nr- (depthInfo[lvl] / 2) + 1);
				}
				e.getB().paintlvl(g, newx, newy, offsetX, lvl + 1, depthInfo, offsetY);
				g.drawLine(oldx + 8, oldy + 16, newx + 8, newy);
				g.drawString(""+e.getC(), (oldx + newx)/2, (oldy + newy)/ 2);
				
				nr++;
			}
		}
	}
	
	public boolean deleteTrie(String s, Trie t){
		// search for a marked node that represents s
		Trie r = t.getRoot();
		for(int i = 0; i < s.length(); i++){
			if(r == null) 
				return false;
			r = r.getEdges()[Alphabet.getIndex(s.charAt(i))].getB();
		}
		if(!r.isMarked) 
			return true;
		else{
			r.isMarked = false;
			while(r.isLeaf() && !r.isMarked){
				Trie p = r.parent;
				for(int i = 0; i < p.edges.length; i++){
					if(p.edges[i] != null && p.edges[i].getB() == r){
						edges[i] = null;
					}
				}
				r = p;
			}
			return true;
		}
	}
	
	
}
