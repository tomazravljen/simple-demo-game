package com.andrej.igra.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Tomaž Ravljen, Drugi Vid d.o.o.
 */

public class GameStage extends Stage {

    private Label scoreLabel;
    private Label timeLabel;

    private float padding;
    private float height;
    private float y;

    public GameStage() {
        padding = Gdx.graphics.getWidth() * 0.04f;
        height = Gdx.graphics.getWidth() * 0.1f;
        y = Gdx.graphics.getHeight() - height;

        build();
    }

    private void build() {
        buildScoreLabel();
        buildTimeLabel();
    }

    private void buildScoreLabel() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        scoreLabel = new Label("0", style);
        addActor(scoreLabel);

        scoreLabel.setPosition(padding, y + height / 2 - scoreLabel.getHeight());
    }

    private void buildTimeLabel() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        timeLabel = new Label("0:00", style);
        addActor(timeLabel);

        timeLabel.setPosition(getWidth() - padding - timeLabel.getWidth(), y + height / 2 - timeLabel .getHeight());
    }

    public void updateTime(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        timeLabel.setText(minutes + ":" + seconds);
    }

    public void updateScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }
}
