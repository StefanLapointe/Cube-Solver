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
    public boolean equals(Object obj) {
        if (!(obj instanceof Cube cube)) return false;
        if (!Arrays.equals(back, cube.back)) return false;
        if (!Arrays.equals(bottom, cube.bottom)) return false;
        if (!Arrays.equals(front, cube.front)) return false;
        if (!Arrays.equals(left, cube.left)) return false;
        if (!Arrays.equals(right, cube.right)) return false;
        return Arrays.equals(top, cube.top);
    }

    /*
    Technically Cube should be immutable in order for me to override this method.
    However, for the time being, Cube's mutability won't interfere with the use of this method.
    This method cally interprets the cube's surface as a 54-digit base-7 number.
    I chose base-7 since 6 is not coprime with 2, which could have increased the rate of collision.
    Collision is still very possible since 7^54, and for that matter 6^54,
    are both far greater than Integer.MAX_VALUE.
     */
    @Override
    public int hashCode() {
        int total = 0;
        for (int i = 0; i < 9; i++) {
            total = 7 * total + back[i].ordinal();
            total = 7 * total + bottom[i].ordinal();
            total = 7 * total + front[i].ordinal();
            total = 7 * total + left[i].ordinal();
            total = 7 * total + right[i].ordinal();
            total = 7 * total + top[i].ordinal();
        }
        return total;
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

    public Cube turnBackClockwise() {
        Sticker temp = back[1];
        back[1] = back[3];
        back[3] = back[7];
        back[7] = back[5];
        back[5] = temp;
        temp = back[0];
        back[0] = back[6];
        back[6] = back[8];
        back[8] = back[2];
        back[2] = temp;
        temp = bottom[6];
        bottom[6] = left[0];
        left[0] = top[2];
        top[2] = right[8];
        right[8] = temp;
        temp = bottom[7];
        bottom[7] = left[3];
        left[3] = top[1];
        top[1] = right[5];
        right[5] = temp;
        temp = bottom[8];
        bottom[8] = left[6];
        left[6] = top[0];
        top[0] = right[2];
        right[2] = temp;
        return this;
    }

    public Cube turnBackReverse() {
        Sticker temp = back[1];
        back[1] = back[5];
        back[5] = back[7];
        back[7] = back[3];
        back[3] = temp;
        temp = back[0];
        back[0] = back[2];
        back[2] = back[8];
        back[8] = back[6];
        back[6] = temp;
        temp = bottom[6];
        bottom[6] = right[8];
        right[8] = top[2];
        top[2] = left[0];
        left[0] = temp;
        temp = bottom[7];
        bottom[7] = right[5];
        right[5] = top[1];
        top[1] = left[3];
        left[3] = temp;
        temp = bottom[8];
        bottom[8] = right[2];
        right[2] = top[0];
        top[0] = left[6];
        left[6] = temp;
        return this;
    }

    public Cube turnBottomClockwise() {
        Sticker temp = bottom[1];
        bottom[1] = bottom[3];
        bottom[3] = bottom[7];
        bottom[7] = bottom[5];
        bottom[5] = temp;
        temp = bottom[0];
        bottom[0] = bottom[6];
        bottom[6] = bottom[8];
        bottom[8] = bottom[2];
        bottom[2] = temp;
        temp = back[6];
        back[6] = right[6];
        right[6] = front[6];
        front[6] = left[6];
        left[6] = temp;
        temp = back[7];
        back[7] = right[7];
        right[7] = front[7];
        front[7] = left[7];
        left[7] = temp;
        temp = back[8];
        back[8] = right[8];
        right[8] = front[8];
        front[8] = left[8];
        left[8] = temp;
        return this;
    }

    public Cube turnBottomReverse() {
        Sticker temp = bottom[1];
        bottom[1] = bottom[5];
        bottom[5] = bottom[7];
        bottom[7] = bottom[3];
        bottom[3] = temp;
        temp = bottom[0];
        bottom[0] = bottom[2];
        bottom[2] = bottom[8];
        bottom[8] = bottom[6];
        bottom[6] = temp;
        temp = back[6];
        back[6] = left[6];
        left[6] = front[6];
        front[6] = right[6];
        right[6] = temp;
        temp = back[7];
        back[7] = left[7];
        left[7] = front[7];
        front[7] = right[7];
        right[7] = temp;
        temp = back[8];
        back[8] = left[8];
        left[8] = front[8];
        front[8] = right[8];
        right[8] = temp;
        return this;
    }

    public Cube turnFrontClockwise() {
        Sticker temp = front[1];
        front[1] = front[3];
        front[3] = front[7];
        front[7] = front[5];
        front[5] = temp;
        temp = front[0];
        front[0] = front[6];
        front[6] = front[8];
        front[8] = front[2];
        front[2] = temp;
        temp = bottom[0];
        bottom[0] = right[6];
        right[6] = top[8];
        top[8] = left[2];
        left[2] = temp;
        temp = bottom[1];
        bottom[1] = right[3];
        right[3] = top[7];
        top[7] = left[5];
        left[5] = temp;
        temp = bottom[2];
        bottom[2] = right[0];
        right[0] = top[6];
        top[6] = left[8];
        left[8] = temp;
        return this;
    }

    public Cube turnFrontReverse() {
        Sticker temp = front[1];
        front[1] = front[5];
        front[5] = front[7];
        front[7] = front[3];
        front[3] = temp;
        temp = front[0];
        front[0] = front[2];
        front[2] = front[8];
        front[8] = front[6];
        front[6] = temp;
        temp = bottom[0];
        bottom[0] = left[2];
        left[2] = top[8];
        top[8] = right[6];
        right[6] = temp;
        temp = bottom[1];
        bottom[1] = left[5];
        left[5] = top[7];
        top[7] = right[3];
        right[3] = temp;
        temp = bottom[2];
        bottom[2] = left[8];
        left[8] = top[6];
        top[6] = right[0];
        right[0] = temp;
        return this;
    }

    public Cube turnLeftClockwise() {
        Sticker temp = left[1];
        left[1] = left[3];
        left[3] = left[7];
        left[7] = left[5];
        left[5] = temp;
        temp = left[0];
        left[0] = left[6];
        left[6] = left[8];
        left[8] = left[2];
        left[2] = temp;
        temp = back[2];
        back[2] = bottom[6];
        bottom[6] = front[6];
        front[6] = top[6];
        top[6] = temp;
        temp = back[5];
        back[5] = bottom[3];
        bottom[3] = front[3];
        front[3] = top[3];
        top[3] = temp;
        temp = back[8];
        back[8] = bottom[0];
        bottom[0] = front[0];
        front[0] = top[0];
        top[0] = temp;
        return this;
    }

    public Cube turnLeftReverse() {
        Sticker temp = left[1];
        left[1] = left[5];
        left[5] = left[7];
        left[7] = left[3];
        left[3] = temp;
        temp = left[0];
        left[0] = left[2];
        left[2] = left[8];
        left[8] = left[6];
        left[6] = temp;
        temp = back[2];
        back[2] = top[6];
        top[6] = front[6];
        front[6] = bottom[6];
        bottom[6] = temp;
        temp = back[5];
        back[5] = top[3];
        top[3] = front[3];
        front[3] = bottom[3];
        bottom[3] = temp;
        temp = back[8];
        back[8] = top[0];
        top[0] = front[0];
        front[0] = bottom[0];
        bottom[0] = temp;
        return this;
    }

    public Cube turnRightClockwise() {
        Sticker temp = right[1];
        right[1] = right[3];
        right[3] = right[7];
        right[7] = right[5];
        right[5] = temp;
        temp = right[0];
        right[0] = right[6];
        right[6] = right[8];
        right[8] = right[2];
        right[2] = temp;
        temp = back[0];
        back[0] = top[8];
        top[8] = front[8];
        front[8] = bottom[8];
        bottom[8] = temp;
        temp = back[3];
        back[3] = top[5];
        top[5] = front[5];
        front[5] = bottom[5];
        bottom[5] = temp;
        temp = back[6];
        back[6] = top[2];
        top[2] = front[2];
        front[2] = bottom[2];
        bottom[2] = temp;
        return this;
    }

    public Cube turnRightReverse() {
        Sticker temp = right[1];
        right[1] = right[5];
        right[5] = right[7];
        right[7] = right[3];
        right[3] = temp;
        temp = right[0];
        right[0] = right[2];
        right[2] = right[8];
        right[8] = right[6];
        right[6] = temp;
        temp = back[0];
        back[0] = bottom[8];
        bottom[8] = front[8];
        front[8] = top[8];
        top[8] = temp;
        temp = back[3];
        back[3] = bottom[5];
        bottom[5] = front[5];
        front[5] = top[5];
        top[5] = temp;
        temp = back[6];
        back[6] = bottom[2];
        bottom[2] = front[2];
        front[2] = top[2];
        top[2] = temp;
        return this;
    }

    public Cube turnTopClockwise() {
        Sticker temp = top[1];
        top[1] = top[3];
        top[3] = top[7];
        top[7] = top[5];
        top[5] = temp;
        temp = top[0];
        top[0] = top[6];
        top[6] = top[8];
        top[8] = top[2];
        top[2] = temp;
        temp = back[0];
        back[0] = left[0];
        left[0] = front[0];
        front[0] = right[0];
        right[0] = temp;
        temp = back[1];
        back[1] = left[1];
        left[1] = front[1];
        front[1] = right[1];
        right[1] = temp;
        temp = back[2];
        back[2] = left[2];
        left[2] = front[2];
        front[2] = right[2];
        right[2] = temp;
        return this;
    }

    public Cube turnTopReverse() {
        Sticker temp = top[1];
        top[1] = top[5];
        top[5] = top[7];
        top[7] = top[3];
        top[3] = temp;
        temp = top[0];
        top[0] = top[2];
        top[2] = top[8];
        top[8] = top[6];
        top[6] = temp;
        temp = back[0];
        back[0] = right[0];
        right[0] = front[0];
        front[0] = left[0];
        left[0] = temp;
        temp = back[1];
        back[1] = right[1];
        right[1] = front[1];
        front[1] = left[1];
        left[1] = temp;
        temp = back[2];
        back[2] = right[2];
        right[2] = front[2];
        front[2] = left[2];
        left[2] = temp;
        return this;
    }

    /*
    The heuristic I came up with for this
    is the number of pairs of pieces
    that are matched up with each other.
     */
    int score() {
        int total = 0;
        // back-bottom edge and neighbours
        if (back[7] == back[4]) total++;
        if (bottom[7] == bottom[4]) total++;
        if (back[7] == back[8] && bottom[7] == bottom[6]) total++;
        if (back[7] == back[6] && bottom[7] == bottom[8]) total++;
        // back-left edge and neighbours
        if (back[5] == back[4]) total++;
        if (left[3] == left[4]) total++;
        if (back[5] == back[8] && left[3] == left[6]) total++;
        if (back[5] == back[2] && left[3] == left[0]) total++;
        // back-right edge and neighbours
        if (back[3] == back[4]) total++;
        if (right[5] == right[4]) total++;
        if (back[3] == back[6] && right[5] == right[8]) total++;
        if (back[3] == back[0] && right[5] == right[2]) total++;
        // back-top edge and neighbours
        if (back[1] == back[4]) total++;
        if (top[1] == top[4]) total++;
        if (back[1] == back[2] && top[1] == top[0]) total++;
        if (back[1] == back[0] && top[1] == top[2]) total++;
        // bottom-front edge and neighbours
        if (bottom[1] == bottom[4]) total++;
        if (front[7] == front[4]) total++;
        if (bottom[1] == bottom[0] && front[7] == front[6]) total++;
        if (bottom[1] == bottom[2] && front[7] == front[8]) total++;
        // bottom-left edge and neighbours
        if (bottom[3] == bottom[4]) total++;
        if (left[7] == left[4]) total++;
        if (bottom[3] == bottom[6] && left[7] == left[6]) total++;
        if (bottom[3] == bottom[0] && left[7] == left[8]) total++;
        // bottom-right edge and neighbours
        if (bottom[5] == bottom[4]) total++;
        if (right[7] == right[4]) total++;
        if (bottom[5] == bottom[8] && right[7] == right[8]) total++;
        if (bottom[5] == bottom[2] && right[7] == right[6]) total++;
        // front-left edge and neighbours
        if (front[3] == front[4]) total++;
        if (left[5] == left[4]) total++;
        if (front[3] == front[6] && left[5] == left[8]) total++;
        if (front[3] == front[0] && left[5] == left[2]) total++;
        // front-right edge and neighbours
        if (front[5] == front[4]) total++;
        if (right[3] == right[4]) total++;
        if (front[5] == front[8] && right[3] == right[6]) total++;
        if (front[5] == front[2] && right[3] == right[0]) total++;
        // front-top edge and neighbours
        if (front[1] == front[4]) total++;
        if (top[7] == top[4]) total++;
        if (front[1] == front[0] && top[7] == top[6]) total++;
        if (front[1] == front[2] && top[7] == top[8]) total++;
        // left-top edge and neighbours
        if (left[1] == left[4]) total++;
        if (top[3] == top[4]) total++;
        if (left[1] == left[0] && top[3] == top[0]) total++;
        if (left[1] == left[2] && top[3] == top[6]) total++;
        // right-top edge and neighbours
        if (right[1] == right[4]) total++;
        if (top[5] == top[4]) total++;
        if (right[1] == right[2] && top[5] == top[2]) total++;
        if (right[1] == right[0] && top[5] == top[8]) total++;
        return total;
    }

}
