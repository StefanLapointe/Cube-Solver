package com.stefanlapointe;

public class Node {

    /*
    The move represents how to get from the parent state to the current state
    in 3x3 move notation (using the letters B, D, F, L, R, and U).
    For example, if move == "D'" then you get from the parent state to the current state
    by rotating the bottom face counterclockwise by 90 degrees.
    The parent node of the search tree will have null values for parent and move.
    */
    private final Cube cube;
    public final Node parent;
    public final String move;
    public final int score;

    public Node(Cube cube, Node parent, String move) {
        this.cube = new Cube(cube);
        this.parent = parent;
        this.move = move;
        score = this.cube.score();
    }

    public Node[] children() {
        Node[] children = new Node[12];
        children[0] = new Node(new Cube(cube).turnBackClockwise(), this, "B");
        children[1] = new Node(new Cube(cube).turnBackReverse(), this, "B'");
        children[2] = new Node(new Cube(cube).turnBottomClockwise(), this, "D");
        children[3] = new Node(new Cube(cube).turnBottomReverse(), this, "D'");
        children[4] = new Node(new Cube(cube).turnFrontClockwise(), this, "F");
        children[5] = new Node(new Cube(cube).turnFrontReverse(), this, "F'");
        children[6] = new Node(new Cube(cube).turnLeftClockwise(), this, "L");
        children[7] = new Node(new Cube(cube).turnLeftReverse(), this, "L'");
        children[8] = new Node(new Cube(cube).turnRightClockwise(), this, "R");
        children[9] = new Node(new Cube(cube).turnRightReverse(), this, "R'");
        children[10] = new Node(new Cube(cube).turnTopClockwise(), this, "U");
        children[11] = new Node(new Cube(cube).turnTopReverse(), this, "U'");
        return children;
    }

    public String path() {
        if (parent == null) return "";
        if (parent.move == null) return move;
        return parent.path() + " " + move;
    }

}
