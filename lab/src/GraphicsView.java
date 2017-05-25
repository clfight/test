import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;


public class GraphicsView extends JPanel implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int winRate = 0;
	private GameModel model;
	
	
	public GraphicsView(){
		setBackground(Color.white);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		model = (GameModel)o;
		winRate =(int) ((double)model.getWinRate()*3.6);
		repaint();
		
	}
	public void paint(Graphics g){
		super.paint(g);
		
		g.setColor(Color.green);
		g.fillArc(80, 40, 200, 200, 0, winRate);
		g.fillRect(40, 320, winRate*3/4, 20);
		
		
		g.setColor(Color.red);
		g.fillArc(80, 40, 200, 200, winRate, 360-winRate);
		g.fillRect(winRate*3/4+40, 320, (360-winRate)*3/4, 20);
		
		g.setColor(Color.black);
		g.drawString("Car rate:"+(int)(winRate/3.6)+"%", 200, 120);
		g.drawString("Car rate:"+(int)(winRate/3.6)+"%", 5, 315);
		g.drawString("Goat rate:"+(int)(100-(winRate/3.6))+"%", 260, 315);
		
		
	}

}
