package io.github.cybervoid.bounce.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.cybervoid.bounce.Bounce;

public class DesktopLauncher {
	private static final int SCREEN_WIDTH = 480;
	private static final int SCREEN_HEIGHT = 800;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.addIcon("icon.png", Files.FileType.Internal);
		config.title = "Bounce";
		config.width = SCREEN_WIDTH;
		config.height = SCREEN_HEIGHT;
		new LwjglApplication(new Bounce(), config);
	}
}
