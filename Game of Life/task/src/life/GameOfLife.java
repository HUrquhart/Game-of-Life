package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*
For the testing reasons, you need to set the name of each component using the method component.setName(String name).
Name your labels with names "GenerationLabel" and "AliveLabel".
 */

public class GameOfLife extends JFrame {

	private JLabel generationLabel;
	private JLabel aliveLabel;

	private JPanel board;
	private JPanel[] cell;
	private UniverseController uc;

	private boolean isPaused;

	public GameOfLife(){
		this(10, new UniverseController(new Universe(10),new UniverseView()));
	}


	public GameOfLife(int x, UniverseController uc) {
		isPaused = true;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.uc = uc;

		// create left/options panel
		JPanel optionsPanel = new JPanel();


		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
		optionsPanel.setMinimumSize(new Dimension(100, 100));

		// Play and pause button
		JToggleButton play = new JToggleButton();
		play.setName("PlayToggleButton");
		play.setText("Play");



		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if(isPaused){
					play.setText("Pause");
					isPaused = false;
				}else{
					play.setText("Play");
					isPaused = true;
				}
				Thread t = new Thread(() -> uc.play());
				t.start();
			}
		});
		optionsPanel.add(play);

		JButton reset = new JButton();
		reset.setName("ResetButton");
		reset.setText("Reset");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				uc.reset();
				uc.updateView();
			}
		});
		optionsPanel.add(reset);

		generationLabel =  new JLabel("Generation #0");
		generationLabel.setName("GenerationLabel");
		generationLabel.setAlignmentX(FlowLayout.LEFT);
		aliveLabel = new JLabel("Alive: 0");
		aliveLabel.setName("AliveLabel");
		aliveLabel.setAlignmentX(FlowLayout.LEFT);
		optionsPanel.add(generationLabel);
		optionsPanel.add(aliveLabel);
		optionsPanel.setVisible(true);

		// create right/board panel
		cell = new JPanel[x*x];
		board = new JPanel();
		board.setLayout(new GridLayout(x, x,1,1));
		board.setSize(400, 400);
		board.setVisible(true);
		board.setBackground(Color.black);
		for(int i = 0; i < this.uc.getCurrentGeneration().length; i++){
			cell[i] = new JPanel();
			cell[i].setVisible(true);
			cell[i].setSize(3,3);
			cell[i].setBackground(Color.white);
			if(this.uc.getCurrentGeneration()[i]){
				cell[i].setBackground(Color.black);
			}
			board.add(cell[i]);
		}
		board.setVisible(true);
		add(optionsPanel);
		add(board);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void updateGenerationNumber(int generationNumber){
		this.generationLabel.setText(String.format("Generation #%d", generationNumber));
	}

	public void updateAliveNumber(int alive){
		this.aliveLabel.setText(String.format("Alive :%d", alive));
	}

	public void repaintGrid(boolean[] board){
		for(int i = 0; i < cell.length; i++){
			if(board[i]){
				cell[i].setBackground(Color.black);
			}else{
				cell[i].setBackground(Color.white);
			}
		}
	}

	public boolean isPaused() {
		return isPaused;
	}
}