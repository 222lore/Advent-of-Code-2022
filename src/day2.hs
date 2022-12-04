import System.IO 

fixWords :: [String] -> [String]
fixWords (first:second:rest) = (first ++ second):(fixWords rest)
fixWords _ = [] 

ascii :: Char -> Int 
ascii c 
    | c == 'A'  = 1  -- Rock
    | c == 'B'  = 2  -- Paper
    | c == 'C'  = 3  -- Scissors
    | c == 'X'  = 4  -- Rock
    | c == 'Y'  = 5  -- Paper
    | c == 'Z'  = 6  -- Scissors
    | otherwise = -1

playGame :: String -> Int
playGame (opponent:(me:[]))
    | (ascii me) - (ascii opponent) == 3                                       = ascii me + 3 - 3
    | (ascii me) - (ascii opponent) == 4 || (ascii me) - (ascii opponent) == 1 = ascii me + 6 - 3
    | otherwise                                                                = ascii me - 3
playGame c = 0

part1 :: [String] -> Int 
part1 (firstGame:rest) = (playGame firstGame) + (part1 rest)
part1 _ = 0

fixGame :: String -> Int 
fixGame ('A':('X':[])) = 3 
fixGame (opponent:('X':[])) = ascii opponent - 1 
fixGame (opponent:('Y':[])) = ascii opponent + 3
fixGame ('C':('Z':[])) = 1 + 6 
fixGame (opponent:('Z':[])) = ascii opponent + 1 + 6 
fixGame _ = 0

part2 :: [String] -> Int
part2 (firstGame:rest) = (fixGame firstGame) + (part2 rest)
part2 _ = 0

main :: IO ()
main = do 
    file <- openFile "../input/day2.txt" ReadMode 
    contents <- hGetContents file 
    let solve = (part1 . fixWords . words) contents
    putStrLn ("Part 1: " ++ (show solve))
    let solve = (part2 . fixWords . words) contents
    putStrLn ("Part 2: " ++ (show solve))
