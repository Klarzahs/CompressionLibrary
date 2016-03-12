package main;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	private MyScreen screen;
	private Main main;
	
	public MyFrame(Main m){
		super("TrieViewer");
		this.pack();
		this.setSize(800, 640);
		main = m;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		screen = new MyScreen(m);
		screen.setSize(this.getSize());
		screen.setVisible(true);
		this.add(screen);

		
		this.setVisible(true);
	}
	
	public MyScreen getScreen(){
		return this.screen;
	}
}
