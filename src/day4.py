"""
Day 4

1st Star: 00:12:22 Rank: 5890
2nd Star: 00:14:00 Rank: 4315

Both parts were just one long if statement checking for overlap(s). 
"""

def part1(file):
    line = file.readline()
    score = 0

    while line != "":
        first = (line.split(",")[0]).split("-")
        second = (line.split(",")[1]).split("-")

        min1 = (int) (first[0])
        max1 = (int) (first[1])
        min2 = (int) (second[0])
        max2 = (int) (second[1])

        # Check if the first range fully encloses the second range, or if the second range fully encloses the first range
        if ((min1 <= min2 and max1 >= max2) or (min2 <= min1 and max2 >= max1)):
            score += 1

        line = file.readline()

    return score 

def part2(file):
    line = file.readline()
    score = 0

    while line != "":
        first = (line.split(",")[0]).split("-")
        second = (line.split(",")[1]).split("-")

        min1 = (int) (first[0])
        max1 = (int) (first[1])
        min2 = (int) (second[0])
        max2 = (int) (second[1])

        # Check if the first range partially encloses the second range, or if the second range partially encloses the first range
        if ((min1 <= min2 and max1 >= min2) or (min1 <= max2 and max1 >= max2) or (min2 <= min1 and max2 >= min1) or (min2 <= max1 and max2 >= max1)):
            score += 1

        line = file.readline()

    return score 

def main():
    file = open("../input/day4.txt", "r")
    file2 = open("../input/day4.txt", "r")

    print("Part 1: " + str(part1(file)))
    print("Part 2: " + str(part2(file2)))


if __name__ == "__main__": main()