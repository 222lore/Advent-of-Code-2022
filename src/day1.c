#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int part1(FILE *file) {
    int most_calories = 0;
    int elf_calories = 0;

    char line[100];
    while (fgets(line, 100, file) != NULL) {
        if (strcmp(line, "\n") == 0) {
            
            // If the current elf's calories is more than the global most calories, update the global most calories 
            most_calories = (elf_calories > most_calories) ? elf_calories : most_calories;
            elf_calories = 0;
            continue;
        }

        elf_calories += atoi(line);
    }

    return most_calories;
}

int part2(FILE *file) {
    int top3[3];
    top3[0] = 0;
    top3[1] = 0;
    top3[2] = 0;
    int elf_calories = 0;

    char line[100];
    while (fgets(line, 100, file) != NULL) {
        if (strcmp(line, "\n") == 0) {
            if (elf_calories >= top3[0]) {

                // If the current elf's calories is more than the global #1 most calories, update the global #1, #2, #3 most calories 
                top3[2] = top3[1];
                top3[1] = top3[0];
                top3[0] = elf_calories;
            }
            else if (elf_calories >= top3[1]) {

                // If the current elf's calories is more than the global #2 most calories, update the global #2, #3 most calories 
                top3[2] = top3[1];
                top3[1] = elf_calories;
            }
            else if (elf_calories >= top3[2]) {

                // If the current elf's calories is more than the global #3 most calories, update the global #3 most calories 
                top3[2] = elf_calories;
            }
            elf_calories = 0;
        }

        elf_calories += atoi(line);
    }

    return (top3[0] + top3[1] + top3[2]);
}

int main() {
    FILE *file = fopen("../input/day1.txt", "r");
    FILE *file2 = fopen("../input/day1.txt", "r");
    if (!file) {
        printf("File cannot be read");
        return 1;
    }
    printf("Part 1: %d\nPart 2: %d\n", part1(file), part2(file2));

    return 0;
}