package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.Star2DGame;
import ru.geekbrains.stargame.base.Base2DScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.sprite.Play;
import ru.geekbrains.stargame.sprite.Quit;
import ru.geekbrains.stargame.sprite.Star;
import ru.geekbrains.stargame.sprite.Background;


public class MenuScreen extends Base2DScreen {

    private TextureAtlas atlas;
    private Texture bg;
    private Background background;
    private Star star[];
    private Play play;
    private Quit quit;
    private boolean playIsPressed;
    private boolean quitIsPressed;
    protected Screen screen;


    @Override
    public void show() {
        super.show();
        bg = new Texture("background.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        play = new Play(atlas);
        quit = new Quit(atlas);
        star = new Star[256];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(atlas);
        }

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(0.5f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        play.draw(batch);
        quit.draw(batch);

        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if(touch.x >= play.getLeft() && touch.x <= play.getRight() && touch.y >= play.getBottom() && touch.y <= play.getTop()){
            this.playIsPressed = true;
            play.setHeightProportion(0.36f);
        }
        if(touch.x >= quit.getLeft() && touch.x <= quit.getRight() && touch.y >= quit.getBottom() && touch.y <= quit.getTop()) {
        quitIsPressed = true;
            quit.setHeightProportion(0.15f);
        }
        return super.touchDown(touch, pointer);
    }

//

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (touch.x >= play.getLeft() && touch.x <= play.getRight() && touch.y >= play.getBottom() && touch.y <= play.getTop() &&playIsPressed==true) {
            this.playIsPressed = false;
            play.setHeightProportion(0.4f);
            this.hide();
            setScreen(new GameScreen());

        }
        if (touch.x >= quit.getLeft() && touch.x <= quit.getRight() && touch.y >= quit.getBottom() && touch.y <= quit.getTop()&& quitIsPressed==true) {
            quitIsPressed = false;
            System.exit(0);
        }
            return super.touchUp(touch, pointer);
    }

    public void setScreen (Screen screen) {
        if (this.screen != null) this.screen.hide();
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }
}

