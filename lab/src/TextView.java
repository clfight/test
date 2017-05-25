import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TextView extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ��Ϸģ��
	private GameModel model;
	private JPanel formerPanel, latterPanel;
	private JPanel subFormerPanel1, subFormerPanel2;
	private JPanel subLatterPanel1, subLatterPanel2, subLatterPanel3,
			subLatterPanel4, subLatterPanel5, subLatterPanel6;
	private JTextField inputField, resultField, carDoorField;
	private JButton formerEnsureButton, latterEnsureButton, restartButton;
	private JRadioButton noChangeButton, changeButton, randomButton;
	private ButtonGroup radioGroup;
	private JLabel latterLabel1, latterLabel2, latterLabel3;

	public TextView() {

		formerPanel = new JPanel();
		formerPanel.setLayout(new GridLayout(10, 1));
		formerPanel.add(new JLabel(" ��������Ϸ������"));

		inputField = new JTextField(20);
		subFormerPanel1 = new JPanel();
		subFormerPanel1.add(inputField);
		formerPanel.add(subFormerPanel1);

		formerEnsureButton = new JButton("ȷ��");
		subFormerPanel2 = new JPanel();
		subFormerPanel2.add(formerEnsureButton);
		formerPanel.add(subFormerPanel2);

		latterPanel = new JPanel();
		latterPanel.setLayout(new GridLayout(13, 1));
		latterPanel.add(new JLabel("�Ƿ���ѡ��"));

		noChangeButton = new JRadioButton("����ѡ��  ");
		changeButton = new JRadioButton("ѡ����һ��");
		randomButton = new JRadioButton("���ѡ��  ");
		subLatterPanel1 = new JPanel();
		subLatterPanel2 = new JPanel();
		subLatterPanel3 = new JPanel();
		subLatterPanel1.add(noChangeButton);
		subLatterPanel2.add(changeButton);
		subLatterPanel3.add(randomButton);

		radioGroup = new ButtonGroup();
		radioGroup.add(noChangeButton);
		radioGroup.add(changeButton);
		radioGroup.add(randomButton);

		latterPanel.add(subLatterPanel1);
		latterPanel.add(subLatterPanel2);
		latterPanel.add(subLatterPanel3);

		restartButton = new JButton("����ѡ��");
		latterEnsureButton = new JButton("  ȷ��  ");
		subLatterPanel4 = new JPanel();
		subLatterPanel4.add(restartButton);
		subLatterPanel4.add(latterEnsureButton);
		latterPanel.add(subLatterPanel4);

		latterLabel1 = new JLabel("��ѡ���0���ţ���1�����Ѵ�");
		latterPanel.add(latterLabel1);
		latterPanel.add(new JLabel("ѡ�н��Ϊ��"));

		resultField = new JTextField(40);
		resultField.setEditable(false);
		subLatterPanel5 = new JPanel();
		subLatterPanel5.add(resultField);
		latterPanel.add(subLatterPanel5);

		latterPanel.add(new JLabel("ѡ����Ϊ��"));
		carDoorField = new JTextField(40);
		carDoorField.setEditable(false);
		subLatterPanel6 = new JPanel();
		subLatterPanel6.add(carDoorField);
		latterPanel.add(subLatterPanel6);

		latterLabel2 = new JLabel("��Ϸ�ѽ���0�Σ���ʣ5��");
		latterPanel.add(latterLabel2);

		latterLabel3 = new JLabel("ѡ�и���Ϊ��");
		latterPanel.add(latterLabel3);

		latterPanel.add(new JLabel("PAC CarGoat"));

		setLayout(new GridLayout(1, 1));
		add(formerPanel);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		model = (GameModel) arg0;
		System.out.println("update called");
		changePanel();
		latterLabel1.setText("��ѡ���" + model.getDoorSelected() + "���ţ�" + "�Ѵ򿪵�"
				+ model.getDoorOpened() + "����");
		resultField.setText(model.getResult());
		carDoorField.setText(model.getRightDoor());
		latterLabel2.setText("��Ϸ�ѽ���" + model.getPlayedTimes() + "�Σ���ʣ"
				+ model.getTotalTimes() + "��");
		latterLabel3.setText("ѡ�и���Ϊ��"+model.getWinRate()+"%");

	}

	// �ı����
	public void changePanel() {
		if (model.getTotalTimes() >= 0) {
			remove(formerPanel);
			add(latterPanel);
			updateUI();
		} else {
			remove(latterPanel);
			add(formerPanel);
			updateUI();
		}
	}
	
	public void addActionListener(TextViewController controller){
		inputField.addActionListener(controller);
		formerEnsureButton.addActionListener(controller);
		restartButton.addActionListener(controller);
		latterEnsureButton.addActionListener(controller);
	}
	public void addMouseListener(TextViewController controller){
		noChangeButton.addMouseListener(controller);
		changeButton.addMouseListener(controller);
		randomButton.addMouseListener(controller);
		
	}
	public String getInputText(){
		return inputField.getText();
	}
	public JButton getFormerEnsureButton(){
		return formerEnsureButton;
	}
	public JButton getLatterEnsureButton(){
		return latterEnsureButton;
	}
	public JButton getRestartButton(){
		return restartButton;
	}
	public JRadioButton getNoChangeButton(){
		return noChangeButton;
	}
	public JRadioButton getChangeButton(){
		return changeButton;
	}
	public JRadioButton getRandonmButton(){
		return randomButton;
	}
	public void disableRadio(){
		noChangeButton.setSelected(false);
		changeButton.setSelected(false);
		randomButton.setSelected(false);
	}

}
