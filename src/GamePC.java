import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GamePC extends JFrame{

	private static final long serialVersionUID = 1L;
	JPanel playerL = new JPanel();
	JPanel playerR = new JPanel();
	Random random = new Random();
	
	JTextField inputTF = new JTextField();
	JLabel[] numR = new JLabel[25];
	JLabel[] numL = new JLabel[25];
	JLabel leftDone = new JLabel(Intro.userL.toUpperCase()+": "+"0/5");
	JLabel rightDone = new JLabel(Intro.userR+": "+"0/5");
	JLabel pcNumber = new JLabel("");
	JButton inputB = new JButton("Enter");
	ArrayList<Integer> checkedLH = new ArrayList<Integer>();
	ArrayList<Integer> checkedRH = new ArrayList<Integer>();
	ArrayList<Integer> checkedLV = new ArrayList<Integer>();
	ArrayList<Integer> checkedRV = new ArrayList<Integer>();
	ArrayList<Integer> randomN = new ArrayList<Integer>();
	ArrayList<String> numAdded = new ArrayList<String>();
	int lineDL = 0;
	int lineDR = 0;
	boolean diagR1 = false, diagR2 =false, diagL1 =false, diagL2 =false;
	
	GamePC(){
		//Frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1400, 800);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Main.darkC);
		this.setLayout(null);
		this.setTitle("Bingo");
		this.getRootPane().setDefaultButton(inputB);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("BINGO.png")).getImage());
				
		//Panel
		playerL.setBounds(400, 20,600,600);
		playerR.setBounds(760, 20,600,600);
		playerL.setBackground(Main.lightC);
		playerR.setBackground(Main.medC);
		playerL.setLayout(new GridLayout(5, 5));
		playerR.setLayout(new GridLayout(5, 5));
		playerR.setVisible(false);
		numAdd(playerL, playerR);
				
		//InputNumber add
		inputTF.setFont(Main.labelF);
		inputTF.setBounds(175, 200, 50, 50);
		inputTF.setHorizontalAlignment(0);
		inputTF.setBackground(Main.medC);
		inputTF.setForeground(Main.darkC);
		inputTF.setBorder(Main.border);
		inputB.setFont(Main.labelF);
		inputB.setBackground(Main.lightC);
		inputB.setForeground(Main.darkC);
		inputB.setBorder(Main.border);
		inputB.setFocusable(false);
		inputB.setBounds(125, 400, 150, 50);
		inputB.addActionListener( e -> newNum());
		leftDone.setBounds(400, 650, 600, 50);
		leftDone.setHorizontalAlignment(0);
		leftDone.setFont(Main.labelF);
		leftDone.setForeground(Main.lightC);
		rightDone.setBounds(800, 650, 600, 50);
		rightDone.setFont(Main.labelF);
		rightDone.setForeground(Main.medC);
		rightDone.setHorizontalAlignment(0);
		rightDone.setVisible(false);
		pcNumber.setBounds(980,400,400,50);
		pcNumber.setFont(Main.labelF);
		pcNumber.setForeground(Main.lightC);
		pcNumber.setFocusable(false);
		pcNumber.setHorizontalAlignment(0);
				
		//MenuBar
		JMenuBar menu = new JMenuBar();
		JMenu file;
		JMenu help;
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
			new GamePC();
		});
		exitG.addActionListener( e -> System.exit(0));
		file.add(newG);
		file.add(restartG);
		file.add(exitG);
		JMenuItem howPlay = new JMenuItem("How to Play");
		JMenuItem credit = new JMenuItem("About");
		howPlay.addActionListener( e -> JOptionPane.showMessageDialog(null,"- First you put a number in the empty field."+"\n- Then you check if you hit a straight number(Horz,vert or Diagonal)."+"\n- When you have 5 straight line you win."));
		credit.addActionListener( e -> JOptionPane.showMessageDialog(null, "Created by MhmdSAbdlh"));
		help.add(howPlay);
		help.add(credit);
		menu.add(file);
		menu.add(help);
				
		//Add to frame
		this.setJMenuBar(menu);
		this.add(rightDone);
		this.add(leftDone);
		this.add(pcNumber);
		this.add(inputB);
		this.add(inputTF);
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
			numAdded.add(numR[i].getText());
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
		//OptionPanel
		int actionO=0;
		String[] messGO = {"New Game","Restart Game","Exit"};
		if(lineDL>=5 || lineDR>=5) {
			//PC Panel visible
			playerL.setBounds(20, 20,600,600);
			leftDone.setBounds(0, 650, 600, 50);
			inputTF.setBounds((1400-50)/2, 600, 50, 50);
			inputB.setBounds((1400-150)/2, 680, 150, 50);
			playerR.setVisible(true);
			rightDone.setVisible(true);
			pcNumber.setVisible(false);
			//Checking
			if(lineDL == lineDR)
				actionO = JOptionPane.showOptionDialog(this, "No one win, it is a draw!", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, messGO, null);
			else
				if(lineDL >= lineDR) 
					actionO = JOptionPane.showOptionDialog(this, "Congratulations "+Intro.userL.toUpperCase()+", you beat a ROBOT "+lineDL+"-"+lineDR, "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, messGO, null);
				else
					if(lineDR >= lineDL)
						actionO = JOptionPane.showOptionDialog(this, "HAHAHAHAHAHA, a ROBOT beats you \""+Intro.userL.toUpperCase()+"\" "+lineDR+"-"+lineDL, "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, messGO, null);
			if(actionO == 0) {
				lineDL=0;
				lineDR=0;
				this.dispose();
				new Intro();
			}
			else
				if(actionO ==1) {
					lineDL=0;
					lineDR=0;
					this.dispose();
					new GamePC();
				}
				else
					System.exit(0);
		}
	}
	
	//Select new number
	private void newNum() {
		if(!numAdded.contains(inputTF.getText()) && Integer.parseInt(inputTF.getText())<26 && Integer.parseInt(inputTF.getText())>0) {
			//User Select
			inputTF.setSelectionStart(0);
			inputTF.setSelectionEnd(2);
			numSelect();
			checkGame();
			leftDone.setText(Intro.userL.toUpperCase()+": "+lineDL+"/5");
			rightDone.setText(Intro.userR.toUpperCase()+": "+lineDR+"/5");
			gameOver();
			//PC Select
			pcSelect();
			numSelect();
			inputTF.setSelectionStart(0);
			inputTF.setSelectionEnd(2);
			checkGame();
			leftDone.setText(Intro.userL.toUpperCase()+": "+lineDL+"/5");
			rightDone.setText(Intro.userR.toUpperCase()+": "+lineDR+"/5");
			gameOver();
		}
		else {
			JOptionPane.showMessageDialog(null, "Enter a new Number Please", "Already Exist", 1);
			inputTF.setSelectionStart(0);
			inputTF.setSelectionEnd(2);
		}
	}
	
	//Find the index of row
	private int rowIndex() {
		int i=0;
		while(numR[i].getForeground().equals(Main.lightC) && i<24)
			i++;
		if(i<5)
			return 0;
		else
			if(i<10)
				return 5;
			else
				if(i<15)
					return 10;
				else
					if(i<20)
						return 15;
					else
						return 20;
	}
	
	//Select random number by PC
	private void randAdd() {
		int j;
		j=random.nextInt(25);
		while(numR[j].getForeground()==Main.lightC && j<24)
			j=random.nextInt(25);
		inputTF.setText(numR[j].getText());
	}
	
	//Select new number by PC
	private void pcSelect() {
		int i=0, j=0;
		int row = rowIndex();
		while(numR[j+row].getForeground().equals(Main.lightC) && j<5)
			j++;
		if(j<5)
			inputTF.setText(numR[j+row].getText());
		else {
			j=0;
			while(numR[j+i-row].getForeground().equals(Main.lightC) && j<25)
				j+=5;
			if(j<25)
				inputTF.setText(numR[j+i-row].getText());
			else
				randAdd();
		}
		pcNumber.setText("PC number: "+inputTF.getText());
	}
	
}