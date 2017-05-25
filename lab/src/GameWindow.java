import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class GameWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GraphicsViewController gController;
	private TextViewController tController;
	private GameModel model;
	public GameWindow(){
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {}
		
		model = new GameModel();
		tController = new TextViewController(model);
		gController = new GraphicsViewController();
		
		setLayout(new GridLayout(1, 2));
		add(tController.getTextView());
		add(gController.getGraphicsView());
		
		model.addObserver(tController.getTextView());
		model.addObserver(gController.getGraphicsView());
		

	
		setSize(700,400);
		setLocation(290,200);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]){
		new GameWindow();
	}

}
