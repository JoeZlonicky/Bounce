package io.github.cybervoid.bounce;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    private static final int COUNTER_FONT_SIZE = 48;
    private static final float COUNTER_HEIGHT = 750;
    private final Bounce game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Paddle paddle;
    private Ball ball;
    private Gem gem;
    private Vector3 touchPosition;
    private BitmapFont counter_font;
    private GlyphLayout counter_label;
    private int counter;
    boolean paused;

    GameScreen(Bounce game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        viewport = new StretchViewport(480, 800, camera);

        paddle = new Paddle();
        ball = new Ball();
        gem = new Gem();
        touchPosition = new Vector3();
        paused = true;

        counter_font = game.generateFont(COUNTER_FONT_SIZE);
        updateCounterLabel();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        drawSprites();
        game.batch.end();

        paddle.update();
        ball.update();
        ball.checkForCollision(paddle);
        if (gem.isHit(ball)) {
            ++counter;
            updateCounterLabel();
            gem.spawn();
        }
        if (ball.isGameOver()) {
            counter = 0;
            updateCounterLabel();
            ball.reset();
            paddle.reset();
            gem.spawn();
        }
        if (Gdx.app.getType() == Application.ApplicationType.Android && Gdx.input.isTouched()) {
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosition);
            if (touchPosition.x >= 240) {
                paddle.moveRight();
            } else {
                paddle.moveLeft();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(game.pauseScreen);
            paused = true;
        }
    }

    void drawSprites() {
        paddle.draw(game.batch);
        ball.draw(game.batch);
        gem.draw(game.batch);
        counter_font.draw(game.batch, counter_label, 0, COUNTER_HEIGHT);
    }

    private void updateCounterLabel() {
        counter_label = game.createLabel(Integer.toString(counter), counter_font);
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
        paddle.getTexture().dispose();
        ball.getTexture().dispose();
        gem.getTexture().dispose();
    }
}
