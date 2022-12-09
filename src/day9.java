/*
 * Day 9
 * 
 * 1st Star: Rank: 
 * 2nd Star: Rank: 
 * 
 * 
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
        int GRID_SIZE = 50000;
        int xH = GRID_SIZE/2;
        int yH = GRID_SIZE/2;
        int xT = xH;
        int yT = yH;
        boolean[][] grid = new boolean[GRID_SIZE][GRID_SIZE]; // very bad practice
        int count = 0;

        grid[xT][yT] = true;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] commands = line.split(" ");
            int move = Integer.parseInt(commands[1]);
            if (commands[0].equals("D")) {
                for (int i = 0; i < move; i++) {
                    yH += 1;
                    
                    int[] movement = moveTail(xH, yH, xT, yT);
                    xT = movement[0];
                    yT = movement[1];
                    grid[xT][yT] = true;
                }
            }

            if (commands[0].equals("U")) {
                for (int i = 0; i < move; i++) {
                    yH -= 1;
                    
                    int[] movement = moveTail(xH, yH, xT, yT);
                    xT = movement[0];
                    yT = movement[1];
                    grid[xT][yT] = true;
                }
            }

            if (commands[0].equals("R")) {
                for (int i = 0; i < move; i++) {
                    xH += 1;
                    
                    int[] movement = moveTail(xH, yH, xT, yT);
                    xT = movement[0];
                    yT = movement[1];
                    grid[xT][yT] = true;
                }
            }

            if (commands[0].equals("L")) {
                for (int i = 0; i < move; i++) {
                    xH -= 1;
                    
                    int[] movement = moveTail(xH, yH, xT, yT);
                    xT = movement[0];
                    yT = movement[1];
                    grid[xT][yT] = true;
                }
            }
        }
        
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j]) count++;
            }
        }

        return count;
    }

	private static int part2(Scanner in) {
        int GRID_SIZE = 50000;
        int xH = GRID_SIZE/2;
        int yH = GRID_SIZE/2;
        int x1 = xH;
        int y1 = yH;
        int x2 = xH;
        int y2 = yH;
        int x3 = xH;
        int y3 = yH;
        int x4 = xH;
        int y4 = yH;
        int x5 = xH;
        int y5 = yH;
        int x6 = xH;
        int y6 = yH;
        int x7 = xH;
        int y7 = yH;
        int x8 = xH;
        int y8 = yH;
        int x9 = xH;
        int y9 = yH;

        boolean[][] grid = new boolean[GRID_SIZE][GRID_SIZE]; // very bad practice
        int count = 0;

        grid[x9][y9] = true;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] commands = line.split(" ");
            int move = Integer.parseInt(commands[1]);
            if (commands[0].equals("D")) {
                for (int i = 0; i < move; i++) {
                    yH += 1;
                    
                    int[] movement = moveTail(xH, yH, x1, y1);
                    x1 = movement[0];
                    y1 = movement[1];
                    movement = moveTail(x1, y1, x2, y2);
                    x2 = movement[0];
                    y2 = movement[1];
                    movement = moveTail(x2, y2, x3, y3);
                    x3 = movement[0];
                    y3 = movement[1];
                    movement = moveTail(x3, y3, x4, y4);
                    x4 = movement[0];
                    y4 = movement[1];
                    movement = moveTail(x4, y4, x5, y5);
                    x5 = movement[0];
                    y5 = movement[1];
                    movement = moveTail(x5, y5, x6, y6);
                    x6 = movement[0];
                    y6 = movement[1];
                    movement = moveTail(x6, y6, x7, y7);
                    x7 = movement[0];
                    y7 = movement[1];
                    movement = moveTail(x7, y7, x8, y8);
                    x8 = movement[0];
                    y8 = movement[1];
                    movement = moveTail(x8, y8, x9, y9);
                    x9 = movement[0];
                    y9 = movement[1];
                    grid[x9][y9] = true;
                }
            }

            if (commands[0].equals("U")) {
                for (int i = 0; i < move; i++) {
                    yH -= 1;
                    
                    int[] movement = moveTail(xH, yH, x1, y1);
                    x1 = movement[0];
                    y1 = movement[1];
                    movement = moveTail(x1, y1, x2, y2);
                    x2 = movement[0];
                    y2 = movement[1];
                    movement = moveTail(x2, y2, x3, y3);
                    x3 = movement[0];
                    y3 = movement[1];
                    movement = moveTail(x3, y3, x4, y4);
                    x4 = movement[0];
                    y4 = movement[1];
                    movement = moveTail(x4, y4, x5, y5);
                    x5 = movement[0];
                    y5 = movement[1];
                    movement = moveTail(x5, y5, x6, y6);
                    x6 = movement[0];
                    y6 = movement[1];
                    movement = moveTail(x6, y6, x7, y7);
                    x7 = movement[0];
                    y7 = movement[1];
                    movement = moveTail(x7, y7, x8, y8);
                    x8 = movement[0];
                    y8 = movement[1];
                    movement = moveTail(x8, y8, x9, y9);
                    x9 = movement[0];
                    y9 = movement[1];
                    grid[x9][y9] = true;
                }
            }

            if (commands[0].equals("R")) {
                for (int i = 0; i < move; i++) {
                    xH += 1;
                    
                    int[] movement = moveTail(xH, yH, x1, y1);
                    x1 = movement[0];
                    y1 = movement[1];
                    movement = moveTail(x1, y1, x2, y2);
                    x2 = movement[0];
                    y2 = movement[1];
                    movement = moveTail(x2, y2, x3, y3);
                    x3 = movement[0];
                    y3 = movement[1];
                    movement = moveTail(x3, y3, x4, y4);
                    x4 = movement[0];
                    y4 = movement[1];
                    movement = moveTail(x4, y4, x5, y5);
                    x5 = movement[0];
                    y5 = movement[1];
                    movement = moveTail(x5, y5, x6, y6);
                    x6 = movement[0];
                    y6 = movement[1];
                    movement = moveTail(x6, y6, x7, y7);
                    x7 = movement[0];
                    y7 = movement[1];
                    movement = moveTail(x7, y7, x8, y8);
                    x8 = movement[0];
                    y8 = movement[1];
                    movement = moveTail(x8, y8, x9, y9);
                    x9 = movement[0];
                    y9 = movement[1];
                    grid[x9][y9] = true;
                }
            }

            if (commands[0].equals("L")) {
                for (int i = 0; i < move; i++) {
                    xH -= 1;
                    
                    int[] movement = moveTail(xH, yH, x1, y1);
                    x1 = movement[0];
                    y1 = movement[1];
                    movement = moveTail(x1, y1, x2, y2);
                    x2 = movement[0];
                    y2 = movement[1];
                    movement = moveTail(x2, y2, x3, y3);
                    x3 = movement[0];
                    y3 = movement[1];
                    movement = moveTail(x3, y3, x4, y4);
                    x4 = movement[0];
                    y4 = movement[1];
                    movement = moveTail(x4, y4, x5, y5);
                    x5 = movement[0];
                    y5 = movement[1];
                    movement = moveTail(x5, y5, x6, y6);
                    x6 = movement[0];
                    y6 = movement[1];
                    movement = moveTail(x6, y6, x7, y7);
                    x7 = movement[0];
                    y7 = movement[1];
                    movement = moveTail(x7, y7, x8, y8);
                    x8 = movement[0];
                    y8 = movement[1];
                    movement = moveTail(x8, y8, x9, y9);
                    x9 = movement[0];
                    y9 = movement[1];
                    grid[x9][y9] = true;
                }
            }
        }
        
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j]) count++;
            }
        }

        return count;
    }

    private static int[] moveTail(int xH, int yH, int xT, int yT) {
        int[] tail = new int[] {xT, yT};

        if (Math.abs(xH - xT) <= 1 && Math.abs(yH - yT) <= 1) return tail;

        else if (xH == xT) {
            if (yH > yT) {
                tail[1] = yT + 1;
            }
            else {
                tail[1] = yT - 1;
            }
        }
        else if (yH == yT) {
            if (xH > xT) {
                tail[0] = xT + 1;
            }
            else {
                tail[0] = xT - 1;
            }
        }
        else {
            if (xH > xT) {
                tail[0] = xT + 1;
            }
            else {
                tail[0] = xT - 1;
            }
            
            if (yH > yT) {
                tail[1] = yT + 1;
            }
            else {
                tail[1] = yT - 1;
            }
        }

        return tail;
    }
}