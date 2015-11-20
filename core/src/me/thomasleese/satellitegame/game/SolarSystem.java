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

    private static float relativeKmToEarth(float km) {
        //return km * 0.0001566091954f;
        return km * 0.000001566091954f;
    }

    public SolarSystem() {
        mBodies = new ArrayList<Body>();

        /*mSun = new Planet(null, relativeKmToEarth(696000), 0, "textures/sunmap.jpg");
        mEarth = new Planet(mSun, relativeKmToEarth(6378.1f), relativeKmToEarth(149597890), "textures/earthmap.jpg");
        mMoon = new Planet(mEarth, relativeKmToEarth(1737.1f), relativeKmToEarth(384399), "textures/moonmap.jpg");*/

        mSun = new Planet(null, 10, 0, "textures/sunmap.jpg");
        mEarth = new Planet(mSun, 5, 50, "textures/earthmap.jpg");
        mMoon = new Planet(mEarth, 0.5f, 15, "textures/moonmap.jpg");

        mBodies.add(mSun);
        mBodies.add(mEarth);
        mBodies.add(mMoon);

        for (int i = 0; i < 10; i++) {
            mBodies.add(new Satellite(mEarth, 7f));
        }
    }

    @Override
    public void dispose() {
        for (Body body : mBodies) {
            body.dispose();
        }
    }

    public void focusCamera(Camera camera) {
        mBodies.get(5).focusCamera(camera);
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
