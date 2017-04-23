package com.andrej.igra.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tomaž Ravljen, Drugi Vid d.o.o.
 */

public class PlayerPad extends AbstractGameObject {

    private Vector2 terminalVelocity;
    private Texture sprite;
    private Body body;

    public PlayerPad() {
        sprite = new Texture("platform.png");
        dimension.set(14f, 3.6f);
        terminalVelocity = new Vector2(14, 0);
    }

    public void initBody(World world) {
        if (body != null) {
            return;
        }

        Vector2 center = new Vector2(dimension.x / 2, dimension.y * .1f);
        PolygonShape polyShape = new PolygonShape();
        polyShape.setAsBox(
                dimension.x * .42f,
                dimension.y * .28f,
                center,
                rotation * MathUtils.degRad
        );

        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(boxBodyDef);
        body.createFixture(polyShape, 1);
        body.setUserData(this);

        polyShape.dispose();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite, position.x, position.y, dimension.x, dimension.y);
    }

    public void setDirectionLeft() {
        velocity.set(-terminalVelocity.x, 0);
    }

    public void setDirectionRight() {
        velocity.set(terminalVelocity.x, 0);
    }

    public void stop() {
        velocity.set(0, 0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (body != null) {
            Vector2 bodyPosition = new Vector2(position.x, position.y + dimension.y / 2);
            body.setTransform(bodyPosition.x, bodyPosition.y, rotation);
        }
    }
}