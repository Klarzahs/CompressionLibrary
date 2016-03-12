package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyScreen extends JPanel{
	Main main;
	
	public MyScreen(Main m){
		super();
		main = m;
	}
	
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		main.getNH().paint(g);
		
	}
}
