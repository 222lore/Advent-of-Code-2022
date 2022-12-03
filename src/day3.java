import java.io.*;
import java.util.*;
 
public class day3 {	
	public static void main(String[] args) {
        try {
            File file = new File("Advent-of-Code-2022\\input\\day3.txt");
            Scanner in = new Scanner(file);
            Scanner in2 = new Scanner(file);

            System.out.println("Part 1: " + part1(in));
            System.out.println("Part 2: " + part2(in2));
            in.close();
        }
        catch (Exception e) {
            System.out.println("Error");
        }
	}
 
	private static int part1(Scanner in) {
        // String first = "";
        // String second = "";
        int sum = 0;

        while (in.hasNextLine()) {
            ArrayList<String> first = new ArrayList<>();
            ArrayList<String> dups = new ArrayList<>();
            String line = in.nextLine();

            String half = line.substring(0, line.length()/2);
            for (int i = 0; i < half.length(); i++) {
                first.add(half.substring(i, i+1));
            }

            String second = line.substring(line.length()/2);

            for (int i = 0; i < second.length(); i++) {
                if (first.contains(second.substring(i, i+1))) {
                    // System.out.println(second.substring(i, i+1));
                    int add = ((second.substring(i, i+1).toUpperCase()).equals(second.substring(i, i+1))) ? 58 : 0;
                    sum += (int) second.charAt(i) - 96 + add;
                    // System.out.println(second.charAt(i) + " " + ((int) second.charAt(i)) + " " + ((int) second.charAt(i) - 96 + add));
                    break;
                }
            }

        }

        return sum;
    }

    private static int part2(Scanner in) {
        int sum = 0;

        while (in.hasNextLine()) {
            ArrayList<String> first = new ArrayList<>();
            String line = in.nextLine();
            for (int i = 0; i < line.length(); i++) {
                first.add(line.substring(i, i+1));
            }
            ArrayList<String> second = new ArrayList<>();
            line = in.nextLine();
            for (int i = 0; i < line.length(); i++) {
                second.add(line.substring(i, i+1));
            }
            ArrayList<String> third = new ArrayList<>();
            line = in.nextLine();
            for (int i = 0; i < line.length(); i++) {
                third.add(line.substring(i, i+1));
            }
            
            for (int i = 0; i < first.size(); i++) {
                String chr = first.get(i);
                if (second.contains(chr) && third.contains(chr)) {
                    int add = ((chr.toUpperCase()).equals(chr)) ? 58 : 0;
                    sum += (int) chr.charAt(0) - 96 + add;
                    break;
                }
            }

        }

        return sum;
    }
}