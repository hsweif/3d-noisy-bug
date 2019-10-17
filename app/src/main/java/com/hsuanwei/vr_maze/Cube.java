package com.hsuanwei.vr_maze;

import android.opengl.GLES20;
import android.util.Pair;

import com.google.vr.sdk.base.sensors.internal.Vector3d;

import java.lang.reflect.Array;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

public class Cube {
    private TexturedMesh texturedMesh;
    private ShortBuffer vertexBuffer;
    private ShortBuffer indices;
    private List<Vector3d> vertex;
    private Texture texture;

    private float x(Pair<Float, Float> p) {
        return p.first;
    }

    private float y(Pair<Float, Float> p) {
        return p.second;
    }

    public Cube(float cubeHeight, Pair<Float, Float>[] vertex, Texture texture) {
        this.vertex = new ArrayList<>();
        /*
        this.vertex.add(new Vector3d(x(vertex[0]), y(vertex[0]), 0));
        this.vertex.add(new Vector3d(x(vertex[1]), y(vertex[1]), 0));
        this.vertex.add(new Vector3d(x(vertex[2]), y(vertex[2]), 0));
         */
        this.texture = texture;
    }

    public void draw() {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 1*3);
    }
}
