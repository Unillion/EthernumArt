package net.unillion.ea.contents.shader;

import net.minecraft.client.MinecraftClient;
import net.unillion.ea.EthernumArtMod;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ShaderProgram {
    private final int programId;
    private final int vertexShaderId;
    private final int fragmentShaderId;

    public ShaderProgram(String vertexPath, String fragmentPath) {
        vertexShaderId = loadShader(vertexPath, GL20.GL_VERTEX_SHADER);
        fragmentShaderId = loadShader(fragmentPath, GL20.GL_FRAGMENT_SHADER);
        programId = GL20.glCreateProgram();

        GL20.glAttachShader(programId, vertexShaderId);
        GL20.glAttachShader(programId, fragmentShaderId);
        GL20.glLinkProgram(programId);

        if (GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == GL20.GL_FALSE) {
            throw new RuntimeException("Error linking shader program: " + GL20.glGetProgramInfoLog(programId));
        }
    }

    private int loadShader(String path, int type) {
        try {
            String shaderCode = new BufferedReader(new InputStreamReader(
                    MinecraftClient.getInstance().getResourceManager()
                            .getResource(new net.minecraft.util.Identifier(EthernumArtMod.MOD_ID, path))
                            .getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            int shaderId = GL20.glCreateShader(type);
            GL20.glShaderSource(shaderId, shaderCode);
            GL20.glCompileShader(shaderId);

            if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == GL20.GL_FALSE) {
                throw new RuntimeException("Error compiling shader: " + GL20.glGetShaderInfoLog(shaderId));
            }

            return shaderId;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load shader: " + path, e);
        }
    }

    public void bind() {
        GL20.glUseProgram(programId);
    }

    public void unbind() {
        GL20.glUseProgram(0);
    }

    public void cleanup() {
        GL20.glDetachShader(programId, vertexShaderId);
        GL20.glDetachShader(programId, fragmentShaderId);
        GL20.glDeleteShader(vertexShaderId);
        GL20.glDeleteShader(fragmentShaderId);
        GL20.glDeleteProgram(programId);
    }
}
