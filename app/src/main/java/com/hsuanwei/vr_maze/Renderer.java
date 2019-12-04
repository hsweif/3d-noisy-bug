package com.hsuanwei.vr_maze;

import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Renderer {
    protected FloatBuffer vertexBuffer;
    protected ShortBuffer indexBuffer;
    protected String vertexShaderCode;
    protected String fragmentShaderCode;
    protected int vPMatrixHandle;
    protected int mProgram;
    protected int COORDS_PER_VERTEX = 3;
    protected int positionHandle;
    protected int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
    private float[] defaultColor = {0.3f, 0.3f, 0.3f, 1.0f};
    private int colorHandle;

    public Renderer(float[] vertices, short[] indices) {
        vertexBuffer = ByteBuffer
                .allocateDirect(4 * vertices.length)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
        indexBuffer = ByteBuffer
                .allocateDirect(2 * indices.length)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
        InitShader();
    }

    protected void InitShader()
    {
        // This matrix member variable provides a hook to manipulate
        // the coordinates of the objects that use this vertex shader
        vertexShaderCode =
                "uniform mat4 uMVPMatrix;" +
                "attribute vec4 vPosition;" +
                "void main() {" +
                // the matrix must be included as a modifier of gl_Position
                // Note that the uMVPMatrix factor *must be first* in order
                // for the matrix multiplication product to be correct.
                "  gl_Position = uMVPMatrix * vPosition;" +
                "}";
        fragmentShaderCode =
                "precision mediump float;" +
                "uniform vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = vColor;" +
                "}";
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);
        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();
        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);
        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);
        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);
    }


    public void draw(float[] mvpMatrix) {
        GLES20.glUseProgram(mProgram);
        // get handle to vertex shader's vPosition member
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);
        // get handle to fragment shader's vColor member
        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        // get handle to shape's transformation matrix
        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        // Pass the projection and view transformation to the shader
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0);
        // Set color for drawing the triangle
        GLES20.glUniform4fv(colorHandle, 1, defaultColor, 0);
        // Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indexBuffer.limit(), GLES20.GL_UNSIGNED_SHORT, indexBuffer);
        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    protected int loadShader(int type, String shaderCode){
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);
        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}

