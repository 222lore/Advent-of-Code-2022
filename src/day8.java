/*
 * Day 8
 * 
 * 1st Star:  Rank: 
 * 2nd Star:  Rank: 
 * 
 * 
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
        int width = 99;
        int height = 99;
        int visible = 0;
        int i = 0;

        int[][] trees = new int[height][width];
        boolean[][] visibleTree = new boolean[height][width];

        while (in.hasNextLine()) {
            String line = in.nextLine();
            for (int j = 0; j < line.length(); j++) {
                trees[i][j] = Integer.parseInt(line.substring(j, j+1));
                visibleTree[i][j] = false;
            }
            i++;
        }
        //System.out.println(trees[1][1]);
        boolean canSee = false;
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {

                //System.out.println("r " + r + " c " + c);
                if (r == 0 || r == height - 1 || c == 0 || c == width - 1) {
                    visible++;
                    visibleTree[r][c] = true;
                }
                else {
                    canSee = true;
                    for (int u = r-1; u >= 0; u--) {
                        if (trees[r][c] <= trees[u][c]) {
                            canSee = false;
                            break;
                        }
                    }
                    if (canSee) {
                        //System.out.println("1^");
                        visible++;
                        continue;
                    }
                    
                    canSee = true;
                    for (int d = r+1; d < height; d++) {
                        if (trees[r][c] <= trees[d][c]) {
                            canSee = false;
                            break;
                        }
                    }
                    if (canSee) {
                        //System.out.println("2^");
                        visible++;
                        continue;
                    }

                    canSee = true;
                    for (int left = c-1; left >= 0; left--) {
                        if (trees[r][c] <= trees[r][left]) {
                            canSee = false;
                            break;
                        }
                    }
                    if (canSee) {
                        //System.out.println("3^");
                        visible++;
                        continue;
                    }

                    canSee = true;
                    for (int right = c+1; right < width; right++) {
                        if (trees[r][c] <= trees[r][right]) {
                            canSee = false;
                            break;
                        }
                    }
                    if (canSee) {
                        //System.out.println("4^");
                        visible++;
                        continue;
                    }
                    // canSee = true;
                    // for (int u = r-1; u >= 0; u--) {
                    //     if (trees[u][c] >= trees[u+1][c]) {
                    //         canSee = false;
                    //         break;
                    //     }
                    // }
                    // if (canSee) {
                    //     visible++;
                    //     continue;
                    // }
                    
                    // canSee = true;
                    // for (int d = r+1; d < height; d++) {
                    //     if (trees[d][c] >= trees[d-1][c]) {
                    //         canSee = false;
                    //         break;
                    //     }
                    // }
                    // if (canSee) {
                    //     visible++;
                    //     continue;
                    // }

                    // canSee = true;
                    // for (int left = c-1; left >= 0; left--) {
                    //     if (trees[r][left] >= trees[r][left+1]) {
                    //         canSee = false;
                    //         break;
                    //     }
                    // }
                    // if (canSee) {
                    //     visible++;
                    //     continue;
                    // }

                    // canSee = true;
                    // for (int right = c+1; right < width; right++) {
                    //     if (trees[r][right] >= trees[r][right-1]) {
                    //         if (c == 1 && r == 2) {
                    //             System.out.println(right);
                    //         }
                    //         canSee = false;
                    //         break;
                    //     }
                    // }
                    // if (canSee) {
                    //     visible++;
                    //     continue;
                    // }
                }
            }
        }

        return visible;
    }

    // private static boolean isVisible(boolean visibleTree[][], int startR, int startC) {
    //     boolean visible = false;

    //     if (visibleTree[startR-1][startC-1] || visibleTree[startR-1][startC] || visibleTree[startR-1][startC+1]
    //         || visibleTree[startR][startC-1] || visibleTree[startR][startC+1]
    //         || visibleTree[startR+1][startC-1] || visibleTree[startR+1][startC] || visibleTree[startR+1][startC+1]) {
    //             return true;
    //         }

    //     return visible;
    // }

    private static int part2(Scanner in) {
        int width = 99;
        int height = 99;
        int visible = 0;
        int max = 0;
        int i = 0;

        int[][] trees = new int[height][width];
        boolean[][] visibleTree = new boolean[height][width];

        while (in.hasNextLine()) {
            String line = in.nextLine();
            for (int j = 0; j < line.length(); j++) {
                trees[i][j] = Integer.parseInt(line.substring(j, j+1));
                visibleTree[i][j] = false;
            }
            i++;
        }
        //System.out.println(trees[1][1]);
        boolean canSee = false;
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (visible > max) {
                    max = visible;
                    //System.out.println(r + " " + c);
                }

                //System.out.println("r " + r + " c " + c);
                if (r == 0 || r == height - 1 || c == 0 || c == width - 1) {
                    //visible++;
                    visibleTree[r][c] = true;
                }
                else {
                    visible = 0;
                    int seeUp = 0;
                    int seeDown = 0;
                    int seeLeft = 0;
                    int seeRight = 0;
                    canSee = true;
                    for (int u = r-1; u >= 0; u--) {

                        if (trees[r][c] <= trees[u][c]) {
                            seeUp++;
                            canSee = false;
                            break;
                        }
                        seeUp++;
                    }
                    
                    // if (canSee) {
                    //     //System.out.println("1^");
                    //     visible++;
                    //     continue;
                    // }
                    
                    canSee = true;
                    for (int d = r+1; d < height; d++) {
 
                        if (trees[r][c] <= trees[d][c]) {
                            seeDown++;
                            canSee = false;
                            break;
                        }
                        seeDown++;
                    }
                    // if (canSee) {
                    //     //System.out.println("2^");
                    //     visible++;
                    //     continue;
                    // }

                    canSee = true;
                    for (int left = c-1; left >= 0; left--) {
   
                        if (trees[r][c] <= trees[r][left]) {
                            seeLeft++;
                            canSee = false;
                            break;
                        }
                        seeLeft++;
                    }
                    // if (canSee) {
                    //     //System.out.println("3^");
                    //     visible++;
                    //     continue;
                    // }

                    canSee = true;
                    for (int right = c+1; right < width; right++) {
              
                        if (trees[r][c] <= trees[r][right]) {
                            seeRight++;
                            canSee = false;
                            break;
                        }
                        seeRight++;
                    }
                    // if (canSee) {
                    //     //System.out.println("4^");
                    //     visible++;
                    //     continue;
                    // }

                    visible = seeUp * seeDown * seeLeft * seeRight;
                    //if (visible == 2) System.out.println(r + " " + c + ", " + seeUp + " " + seeDown + " " + seeLeft + " " + seeRight);
                    // canSee = true;
                    // for (int u = r-1; u >= 0; u--) {
                    //     if (trees[u][c] >= trees[u+1][c]) {
                    //         canSee = false;
                    //         break;
                    //     }
                    // }
                    // if (canSee) {
                    //     visible++;
                    //     continue;
                    // }
                    
                    // canSee = true;
                    // for (int d = r+1; d < height; d++) {
                    //     if (trees[d][c] >= trees[d-1][c]) {
                    //         canSee = false;
                    //         break;
                    //     }
                    // }
                    // if (canSee) {
                    //     visible++;
                    //     continue;
                    // }

                    // canSee = true;
                    // for (int left = c-1; left >= 0; left--) {
                    //     if (trees[r][left] >= trees[r][left+1]) {
                    //         canSee = false;
                    //         break;
                    //     }
                    // }
                    // if (canSee) {
                    //     visible++;
                    //     continue;
                    // }

                    // canSee = true;
                    // for (int right = c+1; right < width; right++) {
                    //     if (trees[r][right] >= trees[r][right-1]) {
                    //         if (c == 1 && r == 2) {
                    //             System.out.println(right);
                    //         }
                    //         canSee = false;
                    //         break;
                    //     }
                    // }
                    // if (canSee) {
                    //     visible++;
                    //     continue;
                    // }
                }
            }
        }

        return max;
    }

}


