package io.github.cybervoid.bounce;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

class Paddle extends Sprite {
    private static final float SPEED = 400.0f;
    private static final float ROTATION_RATE = 0.10f;
    private static final float HEIGHT = 20.0f;
    private static final float HIT_WAIT_TIME = 0.15f;
    private Texture image;
    private Texture hitImage;
    private float hit_wait_counter;

    Paddle() {
        super(new Texture("paddle.png"));
        image = new Texture("paddle.png");
        hitImage = new Texture("paddle_hit.png");
        reset();
        setY(HEIGHT);
    }

    void update() {
        if (hit_wait_counter < HIT_WAIT_TIME) {
            hit_wait_counter += Gdx.graphics.getDeltaTime();
            if (hit_wait_counter > HIT_WAIT_TIME) {
                setTexture(image);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveRight();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveLeft();
        }
    }

    void moveLeft() {
        translateX(-SPEED * Gdx.graphics.getDeltaTime());
        if (getX() < 0) {
            setX(0);
        }
        updateRotation();
    }

    void moveRight() {
        translateX(SPEED * Gdx.graphics.getDeltaTime());
        if (getX() > 480 - getWidth()) {
            setX(480 - getWidth());
        }
        updateRotation();
    }

    void reset() {
        setCenterX(240);
        updateRotation();
    }

    private void updateRotation() {
        setRotation(ROTATION_RATE * (getX() + getOriginX() - 240));
    }

    void hit() {
        setTexture(hitImage);
        hit_wait_counter = 0.0f;
    }
}
