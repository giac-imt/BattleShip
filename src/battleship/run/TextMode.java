package battleship.run;

import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import battleship.Cell;
import battleship.Ship;

public class TextMode {
    public static final int WIDTH = 10, HEIGHT = 10;
    protected static final Pattern answerPattern = Pattern.compile("([a-j])\\s*(\\d+)|q", Pattern.CASE_INSENSITIVE);
    protected static final Pattern answerPatternBoatPosition = Pattern.compile("([a-j])*(\\d+)*([h-v])", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) {
        Cell[][] grid = makeGrid();
        placeShips(grid);
        play(grid);
    }

    protected static Cell[][] makeGrid() {
        Cell[][] grid = new Cell[HEIGHT][WIDTH];
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                grid[i][j] = new Cell();
            }
        }
        return grid;
    }

    protected static void showGrid(Cell[][] grid) { showGrid(grid, false); }

    protected static void showGrid(Cell[][] grid, boolean visibleShips) {
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
    
    protected static boolean isFinish(Cell[][] grid) {
    	for (Cell[] eachRow : grid) {
            for (Cell eachCell : eachRow) {
            	if(eachCell.color(true) == Cell.Color.SHIP) {
            		return false;
            	}
        	}
        }
    	return true;
    }

    protected static void placeShips(Cell[][] grid) {        
        Ship[] tabShip = { new Ship("Cruiser", 4), new Ship("Frigate", 3), new Ship("Destroyer", 2), new Ship("Destroyer", 2) };
        
        for(Ship ship : tabShip) {
        	
            while(true) {
	            try {
	            	MatchResult position = askBoatPosition();
		            char letter = position.group(1).toUpperCase().charAt(0);
		            String number = position.group(2);
		            char orientation = position.group(3).toUpperCase().charAt(0);
		            int y = (int) letter - (int) 'A';
		            int x = Integer.valueOf(number) - 1;
	                ship.placeOnGrid(grid, x, y, orientation);
	                break;
	            } catch (ArrayIndexOutOfBoundsException e) {
	                System.out.println("Invalid coordinates: ");
	            } catch (Exception e) {
	                System.out.println("Position already chosen: ");
				}
            }
        }
    }

    protected static void play(Cell[][] grid) {
        while (true) {
            showGrid(grid);
        	if(isFinish(grid)) {
        		System.out.println("Nice, you win !");
        		break;
        	}
            MatchResult answer = askNextMove();
            if (answer.group(0).equals("Q")) {
                showGrid(grid, true);
                System.out.println("Ok, bye! Here's the final state of affairs:");
                break;
            } else {
                char letter = answer.group(1).toUpperCase().charAt(0);
                String number = answer.group(2);
                int y = (int) letter - (int) 'A';
                int x = Integer.valueOf(number) - 1;
                try {
                    grid[y][x].bomb();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid coordinates: " + answer);
                }
            }
        }
    }

    protected static MatchResult askNextMove() {
        while (true) {
            System.out.println("Drop a bomb where? [letter+number, or Q to give up]");
            String typedAnswer = new Scanner(System.in).nextLine().toUpperCase().trim();
            Matcher answerMatch = answerPattern.matcher(typedAnswer);
            if (answerMatch.matches()) {
                return answerMatch.toMatchResult();
            }
            System.out.println("Sorry, didn't understand that…");
        }
    }
    
    protected static MatchResult askBoatPosition() {
        while (true) {
            System.out.println("Where do you want to place it ? [letter+number+orientation] (orientation : H or V)");
            String typedAnswer = new Scanner(System.in).nextLine().toUpperCase().trim();
            Matcher answerMatch = answerPatternBoatPosition.matcher(typedAnswer);
            if (answerMatch.matches()) {
                return answerMatch.toMatchResult();
            }
            System.out.println("Sorry, didn't understand that…");
        }
    }
}
