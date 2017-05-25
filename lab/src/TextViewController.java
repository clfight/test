import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
/*
 * 
 */

public class TextViewController implements ActionListener,MouseListener{
	
	private TextView tView;
	private GameModel model;
	private boolean exchange,positive = false;
	
	public TextViewController(GameModel model){
		tView = new TextView();
		this.model  = model;
		tView.addActionListener(this);
		tView.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//考虑当发生事件的对象是inputField时的情况
		try{
			JTextField inputField = (JTextField)e.getSource();
			String s = inputField.getText();
			int time = Integer.parseInt(s);
			model.setGameTimes(time);
			model.gameStart();
			
		}catch(Exception e1){
			
		}
		//考虑当发生事件的对象是jbutton时的情况
		try{
			JButton button = (JButton)e.getSource();
			
			if(button == tView.getFormerEnsureButton()){
				String s = tView.getInputText();
				int time = Integer.parseInt(s);
				model.setGameTimes(time);
				model.gameStart();
			}
			
			if(button == tView.getRestartButton()){
				model.gameOver();
				positive = false;
				tView.disableRadio();
			}
			
			if(button == tView.getLatterEnsureButton()){
				if(positive == true){
				model.setChange(exchange);
				if(model.getTotalTimes()>0)
				model.isWin();
				if(model.getTotalTimes()>0)
				model.gameStart();
				}
			}
		}catch(Exception e2){
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		JRadioButton button = (JRadioButton)e.getSource();
		tView.disableRadio();
		button.setSelected(true);
		positive = true;
		if(button == tView.getNoChangeButton()){
			model.setChange(false);
		exchange = false;
			if(model.getTotalTimes()>0)
			model.isWin();
			if(model.getTotalTimes()>0)
			model.gameStart();
		}
		if(button == tView.getChangeButton()){
			model.setChange(true);
			exchange = true;
			if(model.getTotalTimes()>0)
			model.isWin();
			if(model.getTotalTimes()>0)
			model.gameStart();
		}
		if(button == tView.getRandonmButton()){
			Random r = new Random();
			int rd = r.nextInt(2);
			if(rd == 0){
				model.setChange(true);
				exchange = true;
			}
			if(rd == 1){
				model.setChange(false);
				exchange = false;
			}
			if(model.getTotalTimes()>0)
			model.isWin();
			if(model.getTotalTimes()>0)
			model.gameStart();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public TextView getTextView(){
		return tView;
	}

}
