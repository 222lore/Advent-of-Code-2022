/*
 * Day 10
 * 
 * 1st Star: 00:13:56 Rank: 2121
 * 2nd Star: 00:29:20 Rank: 2106
 * 
 * Was a pretty fun day, instructions a bit weird though. My part 2
 * solution doesn't 100% work but you can still figure out the
 * right answer lol.
 */

import java.io.*;
import java.util.*;
 
public class day10 {	
	public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\loren\\GitHub\\Advent-of-Code-2022\\input\\day10.txt");
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
        int out = 0;
        int cycle = 1;
        int x = 1;
        int toCheck = 20;

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] commands = line.split(" ");

            // Always need to increment cycle at least once
            cycle++;

            // If addx command, add to X then increment cycle
            if (commands[0].equals("addx")) {
                // Check if cycle matches a target cycle
                if (cycle == toCheck) {
                    out += x * cycle;
                    toCheck += 40;
                }

                cycle++; 
                x += Integer.parseInt(commands[1]);
            }

            // Check if cycle matches a target cycle
            if (cycle == toCheck) {
                out += x * cycle;
                toCheck += 40;
            }
        }

        return out;
    }
    
    private static String part2(Scanner in) {
        int cycle = 1;
        int x = 1;
        int cmpX = (cycle % 40 == 0) ? 40 : (cycle % 40);
        String out = "";

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] commands = line.split(" ");

            // Update current x pos
            cmpX = (cycle % 40 == 0) ? 40 : (cycle % 40);

            // If reached end of x-axis, move on to next row
            if ((cycle - 1) % 40 == 0) out += "\n";

            // Check if X is within range of current x pos
            if (Math.abs(x - cmpX) <= 1) {
                out += '#';
            }
            else {
                out += '.';
            }

            // If addx command, increment cycle then add to X
            if (commands[0].equals("addx")) {
                cycle++;
                x += Integer.parseInt(commands[1]);

                // Update current x pos
                cmpX = (cycle % 40 == 0) ? 40 : (cycle % 40);

                // If reached enf of x-axis, move on to next row
                if ((cycle - 1) % 40 == 0) out += "\n";

                // Check if X is within range of current x pos
                if (Math.abs(x - cmpX) <= 1) {
                    out += '#';
                }
                else {
                    out += '.';
                }
            }

            // Always need to increment cycle at least once
            cycle++;
        }

        return out;
    }
}