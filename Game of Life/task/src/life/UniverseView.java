package life;

import javax.swing.*;
import java.awt.*;

public class UniverseView extends JFrame {

	public void print(boolean[] board, int size, int numAliveCells){
		for(int i = 1; i <= size*size; i++){
			System.out.print((board[i-1])?'O': ' ');
			if(i%size == 0){
				System.out.println();
			}
		}
	}
}
