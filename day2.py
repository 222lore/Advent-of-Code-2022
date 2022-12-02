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
    score = 0

    while line != "":
        score += result[ord(line[2:3]) - 88]        
        if line[0:1] == 'X':
            score += 4
        elif line[2:3] == 'Y':
            score += 1
        else:
            score += 7

        line = file.readline()

    return score 

def main():
    file = open("day2.txt", "r")
    
    print(part1(file))
    # print("ascii " , ord('A') , " " , ord('B') , " " , ord('C') , " " , ord('X') , " " , ord('Y') , " " , ord('Z'))


if __name__ == "__main__": main()

#rock A 65 - X 88
#paper B 66 - Y 89
#scissors C 67 - Z 90

#           rock (88)    paper (89)   scissors (90) 
# rock (65)     23 T          24 W              25 L
# paper (66)    22 L          23 T              24 W
# scissors (67) 21 W          22 L              23 T
#lose = 0 (22 22 22)
#tie = 3 (23 23 23)
#win = 6 (24 24 24)