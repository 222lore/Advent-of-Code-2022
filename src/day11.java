/*
 * Day 11
 * 
 */

import java.io.*;
import java.util.*;
 
public class day11 {	
	public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\loren\\GitHub\\Advent-of-Code-2022\\input\\day11.txt");
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
        int rounds = 20;
        int playtime1 = 0;
        int playtime2 = 0;

        ArrayList<Monkey> monkeys = Monkey.scanMonkeys(in);
        monkeys = Monkey.play(monkeys, rounds, 1);

        for (Monkey monkey : monkeys) {
            if (monkey.playtime > playtime1) {
                playtime2 = playtime1;
                playtime1 = monkey.playtime;
            }
            else if (monkey.playtime > playtime2) {
                playtime2 = monkey.playtime;
            }
        }

        return playtime1 * playtime2;
    }

    private static long part2(Scanner in) {
        int rounds = 10000;
        long playtime1 = 0;
        long playtime2 = 0;

        ArrayList<Monkey> monkeys = Monkey.scanMonkeys(in);
        monkeys = Monkey.play(monkeys, rounds, 2);

        for (Monkey monkey : monkeys) {
            //System.out.println(monkey.playtime);
            if (monkey.playtime > playtime1) {
                playtime2 = playtime1;
                playtime1 = monkey.playtime;
            }
            else if (monkey.playtime > playtime2) {
                playtime2 = monkey.playtime;
            }
        }

        return playtime1 * playtime2;   
    }

    private static class Monkey {
        Queue<Integer> items;
        char operation;
        int operand;
        int test;
        int[] testOut;
        int playtime;
        static int mods = 1;

        Monkey(Queue<Integer> items, char operation, int operand, int test, int[] testOut) {
            this.items = items;
            this.operation = operation;
            this.operand = operand;
            this.test = test;
            this.testOut = testOut;

            this.playtime = 0;
        }

        private boolean addItem(int item) {
            return this.items.add(item);
        }

        private int removeItem() {
            playtime++;
            return this.items.remove();
        }

        public static ArrayList<Monkey> scanMonkeys(Scanner in) {
            ArrayList<Monkey> monkeys = new ArrayList<>();
            Monkey.mods = 1;

            while (in.hasNextLine()) {
                Queue<Integer> myitems = new LinkedList<>();
                char myoperation = '+';
                int myoperand = 0;
                int mytest = 0;
                int[] mytestOut = new int [2];

                // Monkey x:
                String line = in.nextLine();
                    
                // Starting items: x, y, z
                String[] startItems = in.nextLine().split(" ");
                for (int i = 4; i < startItems.length; i++) {
                    myitems.add(Integer.parseInt(startItems[i].split(",")[0]));
                }

                // Operation: new = old . x
                String[] op = in.nextLine().split(" ");
                myoperation = op[6].charAt(0);
                if (op[7].equals("old")) {
                    myoperand = Integer.MIN_VALUE;
                }
                else {
                    myoperand = Integer.parseInt(op[7]);
                }

                // Test: divisible by x
                mytest = Integer.parseInt(in.nextLine().split(" ")[5]);

                // If true: throw to monkey x
                mytestOut[0] = Integer.parseInt(in.nextLine().split(" ")[9]);

                // If false: throw to monkey x
                mytestOut[1] = Integer.parseInt(in.nextLine().split(" ")[9]);

                //System.out.println("created monkey " + myid + " " + myitems.size() + " " + myoperation + " " + myoperand + " " + mytest);

                Monkey monkey = new Monkey(myitems, myoperation, myoperand, mytest, mytestOut);
                monkeys.add(monkey);

                //System.out.println(Monkey.mods + " adding test to mods " + mytest);
                Monkey.mods *= mytest;

                if (in.hasNextLine()) in.nextLine();
            }
            
            return monkeys;
        }

        private int evalItem(int item, int part) {
            long op = (long) this.operand;
            long iteml = (long) item;

            if (this.operand == (long) Integer.MIN_VALUE) {
                op = (long) item;
            }

            if (this.operation == '+') {
                iteml += op;
            }
            else if (this.operation == '-') {
                iteml -= op;
            }
            else if (this.operation == '*') {
                iteml *= op;
            }
            else {
                iteml /= op;
            }

            if (part == 1) {
                iteml /= 3;
            }
            else {
                iteml = iteml % ((long) Monkey.mods);
            }

            item = (int) iteml;
            return item;
        }

        private int sendItem(int item) {
            int send = 0;

            if (item % this.test == 0) {
                send = testOut[0];
            }
            else {
                send = testOut[1];
            }

            return send;
        }

        public static ArrayList<Monkey> play(ArrayList<Monkey> monkeys, int rounds, int part) {
            for (int i = 0; i < rounds; i++) {
                for (int m = 0; m < monkeys.size(); m++) {
                    Monkey monkey = monkeys.get(m);
                    int itemLen = monkey.items.size();

                    for (int j = 0; j < itemLen; j++) {
                        int item = monkey.removeItem();

                        //System.out.print("monkey " + (m) + " with item " + item);

                        //System.out.println(Monkey.mods);
                        item = monkey.evalItem(item, part);
                        int send = monkey.sendItem(item);

                        //System.out.println(" -> " + item + " to monkey " + (send));

                        Monkey sendMonkey = monkeys.get(send);
                        sendMonkey.addItem(item);
                        monkeys.set(send, sendMonkey);
                    }
                    //System.out.println("");

                    monkeys.set(m, monkey);
                }
            }

            return monkeys;
        }

    }
}