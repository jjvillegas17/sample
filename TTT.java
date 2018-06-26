import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* TIC TAC TOE main game */

public class TTT{
	public static void main(String[] args) {
	JFrame frame = new JFrame("TIC TAC TOE");
        frame.setPreferredSize(new Dimension(700,700));
        Game game = new Game();
        frame.add(game);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}