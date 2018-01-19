package battleship;

public class Fleet {
	public static final int WIDTH = 10, HEIGHT = 10;
	
	public static Cell[][] grid = new Cell[WIDTH][HEIGHT];

	public static Ship[] tabShip = { new Ship("Cruiser", 4), new Ship("Frigate", 3), new Ship("Destroyer", 2), new Ship("Destroyer", 2) };
	
	public static Cell[][] makeGrid() {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
            	grid[i][j] = new Cell();
            }
        }
        return grid;
    }

	public static void showGrid() { showGrid(false); }

    public static void showGrid(boolean visibleShips) {
        System.out.print(' ');
        for (int x = 1; x <= WIDTH; x++) {
            System.out.printf(" %d", x);
        }
        System.out.println();

        char y = 'a';
        for (Cell[] eachRow : grid) {
            System.out.print(y);
            y = (char) ((int) y + 1);
            for (Cell eachCell : eachRow) {
                switch (eachCell.color(visibleShips)) {
                    case WATER:
                        System.out.print(" ~");
                        break;
                    case SHIP:
                        System.out.print(" #");
                        break;
                    case SPLASH:
                        System.out.print(" @");
                        break;
                    case FIRE:
                        System.out.print(" X");
                        break;
                }
            }
            System.out.println();
        }
    }

    public static boolean canPlaceShip(int x, int y, Ship.Orientation o) {
    	
    	return true;
    }
}
