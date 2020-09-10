package life;

import java.util.Random;

public class Universe {
	public int generation;
	private boolean[] board;
	private int size;
	private int numAlive;
	public Universe(int size) {
		this.size = size;
		this.board = new boolean[size*size];
		Random random = new Random();
		for (int i = 0; i < size*size; i++) {
			this.board[i] = random.nextBoolean();
		}
		this.numAlive = 0;
		this.generation = 0;
	}

	// gets a location relative to a cell
	public boolean getLocation(int x, int y){
		int row = y, col = x;
		if(col < 0){
			col = this.size-1;
		}
		if(col >= this.size){
			col = 0;
		}
		if(row < 0){
			row = this.size-1;
		}
		if(row >= this.size){
			row = 0;
		}
		return this.board[col+(row*this.size)];
	}



	public void getNextGeneration() {
		int s = this.getSize();
		boolean[] tempBoard = new boolean[s*s];

		int alive = 0;

		for(int y = 0; y < s; y++){
			for(int x = 0; x < s; x++){
				boolean loc = this.getLocation(x,y);
				int neighbours = this.getNeighbours(x,y);

				if(!loc && neighbours == 3){ // revive a cell
					tempBoard[x+(y*this.size)]= true;
					alive++;
				} else { // determine a live cells fate
					tempBoard[x+(y*this.size)] = loc && (neighbours == 2 || neighbours == 3);
					alive += (loc && (neighbours == 2 || neighbours == 3))? 1: 0;
				}
			}
		}
		this.numAlive = alive;
		this.board = tempBoard;
		this.generation++;
	}

	private int getNeighbours(int x, int y ) {
		int neighbours = 0;

		// check northwest
		if (this.getLocation(x-1, y-1)) { neighbours++; }
		// check north
		if (this.getLocation(x, y-1)) { neighbours++; }
		// check northeast
		if (this.getLocation(x+1, y-1)) { neighbours++; }

		// check west
		if (this.getLocation(x-1, y)) { neighbours++; }
		// check east
		if (this.getLocation(x+1, y)) { neighbours++; }

		// check southwest
		if (this.getLocation(x-1, y+1)) { neighbours++; }
		// check south
		if (this.getLocation(x, y+1)) { neighbours++; }
		// check southeast
		if (this.getLocation(x+1, y+1)) { neighbours++; }

		return neighbours;
	}
	public int getSize(){
		return this.size;
	}

	public boolean[] getBoard() {
		return board;
	}

	public int getNumAlive() {
		return this.numAlive;
	}

	public int getGeneration(){
		return this.generation;
	}
}
