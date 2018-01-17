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

	public void placeOnGrid(Cell[][] grid, int x, int y, char orientationChose)
			throws Exception {
		if (orientationChose == 'H') {
			this.orientation = Orientation.HORIZONTAL;
		} else {
			this.orientation = Orientation.VERTICAL;
		}
		for (int i = 0; i < size(); i++) {
			while (true) {
				if (grid[y][x].hasShip == false) {
					grid[y][x].setShip(this);

					if (this.orientation == Orientation.HORIZONTAL) {
						x++;
					} else {
						y++;
					}
					break;
				}
				throw new Exception();
			}
		}
	}

	public void bomb() {
		hitsLeft--;
		if (isSunk()) {
			System.out.println("Oh no, my " + name() + "!");
		}
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

}
