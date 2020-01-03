package io.github.cybervoid.bounce;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.*;

class MainMenuScreen implements Screen {
    private static final String TITLE = "BOUNCE";
    private static final String PROMPT = "Press Anything\nto Start";
    private static final int TITLE_FONT_SIZE = 64;
    private static final int PROMPT_FONT_SIZE = 48;
    private static final float TITLE_HEIGHT = 600;
    private static final float PROMPT_HEIGHT = 200;

    private final Bounce game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont titleFont;
    private BitmapFont promptFont;
    private GlyphLayout titleLabel;
    private GlyphLayout promptLabel;

    MainMenuScreen(final Bounce game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        viewport = new StretchViewport(480, 800, camera);
        
        titleFont = game.generateFont(TITLE_FONT_SIZE);
        promptFont = game.generateFont(PROMPT_FONT_SIZE);
        titleLabel = game.createLabel(TITLE, titleFont);
        promptLabel = game.createLabel(PROMPT, promptFont);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        titleFont.draw(game.batch, titleLabel, 0, TITLE_HEIGHT);
        promptFont.draw(game.batch, promptLabel, 0, PROMPT_HEIGHT);
        game.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(game.gameScreen);
            game.gameScreen.paused = false;
            dispose();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        titleFont.dispose();
        promptFont.dispose();
    }
}
