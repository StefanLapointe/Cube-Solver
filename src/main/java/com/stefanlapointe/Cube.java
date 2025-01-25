package com.stefanlapointe;

import java.util.Arrays;

import static com.stefanlapointe.Sticker.*;

public class Cube {

    /*
    Each face's array below lists
    the stickers of the face in question
    in what would be English reading order
    (that is, left-to-right and top-to-bottom)
    when the cube is held such that:
    1. The face in question is facing towards you.
    2. The face that is facing upwards is:
        a. the back face in the case that
        the top face was the face in question.
        b. the front face in the case that
        the bottom face was the face in question.
        c. the top face in any other case.
    So, for example, back[2] would be
    the top-right sticker of the back face
    if you were to rotate the cube
    180 degrees on its vertical axis.
     */
    private Sticker[] back;
    private Sticker[] bottom;
    private Sticker[] front;
    private Sticker[] left;
    private Sticker[] right;
    private Sticker[] top;

    public Cube() {
        back = new Sticker[] {BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE};
        bottom = new Sticker[] {YELLOW, YELLOW, YELLOW, YELLOW, YELLOW, YELLOW, YELLOW, YELLOW, YELLOW};
        front = new Sticker[] {GREEN, GREEN, GREEN, GREEN, GREEN, GREEN, GREEN, GREEN, GREEN};
        left = new Sticker[] {ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE};
        right = new Sticker[] {RED, RED, RED, RED, RED, RED, RED, RED, RED};
        top = new Sticker[] {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE};
    }

    public Cube(Sticker[] back, Sticker[] bottom, Sticker[] front, Sticker[] left, Sticker[] right, Sticker[] top) {
        this.back = Arrays.copyOf(back, 9);
        this.bottom = Arrays.copyOf(bottom, 9);
        this.front = Arrays.copyOf(front, 9);
        this.left = Arrays.copyOf(left, 9);
        this.right = Arrays.copyOf(right, 9);
        this.top = Arrays.copyOf(top, 9);
    }

    public Cube(Cube c) {
        this(c.back, c.bottom, c.front, c.left, c.right, c.top);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append("       ");
            for (int j = 0; j < 3; j++) {
                sb.append(top[3*i+j].initial);
                sb.append(" ");
            }
            sb.setCharAt(sb.length() - 1, '\n');
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(left[3*i+j].initial);
                sb.append(" ");
            }
            sb.append(" ");
            for (int j = 0; j < 3; j++) {
                sb.append(front[3*i+j].initial);
                sb.append(" ");
            }
            sb.append(" ");
            for (int j = 0; j < 3; j++) {
                sb.append(right[3*i+j].initial);
                sb.append(" ");
            }
            sb.append(" ");
            for (int j = 0; j < 3; j++) {
                sb.append(back[3*i+j].initial);
                sb.append(" ");
            }
            sb.setCharAt(sb.length() - 1, '\n');
        }
        for (int i = 0; i < 3; i++) {
            sb.append("       ");
            for (int j = 0; j < 3; j++) {
                sb.append(bottom[3*i+j].initial);
                sb.append(" ");
            }
            sb.setCharAt(sb.length() - 1, '\n');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
