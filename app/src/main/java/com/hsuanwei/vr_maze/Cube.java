package com.hsuanwei.vr_maze;


public class Cube {
    private static final String TAG = "Cube";
    private Rectangle[] mRectangles;
    private static float floorHeight = -1.0f;
    private final float[] vertices;
    private final short[] indices;
    private Renderer renderer;
    private Texture texture;

    public static Cube CreateCubeFromCoord(float[] zeroPoint, int _w, int _h, float cubeWidth, float cubeHeight)
    {
        float zW = zeroPoint[0];
        float zH = zeroPoint[1];
        float w = (float)_w;
        float h = (float)_h;
        float vertex[][] = {
                {zW + w * cubeWidth, zH + h * cubeWidth},
                {zW + (w+1.0f) * cubeWidth, zH + h * cubeWidth},
                {zW + (w+1.0f) * cubeWidth, zH + (h+1.0f) * cubeWidth},
                {zW + w * cubeWidth, zH + (h+1.0f) * cubeWidth}
        };
        float[][] stereoVertex = new float[8][3];
        for(int i = 0; i < 8; i ++) {
            stereoVertex[i][0] = vertex[i%4][0]; // x
            stereoVertex[i][2] = vertex[i%4][1]; // z
            if(i < 4){
                stereoVertex[i][1] = cubeHeight + floorHeight; // y
            }
            else{
                stereoVertex[i][1] = floorHeight;
            }
        }
        return new Cube(0.0f, 0.0f);
    }

    /*
    public Cube(float[][] vertex) {
        mRectangles = new Rectangle[6];
        mRectangles[0] = new Rectangle(vertex[0], vertex[1], vertex[2], vertex[3], color1);
        mRectangles[1] = new Rectangle(vertex[0], vertex[3], vertex[4], vertex[7], color2);
        mRectangles[2] = new Rectangle(vertex[1], vertex[5], vertex[6], vertex[2], color2);
        mRectangles[3] = new Rectangle(vertex[0], vertex[4], vertex[5], vertex[1], color3);
        mRectangles[4] = new Rectangle(vertex[2], vertex[6], vertex[7], vertex[3], color3);
        mRectangles[5] = new Rectangle(vertex[4], vertex[5], vertex[6], vertex[7], color1);
    }
     */

    public Cube(float cubeWidth, float cubeHeight) {
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
        /*
        1 0
        5 4
         */
        // renderer = new Renderer(vertices, indices);
    }

    public void draw(float[] mvpMatrix) {
        /*
        for(int i = 0; i < 6; i ++) {
            mRectangles[i].draw(mvpMatrix);
        }
        */
        float color[] = { 0.33f, 0.33f, 0.33f, 1.0f };
        renderer.draw(mvpMatrix, color);
    }


}
