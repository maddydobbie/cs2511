/**
 *
 */
package unsw.automata;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLife {
	
	private BooleanProperty array[][];

    public GameOfLife() {
        // At the start all cells are dead
    	this.array = new BooleanProperty[10][10];
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			this.array[i][j] = new SimpleBooleanProperty(false);
    		}
    	}
    	
    }

    public void ensureAlive(int x, int y) {
    	this.array[x][y].set(true);
    }

    public void ensureDead(int x, int y) {
    	this.array[x][y].set(false);
    }

    public boolean isAlive(int x, int y) {
        return this.array[x][y].get();
    }

    public void tick() {

    	BooleanProperty[][] temp = new BooleanProperty[10][10];
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			temp[i][j] = new SimpleBooleanProperty(false);
    		}
    	}
    	
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			int neighbours = numNeighbours(i, j);
    			// Implementing next generation
    			// Cell is live but under-populated (dies)
    			if (this.array[i][j].get() == true && neighbours < 2) temp[i][j].set(false);
    			// Cell is live but over-populated (dies)
    			else if (this.array[i][j].get() == true && neighbours > 3) temp[i][j].set(false);
    			// Cell is dead but correctly populated (lives)
    			else if (this.array[i][j].get() == false && neighbours == 3) temp[i][j].set(true);
    			// Else, cell stays the same
    			else temp[i][j] = this.array[i][j];
    		}
    	}
    	
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			this.array[i][j].set(temp[i][j].get());
    		}
    	}
    }
    
    public int numNeighbours(int i, int j) {
    	int neighbours = 0;
    	for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				int row, col = 0;
				// If at centre, continue
				if (x == 0 && y == 0) continue;
				// Check x cood is in range (dealing with edge of array)
				if (x + i < 0) row = x + i + 10;
				else if (x + i > 9) row = x + i - 10;
				else row = x + i;
				// Check y cood is in range (dealing with edge of array)
				if (y + j < 0) col = y + j + 10;
				else if (y + j > 9) col = y + j - 10;
				else col = y + j;
				// Increment neighbours if cell is true
				if (this.array[row][col].get() == true) neighbours++;
			}
		}
    	return neighbours;
    }

    public BooleanProperty cellProperty(int x, int y) {
    	return this.array[x][y];
    }
}
