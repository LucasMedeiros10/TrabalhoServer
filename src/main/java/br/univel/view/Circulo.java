package br.univel.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * JPanel para desenho de um círculo
 * O atributo Online define a cor verde quando verdadeiro
 * e vermelho quando offline(falso)
 * 
 * @author LucasMedeiros
 *
 */

public class Circulo extends JPanel{
	
	private Boolean online;
    
	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
		this.repaint();
	}
	
	public Circulo(){
		this.online = false;		
	}

	@Override
    protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(online){
			g2d.setColor(Color.GREEN);
		}else{
			g2d.setColor(Color.RED);			
		}
		g2d.fillOval(0, 0, 25, 25);		
    }
}
