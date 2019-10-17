package com.hsuanwei.vr_maze;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Maze {
    private int mazeSize;
    private Pair entryPoint; // First: x, Second: y
    private List<Cube> cubeList;
    public Texture texture;
    private int mazeWidth = 4;
    private int mazeHeight = 4;
    private float cubeWidth = 0.3f;
    private float cubeHeight = 0.5f;
    private int maze[][] = {
        {1, 0, 0, 1},
        {0, 1, 0, 1},
        {0, 1, 0, 0},
        {0, 0, 1, 0}
    };
    private float[] zeroPoint;

    public Maze(){
        zeroPoint = new float[2];
        zeroPoint[0] = -0.5f;
        zeroPoint[1] = -0.5f;
        cubeList = new ArrayList<>();
        for(int w = 0; w < mazeWidth; w++)
        {
            for(int h = 0; h < mazeHeight; h ++)
            {
                if(maze[w][h] == 1) {
                    cubeList.add(Cube.CreateCubeFromCoord(zeroPoint, w, h, cubeWidth, cubeHeight));
                }
            }
        }
    }

    public void draw(float[] mvpMatrix) {
        for(int i = 0; i < cubeList.size(); i ++)
        {
            cubeList.get(i).draw(mvpMatrix);
        }
    }
}
