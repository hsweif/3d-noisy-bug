package com.hsuanwei.vr_maze;

import android.opengl.GLES20;

import com.hsuanwei.vr_maze.Renderer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public final class TextureRenderer extends Renderer {
    private FloatBuffer uvBuffer;
    private Texture texture;
    private TexturedMesh texturedMesh;
    private int uvHandle;
    private int modelViewProjectionHandle;

    public TextureRenderer(float[] vertices, short[] indices, float[] uv, Texture texture) {
        super(vertices, indices);
        uvBuffer = ByteBuffer
                .allocateDirect(4 * uv.length)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        uvBuffer.put(uv);
        uvBuffer.position(0);
        this.texture = texture;
        InitShader();
        this.texturedMesh = new TexturedMesh(vertexBuffer, uvBuffer, indexBuffer, positionHandle, uvHandle);
    }

    @Override
    protected void InitShader() {
        vertexShaderCode =
                "uniform mat4 u_MVP;"+
                "attribute vec4 a_Position;"+
                "attribute vec2 a_UV;"+
                "varying vec2 v_UV;"+
                ""+
                "void main() {"+
                "  v_UV = a_UV;"+
                "  gl_Position = u_MVP * a_Position;"+
                "}";
        fragmentShaderCode =
                "precision mediump float;"+
                "varying vec2 v_UV;"+
                "uniform sampler2D u_Texture;"+
                "void main() {"+
                "  gl_FragColor = texture2D(u_Texture, vec2(v_UV.x, 1.0 - v_UV.y));"+
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
        positionHandle = GLES20.glGetAttribLocation(mProgram, "a_Position");
        uvHandle = GLES20.glGetAttribLocation(mProgram, "a_UV");
        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVP");
        Util.checkGlError("Textured renderer program params");
    }


    public void draw(float[] mvpMatrix) {
        texture.bind();
        GLES20.glUseProgram(mProgram);
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0);
        GLES20.glEnableVertexAttribArray(uvHandle);
        GLES20.glVertexAttribPointer(uvHandle, 2, GLES20.GL_FLOAT, false, 0, uvBuffer);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indexBuffer.limit(), GLES20.GL_UNSIGNED_SHORT, indexBuffer);
        GLES20.glDisableVertexAttribArray(positionHandle);
    }
/*
    public void draw(float[] mvpMatrix){
        GLES20.glUseProgram(MainActivity.objectProgram);
        GLES20.glUniformMatrix4fv(MainActivity.objectModelViewProjectionParam, 1, false, mvpMatrix, 0);
        texture.bind();
        texturedMesh.draw();
    }
 */

    public int loadShader(int type, String shaderCode){
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);
        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
