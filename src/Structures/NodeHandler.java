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
			root.paintlvl(g, 400, 0, 400, 1, depthInfo, 640 / depthInfo.length);
		}
	}

}
