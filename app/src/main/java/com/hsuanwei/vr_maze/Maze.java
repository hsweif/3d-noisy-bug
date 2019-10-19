package com.hsuanwei.vr_maze;

import android.util.Log;

public class Maze {
    private static int[] entryPoint = {4, 9}; // first: vertical, second: horizontal
    private Cube[] cubeList;
    public Texture texture;
    private static int mazeWidth = 10;
    private static int mazeHeight = 10;
    private static float cubeWidth = 0.2f;
    private static float cubeHeight = 0.4f;
    private static String TAG = "Maze";
    private int cubeSum;
    private int maze[][] = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
        {1, 0, 0, 0, 1, 0, 1, 0, 0, 0},
        {1, 0, 1, 0, 0, 0, 1, 0, 1, 1},
        {1, 0, 1, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
    };
    private float[] zeroPoint;

    public Maze(){
        zeroPoint = new float[2];
        zeroPoint[0] = -0.5f;
        zeroPoint[1] = -0.5f;
        initCubes();
    }

    public void draw(float[] mvpMatrix) {
        for(int i = 0; i < cubeSum; i ++)
        {
            // FIXME: Why only draw once?
            cubeList[i].draw(mvpMatrix);
            Log.i(TAG, "drawing cube.");
        }
    }

    private void initCubes() {
        cubeSum = 0;
        for(int w = 0; w < mazeWidth; w++) {
            for (int h = 0; h < mazeHeight; h++) {
                if(maze[w][h] == 1) {
                    cubeSum ++;
                }
            }
        }
        int cnt = 0;
        cubeList = new Cube[cubeSum];
        for(int w = 0; w < mazeWidth; w++) {
            for (int h = 0; h < mazeHeight; h++) {
                if (maze[w][h] == 1) {
                    cubeList[cnt++] = Cube.CreateCubeFromCoord(zeroPoint, w, h, cubeWidth, cubeHeight);
                }
            }
        }
    }

    public float[] entryCoords()
    {
        float[] coords = {-(zeroPoint[0] + entryPoint[0] * cubeWidth), -(zeroPoint[1] + entryPoint[1] * cubeWidth)};
        return coords;
    }
}
