package com.hsuanwei.vr_maze;


import android.content.Context;

public class Rectangle {
    private float[] vertices;
    private short[] indices;
    private float[] uv;
    private float[] color = {0.6f, 0.6f, 0.6f, 1.0f};
    private Renderer renderer;

    public Rectangle(float[] _vertices, short[] _indices, float[] _uv, Texture texture) {
        vertices = _vertices;
        indices = _indices;
        uv = _uv;
        renderer = new TextureRenderer(vertices, indices, uv, texture);
    }

    public void draw(float[] mvpMatrix)
    {
        renderer.draw(mvpMatrix);
    }
}
