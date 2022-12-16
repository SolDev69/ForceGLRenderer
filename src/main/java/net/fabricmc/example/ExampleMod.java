package net.fabricmc.example;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class ExampleMod implements PreLaunchEntrypoint {


	@Override
	public void onPreLaunch() {
		System.out.println("OVERRIDING MESA VERSION! THIS ONLY WORKS ON LINUX!");
		System.setProperty("MESA_GL_VERSION_OVERRIDE", "4.3");
		System.setProperty("MESA_GLSL_VERSION_OVERRIDE", "430");
		System.setProperty("MESA_SHADER_CACHE_DISABLE", "true");
	}
}
