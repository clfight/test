import java.util.Observable;
import java.util.Random;

public class GameModel extends Observable {

	// �ֱ����û�ѡ����š������˴򿪵��š���һ�����Լ������г����ŵı��
	private int doorSelected, doorOpened, anotherDoor, carNum;

	// ��Ϸ�ܴ�����ʤ��������ʧ�ܴ�������Ϸʤ�ʣ��ٷ�����,��Ϸ�ѽ��еĴ���
	private int totalTimes, winTimes, failTimes, winRate,playedTimes = 0;

	// ���ڴ�����Ϸ�������*����ʾһ��ʤ������������ʾһ��ʧ��,��Ϸ��ȷ���ź���
	private String result = "",rightDoor = "";

	private boolean exchange;// ֵΪ���ʾ��Ϸ��ѡ������һ����

	// ���캯��
	public GameModel() {

	}

	// ��������1��3֮���������������ڱ�ʾÿһ����
	private int random() {
		Random rd = new Random();
		return 1 + rd.nextInt(3);

	}

	// ������Ϸ��������ʼ��ģ������
	public void setGameTimes(int num) {
		totalTimes = num;
		System.out.println("set game times "+totalTimes);
		winTimes = 0;
		failTimes = 0;
		winRate = 0;
		setChanged();
		notifyObservers();
	}

	public void setChange(boolean change) {
		exchange = change;
	}

	// ��ʼ��Ϸ
	public void gameStart() {

		if (totalTimes > 0) {

			carNum = random();
			doorSelected = random();
			do {
				anotherDoor = random();
			} while (anotherDoor == doorSelected);
			do {
				doorOpened = random();
			} while (doorOpened == doorSelected || doorOpened == carNum);

		}
		setChanged();
		notifyObservers();

	}// end method gameStart()
	
	public void isWin(){
		if (doorSelected == carNum && exchange == true) {
			result = "!" + result;
			failTimes = failTimes + 1;
		}
		if (doorSelected == carNum && exchange == false) {
			result = "*" + result;
			winTimes = winTimes + 1;
		}
		if (doorSelected != carNum && exchange == true) {
			result = "*" + result;
			winTimes = winTimes + 1;
		}
		if (doorSelected != carNum && exchange == false) {
			result = "!" + result;
			failTimes = failTimes + 1;
		}

		winRate = (int) ((double) winTimes*100 / (double) (winTimes + failTimes));

	// end if
		rightDoor = String.valueOf(carNum)+rightDoor;
		if(totalTimes >= 0){
			playedTimes = playedTimes + 1;
			
		}

		totalTimes = totalTimes - 1;
	setChanged();
	notifyObservers();
	
	}
		

	//��Ϸ����
	public void gameOver(){
		
		totalTimes = -1;
		playedTimes = 0;
		winTimes = 0;
		failTimes = 0;
		winRate = 0;
		result = "";
		rightDoor = "";
		setChanged();
		notifyObservers();
	}
	
	//�����Ϸʤ��
	public int getWinRate(){
		return winRate;
	}
	
	//�����Ϸ���
	public String getResult(){
		return result;
	}
	
	//�����ѡ����ŵı��
	public int getDoorSelected(){
		return doorSelected;
	}
	
	//����Ѵ򿪵��ŵı��
	public int getDoorOpened(){
		return doorOpened;
	}
	
	//�����ȷ���ŵı��
	public String getRightDoor(){
		return rightDoor;
	}
	
	//�����Ϸʣ�����
	public int getTotalTimes(){
		return totalTimes;
	}
	
	public int getPlayedTimes(){
		return playedTimes;
	}
}
