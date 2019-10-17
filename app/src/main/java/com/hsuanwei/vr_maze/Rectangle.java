package com.hsuanwei.vr_maze;


import java.util.List;

public class Rectangle {
    private Triangle[] mTriangles;
    private float[] color;
    /**
     * Constructor function which receive vertex in clockwise order
     * @param v1
     * @param v2
     * @param v3
     * @param v4
     */
    public Rectangle(float[] v1, float[] v2, float[] v3, float[] v4, float[] color) {
        mTriangles = new Triangle[2];
        mTriangles[0] = Triangle.CreateTriangle(v1, v2, v3, color);
        mTriangles[1] = Triangle.CreateTriangle(v3, v4, v1, color);
    }

    public void draw(float[] mvpMatrix) {
        mTriangles[0].draw(mvpMatrix);
        mTriangles[1].draw(mvpMatrix);
    }
}
