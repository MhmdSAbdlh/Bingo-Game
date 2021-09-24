import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Intro extends JFrame{

	private static final long serialVersionUID = 1L;
	public static String userL;
	public static String userR;
	JLabel gameName = new JLabel("Bingo");
	JButton twoPlayer = new JButton("Two Player");
	
	Intro(){
		
		//Frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setResizable(false);	
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Main.darkC);
		this.setLayout(null);
		this.setTitle("Bingo");
		this.getRootPane().setDefaultButton(twoPlayer);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("BINGO.png")).getImage());
		
		//Label
		gameName.setBounds(0, 80, 800, 100);
		gameName.setHorizontalAlignment(0);
		gameName.setFont(Main.gameNameF);
		gameName.setForeground(Main.lightC);
		JLabel credit = new JLabel("Created with Love by MhmdSAbdlh");
		credit.setHorizontalAlignment(0);
		credit.setBounds(0, 730, 800, 20);
		credit.setForeground(Color.white);
		
		//Button
		twoPlayer.setFont(Main.labelF);
		twoPlayer.setBounds(200, 400, 400, 50);
		twoPlayer.setFocusable(false);
		twoPlayer.setBackground(Main.medC);
		twoPlayer.setForeground(Main.darkC);
		twoPlayer.addActionListener( e -> {
			userL = JOptionPane.showInputDialog("First player Name");
			userR = JOptionPane.showInputDialog("Second player Name");
			this.dispose();
			new GameMP();
			});
		JButton onePLayer = new JButton("One Player");
		onePLayer.setFont(Main.labelF);
		onePLayer.setBounds(200, 300, 400, 50);
		onePLayer.setFocusable(false);
		onePLayer.setBackground(Main.medC);
		onePLayer.setForeground(Main.darkC);
		onePLayer.addActionListener( e -> {
			userL = JOptionPane.showInputDialog("First player Name");
			userR = "PC";
			this.dispose();
			new GamePC();
			});
		JButton Exit = new JButton("Exit");
		Exit.setFont(Main.labelF);
		Exit.setBounds(200, 500, 400, 50);
		Exit.setFocusable(false);
		Exit.setBackground(Main.medC);
		Exit.setForeground(Main.darkC);
		Exit.addActionListener( e -> System.exit(0));
		
		//Add to frame
		this.add(gameName);
		this.add(twoPlayer);
		this.add(onePLayer);
		this.add(Exit);
		this.add(credit);
		
		this.setVisible(true);
	}
}
