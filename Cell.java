package battleship;

public class Cell {
	public enum Color {
		WATER, SHIP, SPLASH, FIRE
	}

	public Ship ship;
	protected boolean bombed;
	protected boolean hasShip = false;

	public Ship getShip() {
		return this.ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
		this.hasShip = true;
	}

	public void bomb() {
		if (!bombed) {
			bombed = true;
			if (ship == null) {
				System.out.println("Missed.");
			} else {
				System.out.println("Touché!");
				ship.bomb();
			}
		}
	}

	public Color color(boolean visibleShips) {
		if (ship == null) {
			return bombed ? Color.SPLASH : Color.WATER;
		} else {
			Color shipColor = visibleShips ? Color.SHIP : Color.WATER;
			return bombed ? Color.FIRE : shipColor;
		}
	}
}
