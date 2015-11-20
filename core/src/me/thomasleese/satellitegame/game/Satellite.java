package me.thomasleese.satellitegame.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.*;
import com.badlogic.gdx.graphics.g3d.environment.*;
import com.badlogic.gdx.graphics.g3d.utils.*;
import com.badlogic.gdx.math.*;

public class Satellite extends Body {

    private Model mModel;
    private ModelInstance mInstance;

    public Satellite(Body primary, float altitude) {
        super(primary, altitude, MathUtils.random(0.5f, 1.5f));

        Material material = new Material(ColorAttribute.createDiffuse(Color.GREEN));

        ModelBuilder modelBuilder = new ModelBuilder();
        mModel = modelBuilder.createSphere(0.1f, 0.1f, 0.1f, 8, 8, material,
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        mInstance = new ModelInstance(mModel);
    }

    public void dispose() {
        mModel.dispose();
    }

    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(mInstance, environment);
    }

    @Override
    public void step(float dt) {
        super.step(dt);

        mInstance.transform.setTranslation(mPosition);
    }

}