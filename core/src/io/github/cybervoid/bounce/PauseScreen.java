package io.github.cybervoid.bounce;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class PauseScreen implements Screen {
    private static final String MESSAGE = "PAUSED";
    private static final int MESSAGE_FONT_SIZE = 58;
    private static final float MESSAGE_HEIGHT = 600;
    private final Bounce game;
    private OrthographicCamera camera;
    private BitmapFont messageFont;
    private GlyphLayout messageLabel;

    PauseScreen(final Bounce game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        messageFont = game.generateFont(MESSAGE_FONT_SIZE);
        messageLabel = game.createLabel(MESSAGE, messageFont);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.gameScreen.drawSprites();
        messageFont.draw(game.batch, messageLabel, 0, MESSAGE_HEIGHT);
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(game.gameScreen);
            game.gameScreen.paused = false;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
