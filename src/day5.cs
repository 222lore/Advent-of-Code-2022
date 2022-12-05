using System;
using System.Collections.Generic;

class day5
{
    static Stack<char>[] stacks = new Stack<char>[9];

    static void CreateStack()
    {
        /* 
            Layout for my unique input:
                                    [Z] [W] [Z]
                    [D] [M]         [L] [P] [G]
                [S] [N] [R]         [S] [F] [N]
                [N] [J] [W]     [J] [F] [D] [F]
            [N] [H] [G] [J]     [H] [Q] [H] [P]
            [V] [J] [T] [F] [H] [Z] [R] [L] [M]
            [C] [M] [C] [D] [F] [T] [P] [S] [S]
            [S] [Z] [M] [T] [P] [C] [D] [C] [D]
            1   2   3   4   5   6   7   8   9 
        */

        Stack<char> stack1 = new Stack<char>();
        Stack<char> stack2 = new Stack<char>();
        Stack<char> stack3 = new Stack<char>();
        Stack<char> stack4 = new Stack<char>();
        Stack<char> stack5 = new Stack<char>();
        Stack<char> stack6 = new Stack<char>();
        Stack<char> stack7 = new Stack<char>();
        Stack<char> stack8 = new Stack<char>();
        Stack<char> stack9 = new Stack<char>();

        stack1.Clear();
        stack1.Push('S');
        stack1.Push('C');
        stack1.Push('V');
        stack1.Push('N');
        stacks[0] = stack1;

        stack2.Clear();
        stack2.Push('Z');
        stack2.Push('M');
        stack2.Push('J');
        stack2.Push('H');
        stack2.Push('N');
        stack2.Push('S');
        stacks[1] = stack2;

        stack3.Clear();
        stack3.Push('M');
        stack3.Push('C');
        stack3.Push('T');
        stack3.Push('G');
        stack3.Push('J');
        stack3.Push('N');
        stack3.Push('D');
        stacks[2] = stack3;

        stack4.Clear();
        stack4.Push('T');
        stack4.Push('D');
        stack4.Push('F');
        stack4.Push('J');
        stack4.Push('W');
        stack4.Push('R');
        stack4.Push('M');
        stacks[3] = stack4;

        stack5.Clear();
        stack5.Push('P');
        stack5.Push('F');
        stack5.Push('H');
        stacks[4] = stack5;

        stack6.Clear();
        stack6.Push('C');
        stack6.Push('T');
        stack6.Push('Z');
        stack6.Push('H');
        stack6.Push('J');
        stacks[5] = stack6;

        stack7.Clear();
        stack7.Push('D');
        stack7.Push('P');
        stack7.Push('R');
        stack7.Push('Q');
        stack7.Push('F');
        stack7.Push('S');
        stack7.Push('L');
        stack7.Push('Z');
        stacks[6] = stack7;

        stack8.Clear();
        stack8.Push('C');
        stack8.Push('S');
        stack8.Push('L');
        stack8.Push('H');
        stack8.Push('D');
        stack8.Push('F');
        stack8.Push('P');
        stack8.Push('W');
        stacks[7] = stack8;

        stack9.Clear();
        stack9.Push('D');
        stack9.Push('S');
        stack9.Push('M');
        stack9.Push('P');
        stack9.Push('F');
        stack9.Push('N');
        stack9.Push('G');
        stack9.Push('Z');
        stacks[8] = stack9;
    }

    static void MoveFromStack(int numToMove, int srcStack, int dstStack)
    {
        for (int i = 0; i < numToMove; i++)
        {
            // Pop from the source stack and push the returned value to the destination stack
            stacks[dstStack - 1].Push(stacks[srcStack - 1].Pop());
        }
    }

    static void MoveFromStackFixed(int numToMove, int srcStack, int dstStack)
    {
        char[] toMove = new char[numToMove];

        for (int i = 0; i < numToMove; i++)
        {
            // Pop from the source stack
            toMove[i] = stacks[srcStack - 1].Pop();
        }

        for (int i = numToMove - 1; i >= 0; i--)
        {
            // Push to the destination stack in reverse order 
            stacks[dstStack - 1].Push(toMove[i]);
        }
    }
    
    static string Part1(string[] file)
    {
        string result = "";

        foreach(string line in file)
        {
            string[] commands = line.Split(' ');
            try
            {
                // Execute move command
                int numToMove = Int32.Parse(commands[1]);
                int srcStack = Int32.Parse(commands[3]);
                int dstStack = Int32.Parse(commands[5]);
                MoveFromStack(numToMove, srcStack, dstStack);
            }
            catch (Exception e)
            {
                Console.WriteLine("Error: " + e.Message);
                return "";
            }
        }

        for (int i = 0; i < 9; i++)
        {
            // Get values on the top of all stacks
            result += stacks[i].Pop();
        }

        return result;
    }

    static String Part2(string[] file)
    {
        string result = "";

        foreach(string line in file)
        {
            string[] commands = line.Split(' ');
            try
            {
                // Execute move command
                int numToMove = Int32.Parse(commands[1]);
                int srcStack = Int32.Parse(commands[3]);
                int dstStack = Int32.Parse(commands[5]);
                MoveFromStackFixed(numToMove, srcStack, dstStack);
            }
            catch (Exception e)
            {
                Console.WriteLine("Error: " + e.Message);
                return "";
            }
        }

        for (int i = 0; i < 9; i++)
        {
            // Get values on the top of all stacks
            result += stacks[i].Pop();
        }

        return result;
    }

    static void Main(string[] args)
    {
        CreateStack();
        string part1 = Part1(System.IO.File.ReadAllLines("../input/day5.txt"));
        Console.WriteLine("Part1: " + part1);
        CreateStack();
        string part2 = Part2(System.IO.File.ReadAllLines("../input/day5.txt"));
        Console.WriteLine("Part1: " + part2);
    }
} 