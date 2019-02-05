package ru.geekbrains.sprite.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;

public class ButtonGameOver extends ScaledTouchUpButton {

    private Game game;

    public ButtonGameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
        this.game = game;
        setHeightProportion(0.05f);

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
    }


    @Override
    public void action() {
            Gdx.app.exit();
        }

}
