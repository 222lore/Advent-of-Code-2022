/*
 * Day 7
 * 
 * 1st Star:  Rank: 
 * 2nd Star:  Rank: 
 * 
 * 
 */

import java.io.*;
import java.util.*;
 
public class day7 {	
	public static void main(String[] args) {
        try {
            File file = new File("/home/student/git/Advent-of-Code-2022/input/day7.txt");
            Scanner in = new Scanner(file);
            Scanner in2 = new Scanner(file);

            // ArrayList<String> lines = new ArrayList<>();
            // int i = 0;

            // while (in.hasNextLine()) {  
            //     String line = in.nextLine();
            //     String[] commands = line.split(" ");
            //     if (commands[1].equals("cd") && !commands[2].equals("/") && !commands[2].equals("..")) line += ("" + i);

            //     i++;
            //     lines.add(line);
            //     System.out.println(line);
            // }

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
        boolean addSize = false;
        Folder head = null;
        Folder curfolder = null;
        Folder parent = null;
        boolean first = false;
        ArrayList<Dir> directories = new ArrayList<>();
        ArrayList<Folder> folders = new ArrayList<>();
        int cds = 0;

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] commands = line.split(" ");
            
            if (commands[1].equals("ls")) addSize = true;
            else if (commands[1].equals("cd")) {
                String name = commands[2];
                if (name.equals("..")) {
                    parent = parent.parent;
                    cds--;
                    addSize = false;
                    continue;
                }

                // lookup
                // curfolder = Folder.lookup(folders, name, cds);
                // if (curfolder == null) {
                //     //System.out.println("new dir " + name);
                //     curfolder = new Folder(name, cds);
                //     folders.add(curfolder);
                //     if (name.equals("/")) {
                //         head = Folder.lookup(folders, name, cds);
                //         parent = head;
                //         System.out.println("added head");
                //     }
                //     else {
                //         curfolder.addParent(parent);
                //         parent = curfolder;
                //     }
                // } 
                curfolder = new Folder(name, cds);
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

                cds++;
            }
            else if (commands[0].equals("dir")) {

                // Dir child = Dir.dirLookup(directories, commands[1]);
                // if (child == null) {
                //     child = new Dir(commands[1]);
                //     directories.add(child);
                //     curDir.addSubDir(child);
                // }
                // Dir child = new Dir(commands[1]);
                // directories.add(child);
                // curDir.addSubDir(child);
                // System.out.println("added child");
            }
            else {
                int size = Integer.parseInt(commands[0]);
                curfolder.updateSize(size);
            }
        }

        head.finalizeSize();
        return (head.solvePart1());
    }

    private static int part2(Scanner in) {
        boolean addSize = false;
        Folder head = null;
        Folder curfolder = null;
        Folder parent = null;
        boolean first = false;
        ArrayList<Dir> directories = new ArrayList<>();
        ArrayList<Folder> folders = new ArrayList<>();
        int cds = 0;

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] commands = line.split(" ");
            
            if (commands[1].equals("ls")) addSize = true;
            else if (commands[1].equals("cd")) {
                String name = commands[2];
                if (name.equals("..")) {
                    parent = parent.parent;
                    cds--;
                    addSize = false;
                    continue;
                }

                // lookup
                // curfolder = Folder.lookup(folders, name, cds);
                // if (curfolder == null) {
                //     //System.out.println("new dir " + name);
                //     curfolder = new Folder(name, cds);
                //     folders.add(curfolder);
                //     if (name.equals("/")) {
                //         head = Folder.lookup(folders, name, cds);
                //         parent = head;
                //         System.out.println("added head");
                //     }
                //     else {
                //         curfolder.addParent(parent);
                //         parent = curfolder;
                //     }
                // } 
                curfolder = new Folder(name, cds);
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

                cds++;
            }
            else if (commands[0].equals("dir")) {

                // Dir child = Dir.dirLookup(directories, commands[1]);
                // if (child == null) {
                //     child = new Dir(commands[1]);
                //     directories.add(child);
                //     curDir.addSubDir(child);
                // }
                // Dir child = new Dir(commands[1]);
                // directories.add(child);
                // curDir.addSubDir(child);
                // System.out.println("added child");
            }
            else {
                int size = Integer.parseInt(commands[0]);
                curfolder.updateSize(size);
            }
        }

        head.finalizeSize();

        int totalSize = head.size;
        int freeSpace = 70000000 - totalSize;
        int toFree = 30000000 - freeSpace;

        // System.out.println("total size " + totalSize + " free space " + freeSpace + " to free " + toFree);

        int solved = head.solvePart2(toFree);
        int answer = toFree + solved;
        return (answer);
    }

    static class Folder {
        private String name;
        private int cds;
        private int size;
        private Folder parent;
        private ArrayList<Folder> children;

        Folder(String name, int cds) {
            this.name = name;
            this.cds = cds;
            this.size = 0;
            this.parent = null;
            children = new ArrayList<>();
        }

        public void addParent(Folder parent) {
            this.parent = parent;
        }

        public int updateSize(int add) {
            this.size += add;
            return this.size;
        }

        public void addChild(Folder child) {
            this.children.add(child);
        }

        // public static Folder lookup(ArrayList<Folder> folders, String name, int cds) {
        //     for (Folder folder : folders) {
        //         if (name.equals(folder.name) && cds == folder.cds) {
        //             return folder;
        //         }
        //     }

        //     return null;
        // }

        // public static Folder lookup(ArrayList<Folder> folders, String name, int cds, Folder parent) {
        //     for (Folder folder : folders) {
        //         if (name.equals(folder.name) && cds == folder.cds) {
        //             return folder;
        //         }
        //     }

        //     return null;
        // }

        public void finalizeSize() {
            int sizes = this.size;

            for (Folder child : this.children) {
                child.finalizeSize();
                sizes += child.size;
            }

            this.size = sizes;
        }

        public int solvePart1() {
            int sizes = (this.size <= 100000) ? this.size : 0;

            for (Folder child : this.children) {
                sizes += child.solvePart1();
            }

            return sizes;
        }

        public int solvePart2(int toFree) {
            int diff = ((this.size - toFree) >= 0) ? (this.size - toFree) : Integer.MAX_VALUE;

            for (Folder child : this.children) {
                int childDiff = child.solvePart2(toFree);

                diff = diff < childDiff ? diff : childDiff;
            }

            return diff;
        }
    }

    static class Dir {
        private String name;

        private int size;
        private ArrayList<Dir> holds;

        Dir(String name) {
            this.name = name;
            this.size = 0;
            this.holds = new ArrayList<>();
        }

        public int updateSize(int add) {
            this.size += add;
            return this.size;
        }

        public void addSubDir(Dir subDir) {
            holds.add(subDir);
        }

        public void finalizeSize() {
            int sizes = this.size;

            for (Dir subDir : holds) {
                subDir.finalizeSize();
                sizes += subDir.size;
            }

            this.size = sizes;
        }

        public int solvePart1() {
            int sizes = (this.size <= 100000) ? this.size : 0;

            for (Dir subDir : holds) {
                sizes += subDir.solvePart1();
            }

            return sizes;
        }

        public static Dir dirLookup(ArrayList<Dir> directories, String name) {
            for (Dir dir : directories) {
                if (name.equals(dir.name)) return dir;
            }
            return null;
        } 
    }
}