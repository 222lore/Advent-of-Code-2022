/*
 * Day 9
 * 
 * 1st Star: 00:44:20 Rank: 6399 
 * 2nd Star: 00:55:56 Rank: 3893
 * 
 * It is very easy to misspell variable names when they are two characters long.
 * I had a very messy solution at first (9 variables each for the x and y pos
 * of all the tails for part 2, and using a 2d array) but cleaned it up a bit.
 */

import java.io.*;
import java.util.*;
 
public class day9 {	
	public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\loren\\GitHub\\Advent-of-Code-2022\\input\\day9.txt");
            Scanner in = new Scanner(file);
            Scanner in2 = new Scanner(file);

            System.out.println("Part 1: " + part1(in));
            System.out.println("Part 2: " + part2(in2));
            in.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
	}
    
    private static int part1(Scanner in) {
        int xH = 0;
        int yH = 0;
        int[] tails = new int[] {xH, yH};
        int endTail = 0; // Only 1 tail for part 1
        ArrayList<String> positions = new ArrayList<>();

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] commands = line.split(" ");
            int move = Integer.parseInt(commands[1]);

            // Move head down
            if (commands[0].equals("D")) {
                for (int i = 0; i < move; i++) {
                    yH += 1;
                    
                    tails = moveTails(xH, yH, tails); // Update tail
                    String moveStr = tails[endTail] + " " + tails[endTail + 1];
                    if (!positions.contains(moveStr)) positions.add(moveStr); // Update unique tail positions
                }
            }

            // Move head up
            else if (commands[0].equals("U")) {
                for (int i = 0; i < move; i++) {
                    yH -= 1;
                    
                    tails = moveTails(xH, yH, tails); 
                    String moveStr = tails[endTail] + " " + tails[endTail + 1];
                    if (!positions.contains(moveStr)) positions.add(moveStr); 
                }
            }

            // Move head right
            else if (commands[0].equals("R")) {
                for (int i = 0; i < move; i++) {
                    xH += 1;
                    
                    tails = moveTails(xH, yH, tails); 
                    String moveStr = tails[endTail] + " " + tails[endTail + 1];
                    if (!positions.contains(moveStr)) positions.add(moveStr);
                }
            }

            // Move head left
            else if (commands[0].equals("L")) {
                for (int i = 0; i < move; i++) {
                    xH -= 1;
                    
                    tails = moveTails(xH, yH, tails); 
                    String moveStr = tails[endTail] + " " + tails[endTail + 1];
                    if (!positions.contains(moveStr)) positions.add(moveStr);
                }
            }
        }
        
        return positions.size(); // Return number of unique positions the tail has been
    }

	private static int part2(Scanner in) {
        int xH = 0;
        int yH = 0;
        int[] tails = new int[] {xH, yH, xH, yH, xH, yH, xH, yH, xH, yH, xH, yH, xH, yH, xH, yH, xH, yH};
        int endTail = 16; // 9th tail, (9-1) * 2
        ArrayList<String> positions = new ArrayList<>();

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] commands = line.split(" ");
            int move = Integer.parseInt(commands[1]);

            // Move head down
            if (commands[0].equals("D")) {
                for (int i = 0; i < move; i++) {
                    yH += 1;
                    
                    tails = moveTails(xH, yH, tails); // Update tails
                    String moveStr = tails[endTail] + " " + tails[endTail + 1];
                    if (!positions.contains(moveStr)) positions.add(moveStr); // Update unique end of tail positions
                }
            }

            // Move head up
            else if (commands[0].equals("U")) {
                for (int i = 0; i < move; i++) {
                    yH -= 1;
                    
                    tails = moveTails(xH, yH, tails);
                    String moveStr = tails[endTail] + " " + tails[endTail + 1];
                    if (!positions.contains(moveStr)) positions.add(moveStr);
                }
            }

            // Move head right
            else if (commands[0].equals("R")) {
                for (int i = 0; i < move; i++) {
                    xH += 1;
                    
                    tails = moveTails(xH, yH, tails);
                    String moveStr = tails[endTail] + " " + tails[endTail + 1];
                    if (!positions.contains(moveStr)) positions.add(moveStr);
                }
            }

            // Move head left
            else if (commands[0].equals("L")) {
                for (int i = 0; i < move; i++) {
                    xH -= 1;
                    
                    tails = moveTails(xH, yH, tails);
                    String moveStr = tails[endTail] + " " + tails[endTail + 1];
                    if (!positions.contains(moveStr)) positions.add(moveStr);
                }
            }
        }

        return positions.size(); // Return number of unique positions the end of the tail has been
    }

    // Return the position of the 1 tail following a head of a rope moving
    private static int[] moveTail(int xH, int yH, int xT, int yT) {
        int[] tail = new int[] {xT, yT};

        // Tail is touching head
        if (Math.abs(xH - xT) <= 1 && Math.abs(yH - yT) <= 1) return tail;

        // Tail is vertically far from head
        else if (xH == xT) {
            if (yH > yT) {
                tail[1]++;
            }
            else {
                tail[1]--;
            }
        }

        // Tail is horizontally far from head
        else if (yH == yT) {
            if (xH > xT) {
                tail[0]++;
            }
            else {
                tail[0]--;
            }
        }

        // Tail is diagonally far from head
        else {
            if (xH > xT) {
                tail[0]++;
            }
            else {
                tail[0]--;
            }
            
            if (yH > yT) {
                tail[1]++;
            }
            else {
                tail[1]--;
            }
        }

        return tail;
    }

    // Return the positions of multiple tails following a head of a rope moving
    private static int[] moveTails(int xH, int yH, int[] tails) {
        int[] iTail = moveTail(xH, yH, tails[0], tails[1]);
        tails[0] = iTail[0];
        tails[1] = iTail[1];

        for (int i = 0; i < tails.length-3; i += 2) {
            iTail = moveTail(tails[i], tails[i+1], tails[i+2], tails[i+3]);
            tails[i+2] = iTail[0];
            tails[i+3] = iTail[1];
        }

        return tails;
    }
}