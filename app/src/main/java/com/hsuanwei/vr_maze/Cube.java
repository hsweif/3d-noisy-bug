package com.hsuanwei.vr_maze;

public class Cube {
    private static final String TAG = "Cube";
    private Rectangle[] mRectangles;
    private static float floorHeight = 0.0f;
    private final float[] vertices;
    private final short[] indices;
    private final float[] uv;
    private final float TEXTURE_STRIDE = 2.0f;
    private Renderer renderer;

    public Cube(float cubeWidth, float cubeHeight, Texture texture) {
        vertices = new float [] {
            0.0f, floorHeight, 0.0f,
            cubeWidth, floorHeight, 0.0f,
            cubeWidth, floorHeight, cubeWidth,
            0.0f, floorHeight,cubeWidth,
            0.0f, floorHeight+cubeHeight, 0.0f,
            cubeWidth, floorHeight+cubeHeight, 0.0f,
            cubeWidth, floorHeight+cubeHeight, cubeWidth,
            0.0f, floorHeight+cubeHeight,cubeWidth,
        };
        indices = new short[] {
                0, 1, 2,
                0, 2, 3,
                4, 5, 6,
                4, 6, 7,
                0, 3, 4,
                3, 7, 4,
                7, 6, 2,
                7, 2, 3,
                6, 5, 1,
                6, 1, 2,
                1, 0, 4,
                1, 4, 5
        };
        uv = new float[] {
            0.0f, 0.0f,
            TEXTURE_STRIDE, 0.0f,
            TEXTURE_STRIDE, TEXTURE_STRIDE,
            0.0f, TEXTURE_STRIDE,
            TEXTURE_STRIDE, TEXTURE_STRIDE,
            0.0f, TEXTURE_STRIDE,
            0.0f, 0.0f,
            TEXTURE_STRIDE, 0.0f,
        };
        /*
        A: 0,0
        B: 1,0
        C: 1,1
        D: 0,1
         */
        /*
        1 0
        5 4
         */
        renderer = new TextureRenderer(vertices, indices, uv, texture);
    }

    public void draw(float[] mvpMatrix) {
        renderer.draw(mvpMatrix);
    }

}
