package com.hsuanwei.vr_maze;


import android.util.Log;

public class Cube {
    private static final String TAG = "Cube";
    private Rectangle[] mRectangles;
    float color1[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };
    float color2[] = { 0.367187f, 0.953125f, 0.65625f, 1.0f };
    float color3[] = { 0.1875f, 0.53125f, 0.25f, 1.0f };

    public static Cube CreateCubeFrom2D(float cubeHeight, float[][] vertex)
    {
        try {
            float[][] stereoVertex = new float[8][3];
            for(int i = 0; i < 8; i ++) {
               stereoVertex[i][0] = vertex[i%4][0]; // x
               stereoVertex[i][2] = vertex[i%4][1]; // z
               if(i < 4){
                   stereoVertex[i][1] = cubeHeight - 1.0f; // y
               }
               else{
                   stereoVertex[i][1] = -1.0f;
               }
            }
            return new Cube(stereoVertex);
        }
        catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "Invalid vertex to construct the cube.");
            return null;
        }
    }

    public Cube(float[][] vertex) {
        mRectangles = new Rectangle[6];
        mRectangles[0] = new Rectangle(vertex[0], vertex[1], vertex[2], vertex[3], color1);
        mRectangles[1] = new Rectangle(vertex[0], vertex[3], vertex[4], vertex[7], color2);
        mRectangles[2] = new Rectangle(vertex[1], vertex[5], vertex[6], vertex[2], color2);
        mRectangles[3] = new Rectangle(vertex[0], vertex[4], vertex[5], vertex[1], color3);
        mRectangles[4] = new Rectangle(vertex[2], vertex[6], vertex[7], vertex[3], color3);
        mRectangles[5] = new Rectangle(vertex[4], vertex[5], vertex[6], vertex[7], color1);
    }

    public void draw(float[] mvpMatrix) {
        for(int i = 0; i < 8; i ++) {
            mRectangles[i].draw(mvpMatrix);
        }
    }
}
