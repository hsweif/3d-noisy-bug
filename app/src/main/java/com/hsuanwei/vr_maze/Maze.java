package com.hsuanwei.vr_maze;

import android.content.Context;
import android.opengl.Matrix;
import android.util.Log;

public class Maze {
    private static int[] entryPoint = {4, 9}; // first: vertical, second: horizontal
    private Cube[] cubeList;
    public Texture texture;
    private static int mazeWidth = 10;
    private static int mazeHeight = 10;
    public static float cubeWidth = 0.5f;
    private static float cubeHeight = 1.0f;
    private static String TAG = "Maze";
    private int cubeSum;
    private int[] curPos;
    private float[] modelMaze;
    private float[] modelProjection;
    private float[] modelView;
    private float floorHeight = 1.5f;
    private Cube cube;
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

    /*
    private int maze[][] = {
            {0, 1, 0},
            {1, 0, 0},
            {1, 0, 0},
    };
     */
    private float[] zeroPoint;

    public Maze(Texture texture){
        zeroPoint = new float[2];
        curPos = new int[2];
        zeroPoint[0] = -0.5f;
        zeroPoint[1] = -0.5f;
        curPos[0] = entryPoint[0];
        curPos[1] = entryPoint[1];
        modelMaze = new float[16];
        modelView = new float[16];
        modelProjection = new float[16];
        // initCubes();
        cube = new Cube(cubeWidth, cubeHeight, texture);
    }

    public void draw(float[] modelTarget, float[] view, float[] perspective) {
        /*
        for(int i = 0; i < cubeSum; i ++)
        {
            // FIXME: Why only draw once?
            cubeList[i].draw(mvpMatrix);
        }
        */
        float wOffset, hOffset;
        for(int w = 0; w < mazeWidth; w++) {
            for (int h = 0; h < mazeHeight; h++) {
                if(maze[w][h] == 1) {
                    wOffset = w * cubeWidth;
                    hOffset = h * cubeWidth;
                    Log.i(TAG, "draw cube");
                    modelMaze = modelTarget.clone();
                    Matrix.translateM(modelMaze, 0, wOffset, -floorHeight, hOffset);
                    Matrix.multiplyMM(modelView, 0, view,0, modelMaze,0);
                    Matrix.multiplyMM(modelProjection, 0, perspective, 0, modelView, 0);
                    cube.draw(modelProjection);
                }
            }
        }
    }

    /*
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
     */

    public float[] entryCoords()
    {
        float[] coords = {zeroPoint[0] + (entryPoint[0]-0.5f) * cubeWidth, zeroPoint[1] + (entryPoint[1]-0.5f) * cubeWidth};
        return coords;
    }

}
