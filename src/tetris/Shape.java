package tetris;

/**
 * Created by Paul on 7/28/2017.
 */

import java.util.Random;
import java.lang.Math;

public class Shape {

    enum Tetrominos { NoShpe, ZShape, SShape, LineShape,
    TShape, SquareShape, LShape, MirroredLShape}

    private Tetrominos pieceShape;
    private int coords[][];
    private int[][][] coordsTable;

    public Shape() {
        coords = new int[4][2];
        setShape(Tetrominos.NoShpe);
    }

    public void setShape(Tetrominos shape) {

        coordsTable = new int[][][] {
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
                {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
                {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
                {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
                {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
                {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
                {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
        };

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 2; ++j) {
                coords[i][j] = coordsTable[shape.ordinal()][i][j];
            }
        }
        pieceShape = shape;
    }

    private void setX(int index, int x) {
        coords[index][0] = x;
    }

    private void setY(int index, int y) {
        coords[index][1] = y;
    }

    public int x(int index) {
        return coords[index][0];
    }

    public int y(int index) {
        return coords[index][1];
    }

    public Tetrominos getShape() {
        return pieceShape;
    }

    public void setRandomShape() {

        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Tetrominos[] values = Tetrominos.values();
        setShape(values[x]);
    }

    public int minX() {

        int m = coords[0][0];
        for(int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][0]);
        }
        return m;
    }

    public int minY() {

        int m = coords[0][1];
        for(int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][1]);
        }
        return m;
    }

    public Shape rotateLeft() {

        if(pieceShape == Tetrominos.SquareShape) {
            return this;
        }

        Shape result = new Shape();
        result.pieceShape = pieceShape;

        for(int i = 0; i < 4; ++i) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }

    public Shape rotateRight() {

        if(pieceShape == Tetrominos.SquareShape) {
            return this;
        }

        Shape result = new Shape();
        result.pieceShape = pieceShape;

        for(int i = 0; i < 4; ++i) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }
}
