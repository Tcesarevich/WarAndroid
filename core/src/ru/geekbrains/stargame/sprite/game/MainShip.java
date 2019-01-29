package ru.geekbrains.stargame.sprite.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPool;

public class MainShip extends Sprite {

    private Rect worldBounds;

    private final Vector2 v0 = new Vector2(0.2f, 0);
    private Vector2 v = new Vector2();
    private Vector2 touchme  = new Vector2();

    private boolean isPressedLeft;
    private boolean isPressed;
    private boolean isPressedRight;

    private BulletPool bulletPool;
    private Vector2 touch=new Vector2();
    private TextureRegion bulletRegion;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("laser.wav"));

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletPool = bulletPool;
        setHeightProportion(0.15f);

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                isPressedLeft = true;
                    moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                isPressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (isPressed || !isMe(touch)) {
            return false;
        }
//        v.set(touch.cpy().sub(pos).setLength(0.02f));
      if(touch.x<0){
            moveLeft();
      }

        this.isPressed = true;

        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        return super.touchUp(touch, pointer);
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                isPressedLeft = false;
                if (isPressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                isPressedRight = false;
                if (isPressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(pos.x<0.1f)
            //TODO Почему то с этим условием вообще не двигается (зависает)

            // while (pos.x+this.getHalfWidth() <= worldBounds.getRight()){
            moveRight();
        //}

        if(pos.x>0.1f) {
           //TODO Почему то с этим условием вообще не двигается(зависает)
            // while (pos.x - this.getHalfWidth() >= worldBounds.getLeft()){
                moveLeft();
    //    }
    }
        return touchDragged(touch, pointer);
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        return false;
    }

    private void moveRight() {
        if(pos.x+this.getHalfWidth() >= worldBounds.getRight()) {
            stop();
        }else v.set(v0);
    }

    private void moveLeft() {

        if (pos.x-this.getHalfWidth() <= worldBounds.getLeft()) {
            stop();
        }
        else
            v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, new Vector2(0, 0.5f), 0.01f, worldBounds, 1);
    sound.play();
    }

}
