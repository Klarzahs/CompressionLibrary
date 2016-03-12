package Structures;

import java.awt.Graphics;

import main.Main;

public class NodeHandler {
	private Main main;
	private int[] depthInfo;
	private Trie root;
	
	public NodeHandler(Main m){
		main = m;
	}
	
	public void setDepthInfo(int[] arr, Trie t){
		depthInfo = arr;
		root = t;
	}
	
	public void paint(Graphics g){
		if(depthInfo != null){
			int heightPerLevel = 640/depthInfo.length;
			root.paintNode(g, 0, 1, depthInfo, heightPerLevel);
		}
	}
	
}
