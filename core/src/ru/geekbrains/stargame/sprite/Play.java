package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.compression.lzma.Base;

import ru.geekbrains.stargame.base.Base2DScreen;
import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.math.Rnd;
import ru.geekbrains.stargame.sprite.menu.ScaledTouchUpButton;

public class Play extends ScaledTouchUpButton {

    private Vector2 v = new Vector2();
    private Rect worldBounds;
    private Vector2 p = new Vector2();

    public Play(TextureAtlas atlas) {
        super(atlas.findRegion("btPlay"));
        setHeightProportion(0.4f);
        v.set(0.0f,2f);
        pos.mulAdd(v, 0.1f);
        p.set(Gdx.input.getX(), Gdx.input.getY());

        touchUp(v,0);


    }

    @Override
    public void update(float delta) {
       // pos.mulAdd(v, delta);
    //    checkAndHandleBounds();
    }

    private void checkAndHandleBounds() {
    }


    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
//
        //pos.set(posX, posY);
    }

    @Override
    public void action() {
        System.out.println("hihi");



    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {

        return super.touchDown(touch, pointer);
    }
//    private void checkAndHandleBounds() {
//        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
//        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
//        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
//        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
//    }
}
