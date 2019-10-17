package com.hsuanwei.vr_maze;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Maze {
    private int cubeWidth;
    private int cubeHeight;
    private int mazeSize;
    private Pair entryPoint; // First: x, Second: y
    private List<Cube> cubeList;
    public Texture texture;

    public Maze(Context context){
       cubeList = new ArrayList<>();
       Pair<Float, Float>[] vertex = new Pair[10];
       vertex[0] = new Pair<>(0.0f, 0.0f);
       vertex[1] = new Pair<>(0.0f, 0.1f);
       vertex[2] = new Pair<>(0.1f, 0.1f);
       vertex[3] = new Pair<>(0.1f, 0.0f);
       try {
           texture = new Texture(context, "Icosahedron_Blue_BakedDiffuse.png");
       }
       catch (IOException e) {

       }
    }

    public void draw() {
    }
}
