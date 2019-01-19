package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    SpriteBatch batch;
    Texture myspaceship;
    Texture background;

    Vector2 pos;
    Vector2 v;
    Vector2 vtouch;

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        background = new Texture("background.jpg");
        myspaceship = new Texture("Myship2.png");
        pos = new Vector2(260, 0);
        v = new Vector2(0, 2);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(myspaceship, pos.x, pos.y);
        batch.end();
//        if (Gdx.graphics.getWidth() - 256 > pos.x && Gdx.graphics.getHeight() - 256 > pos.y) {
//            pos.add(v);
//        }
        pos.add(v);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        myspaceship.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 touch;
        touch = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
              if (pos.x == touch.x && pos.y==touch.y) {
            v = new Vector2(0, 0);}
            else{
            v = touch.sub(pos);
            v.nor();
            v.scl(2);
        }

            System.out.println("touchDown " + screenX + " " + (Gdx.graphics.getHeight() - screenY));
            return super.touchDown(screenX, screenY, pointer, button);
        }
        @Override
        public boolean keyDown ( int keycode){
            System.out.println("keyDown keycode = " + keycode);

            switch (keycode) {
                case 21:
                    v = new Vector2(-1, 0);
                    break;
                case 20:
                    v = new Vector2(0, -1);
                    break;
                case 22:
                    v = new Vector2(1, 0);
                    break;
                case 19:
                    v = new Vector2(0, 1);
                    break;
            }
            v.scl(2);
            return false;
        }


    }


