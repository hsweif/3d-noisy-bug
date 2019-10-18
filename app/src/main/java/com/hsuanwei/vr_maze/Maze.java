package com.hsuanwei.vr_maze;

import android.util.Log;
import android.util.Pair;

public class Maze {
    private int mazeSize;
    private Pair entryPoint; // First: x, Second: y
    private Cube[] cubeList;
    public Texture texture;
    private int mazeWidth = 6;
    private int mazeHeight = 6;
    private float cubeWidth = 0.3f;
    private float cubeHeight = 0.5f;
    private static String TAG = "Maze";
    private int cubeSum;
    private int maze[][] = {
        {1, 0, 1, 1, 1, 1},
        {1, 0, 0, 1, 0, 1},
        {1, 1, 0, 1, 0, 1},
        {1, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 0, 1}
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
}
