/*
 * Day 7
 * 
 * 1st Star: 00:59:16 Rank: 5919
 * 2nd Star: 01:16:36 Rank: 6441
 * 
 * Had fun creating a class, realizing it didn't work, deleting it,
 * creating another class, then debugging for 1 hour.
 */

import java.io.*;
import java.util.*;
 
public class day7 {	
	public static void main(String[] args) {
        try {
            File file = new File("Advent-of-Code-2022\\input\\day7.txt");
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
        Folder head = null;
        Folder curfolder = null;
        Folder parent = null;
        ArrayList<Folder> folders = new ArrayList<>();

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] commands = line.split(" ");
            
            if (commands[1].equals("ls")) continue;
            else if (commands[1].equals("cd")) {
                String name = commands[2];
                if (name.equals("..")) {
                    parent = parent.parent;
                    continue;
                }

                // Line was cd, create new Folder object
                curfolder = new Folder(); //name, cds);
                if (name.equals("/")) {
                    head = curfolder;
                    parent = head;
                }
                else {
                    curfolder.addParent(parent);
                    parent.addChild(curfolder);
                    parent = curfolder;
                }

                folders.add(curfolder);

            }
            else if (commands[0].equals("dir")) continue;
            else {
                // Line is a file, add file size to folder object
                int size = Integer.parseInt(commands[0]);
                curfolder.updateSize(size);
            }
        }

        // Part 1 solution
        head.finalizeSize();
        return (head.solvePart1());
    }

    private static int part2(Scanner in) {
        Folder head = null;
        Folder curfolder = null;
        Folder parent = null;
        ArrayList<Folder> folders = new ArrayList<>();

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] commands = line.split(" ");
            
            if (commands[1].equals("ls")) continue;
            else if (commands[1].equals("cd")) {
                String name = commands[2];
                if (name.equals("..")) {
                    parent = parent.parent;
                    continue;
                }

                // Line was cd, create new Folder object
                curfolder = new Folder(); // (name, cds);
                if (name.equals("/")) {
                    head = curfolder;
                    parent = head;
                }
                else {
                    curfolder.addParent(parent);
                    parent.addChild(curfolder);
                    parent = curfolder;
                }

                folders.add(curfolder);

            }
            else if (commands[0].equals("dir")) continue;
            else {
                // Line is a file, add file size to folder object
                int size = Integer.parseInt(commands[0]);
                curfolder.updateSize(size);
            }
        }

        // Part 2 solution
        head.finalizeSize();

        int totalSize = head.size;
        int freeSpace = 70000000 - totalSize;
        int toFree = 30000000 - freeSpace;

        int solved = head.solvePart2(toFree);
        int answer = toFree + solved;
        return (answer);
    }

    static class Folder {
        private int size;
        private Folder parent;
        private ArrayList<Folder> children;

        Folder() {
            this.size = 0;
            this.parent = null;
            children = new ArrayList<>();
        }

        // Add a parent folder
        public void addParent(Folder parent) {
            this.parent = parent;
        }

        // Update the size of the folder
        public int updateSize(int add) {
            this.size += add;
            return this.size;
        }

        // Add a child folder
        public void addChild(Folder child) {
            this.children.add(child);
        }

        // Finalize the size of the folder (folder size = folder size + size of children)
        public void finalizeSize() {
            int sizes = this.size;

            for (Folder child : this.children) {
                child.finalizeSize();
                sizes += child.size;
            }

            this.size = sizes;
        }

        // Solve part 1, if folder size is less than 100000, add it to return value
        public int solvePart1() {
            int sizes = (this.size <= 100000) ? this.size : 0;

            for (Folder child : this.children) {
                sizes += child.solvePart1();
            }

            return sizes;
        }

        // Solve part 2, return smallest difference between a folder's size and space needed to free
        public int solvePart2(int toFree) {
            int diff = ((this.size - toFree) >= 0) ? (this.size - toFree) : Integer.MAX_VALUE;

            for (Folder child : this.children) {
                int childDiff = child.solvePart2(toFree);

                diff = diff < childDiff ? diff : childDiff;
            }

            return diff;
        }
    }
}