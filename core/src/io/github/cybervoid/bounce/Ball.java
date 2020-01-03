package io.github.cybervoid.bounce;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.badlogic.gdx.math.MathUtils.*;

class Ball extends Sprite {
    private static final float GRAVITY = 25.0f;
    private static final float BOUNCE_FORCE = 1750.0f;
    private static final float RESET_WAIT = 1.0f;
    private static final float MAX_FALL_VELOCITY = -1500.0f;
    private static final float GAME_OVER_HEIGHT = -1500.0f;
    private float waitCounter;
    private float xVelocity;
    private float yVelocity;

    Ball() {
        super(new Texture("ball.png"));
        reset();
    }

    void update() {
        if (waitCounter > RESET_WAIT) {
            yVelocity -= GRAVITY * Gdx.graphics.getDeltaTime() * 100;
            if (yVelocity < MAX_FALL_VELOCITY) {
                yVelocity = MAX_FALL_VELOCITY;
            }
            translate(xVelocity * Gdx.graphics.getDeltaTime(), yVelocity * Gdx.graphics.getDeltaTime());

            if (getX() < 0) {
                setX(0);
                xVelocity *= -1;
            }
            if (getX() > 480 - getWidth()) {
                setX(480 - getWidth());
                xVelocity *= -1;
            }
        } else {
            waitCounter += Gdx.graphics.getDeltaTime();
        }
    }

    void checkForCollision(Paddle paddle) {
        if (paddle.getBoundingRectangle().overlaps(getBoundingRectangle())) {
            setY(paddle.getY() + paddle.getHeight());
            yVelocity = cos(degreesToRadians * paddle.getRotation()) * BOUNCE_FORCE;
            xVelocity = -sin(degreesToRadians * paddle.getRotation()) * BOUNCE_FORCE;
            paddle.hit();
        }
    }

    boolean isGameOver() {
        return getY() < GAME_OVER_HEIGHT;
    }

    void reset() {
        setCenter(240, 400);
        xVelocity = 0.0f;
        yVelocity = 0.0f;
        waitCounter = 0.0f;
    }
}
