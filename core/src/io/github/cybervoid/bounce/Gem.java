package io.github.cybervoid.bounce;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

class Gem extends Sprite {
    private static final float X_SPAWN_MARGIN = 50.0f;
    private static final float TOP_SPAWN_MARGIN = 150.0f;
    private static final float BOTTOM_SPAWN_MARGIN = 200.0f;
    private Random randomGenerator;

    Gem() {
        super(new Texture("gem.png"));
        randomGenerator = new Random();
        spawn();
    }

    void spawn() {
        int width = (int) (480 - X_SPAWN_MARGIN * 2 - getWidth());
        int height = (int) (800 - TOP_SPAWN_MARGIN - BOTTOM_SPAWN_MARGIN - getHeight());
        float spawnX = randomGenerator.nextInt(width) + X_SPAWN_MARGIN;
        float spawnY = randomGenerator.nextInt(height) + BOTTOM_SPAWN_MARGIN;
        setPosition(spawnX, spawnY);
    }

    boolean isHit(Ball ball) {
        return getBoundingRectangle().overlaps(ball.getBoundingRectangle());
    }
}
