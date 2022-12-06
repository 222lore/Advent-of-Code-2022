/*
Day 6

1st Star: 00:06:12 Rank: 3081
2nd Star: 00:14:21 Rank: 7170

Get a substring and check if it has only unique characters.
Wouldn't been 5 minutes faster on part 2 if I realized the 2nd parameter
for the substring function in C++ is length and not end index...
*/

#include <iostream>
#include <fstream>
#include <list>
using namespace std;

int part1(string text) {
    int marker = 4;

    for (int i = 0; i < text.length() - 3; i++) {
        if (text.at(i) != text.at(i+1) && text.at(i) != text.at(i+2) && text.at(i) != text.at(i+3) 
            && text.at(i+1) != text.at(i+2) && text.at(i+1) != text.at(i+3) && text.at(i+2) != text.at(i+3)) {
            return marker;
        }
        marker++;
    }

    return -404;
}

int part2(string text) {
    int marker = 14;
    bool unique = true;
    for (int i = 0; i < text.length() - 13; i++) {
        
        string str = text.substr(i, 14);
        unique = true;

        for (int j = 0; j < str.length() - 1; j++) {
            for (int k = j + 1; k < str.length(); k++) {
                if (str.at(j) == str.at(k)) {
                    unique = false;
                    break;
                }
            }
            if (!unique) break;
        }

        if (unique) {
            return marker;
        }

        marker++;
    }

    return -404;
}

int main() {
    ifstream file("../input/day6.txt");
    string line = "";
    getline(file, line);

    printf("%d\n", part1(line));
    printf("%d\n", part2(line));


    return 0;
}