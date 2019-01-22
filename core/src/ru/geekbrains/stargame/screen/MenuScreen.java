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
    private static final float V_LEN=1.5f;
//    SpriteBatch batch;
    Texture myspaceship;
    Texture background;

    Vector2 pos;
    Vector2 v;
    Vector2 v2;
    Vector2 touch;
    Vector2 touch1;
    Vector2 buf;


    @Override
    public void show() {
        super.show();
     //   batch = new SpriteBatch();
        background = new Texture("background.jpg");
        myspaceship = new Texture("Myship2.png");
//        pos = new Vector2(260, 0);
//        v = new Vector2(0, 2);
        pos = new Vector2(-0.5f, -0.5f);
        v = new Vector2(0.002f, 0.002f);
        buf=new Vector2();
        touch1 = new Vector2();
        touch = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, -1f, -0.5f, 2f, 1f);
        batch.draw(myspaceship, pos.x, pos.y, 0.2f, 0.2f);
        batch.end();
        pos.add(v);
//        buf.set(touch);
//        if(buf.cpy().sub(pos).len()>V_LEN) {
//            pos.add(v);
//        }else {
//            pos.set(buf);
//        }

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

//    @Override СТАРЫЙ МЕТОД, РАБОТАЛ В ПРЕДЫДУЩЕЙ СИСТЕМЕ КООРДИНАТ
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        Vector2 touch;
//        touch = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
////              if (pos.x == touch.x && pos.y==touch.y) {
//            v = new Vector2(-1f, 0f);
////            else{
//            v = touch..sub(pos);
//      //      v.nor();
//            v.scl(2);
//
//            return super.touchDown(screenX, screenY, pointer, button);
//    }
//
//
//          //  System.out.println("touchDown " + screenX + " " + (Gdx.graphics.getHeight() - screenY));
//      //  }
@Override //TODO вроде все реализовывал как и в пред. системе координат, но что то не работает.
public boolean touchDown(Vector2 touch, int pointer) {
    System.out.println("touchDown screenX = " + pos.x + " screenY = " + pos.y);
    touch.set(pos.x, screenBounds.getHeight() - pos.y).mul(screenToWorlds);
    v =touch.sub(pos);
    v.nor();
    return super.touchDown(touch, pointer);
}

//Управление клавишами реализовано, все работает
        @Override
        public boolean keyDown ( int keycode){
            System.out.println("keyDown keycode = " + keycode);

            switch (keycode) {
                case 21:
                    v = new Vector2(-0.001f, 0.000f);
                    break;
                case 20:
                    v = new Vector2(0.000f, -0.001f);
                    break;
                case 22:
                    v = new Vector2(0.001f, 0.000f);
                    break;
                case 19:
                    v = new Vector2(0.000f, 0.001f);
                    break;
            }
            v.scl(2);
            return false;
        }


    }


