import java.awt.Color;
import java.awt.Font;

import javax.swing.border.LineBorder;

public class Main {
	
	static Font labelF = new Font("Tahoma",Font.BOLD,30);	
	static Font labelS = new Font("Tahoma",Font.BOLD,35);
	static Font gameNameF = new Font("Algerian",Font.BOLD,95);
	static Color darkC = new Color(68, 73, 65);		
	static Color lightC = new Color(127, 200, 169);		
	static Color medC = new Color(213, 238, 187);
	static LineBorder border = new LineBorder(darkC, 1);

	public static void main(String[] args) {
	
		new Intro();
	}
}