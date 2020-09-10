package life;

import java.awt.*;

public class UniverseController {
	private GameOfLife game;
	private Universe model;
	private UniverseView view;
	public UniverseController(Universe model, UniverseView view){
		this.model = model;
		this.view = view;
		this.game = new GameOfLife(model.getSize(), this);
	}

	public void getNextGeneration(){
		this.model.getNextGeneration();
	}

	public void updateView(){
		// this.view.print(this.model.getBoard(), this.model.getSize(), this.model.getNumAlive());
		this.game.updateAliveNumber(this.model.getNumAlive());
		this.game.updateGenerationNumber(this.model.getGeneration());
		GameOfLife g = this.game;
		g.repaintGrid(model.getBoard());
	}

	public void reset() {
		model = new Universe(model.getSize());
	}

	public boolean isGamePaused(){
		return game.isPaused();
	}

	public void play(){
		while(!isGamePaused()){
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				break;
			}
			getNextGeneration();
			updateView();
		}
	}

	public boolean[] getCurrentGeneration() {
		return this.model.getBoard();
	}
}
