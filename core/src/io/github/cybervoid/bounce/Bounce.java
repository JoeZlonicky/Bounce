package io.github.cybervoid.bounce;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;

public class Bounce extends Game {
	GameScreen gameScreen;
	PauseScreen pauseScreen;
	SpriteBatch batch;


	@Override
	public void create () {
		gameScreen = new GameScreen(this);
		pauseScreen = new PauseScreen(this);
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose () {
		batch.dispose();
	}

	BitmapFont generateFont(int size) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Orbitron-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = size;
		BitmapFont font = generator.generateFont(parameter);
		generator.dispose();
		return font;
	}

	GlyphLayout createLabel(String text, BitmapFont font) {
		GlyphLayout label = new GlyphLayout();
		label.setText(font, text, Color.WHITE, 480, Align.center, true);
		return label;
	}
}
