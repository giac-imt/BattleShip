package battleship;

public class Ship {
	public enum Orientation {
		HORIZONTAL, VERTICAL
	}

	protected String name;
	protected int size;
	protected int hitsLeft;
	protected Orientation orientation;

	public Ship(String name, int size) {
		this.name = name;
		this.size = size;
		hitsLeft = size;
	}

	public String name() {
		return name;
	}

	public int size() {
		return size;
	}

	public boolean isSunk() {
		return hitsLeft <= 0;
	}

	public void placeOnGrid(Cell[][] grid, int x, int y, char orientationChose) {
		if(orientationChose == 'H') {
			this.orientation = Orientation.HORIZONTAL;
		} else {
			this.orientation = Orientation.VERTICAL;
		}
		System.out.println(grid[y][x]);
		for (int i = 0; i < size(); i++) {
			grid[y][x].setShip(this);
			if(this.orientation == Orientation.HORIZONTAL) {
				x++;
			} else {
				y++;				
			}
		}
	}

	public void bomb() {
		hitsLeft--;
		if (isSunk()) {
			System.out.println("Oh no, my " + name() + "!");
		}
	}
}
