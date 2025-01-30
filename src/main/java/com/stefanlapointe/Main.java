package com.stefanlapointe;

import java.util.*;
import java.util.function.IntFunction;

public class Main {

    public static void main(String[] args) {

        System.out.println("You will be asked to enter the colour of each sticker one face at a time.");
        System.out.println("Please enter the colours for each face in rows, going left-to-right, top-to-bottom.");
        System.out.println("Type b for blue, g for green, o for orange, r for red, w for white, and y for yellow.");
        System.out.println("For example, one line of input might look like this: bgorwybgo");
        System.out.println("To look at the back, left, and right faces, rotate the cube on its y-axis.");
        System.out.println("To look at the bottom and top faces, rotate the cube on its x-axis.");
        System.out.println("Return the cube to its original orientation after inspecting each face.");

        Scanner scanner = new Scanner(System.in);

        IntFunction<Sticker> toSticker = i -> switch (i) {
            case 'b' -> Sticker.BLUE;
            case 'g' -> Sticker.GREEN;
            case 'o' -> Sticker.ORANGE;
            case 'r' -> Sticker.RED;
            case 'w' -> Sticker.WHITE;
            case 'y' -> Sticker.YELLOW;
            default -> null;
        };

        System.out.print("Please enter the back face: ");
        Sticker[] back = scanner.nextLine().chars().mapToObj(toSticker).toArray(Sticker[]::new);
        System.out.flush();
        System.out.print("Please enter the bottom face: ");
        Sticker[] bottom = scanner.nextLine().chars().mapToObj(toSticker).toArray(Sticker[]::new);
        System.out.flush();
        System.out.print("Please enter the front face: ");
        Sticker[] front = scanner.nextLine().chars().mapToObj(toSticker).toArray(Sticker[]::new);
        System.out.flush();
        System.out.print("Please enter the left face: ");
        Sticker[] left = scanner.nextLine().chars().mapToObj(toSticker).toArray(Sticker[]::new);
        System.out.flush();
        System.out.print("Please enter the right face: ");
        Sticker[] right = scanner.nextLine().chars().mapToObj(toSticker).toArray(Sticker[]::new);
        System.out.flush();
        System.out.print("Please enter the top face: ");
        Sticker[] top = scanner.nextLine().chars().mapToObj(toSticker).toArray(Sticker[]::new);

        Cube scrambled = new Cube(back, bottom, front, left, right, top);

        System.out.println("Here is the scramble you entered:\n" + scrambled);
        System.out.println("Here is the score of your scramble: " + scrambled.score());
        System.out.println("The minimum score is 0, while 48 means fully solved.");

        System.out.println("Work in progress...");

        // Multiplying the scores by 2 helps the algorithm not worry *too much* about solution length.
        Comparator<Node> nodeComparator = (n1, n2) -> (2 * n2.score - n2.depth) - (2 * n1.score - n1.depth);
        PriorityQueue<Node> unexpandedNodes = new PriorityQueue<>(nodeComparator);
        unexpandedNodes.add(new Node(scrambled, null, null, 0));
        Set<Cube> visitedCubes = new HashSet<>();

        int bestScore = scrambled.score();

        for (int i = 0; i < 1000000; i++) {
            Node bestNode = unexpandedNodes.remove();
            if (visitedCubes.contains(bestNode.cube)) continue;
            visitedCubes.add(bestNode.cube);
            if (bestNode.score == 48) {
                System.out.println("Solution: " + bestNode.path());
                return;
            }
            if (bestNode.score > bestScore) {
                bestScore = bestNode.score;
                System.out.println("Current best score: " + bestScore + "/48 (" + i + " iterations, " + bestNode.depth + " moves)");
            }
            unexpandedNodes.addAll(Arrays.asList(bestNode.children()));
        }
        System.out.println("No solution found within iteration limit.");

    }

}