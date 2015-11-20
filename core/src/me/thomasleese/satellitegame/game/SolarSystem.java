package me.thomasleese.satellitegame.game;

import java.util.*;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.*;
import com.badlogic.gdx.graphics.g3d.environment.*;
import com.badlogic.gdx.graphics.g3d.utils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;

import me.thomasleese.satellitegame.game.*;

public class SolarSystem implements Disposable {

    private Planet mSun;
    private Planet mEarth;
    private Planet mMoon;

    private List<Body> mBodies;

    public SolarSystem() {
        mBodies = new ArrayList<Body>();

        mSun = new Planet(null, 0, 0, 3f, 0.408407f, 0.1f, "textures/sunmap.jpg");
        mEarth = new Planet(mSun, 18, 0.5f, 2f, 0.408407f, 0.1f, "textures/earthmap.jpg");
        mMoon = new Planet(mEarth, 4, 1f, 0.5f, 0.4f, 0.1f, "textures/moonmap.jpg");

        mBodies.add(mSun);
        mBodies.add(mEarth);
        mBodies.add(mMoon);

        for (int i = 0; i < 10; i++) {
            mBodies.add(new Satellite(mEarth, 3f));
        }
    }

    @Override
    public void dispose() {
        for (Body body : mBodies) {
            body.dispose();
        }
    }

    public void render(ModelBatch modelBatch, Environment environment) {
        for (Body body : mBodies) {
            body.render(modelBatch, environment);
        }
    }

    public void step(float dt) {
        for (Body body : mBodies) {
            body.step(dt);
        }
    }

}
