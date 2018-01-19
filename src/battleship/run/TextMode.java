package battleship.run;

import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import battleship.Cell;
import battleship.Ship;
import battleship.Fleet;

public class TextMode {
    protected static final Pattern answerPattern = Pattern.compile("([a-j])\\s*(\\d+)|q", Pattern.CASE_INSENSITIVE);
    
    protected static final Pattern answerPatternBoatPosition = Pattern.compile("([a-j])*(\\d+)*([h-v])", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) {
        Fleet.makeGrid();
        placeShips();
        play();
    }
    
    protected static boolean isFinish() {
    	for (Cell[] eachRow : Fleet.grid) {
            for (Cell eachCell : eachRow) {
            	if(eachCell.color(true) == Cell.Color.SHIP) {
            		return false;
            	}
        	}
        }
    	return true;
    }

    protected static void placeShips() {
        
        for(Ship ship : Fleet.tabShip) {
        	
            while(true) {
	            try {
	            	MatchResult position = askBoatPosition();
		            char letter = position.group(1).toUpperCase().charAt(0);
		            String number = position.group(2);
		            char orientation = position.group(3).toUpperCase().charAt(0);
		            int y = (int) letter - (int) 'A';
		            int x = Integer.valueOf(number) - 1;
	                ship.placeOnGrid(x, y, orientation);
	                break;
	            } catch (ArrayIndexOutOfBoundsException e) {
	                System.out.println("Invalid coordinates: ");
	            } catch (Exception e) {
	                System.out.println("Position already chosen: ");

				}
			}
		}
	}

    protected static void play() {
        while (true) {
            Fleet.showGrid();
        	if(isFinish()) {
        		System.out.println("Nice, you win !");
        		break;
        	}
            MatchResult answer = askNextMove();
            if (answer.group(0).equals("Q")) {
                Fleet.showGrid(true);
                System.out.println("Ok, bye! Here's the final state of affairs:");
                break;
            } else {
                char letter = answer.group(1).toUpperCase().charAt(0);
                String number = answer.group(2);
                int y = (int) letter - (int) 'A';
                int x = Integer.valueOf(number) - 1;
                try {
                	Fleet.grid[y][x].bomb();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid coordinates: " + answer);
                }
            }
        }
    }

    @SuppressWarnings("resource")
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
    
    @SuppressWarnings("resource")
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
