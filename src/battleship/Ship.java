package battleship;

public class Ship {
	public enum Orientation {
		HORIZONTAL, VERTICAL
	}

	protected String name;
	protected int size;
	protected int hitsLeft;
	protected Orientation orientation;
	protected static int[][] takenCell = new int[Fleet.HEIGHT][Fleet.WIDTH];

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

	public void placeOnGrid(int x, int y, char orientationChose) throws Exception {
		if(orientationChose == 'H') {
			this.orientation = Orientation.HORIZONTAL;
		} else {
			this.orientation = Orientation.VERTICAL;
		}
		for (int i = 0; i < size(); i++) {
			while(true) {
				if(takenCell[y][x] != 1) {
					Fleet.grid[y][x].setShip(this);
					takenCell[y][x] = 1;
					
					if(this.orientation == Orientation.HORIZONTAL) {
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
