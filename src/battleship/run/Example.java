package battleship.run;

import battleship.Cell;
import battleship.Ship;

public class Example {
	public static final int DIMX = 8, DIMY = 8;

	public static void main(String[] args) {
		Cell[][] grid = new Cell[DIMX][DIMY];
		for (int i = 0; i < DIMX; i++) {
			for (int j = 0; j < DIMY; j++) {
				grid[i][j] = new Cell();
			}
		}

		Ship c = new Ship("Cruiser", 4);
		Ship f = new Ship("Frigate", 3);
		Ship d1 = new Ship("Destroyer", 2);
		Ship d2 = new Ship("Destroyer", 2);

		try {
			c.placeOnGrid(grid, 2, 0, 'V');
			f.placeOnGrid(grid, 0, 0, 'V');
			d1.placeOnGrid(grid, 2, 3, 'V');
			d2.placeOnGrid(grid, 7, 1, 'V');
		} catch (Exception e) {
		}

		grid[5][5].bomb(); // dans l'eau

		grid[0][0].bomb(); // touché
		grid[1][0].bomb(); // touché
		grid[2][0].bomb(); // frégate coulée !
	}
}
