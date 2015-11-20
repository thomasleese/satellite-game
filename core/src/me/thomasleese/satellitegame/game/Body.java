package me.thomasleese.satellitegame.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.*;
import com.badlogic.gdx.graphics.g3d.environment.*;
import com.badlogic.gdx.graphics.g3d.utils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;

public abstract class Body implements Disposable {

    private Body mPrimary;

    protected Vector3 mPosition;
    private float mAltitude;
    private float mTheta;
    private float mSpeed;

    public Body(Body primary, float altitude, float speed) {
        mPrimary = primary;

        mPosition = new Vector3();
        mAltitude = altitude;
        mTheta = MathUtils.random(MathUtils.PI2);
        mSpeed = speed;
    }

    public void focusCamera(Camera camera) {
        Vector3 cameraPosition = Vector3.X
            .set(mPrimary.mPosition)
            .lerp(mPosition, 1.5f);

        camera.position.set(cameraPosition);
        camera.lookAt(mPrimary.mPosition);
        camera.update();
    }

    public void step(float dt) {
        mTheta += dt * mSpeed;

        if (mPrimary != null) {
            Vector3 primaryPosition = mPrimary.mPosition;

            mPosition.x = primaryPosition.x + mAltitude * MathUtils.cos(mTheta);
            mPosition.y = primaryPosition.y + 0;
            mPosition.z = primaryPosition.z + mAltitude * MathUtils.sin(mTheta);
        }
    }

    public abstract void render(ModelBatch modelBatch, Environment environment);

}
