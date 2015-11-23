package me.thomasleese.satellitegame.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.*;
import com.badlogic.gdx.graphics.g3d.environment.*;
import com.badlogic.gdx.graphics.g3d.loader.*;
import com.badlogic.gdx.graphics.g3d.utils.*;
import com.badlogic.gdx.math.*;

public class Satellite extends Body {

    private Model mModel;
    private ModelInstance mInstance;

    public Satellite(Body primary, float altitude) {
        super(primary, altitude, MathUtils.random(0.5f, 1.5f));

        Material material = new Material(ColorAttribute.createDiffuse(Color.GREEN));

        final float diameter = 0.1f;

        ObjLoader loader = new ObjLoader();
        mModel = loader.loadModel(Gdx.files.internal("models/satellite.obj"));
        mInstance = new ModelInstance(mModel);

        mInstance.transform.scale(0.05f, 0.05f, 0.05f);
    }

    @Override
    public void dispose() {
        mModel.dispose();
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(mInstance, environment);
    }

    @Override
    public void step(float dt) {
        super.step(dt);

        mInstance.transform.setTranslation(mPosition);
    }

}
