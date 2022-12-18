package net.fabricmc.example;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.opengl.GL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.opengl.GL11.*;
@Environment(EnvType.CLIENT)
public class ExampleMod implements PreLaunchEntrypoint {

	public static String getSysOpenGL() {
		return MinecraftClient.getInstance().getWindow().get;
	}
	@Override
	public void onPreLaunch() {
		String os = System.getProperty("os.name");
		System.out.println("GL Version: " + getSysOpenGL());
		File myObj = new File(FabricLoader.getInstance().getConfigDir().toString() + "config_gl.txt");
		if (os.startsWith("Linux") || os.startsWith("Windows") /* debug */) {
			String[] fileData = new String[3];
			try {
				Scanner myReader = new Scanner(myObj);
				for (int i = 0; myReader.hasNextLine() && i < 3; i++) {
					fileData[i] = myReader.nextLine();
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				System.err.println("An error occurred.");
				e.printStackTrace();
				System.err.println("Resetting config!");
				try {
					FileWriter newconf = new FileWriter(FabricLoader.getInstance().getConfigDir().toString() + "config_gl.txt");
					newconf.write("4.3");
					newconf.write("430");
					newconf.write("true");
					newconf.write("Line 1 will set MESA_GL_VERSION_OVERRIDE, line 2 will set MESA_GLSL_VERSION_OVERRIDE, line 3 will disable shader cache (will help mod compatibility)");
					newconf.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
			System.out.println("OVERRIDING MESA VERSION!");
			System.setProperty("MESA_GL_VERSION_OVERRIDE", fileData[0]);
			System.setProperty("MESA_GLSL_VERSION_OVERRIDE", fileData[1]);
			System.setProperty("MESA_SHADER_CACHE_DISABLE", fileData[2]);
			System.out.println("Your OpenGL Version now reports as: " + getSysOpenGL());

		} else {
			System.err.println("This mod only works on Linux!");
		}

	}
}
