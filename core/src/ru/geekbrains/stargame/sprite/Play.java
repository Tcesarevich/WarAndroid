package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

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
//
    }

    @Override
    public void action() {



    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {

        return super.touchDown(touch, pointer);
    }

}
