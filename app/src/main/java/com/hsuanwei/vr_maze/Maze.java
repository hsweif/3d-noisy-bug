package com.hsuanwei.vr_maze;

import android.opengl.Matrix;
import android.util.Log;

public class Maze {
    private static int[] entryPoint = {4, 9}; // first: vertical, second: horizontal
    private static int mazeWidth = 10;
    private static int mazeHeight = 10;
    public static float cubeWidth = 1.0f;
    private static float cubeHeight = 1.5f;
    private static String TAG = "Maze";
    private int[] curPos;
    private float[] modelMaze;
    private float[] modelProjection;
    private float[] modelView;
    private float floorHeight = 1.8f;
    private Cube cube;
    private float[] zeroPoint;
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


    public Maze(Texture texture){
        zeroPoint = new float[2];
        curPos = new int[2];
        zeroPoint[0] = 0.0f;
        zeroPoint[1] = 0.0f;
        curPos[0] = entryPoint[0];
        curPos[1] = entryPoint[1];
        modelMaze = new float[16];
        modelView = new float[16];
        modelProjection = new float[16];
        cube = new Cube(cubeWidth, cubeHeight, texture);
    }

    public void draw(float[] modelTarget, float[] view, float[] perspective) {
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

    public boolean move(String direction)
    {
        int h = curPos[0], w = curPos[1];
        if(direction == "right" && h+1 < mazeHeight && maze[h+1][w] == 0) {
            curPos[0] ++;
            return true;
        }
        else if(direction == "left" && h-1 >= 0 && maze[h-1][w] == 0) {
            curPos[0] --;
            return true;
        }
        else if(direction == "forward" && w-1 >= 0 && maze[h][w-1] == 0) {
            curPos[1] --;
            return true;
        }
        else if(direction == "back" && w+1 < mazeWidth && maze[h][w+1] == 0) {
            curPos[1] ++;
            return true;
        }
        return false;
    }


    public float[] entryCoords()
    {
        float[] coords = {zeroPoint[0] + (entryPoint[0]+0.5f) * cubeWidth, zeroPoint[1] + (entryPoint[1]+0.5f) * cubeWidth};
        return coords;
    }

}
