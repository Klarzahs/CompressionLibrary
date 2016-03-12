package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class MyScreen extends JPanel{
	Main main;
	
	public MyScreen(Main m){
		super(true);
		main = m;
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g); 
	    Graphics2D graphics2D = (Graphics2D) g;

	    //Set  anti-alias!
	    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON); 

	   // Set anti-alias for text
	    graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	            RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 
		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		main.getNH().paint(g);
		
	}
}
