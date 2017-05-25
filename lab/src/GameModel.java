import java.util.Observable;
import java.util.Random;

public class GameModel extends Observable {

	// 分别定义用户选择的门‘主持人打开的门‘另一扇门以及后面有车的门的编号
	private int doorSelected, doorOpened, anotherDoor, carNum;

	// 游戏总次数，胜利次数，失败次数，游戏胜率（百分数）,游戏已进行的次数
	private int totalTimes, winTimes, failTimes, winRate,playedTimes = 0;

	// 用于储存游戏结果，“*”表示一次胜利，“！”表示一次失败,游戏正确的门号码
	private String result = "",rightDoor = "";

	private boolean exchange;// 值为真表示游戏者选择另外一扇门

	// 构造函数
	public GameModel() {

	}

	// 产生介于1到3之间的随机整数，用于标示每一扇门
	private int random() {
		Random rd = new Random();
		return 1 + rd.nextInt(3);

	}

	// 设置游戏次数，初始化模型数据
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

	// 开始游戏
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
		

	//游戏结束
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
	
	//获得游戏胜率
	public int getWinRate(){
		return winRate;
	}
	
	//获得游戏结果
	public String getResult(){
		return result;
	}
	
	//获得已选择的门的编号
	public int getDoorSelected(){
		return doorSelected;
	}
	
	//获得已打开的门的编号
	public int getDoorOpened(){
		return doorOpened;
	}
	
	//获得正确的门的编号
	public String getRightDoor(){
		return rightDoor;
	}
	
	//获得游戏剩余次数
	public int getTotalTimes(){
		return totalTimes;
	}
	
	public int getPlayedTimes(){
		return playedTimes;
	}
}
