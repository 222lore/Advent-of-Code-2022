/*
 * Day 8
 * 
 * 1st Star: 00:32:28 Rank: 6056
 * 2nd Star: 00:50:15 Rank: 5097
 * 
 * Coding is hard when it is unreadable and a bunch of loops.
 */

import java.io.*;
import java.util.*;
 
public class day8 {	
	public static void main(String[] args) {
        try {
            File file = new File("Advent-of-Code-2022\\input\\day8.txt");
            Scanner in = new Scanner(file);
            Scanner in2 = new Scanner(file);

            System.out.println("Part 1: " + part1(in));
            System.out.println("Part 2: " + part2(in2));
            in.close();
            in2.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
	}
 
	private static int part1(Scanner in) {
        int width = 99; // Hardcoded width
        int height = 99; // Hardcoded height
        int visible = 0;
        int i = 0;

        int[][] trees = new int[height][width];

        while (in.hasNextLine()) {
            String line = in.nextLine();
            for (int j = 0; j < line.length(); j++) {
                trees[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
            i++;
        }

        boolean canSee = false;
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {

                if (r == 0 || r == height - 1 || c == 0 || c == width - 1) {
                    visible++;
                }
                else {
                    // Check if all trees above current tree are shorter
                    canSee = true;
                    for (int u = r-1; u >= 0; u--) {
                        if (trees[r][c] <= trees[u][c]) {
                            canSee = false;
                            break;
                        }
                    }
                    if (canSee) {
                        visible++;
                        continue;
                    }
                    
                    // Check if all trees below current tree are shorter
                    canSee = true;
                    for (int d = r+1; d < height; d++) {
                        if (trees[r][c] <= trees[d][c]) {
                            canSee = false;
                            break;
                        }
                    }
                    if (canSee) {
                        visible++;
                        continue;
                    }

                    // Check if all trees to the left of current tree are shorter
                    canSee = true;
                    for (int left = c-1; left >= 0; left--) {
                        if (trees[r][c] <= trees[r][left]) {
                            canSee = false;
                            break;
                        }
                    }
                    if (canSee) {
                        visible++;
                        continue;
                    }

                    // Check if all trees to the right of current tree are shorter
                    canSee = true;
                    for (int right = c+1; right < width; right++) {
                        if (trees[r][c] <= trees[r][right]) {
                            canSee = false;
                            break;
                        }
                    }
                    if (canSee) {
                        visible++;
                        continue;
                    }
                }
            }
        }

        return visible;
    }

    private static int part2(Scanner in) {
        int width = 99; // Hardcoded width
        int height = 99; // Hardcoded height
        int visible = 0;
        int max = 0;
        int i = 0;

        int[][] trees = new int[height][width];

        while (in.hasNextLine()) {
            String line = in.nextLine();
            for (int j = 0; j < line.length(); j++) {
                trees[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
            i++;
        }

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                // Update max value
                if (visible > max) max = visible;

                // Tree on edge will always be 0
                if (r == 0 || r == height - 1 || c == 0 || c == width - 1) continue;
                else {
                    visible = 0;

                    // Check trees above current tree
                    int seeUp = 0;
                    for (int u = r-1; u >= 0; u--) {

                        if (trees[r][c] <= trees[u][c]) {
                            seeUp++;
                            break;
                        }
                        seeUp++;
                    }
                    
                    // Check trees below current tree  
                    int seeDown = 0;
                    for (int d = r+1; d < height; d++) {
 
                        if (trees[r][c] <= trees[d][c]) {
                            seeDown++;
                            break;
                        }
                        seeDown++;
                    }

                    // Check trees to the left of current tree
                    int seeLeft = 0;
                    for (int left = c-1; left >= 0; left--) {
   
                        if (trees[r][c] <= trees[r][left]) {
                            seeLeft++;
                            break;
                        }
                        seeLeft++;
                    }

                    // Check trees to the right of current tree
                    int seeRight = 0;
                    for (int right = c+1; right < width; right++) {
              
                        if (trees[r][c] <= trees[r][right]) {
                            seeRight++;
                            break;
                        }
                        seeRight++;
                    }

                    visible = seeUp * seeDown * seeLeft * seeRight;
                }
            }
        }

        return max;
    }
}