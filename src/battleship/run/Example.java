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

		Ship c = new Ship("Cruiser", 4, Ship.Orientation.VERTICAL);
		c.placeOnGrid(grid, 2, 0);

		Ship f = new Ship("Frigate", 3, Ship.Orientation.VERTICAL);
		f.placeOnGrid(grid, 0, 0);

		Ship d1 = new Ship("Destroyer", 2, Ship.Orientation.VERTICAL);
		d1.placeOnGrid(grid, 2, 3);

		Ship d2 = new Ship("Destroyer", 2, Ship.Orientation.VERTICAL);
		d2.placeOnGrid(grid, 7, 1);

		grid[5][5].bomb(); // dans l'eau

		grid[0][0].bomb(); // touché
		grid[0][1].bomb(); // touché
		grid[0][2].bomb(); // frégate coulée !
	}
}
