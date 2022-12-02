def part1(file):
    line = file.readline()
    score = 0

    while line != "":
        score += ord(line[2:3]) - 87
        if (ord(line[2:3]) - ord(line[0:1])) == 23:
            score += 3
        elif ((ord(line[2:3]) - ord(line[0:1])) == 24) or (ord(line[2:3]) - ord(line[0:1]) == 21):
            score += 6

        line = file.readline()

    return score 
 
def part2(file):
    line = file.readline()
    result = [0, 3, 6]
    play = [[3, 1, 2], [1, 2, 3], [2, 3, 1]]
    score = 0

    while line != "":
        score += result[ord(line[2:3]) - 88]        
        score += play[ord(line[2:3]) - 88][ord(line[0:1]) - 65]

        line = file.readline()

    return score 

def main():
    file = open("day2.txt", "r")
    file2 = open("day2.txt", "r")
    
    print(part1(file))
    print(part2(file2))


if __name__ == "__main__": main()