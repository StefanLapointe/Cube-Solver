# Cube-Solver

Cube-Solver is a command line program to help you solve a Rubik's cube written in Java.

## How to use it

If you open this project as an IntelliJ IDEA project, you can just click the Run button on build/classes/java/main/com/stefanlapointe/Main.java in order to compile and run the program. Alternatively, you can use the Gradle build and run tasks. Once you run the program, you will be asked to input the colours of the stickers in a specific order, and you will (hopefully) be given a solution in standard 3x3 Rubik's cube notation as a result.

Here's some example input and output:
```
You will be asked to enter the colour of each sticker one face at a time.
Please enter the colours for each face in rows, going left-to-right, top-to-bottom.
Type b for blue, g for green, o for orange, r for red, w for white, and y for yellow.
For example, one line of input might look like this: bgorwybgo
To look at the back, left, and right faces, rotate the cube on its y-axis.
To look at the bottom and top faces, rotate the cube on its x-axis.
Return the cube to its original orientation after inspecting each face.
Please enter the back face: gybybyobr
Please enter the bottom face: ggworrywy
Please enter the front face: owgogoyro
Please enter the left face:woggyybbr
Please enter the right face: rgowwrbbb
Please enter the top face: rbwgowyrw
Here is the scramble you entered:
       R B W
       G O W
       Y R W
W O G  O W G  R G O  G Y B
G Y Y  O G O  W W R  Y B Y
B B R  Y R O  B B B  O B R
       G G W
       O R R
       Y W Y
Here is the score of your scramble: 4
The minimum score is 0, while 48 means fully solved.
Work in progress...
Current best score: 6/48 (1 iterations, 1 moves)
Current best score: 9/48 (2 iterations, 2 moves)
Current best score: 11/48 (3 iterations, 3 moves)
Current best score: 14/48 (4 iterations, 4 moves)
Current best score: 15/48 (5 iterations, 5 moves)
Current best score: 16/48 (7 iterations, 7 moves)
Current best score: 17/48 (13 iterations, 7 moves)
Current best score: 18/48 (15 iterations, 9 moves)
Current best score: 19/48 (116 iterations, 11 moves)
Current best score: 22/48 (217 iterations, 9 moves)
Current best score: 23/48 (218 iterations, 10 moves)
Current best score: 25/48 (228 iterations, 12 moves)
Current best score: 26/48 (476 iterations, 16 moves)
Current best score: 27/48 (597 iterations, 23 moves)
Current best score: 32/48 (598 iterations, 24 moves)
Current best score: 35/48 (2502 iterations, 29 moves)
Current best score: 36/48 (8548 iterations, 30 moves)
Current best score: 40/48 (9203 iterations, 34 moves)
Solution: U' B' D R F L' U' R U L F L' B L U' F' B' R B' R' B U R U' R U R R U' R R U R U' R'
```


## How it works

The current iteration of this program uses weighted A* search to explore a search tree where the nodes are reachable states of the Rubik's cube and the edges correspond to transitions between states that can be made in one move.

The heuristic function is based on counting the number of pairs of adjacent pieces of the Rubik's cube that are matched up. An edge piece and a corner piece are matched up if both of their shared pairs of adjacent stickers match. An edge piece is matched up with a centre piece if the edge piece's sticker that is adjacent to the centre piece is the same colour as the centre piece.

The first working iteration of this program was just a greedy heuristic search. By changing to weighted A* search, which avoids long solutions, the expected solution length was reduced from something like 300 to something like 35. Unweighted A* search failed to produce solutions in a reasonable amount of time because the algorithm was avoiding long solutions so strongly. By weighting the score more heavily, I was able to get this program to produce acceptably short solutions in an acceptably short amount of time.
