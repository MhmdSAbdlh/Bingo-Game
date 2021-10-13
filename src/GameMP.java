import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameMP extends JFrame{
	
	Random random = new Random();
	JTextField inputTF = new JTextField();
	JLabel[] numR = new JLabel[25];
	JLabel[] numL = new JLabel[25];
	JLabel leftDone = new JLabel();
	JLabel rightDone = new JLabel();
	JLabel turnL = new JLabel();
	JButton inputB = new JButton("Enter");		
	ArrayList<Integer> checkedLH = new ArrayList<Integer>();
	ArrayList<Integer> checkedRH = new ArrayList<Integer>();
	ArrayList<Integer> checkedLV = new ArrayList<Integer>();
	ArrayList<Integer> checkedRV = new ArrayList<Integer>();
	ArrayList<Integer> randomN = new ArrayList<Integer>();
	int lineDL = 0;
	int lineDR = 0;
	boolean diagR1 = false, diagR2 =false, diagL1 =false, diagL2 =false;
	boolean turnRight;
	
	GameMP(){
		//Frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1400, 800);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Main.darkC);
		this.setLayout(null);
		this.setTitle("BINGO");
		this.getRootPane().setDefaultButton(inputB);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("BINGO.png")).getImage());
		
		//Panel
		JPanel playerL = new JPanel();
		JPanel playerR = new JPanel();
		playerL.setBounds(20, 20,600,600);
		playerR.setBounds(760, 20,600,600);
		playerL.setBackground(Main.lightC);
		playerR.setBackground(Main.medC);
		playerL.setLayout(new GridLayout(5, 5));
		playerR.setLayout(new GridLayout(5, 5));
		numAdd(playerL, playerR);
		
		//InputNumber add
		inputTF.setFont(Main.labelF);
		inputTF.setBounds(665, 500, 50, 50);
		inputTF.setHorizontalAlignment(0);
		inputTF.setBackground(Main.medC);
		inputTF.setForeground(Main.darkC);
		inputTF.setBorder(Main.border);
		int turnN = random.nextInt(2);
		if(turnN == 0) {
			turnL.setText("← Turn");
			turnRight = false;
		}
		else {
			turnL.setText("Turn →");
			turnRight = true;
		}
		turnL.setFont(Main.labelS);
		turnL.setBounds(620, 650, 140, 50);
		turnL.setHorizontalAlignment(0);
		turnL.setForeground(Main.darkC);
		turnL.setBackground(Main.medC);
		turnL.setOpaque(true);
		inputB.setFont(Main.labelF);
		inputB.setBackground(Main.lightC);
		inputB.setForeground(Main.darkC);
		inputB.setBorder(Main.border);
		inputB.setFocusable(false);
		inputB.setBounds(640, 570, 100, 50);
		inputB.addActionListener( e -> newNum());
		leftDone.setBounds(0, 650, 600, 50);
		leftDone.setHorizontalAlignment(0);
		leftDone.setForeground(Main.lightC);
		leftDone.setFont(Main.labelF);
		rightDone.setBounds(800, 650, 600, 50);
		rightDone.setFont(Main.labelF);
		rightDone.setForeground(Main.medC);
		rightDone.setHorizontalAlignment(0);
		if(Intro.userL.isEmpty())
			Intro.userL = "Player 1";
		if(Intro.userR.isEmpty())
			Intro.userR = "Player 2";
		leftDone.setText(Intro.userL.toUpperCase()+": "+"0/5");
		rightDone.setText(Intro.userR.toUpperCase()+": "+"0/5");
		
		//MenuBar
		JMenuBar menu = new JMenuBar();
		JMenu file;
		JMenu help = null;
		file = new JMenu("File");
		help = new JMenu("Help");
		JMenuItem newG = new JMenuItem("New Game");
		JMenuItem restartG = new JMenuItem("Restart Game");
		JMenuItem exitG = new JMenuItem("Exit Game");
		newG.addActionListener( e -> {
			this.dispose();
			new Intro();
		});
		restartG.addActionListener( e -> {
			this.dispose();
			new GameMP();
		});
		exitG.addActionListener( e -> System.exit(0));
		file.add(newG);
		file.add(restartG);
		file.add(exitG);
		JMenuItem howPlay = new JMenuItem("How to Play");
		JMenuItem credit = new JMenuItem("About");
		howPlay.addActionListener( e -> JOptionPane.showMessageDialog(null,"- First you put a number in the empty field."+"\n- Then you check if you hit a straight number(Horz,vert or Diagonal)."+"\n- When you have 5 straight line you win."));
		credit.addActionListener( e -> JOptionPane.showMessageDialog(null, "Created & Designed by MhmdSAbdlh ®"));
		menu.add(file);
		menu.add(help);
		help.add(howPlay);
		help.add(credit);
		
		//Add to frame
		this.setJMenuBar(menu);
		this.add(rightDone);
		this.add(leftDone);
		this.add(inputB);
		this.add(inputTF);
		this.add(turnL);
		this.add(playerL);
		this.add(playerR);
		this.setVisible(true);
	}

	//Add the two panels
	private void numAdd(JPanel playerL, JPanel playerR) {
		int num;
		for(int i=0;i<25;i++) {
			num = random.nextInt(25)+1;
			while(randomN.contains(num)) {
				num = random.nextInt(25)+1;
			}
			randomN.add(num);
			numL[i] = new JLabel(""+num);
			numL[i].setFont(Main.labelF);
			numL[i].setHorizontalAlignment(0);
			numL[i].setBorder(Main.border);
			playerL.add(numL[i]);
			}
		randomN.clear();
		for(int i=0;i<25;i++) {
			num = random.nextInt(25)+1;
			while(randomN.contains(num)) {
				num = random.nextInt(25)+1;
			}
			randomN.add(num);
			numR[i] = new JLabel(""+num);
			numR[i].setFont(Main.labelF);
			numR[i].setHorizontalAlignment(0);
			numR[i].setBorder(Main.border);
			playerR.add(numR[i]);
			}
	}
	
	//Add number to panels
	private void numSelect() {
		int i=0;
		while(!inputTF.getText().equals(numR[i].getText()) && i<24) {
			i++;
		}
		if(inputTF.getText().equals(numR[i].getText())) {
			numR[i].setForeground(Main.lightC);
			numR[i].setFont(Main.labelS);
		}
		i=0;
		while(!inputTF.getText().equals(numL[i].getText()) && i<24) {
			i++;
		}
		if(inputTF.getText().equals(numL[i].getText())) {
			numL[i].setForeground(Main.medC);
			numL[i].setFont(Main.labelS);
		}
	}

	//Check the line completed
	private void checkGame() {
		int i=0;
		//Check Horizontally
		while(i<25) {
			if(!checkedLH.contains(i) && numL[i].getForeground().equals(Main.medC)&&numL[i].getForeground().equals(numL[i+1].getForeground())&&numL[i].getForeground().equals(numL[i+2].getForeground())&&numL[i].getForeground().equals(numL[i+3].getForeground())&&numL[i].getForeground().equals(numL[i+4].getForeground())){
				lineDL++;
				numL[i].setText("X");
				numL[i+1].setText("X");
				numL[i+2].setText("X");
				numL[i+3].setText("X");
				numL[i+4].setText("X");
				checkedLH.add(i);
				}
				else
					i+=5;
			}
		i=0;
		while(i<25) {
			if(!checkedRH.contains(i) && numR[i].getForeground().equals(Main.lightC)&&numR[i].getForeground().equals(numR[i+1].getForeground())&&numR[i].getForeground().equals(numR[i+2].getForeground())&&numR[i].getForeground().equals(numR[i+3].getForeground())&&numR[i].getForeground().equals(numR[i+4].getForeground())){
				lineDR++;
				numR[i].setText("X");
				numR[i+1].setText("X");
				numR[i+2].setText("X");
				numR[i+3].setText("X");
				numR[i+4].setText("X");
				checkedRH.add(i);
				}
				else
					i+=5;
		}
		//End Horizontally
		//Check Vertically
		i=0;
		while(i<5) {
			if(!checkedLV.contains(i) && numL[i].getForeground().equals(Main.medC)&&numL[i].getForeground().equals(numL[i+5].getForeground())&&numL[i].getForeground().equals(numL[i+10].getForeground())&&numL[i].getForeground().equals(numL[i+15].getForeground())&&numL[i].getForeground().equals(numL[i+20].getForeground())){
				lineDL++;
				numL[i].setText("X");
				numL[i+5].setText("X");
				numL[i+10].setText("X");
				numL[i+15].setText("X");
				numL[i+20].setText("X");
				checkedLV.add(i);
				}
				else
					i++;
		}
		i=0;
		while(i<5) {
			if(!checkedRV.contains(i) && numR[i].getForeground().equals(Main.lightC)&&numR[i].getForeground().equals(numR[i+5].getForeground())&&numR[i].getForeground().equals(numR[i+10].getForeground())&&numR[i].getForeground().equals(numR[i+15].getForeground())&&numR[i].getForeground().equals(numR[i+20].getForeground())){
				lineDR++;
				numR[i].setText("X");
				numR[i+5].setText("X");
				numR[i+10].setText("X");
				numR[i+15].setText("X");
				numR[i+20].setText("X");
				checkedRV.add(i);
				}
			else
				i++;
		}
		//End Vertically
		//Check Diagonally
		if(!diagL1 && numL[0].getForeground().equals(Main.medC)&&numL[0].getForeground().equals(numL[6].getForeground())&&numL[0].getForeground().equals(numL[12].getForeground())&&numL[0].getForeground().equals(numL[18].getForeground())&&numL[0].getForeground().equals(numL[24].getForeground()))
			{lineDL++;
			numL[0].setText("X");
			numL[6].setText("X");
			numL[12].setText("X");
			numL[18].setText("X");
			numL[24].setText("X");
			diagL1 = true;
			}
		if(!diagL2 && numL[4].getForeground().equals(Main.medC)&&numL[4].getForeground().equals(numL[8].getForeground())&&numL[4].getForeground().equals(numL[12].getForeground())&&numL[4].getForeground().equals(numL[16].getForeground())&&numL[4].getForeground().equals(numL[20].getForeground()))
			{lineDL++;
			numL[4].setText("X");
			numL[8].setText("X");
			numL[12].setText("X");
			numL[16].setText("X");
			numL[20].setText("X");
			diagL2 = true;
			}
		if(!diagR1 && numR[0].getForeground().equals(Main.lightC)&&numR[0].getForeground().equals(numR[6].getForeground())&&numR[0].getForeground().equals(numR[12].getForeground())&&numR[0].getForeground().equals(numR[18].getForeground())&&numR[0].getForeground().equals(numR[24].getForeground()))
			{lineDR++;
			numR[0].setText("X");
			numR[6].setText("X");
			numR[12].setText("X");
			numR[18].setText("X");
			numR[24].setText("X");
			diagR1 =true;
			}
		if(!diagR2 && numR[4].getForeground().equals(Main.lightC)&&numR[4].getForeground().equals(numR[8].getForeground())&&numR[4].getForeground().equals(numR[12].getForeground())&&numR[4].getForeground().equals(numR[16].getForeground())&&numR[4].getForeground().equals(numR[20].getForeground()))
				{lineDR++;
				numR[4].setText("X");
				numR[8].setText("X");
				numR[12].setText("X");
				numR[16].setText("X");
				numR[20].setText("X");
				diagR2 = true;
				}
		//End Diagonally
		}
	
	//Check the end of the game
	private void gameOver() {
		int actionO=0;
		String[] messGO = {"RESTART GAME", "MAIN MENU", "EXIT"};
		if(lineDL>=5 || lineDR>=5) {
			if(lineDL == lineDR)
				actionO = JOptionPane.showOptionDialog(this, "No one win, it is a draw!", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, messGO, null);
			else
				if(lineDL >= lineDR) 
					actionO = JOptionPane.showOptionDialog(this, "\""+Intro.userL.toUpperCase()+"\" beat \""+Intro.userR.toUpperCase()+"\" ("+lineDL+"-"+lineDR+")", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, messGO, null);
				else
					if(lineDR >= lineDL)
						actionO = JOptionPane.showOptionDialog(this, "\""+Intro.userR.toUpperCase()+"\" beat \""+Intro.userL.toUpperCase()+"\" ("+lineDR+"-"+lineDL+")", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, messGO, null);
			if(actionO == 1) {
				this.dispose();
				new Intro();
			}
			else
				if(actionO == 0) {
					this.dispose();
					new GameMP();
				}
				else
					System.exit(0);
		}
	}

	//Select new number
	private void newNum() {
		numSelect();
		checkGame();
		leftDone.setText(Intro.userL.toUpperCase()+": "+lineDL+"/5");
		rightDone.setText(Intro.userR.toUpperCase()+": "+lineDR+"/5");
		gameOver();
		if(lineDL<5 && lineDR<5) {
			if(!inputTF.getText().isBlank())
				if(turnRight) {
					turnL.setText("←Turn");
					turnRight = false;
				}
				else {
					turnL.setText("Turn→");
					turnRight = true;
				}
		inputTF.setText("");
		}
	}
}